package com.dish.springplayground.controllers;

import com.dish.springplayground.model.Movie;
import com.dish.springplayground.repositories.MovieRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/movies")
public class MovieController {

//    private final SimpleMovieService simpleMovieService;
//
//    public MovieController(SimpleMovieService simpleMovieService) {
//        this.simpleMovieService = simpleMovieService;
//    }
//
//    @GetMapping("")
//    public List<SimpleMovie> getMovies(@RequestParam String q) {
//        System.out.println("getMovies() with q:" + q);
//        return simpleMovieService.getMovies(q);
//    }

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("")
    public HttpEntity<List<Movie>> all() {
        List<Movie> movies = movieRepository.findAll();
        List<Movie> moviesWithLinks = movies.stream()
                .map(movie -> {
                    Movie m = new Movie(movie.getMovieId(), movie.getTitle());
                    m.add(linkTo(methodOn(MovieController.class).byId(movie.getMovieId())).withSelfRel());
                    m.add(linkTo(methodOn(TrailerController.class).findTrailer(movie.getMovieId())).withRel("trailer"));
                    return m;
                }).collect(Collectors.toList());
        return new ResponseEntity<>(moviesWithLinks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<Movie> byId(@PathVariable long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
