package com.lauracercas.moviecards.controller;

import com.lauracercas.moviecards.client.ActorClient;
import com.lauracercas.moviecards.client.MovieClient;
import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Card;
import com.lauracercas.moviecards.model.Movie;
//import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.service.card.CardService;
//import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
@Controller
public class CardController {
    //private final MovieService movieService;
    private final ActorClient actorClient;
    private final MovieClient movieClient;
    //private final ActorService actorService;
    private final CardService cardService;

    public CardController(MovieClient movieClient, ActorClient actorClient, CardService cardService) {
        this.movieClient = movieClient;
        this.actorClient = actorClient;
        this.cardService = cardService;
    }

    @GetMapping("registerActorMovie")
    public String showInfoForm(Model model) {
        prepareCardInfoForm(model);

        return "cards/registerActorMovieForm";
    }

    private void prepareCardInfoForm(Model model) {
        List<Actor> actors = actorClient.getAllActors();
        List<Movie> movies = movieClient.getAllMovies();

        model.addAttribute("actors", actors);
        model.addAttribute("movies", movies);
        model.addAttribute("card", new Card());
    }

    @PostMapping("register")
    public String registerCard(@ModelAttribute Card card, Model model) {
        String result = cardService.registerActorInMovie(card);

        model.addAttribute("message", result);
        if (!result.equals(Messages.CARD_REGISTRATION_SUCCESS)) {
            prepareCardInfoForm(model);
            return "cards/registerActorMovieForm";
        }

        return "index";
    }
}
