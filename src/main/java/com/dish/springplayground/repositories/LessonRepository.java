package com.dish.springplayground.repositories;

import com.dish.springplayground.model.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
