package com.lauracercas.moviecards.client;

import com.lauracercas.moviecards.model.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActorClientTest {

    @Test
    void testGetAllActors() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        ActorClient client = new ActorClient(restTemplate);

        Actor actor = new Actor();
        actor.setName("Jorge");
        actor.setCountry("Peru");
        actor.setBirthDate(new Date());

        List<Actor> mockActors = List.of(actor);

        when(restTemplate.exchange(
                eq("https://moviecards-service-caceres.azurewebsites.net/actors"),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class))
        ).thenReturn(ResponseEntity.ok(mockActors));

        List<Actor> result = client.getAllActors();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Jorge", result.get(0).getName());
        assertEquals("Peru", result.get(0).getCountry());
    }

    @Test
    void testGetActorById() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        ActorClient client = new ActorClient(restTemplate);

        Actor actor = new Actor();
        actor.setName("Jorge");
        actor.setCountry("Peru");

        when(restTemplate.getForObject(
                "https://moviecards-service-caceres.azurewebsites.net/actors/1",
                Actor.class)
        ).thenReturn(actor);

        Actor result = client.getActorById(1);

        assertNotNull(result);
        assertEquals("Jorge", result.getName());
        assertEquals("Peru", result.getCountry());
    }

    @Test
    void testSaveActor() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        ActorClient client = new ActorClient(restTemplate);

        Actor actor = new Actor();
        actor.setName("Jorge");
        actor.setCountry("Peru");

        client.save(actor);

        verify(restTemplate).postForLocation(
                "https://moviecards-service-caceres.azurewebsites.net/actors",
                actor
        );
    }

    @Test
    void testUpdateActor() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        ActorClient client = new ActorClient(restTemplate);

        Actor actor = new Actor();
        actor.setName("Jorge");
        actor.setCountry("Peru");

        client.updateActor(1L, actor);

        verify(restTemplate).put(
                "https://moviecards-service-caceres.azurewebsites.net/actors/1",
                actor
        );
    }
}
