package com.lauracercas.moviecards.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.lauracercas.moviecards.model.Movie;

@Service
public class MovieClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "https://moviecards-service-caceres.azurewebsites.net/movies";

    public MovieClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Movie> getAllMovies() {
        ResponseEntity<List<Movie>> response =
            restTemplate.exchange(baseUrl,
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<Movie>>() {});
        return response.getBody();
    }

    public Movie getMovieById(Integer id) {
        return restTemplate.getForObject(baseUrl + "/" + id, Movie.class);
    }

    public void save(Movie actorRequest) {
        restTemplate.postForLocation(baseUrl, actorRequest);
    }

    public void updateActor(Integer id, Movie actorRequest) {
        restTemplate.put(baseUrl + "/" + id, actorRequest);
    }
}
