package com.lauracercas.moviecards.client;

import com.lauracercas.moviecards.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieClientTest {

    @Test
    void testGetAllMovies() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        MovieClient client = new MovieClient(restTemplate);

        Movie movie = new Movie();
        movie.setTitle("Matrix");
        movie.setCountry("USA");
        movie.setDirector("Wachowski");

        List<Movie> mockMovies = List.of(movie);

        when(restTemplate.exchange(
                eq("https://moviecards-service-caceres.azurewebsites.net/movies"),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class))
        ).thenReturn(ResponseEntity.ok(mockMovies));

        List<Movie> result = client.getAllMovies();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Matrix", result.get(0).getTitle());
        assertEquals("USA", result.get(0).getCountry());
        assertEquals("Wachowski", result.get(0).getDirector());
    }

    @Test
    void testGetMovieById() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        MovieClient client = new MovieClient(restTemplate);

        Movie movie = new Movie();
        movie.setTitle("Matrix");
        movie.setGenre("Drama");

        when(restTemplate.getForObject(
                "https://moviecards-service-caceres.azurewebsites.net/movies/1",
                Movie.class)
        ).thenReturn(movie);

        Movie result = client.getMovieById(1);

        assertNotNull(result);
        assertEquals("Matrix", result.getTitle());
        assertEquals("Drama", result.getGenre());
    }

    @Test
    void testSaveMovie() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        MovieClient client = new MovieClient(restTemplate);

        Movie movie = new Movie();
        movie.setTitle("Matrix");
        movie.setCountry("USA");

        client.save(movie);

        verify(restTemplate).postForLocation(
                "https://moviecards-service-caceres.azurewebsites.net/movies",
                movie
        );
    }

    @Test
    void testUpdateMovie() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        MovieClient client = new MovieClient(restTemplate);

        Movie movie = new Movie();
        movie.setTitle("Matrix Reloaded");
        movie.setDirector("Wachowski");

        client.updateActor(1, movie);

        verify(restTemplate).put(
                "https://moviecards-service-caceres.azurewebsites.net/movies/1",
                movie
        );
    }
}
