package com.dish.springplayground.controllers;

import com.dish.springplayground.services.WordCounter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/words")
public class WordController {

    private WordCounter wordCounter;

    public WordController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/count")
    public Map<String, Integer> wordCount(@RequestBody String input) {
        System.out.println("input received: " + input);
        return this.wordCounter.count(input);
    }
}
