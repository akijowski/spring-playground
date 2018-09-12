package com.dish.springplayground.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
@AutoConfigureMockMvc(secure = false)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();


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

    @Test
    public void flights_tickets_total_endpoint_returns_sum_of_prices_as_JSON_string_literal() throws Exception {
        String payload = "{\"Tickets\": [{\"Passenger\": {\"FirstName\": \"Some name\",\"LastName\": \"Some other name\"}," +
                "\"Price\": 200}, {\"Passenger\": {\"FirstName\": \"Name B\",\"LastName\": \"Name C\"},\"Price\": 150" +
                "}]}";

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse();

        String expected = getJSON("/ticketTotalResponse.json");

        JSONAssert.assertEquals(expected, response.getContentAsString(), JSONCompareMode.LENIENT);
    }

    @Test
    public void flights_tickets_total_endpoint_returns_sum_of_prices_as_JSON_object_mapper() throws Exception {
        HashMap<String, Object> flight = new HashMap<>();
        HashMap<String, Object> ticket1 = new HashMap<String, Object>() {
            {
                put("Price", 200);
                put("Passenger", new HashMap<String, Object>() {
                    {
                        put("FirstName", "Name B");
                        put("LastName", "Name C");
                    }
                });
            }
        };
        HashMap<String, Object> ticket2 = new HashMap<String, Object>() {
            {
                put("Price", 150);
                put("Passenger", new HashMap<String, Object>() {
                    {
                        put("FirstName", "Some name");
                        put("LastName", "Some other name");
                    }
                });
            }
        };

        flight.put("Tickets", asList(ticket1, ticket2));

        System.out.println(flight);

        String payload = objectMapper.writeValueAsString(flight);

        System.out.println(payload);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse();

        String expected = getJSON("/ticketTotalResponse.json");

        JSONAssert.assertEquals(expected, response.getContentAsString(), JSONCompareMode.LENIENT);
    }

    @Test
    public void flights_tickets_total_endpoint_returns_sum_of_prices_as_JSON_file() throws Exception {
        String payload = getJSON("/ticketTotalRequest.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse();

        String expected = getJSON("/ticketTotalResponse.json");

        JSONAssert.assertEquals(expected, response.getContentAsString(), JSONCompareMode.LENIENT);
    }


    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        String json = new String(Files.readAllBytes(Paths.get(url.getFile())));
        return json;
    }

}