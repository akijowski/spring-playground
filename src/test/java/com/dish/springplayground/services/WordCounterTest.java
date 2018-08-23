package com.dish.springplayground.services;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class WordCounterTest {

    private WordCounter wordCounter;

    @Before
    public void setup() {
        this.wordCounter = new WordCounter();
    }

    @Test
    public void count_returns_an_empty_map_when_input_is_null_or_empty() {

        Map<String, Integer> actual = this.wordCounter.count(null);
        assertThat(actual.size(), equalTo(0));

        actual = this.wordCounter.count("");
        assertThat(actual.size(), equalTo(0));

    }

    @Test
    public void count_returns_a_map_of_words_and_counts_for_a_string() {

        Map<String, Integer> expected = new HashMap<>();
        expected.put("A", 1);
        expected.put("brown", 2);
        expected.put("cow", 1);
        expected.put("jumps", 1);
        expected.put("over", 1);
        expected.put("a", 1);
        expected.put("fox", 1);

        Map<String, Integer> actual = this.wordCounter.count("A brown cow jumps over a brown fox");

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void count_returns_a_map_and_ignores_punctuation() {

        Map<String, Integer> expected = new HashMap<>();
        expected.put("How", 1);
        expected.put("now", 1);
        expected.put("cow", 1);
        expected.put("brown", 1);

        Map<String, Integer> actual = this.wordCounter.count("How now, brown cow!");

        assertThat(actual, equalTo(expected));
    }
}