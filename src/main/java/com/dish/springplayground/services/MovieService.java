package com.dish.springplayground.services;

import com.dish.springplayground.model.Movie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class MovieService {

    private final RestTemplate restTemplate;
    private final String HOST = "http://www.omdbapi.com/?s={search}";

    public MovieService() {
        this.restTemplate = new RestTemplate();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public List<Movie> getMovies(String queryParam) {
        System.out.println("getMovies() queryParam: " + queryParam);
        List<Movie> movies = emptyList();
        try {
            URI uri = getSearchUri(queryParam);
            System.out.println("using uri: " + uri.toString());

            RequestEntity<?> request = RequestEntity.get(uri)
                    .header("Authorization", "some-token")
                    .build();

           ResponseEntity<List<Movie>> movieResponse = restTemplate.exchange(
                   request,
                   new ParameterizedTypeReference<List<Movie>>() {
                   }
           );
           System.out.println(String.format("Found %s movies: %s", movieResponse.getBody().size(), movieResponse.getBody()));
           movies = movieResponse.getBody();
        } catch (RestClientException rce) {
            rce.printStackTrace();
        }
        return movies;
    }

    private URI getSearchUri(String search) {
        return UriComponentsBuilder
                .fromUriString(HOST)
                .buildAndExpand(search)
                .toUri();
    }
}
