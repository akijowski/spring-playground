package com.dish.springplayground.repositories;

import com.dish.springplayground.model.Trailer;
import org.springframework.data.repository.CrudRepository;

public interface TrailerRepository extends CrudRepository<Trailer, Long> {
    Trailer findById(long id);
}
