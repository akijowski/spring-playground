//package com.dish.springplayground.controllers;
//
//import com.dish.springplayground.services.WordCounter;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static java.util.Collections.emptyMap;
//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.junit.Assert.assertThat;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class WordControllerTest {
//
//    @Autowired
//    WordCounter wordCounter;
//
//    @Autowired
//    WordController wordController;
//
//    @Test
//    public void testShouldWork() {
//        assertThat(wordCounter.count(""), equalTo(emptyMap()));
//    }
//
//    @Test
//    public void count_returns_empty_map_when_body_is_empty() throws Exception{
//
//        Map<String, Integer> expected = emptyMap();
//
//        Map<String, Integer> actual = wordController.wordCount("");
//
//        assertThat(expected, equalTo(actual));
//    }
//
//    @Test
//    public void count_returns_map_when_body_is_not_empty() throws Exception{
//
//        Map<String, Integer> expected = new HashMap<>();
//        expected.put("how", 1);
//        expected.put("now", 1);
//        expected.put("brown", 1);
//        expected.put("cow", 1);
//
//        Map<String, Integer> actual = wordController.wordCount("how now, brown cow");
//
//        assertThat(expected, equalTo(actual));
//    }
//
//}