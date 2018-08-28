package com.dish.springplayground.services;

import com.dish.springplayground.model.Movie;
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
public class MovieServiceTest {

    @Test
    public void getMovies_shouldReturnListOfMovies() throws Exception {
        MovieService service = new MovieService();

        MockRestServiceServer mockServer = MockRestServiceServer.createServer(service.getRestTemplate());

        mockServer.expect(requestTo("http://www.omdbapi.com/?s=Harry"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Accept", "application/json, application/*+json"))
                .andRespond(withSuccess(getJSON("/sampleMovies.json"), MediaType.APPLICATION_JSON));

        List<Movie> movies = service.getMovies("Harry");

        assertThat(movies.size(), equalTo(2));
        assertThat(movies.get(0).getImdbId(), equalTo("tt1201607"));
        assertThat(movies.get(0).getPoster(), equalTo("https://images-na.ssl-imag..."));
        assertThat(movies.get(0).getTitle(), equalTo("Harry Potter and the Deathly Hallows: Part 2"));
        assertThat(movies.get(0).getYear(), equalTo(2011));

        mockServer.verify();
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        String json = new String(Files.readAllBytes(Paths.get(url.getFile())));
        return json;
    }
}