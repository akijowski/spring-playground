package com.dish.springplayground.controllers;

import com.dish.springplayground.model.Movie;
import com.dish.springplayground.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    public List<Movie> getMovies(@RequestParam String q) {
        System.out.println("getMovies() with q:" + q);
        return movieService.getMovies(q);
    }
}
