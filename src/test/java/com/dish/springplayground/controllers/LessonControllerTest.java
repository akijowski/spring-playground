package com.dish.springplayground.controllers;

import com.dish.springplayground.model.Lesson;
import com.dish.springplayground.repositories.LessonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class LessonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testListAllLessons() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setDeliveredOn(new Date());
        lesson.setTitle("sample title");

        repository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(lesson.getId().intValue())));
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateLesson() throws Exception {
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJSON("/lessonSample.json"));

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)));
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateLesson() throws  Exception {
        String beforeTitle = "Spring insecurity";
        String afterTitle = "Spring Security";
        String afterDate = "2017-04-12";

        Lesson lessonToUpdate = new Lesson();
        lessonToUpdate.setTitle(beforeTitle);
        lessonToUpdate.setDeliveredOn(new Date());

        repository.save(lessonToUpdate);

        MockHttpServletRequestBuilder request = patch("/lessons/{id}", lessonToUpdate.getId().intValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJSON("/lessonUpdateSample.json"));

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(afterTitle)))
                .andExpect(jsonPath("$.deliveredOn", equalTo(afterDate)));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindLessonByTitle() throws Exception {
        Lesson lessonToFind = new Lesson();
        String lessonTitle = "SQL";
        lessonToFind.setTitle(lessonTitle);
        lessonToFind.setDeliveredOn(new Date());

        repository.save(lessonToFind);

        MockHttpServletRequestBuilder request = get("/lessons/find/{title}", lessonTitle);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(lessonTitle)));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindLessonBetweenDates() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2014-03-17");
        Date date2 = dateFormat.parse("2015-03-17");

        Lesson lesson1 = new Lesson();
        lesson1.setDeliveredOn(date1);
        lesson1.setTitle("Dependency Injection");

        Lesson lesson2 = new Lesson();
        lesson2.setDeliveredOn(date2);
        lesson2.setTitle("Transactions");

        repository.save(lesson1);
        repository.save(lesson2);

        MockHttpServletRequestBuilder request = get("/lessons/between?date1={1}&date2={2}", "2014-03-16", "2015-03-17");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", instanceOf(Number.class)))
                .andExpect(jsonPath("[0].title", equalTo("Dependency Injection")))
                .andExpect(jsonPath("$[0].deliveredOn", equalTo("2014-03-17")))
                .andExpect(jsonPath("$[1].id", instanceOf(Number.class)))
                .andExpect(jsonPath("[1].title", equalTo("Transactions")))
                .andExpect(jsonPath("$[1].deliveredOn", equalTo("2015-03-17")));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        String json = new String(Files.readAllBytes(Paths.get(url.getFile())));
        return json;
    }

}