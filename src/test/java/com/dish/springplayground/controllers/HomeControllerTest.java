package com.dish.springplayground.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void math_pi_endpoint_should_return_string() throws Exception {
        this.mockMvc.perform(get("/math/pi").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void flights_flight_endpoint_should_return_flight_as_JSON() throws Exception {
        String flightJson = getJSON("/flightSample.json");
//        System.out.println(flightJson);
        MockHttpServletRequestBuilder request = get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse();

        JSONAssert.assertEquals(flightJson, response.getContentAsString(), JSONCompareMode.LENIENT);
    }

    @Test
    public void flights_endpoint_should_return_list_of_flights_as_JSON() throws Exception {
        String flightListJson = getJSON("/flightListSample.json");
//        System.out.println(flightListJson)
        
        MockHttpServletRequestBuilder request = get("/flights")
                .accept(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse();

        JSONAssert.assertEquals(flightListJson, response.getContentAsString(), JSONCompareMode.STRICT);
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        String json = new String(Files.readAllBytes(Paths.get(url.getFile())));
        return json;
    }

}