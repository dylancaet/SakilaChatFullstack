package com.rental.sakila.controller;

import com.rental.sakila.dto.request.ActorRequest;
import com.rental.sakila.dto.response.ActorResponse;
import com.rental.sakila.model.Actor;
import com.rental.sakila.repository.ActorRepository;
import jakarta.el.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/sakila/actors")
public class ActorController
{
    private final ActorRepository actorRepository;

    @Autowired
    public ActorController(ActorRepository actorRepository)
    {
        this.actorRepository = actorRepository;
    }

    /* GET List */
    @GetMapping
    public List<ActorResponse> listActors(@RequestParam(required = false) Optional<String> name) {
        return name.map(actorRepository::findByFullNameContainingIgnoreCase)
                .orElseGet(actorRepository::findAll)
                .stream()
                .map(ActorResponse::from)
                .toList();
    }

    /* GET Individual */
    @GetMapping("/{id}")
    public ActorResponse getActorIndividual(@PathVariable Short id) {
        return ActorResponse.from(actorRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Actor ID not found")));
    }

    /* POST */
    @PostMapping
    public ActorResponse createActor(@RequestBody ActorRequest request)
    {
        Actor actor = new Actor();

        actor.setFirstName(request.firstName);
        actor.setLastName(request.lastName);

        Actor savedActor = actorRepository.save(actor);

        return ActorResponse.from(actor);
    }

    /* PATCH */
    @PatchMapping
    public ActorResponse updateActor(@RequestBody ActorRequest request)
    {
        Actor actor = actorRepository.findById(request.getId().orElseThrow(() -> new PropertyNotFoundException("ID not found"))) // this should be a bad request, fix this pls
                .orElseThrow(() -> new PropertyNotFoundException("Actor ID not found"));


        // could iterate with a .values() method, check null, would need to contain corresponding keys, annotation?

        if (request.firstName != null) {
            actor.setFirstName(request.firstName);
        }

        if (request.lastName != null) {
            actor.setLastName(request.lastName);
        }

        actorRepository.save(actor);

        return ActorResponse.from(actor);
    }

    /* DELETE */
    @DeleteMapping("/{id}")
    public ActorResponse deleteActor(@PathVariable Short id)
    {
        Actor actor = actorRepository.findById(id)
                    .orElseThrow(() -> new PropertyNotFoundException("Actor ID not found"));

        actorRepository.delete(actor);

        return ActorResponse.from(actor);
    }
}
