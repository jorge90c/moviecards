package com.lauracercas.moviecards.service.card;


import com.lauracercas.moviecards.client.ActorClient;
import com.lauracercas.moviecards.client.MovieClient;
import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Card;
import com.lauracercas.moviecards.model.Movie;
//import com.lauracercas.moviecards.service.actor.ActorService;
//import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.stereotype.Service;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
@Service
public class CardServiceImpl implements CardService {

    private final MovieClient movieClient;
    private final ActorClient actorClient;

    //private final ActorService actorService;
    //private final MovieService movieService;

    public CardServiceImpl(ActorClient actorClient, MovieClient movieClient) {
        this.actorClient = actorClient;
        this.movieClient = movieClient;
    }

    @Override
    public String registerActorInMovie(Card card) {
        Integer actorId = card.getIdActor();
        Integer movieId = card.getIdMovie();

        Actor actor = actorClient.getActorById(actorId);
        Movie movie = movieClient.getMovieById(movieId);

        if (actor == null || movie == null) {
            return Messages.ERROR_MESSAGE;
        }

        if (movie.existActorInMovie(actor)) {
            return Messages.CARD_ALREADY_EXISTS;
        }

        movie.addActor(actor);
        movieClient.save(movie);
        return Messages.CARD_REGISTRATION_SUCCESS;
    }


}
