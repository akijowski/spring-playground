package com.dish.springplayground.services;

import com.dish.springplayground.config.WordCounterConfig;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class WordCounter {

    private final WordCounterConfig config;

    public WordCounter(WordCounterConfig config) {
        this.config = config;
    }

    public Map<String, Integer> count(String input) {
        System.out.println("is case sensitive: " + config.isCaseSensitive());
        if (config.getWords() != null) {
            System.out.println("words to skip: ");
            for (String word : config.getWords().getSkip()) {
                System.out.println(word);
            }
        } else {
            System.out.println("words is null");
        }
        Map<String, Integer> wordMap;
        if (input == null || input.length() == 0) {
            wordMap = Collections.emptyMap();
        } else {
            String sanitizedInput = sanitizeInput(input);

            String[] words = sanitizedInput.split(" ");
            wordMap = parseWordsInToMap(words);
        }

        return wordMap;
    }

    private String sanitizeInput(String input) {
        String out;
        if (config.isCaseSensitive()) {
            out = input.replaceAll("[^\\w\\s]", "");
        } else {
            out = input.toLowerCase().replaceAll("[^\\w\\s]", "");
        }

        return out;
    }

    private Map<String, Integer> parseWordsInToMap(String[] words) {
        Map<String, Integer> output = new HashMap<>();
        for (String word : words) {
            if (!isWordInSkipList(word)) {
                if (!output.containsKey(word)) {
                    output.put(word, 1);
                } else {
                    int oldValue = output.get(word);
                    output.put(word, ++oldValue);
                }
            }
        }
        return output;
    }

    private boolean isWordInSkipList(String word) {
        if (config.getWords() == null || config.getWords().getSkip().isEmpty()) {
            return false;
        }
        return config.getWords().getSkip().contains(word);
    }

}
