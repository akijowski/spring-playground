package com.dish.springplayground.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    public WordCounter() {}

    public Map<String, Integer> count(String input) {
        if (input == null || input.length() == 0) {
            return Collections.emptyMap();
        } else {
            String sanitizedInput = input.replaceAll("[^\\w\\s]", "");
            Map<String, Integer> output = new HashMap<>();
            String[] words = sanitizedInput.split(" ");
            for (String word : words) {
                if (!output.containsKey(word)) {
                    output.put(word, 1);
                } else {
                    int oldValue = output.get(word);
                    output.put(word, ++oldValue);
                }
            }
            return output;
        }
    }

}
