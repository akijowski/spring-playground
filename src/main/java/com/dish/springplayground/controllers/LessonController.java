package com.dish.springplayground.controllers;

import com.dish.springplayground.model.Lesson;
import com.dish.springplayground.repositories.LessonRepository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepository repository;

    public LessonController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Lesson findById(@PathVariable long id) {
        return this.repository.findById(id).get();
    }

    @GetMapping("/find/{title}")
    public Lesson findByTitle(@PathVariable String title) {
        return this.repository.findByTitle(title);
    }

    @GetMapping("/between")
    public Iterable<Lesson> findBetweenDeliveredOn(
            @RequestParam String date1,
            @RequestParam String date2) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date before = dateFormatter.parse(date1);
            Date after = dateFormatter.parse(date2);
//            System.out.println("before: " + before);
//            System.out.println("after: " + after);
//            Iterable<Lesson> lessons = this.repository.customQuery(before, after);
            Iterable<Lesson> lessons = this.repository.findByDeliveredOnBetween(before, after);
//            for(Lesson l : lessons) {
//                System.out.println("lesson: " + l.getId() + ", " + l.getTitle() + ", " + l.getDeliveredOn());
//            }
            return lessons;
        } catch (ParseException e) {
            e.printStackTrace();
            return emptyList();
        }
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @PatchMapping("/{id}")
    public Lesson update(
            @PathVariable long id,
            @RequestBody Lesson lessonRequest) {
        Optional<Lesson> lessonToUpdate = this.repository.findById(id);
        if (lessonToUpdate.isPresent()) {
            Lesson updateLesson = lessonToUpdate.get();
            updateLesson.setDeliveredOn(lessonRequest.getDeliveredOn());
            updateLesson.setTitle(lessonRequest.getTitle());
            return this.repository.save(updateLesson);
        } else {
            System.out.println("No lesson found matching request, saving as new to database");
            return this.repository.save(lessonRequest);
        }
    }

    @DeleteMapping("/{id}")
    public String removeById(@PathVariable long id) {
        this.repository.deleteById(id);
        return "";
    }
}
