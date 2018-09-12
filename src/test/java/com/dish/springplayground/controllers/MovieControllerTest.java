package com.dish.springplayground.controllers;

import com.dish.springplayground.model.Movie;
import com.dish.springplayground.repositories.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc(secure = false)
public class MovieControllerTest {

    private ArrayList<Movie> movies;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @Before
    public void setUp() throws Exception {
        movie1 = new Movie("Boss Baby");
        movie1.setMovieId(1L);
        movie2 = new Movie("Beauty and the Beast");
        movie2.setMovieId(2L);
        movie3 = new Movie("Logan");
        movie3.setMovieId(3L);
        movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
    }

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieRepository repo;

    @Test
    public void testHateoasIndexRoute() throws Exception {
        when(this.repo.findAll()).thenReturn(movies);

        MockHttpServletRequestBuilder request = get("/movies");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(jsonPath("$[0].links[0].href", equalTo("http://localhost/movies/1")))
                .andExpect(jsonPath("$[0].links[1].href", equalTo("http://localhost/trailers/1")))
                .andExpect(jsonPath("$[1].links[1].href", equalTo("http://localhost/trailers/2")))
                .andExpect(jsonPath("$[2].links[0].href", equalTo("http://localhost/movies/3")));
    }

}