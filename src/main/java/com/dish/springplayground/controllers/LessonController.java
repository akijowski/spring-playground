package com.dish.springplayground.controllers;

import com.dish.springplayground.model.Lesson;
import com.dish.springplayground.repositories.LessonRepository;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public String removeById(@PathVariable long id) {
        this.repository.deleteById(id);
        return "";
    }
}
