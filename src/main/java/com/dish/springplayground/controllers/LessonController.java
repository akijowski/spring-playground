package com.dish.springplayground.controllers;

import com.dish.springplayground.model.Lesson;
import com.dish.springplayground.repositories.LessonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
