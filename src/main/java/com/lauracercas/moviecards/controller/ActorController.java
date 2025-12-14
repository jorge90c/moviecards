package com.lauracercas.moviecards.controller;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
//import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import com.lauracercas.moviecards.client.ActorClient;


/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
@Controller
public class ActorController {

    /*private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }*/

    private final ActorClient actorClient;

    public ActorController(ActorClient actorClient) {
        this.actorClient = actorClient;
    }

    @GetMapping("actors")
    public String getActorsList(Model model) {
        model.addAttribute("actors", actorClient.getAllActors());
        return "actors/list";
    }

    @GetMapping("actors/new")
    public String newActor(Model model) {
        model.addAttribute("actor", new Actor());
        model.addAttribute("title", Messages.NEW_ACTOR_TITLE);
        return "actors/form";
    }

    @PostMapping("saveActor")
    public String saveActor(@ModelAttribute Actor actor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("actor", actor);
            return "actors/form";
        }
        try{
        actorClient.save(actor);
        model.addAttribute("actor", actor);
        model.addAttribute("title", Messages.EDIT_ACTOR_TITLE);
        if (actor.getId() != null) {
            model.addAttribute("message", Messages.UPDATED_ACTOR_SUCCESS);
        } else {
            model.addAttribute("message", Messages.SAVED_ACTOR_SUCCESS);
        }
        return "actors/form";
        } catch (Exception e) {
            model.addAttribute("actor", actor);
            model.addAttribute("error", "Ocurrió un error al guardar el actor: " + e.getMessage());
            return "actors/form";
        }
    }

    @GetMapping("editActor/{actorId}")
    public String editActor(@PathVariable Integer actorId, Model model) {
        Actor actor = actorClient.getActorById(actorId);
        List<Movie> movies = actor.getMovies();
        model.addAttribute("actor", actor);
        model.addAttribute("movies", movies);

        model.addAttribute("title", Messages.EDIT_ACTOR_TITLE);

        return "actors/form";
    }


}
