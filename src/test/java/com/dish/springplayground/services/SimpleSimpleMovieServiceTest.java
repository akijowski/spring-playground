package com.dish.springplayground.services;

import com.dish.springplayground.model.SimpleMovie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
public class SimpleSimpleMovieServiceTest {

    @Test
    public void getMovies_shouldReturnListOfMovies() throws Exception {
        SimpleMovieService service = new SimpleMovieService();

        MockRestServiceServer mockServer = MockRestServiceServer.createServer(service.getRestTemplate());

        mockServer.expect(requestTo("http://www.omdbapi.com/?s=Harry"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Accept", "application/json, application/*+json"))
                .andRespond(withSuccess(getJSON("/sampleMovies.json"), MediaType.APPLICATION_JSON));

        List<SimpleMovie> simpleMovies = service.getMovies("Harry");

        assertThat(simpleMovies.size(), equalTo(2));
        assertThat(simpleMovies.get(0).getImdbId(), equalTo("tt1201607"));
        assertThat(simpleMovies.get(0).getPoster(), equalTo("https://images-na.ssl-imag..."));
        assertThat(simpleMovies.get(0).getTitle(), equalTo("Harry Potter and the Deathly Hallows: Part 2"));
        assertThat(simpleMovies.get(0).getYear(), equalTo(2011));

        mockServer.verify();
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        String json = new String(Files.readAllBytes(Paths.get(url.getFile())));
        return json;
    }
}