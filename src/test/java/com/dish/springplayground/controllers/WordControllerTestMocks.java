//package com.dish.springplayground.controllers;
//
//import com.dish.springplayground.services.WordCounter;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static java.util.Collections.emptyMap;
//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.junit.Assert.assertThat;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(WordController.class)
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc(secure = false)
//public class WordControllerTestMocks {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    WordCounter wordCounter;
//
//    @Before
//    public void setup() {
//        Map<String, Integer> dummyMap = new HashMap<>();
//        dummyMap.put("sample", 1);
//        when(wordCounter.count(anyString()))
//                .thenReturn(emptyMap());
//    }
//
//    @Test
//    public void testShouldWork() {
//        assertThat(wordCounter.count(""), equalTo(emptyMap()));
//    }
//
//    @Test
//    public void count_POST_returns_map_when_body_is_not_empty() throws Exception{
//
//        String expected = "{\"a\":1,\"dog\":1}";
//        Map<String, Integer> sampleMap = new HashMap<>();
//        sampleMap.put("a", 1);
//        sampleMap.put("dog", 1);
//        when(wordCounter.count(anyString()))
//                .thenReturn(sampleMap);
//
//        MockHttpServletResponse response = this.mockMvc.perform(
//                post("/words/count")
//                        .contentType(MediaType.TEXT_PLAIN)
//                        .content("a dog"))
//                .andExpect(status().isOk())
//                .andReturn().getResponse();
//
//        System.out.println(response.getContentAsString());
//        assertThat(response.getContentAsString(), equalTo(expected));
//    }
//
//}