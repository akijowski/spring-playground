package com.dish.springplayground.repositories;

import com.dish.springplayground.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findAll();
}
