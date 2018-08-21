package com.dish.springplayground.repositories;

import com.dish.springplayground.model.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    Lesson findByTitle(String title);

    Iterable<Lesson> findByDeliveredOnBetween(Date before, Date after);

//    @Query("select l from Lesson l where l.deliveredOn >= :before and l.deliveredOn <= :after")
//    Iterable<Lesson> customQuery(@Param("before") Date before, @Param("after") Date after);
}
