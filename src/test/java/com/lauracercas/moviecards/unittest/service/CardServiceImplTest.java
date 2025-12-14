package com.lauracercas.moviecards.unittest.service;

import com.lauracercas.moviecards.client.ActorClient;
import com.lauracercas.moviecards.client.MovieClient;
import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Card;
import com.lauracercas.moviecards.model.Movie;
//import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.service.card.CardServiceImpl;
//import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.util.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
class CardServiceImplTest {
/* 
    @Mock
    ActorClient actorClient;
    //ActorService actorService;
    
    @Mock
    MovieClient movieClient;
    //MovieService movieService;
    
    private CardServiceImpl sut;
    private AutoCloseable closeable;


    @BeforeEach
    public void setUp() {
        closeable = openMocks(this);
        sut = new CardServiceImpl(actorClient, movieClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldRegisterCardAndReturnOkMessage() {
        Card card = new Card();
        card.setIdActor(1);
        card.setIdMovie(2);

        Actor actor = new Actor();
        actor.setId(1);
        actor.setName("Sample Actor");
        Movie movie = new Movie();
        movie.setId(2);
        movie.setTitle("Sample Movie");
        movie.setActors(new ArrayList<>());

        when(actorClient.getActorById(1)).thenReturn(actor);
        when(movieClient.getMovieById(2)).thenReturn(movie);
        doNothing().when(movieClient).save(any(Movie.class));


        String result = sut.registerActorInMovie(card);

        assertEquals(Messages.CARD_REGISTRATION_SUCCESS, result);
    }

    @Test
    public void shouldNotCreateCardAndReturnErrorMessage() {
        Card card = new Card();
        card.setIdActor(1);
        card.setIdMovie(2);

        when(actorClient.getActorById(1)).thenReturn(null);
        when(movieClient.getMovieById(2)).thenReturn(null);

        String result = sut.registerActorInMovie(card);

        assertEquals(Messages.ERROR_MESSAGE, result);
    }

    @Test
    public void shouldNotRegisterCardWhenAlreadyExistAndReturnAlertMessage() {
        Card card = new Card();
        card.setIdActor(1);
        card.setIdMovie(2);

        Actor actor = new Actor();
        actor.setId(1);
        actor.setName("John Doe");

        Movie movie = new Movie();
        movie.setId(2);
        movie.setTitle("Sample Movie");
        movie.setActors(List.of(actor));

        when(actorClient.getActorById(1)).thenReturn(actor);
        when(movieClient.getMovieById(2)).thenReturn(movie);

        String result = sut.registerActorInMovie(card);

        assertEquals(Messages.CARD_ALREADY_EXISTS, result);

    }
*/
}