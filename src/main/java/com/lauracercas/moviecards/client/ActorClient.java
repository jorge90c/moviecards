package com.lauracercas.moviecards.client;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lauracercas.moviecards.model.Actor;

@Service
public class ActorClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "https://moviecards-service-caceres.azurewebsites.net/actors";

    public ActorClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Actor> getAllActors() {
        ResponseEntity<List<Actor>> response =
            restTemplate.exchange(baseUrl,
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<Actor>>() {});
        return response.getBody();
    }

    public Actor getActorById(Integer id) {
        return restTemplate.getForObject(baseUrl + "/" + id, Actor.class);
    }

    public void save(Actor actorRequest) {
        restTemplate.postForLocation(baseUrl, actorRequest);
    }

    public void updateActor(Long id, Actor actorRequest) {
        restTemplate.put(baseUrl + "/" + id, actorRequest);
    }
}

