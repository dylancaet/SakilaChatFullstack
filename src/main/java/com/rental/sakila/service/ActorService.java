package com.rental.sakila.service;

import com.rental.sakila.dto.response.ActorResponse;
import com.rental.sakila.exception.ActorNotFoundException;
import com.rental.sakila.model.Actor;
import com.rental.sakila.repository.ActorRepository;
import jakarta.el.PropertyNotFoundException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@CommonsLog
@Service
public class ActorService {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getActorList(Optional<String> filterName)
    {
        List<Actor> listOfActors = filterName.map(actorRepository::findByFullNameContainingIgnoreCase).orElseGet(actorRepository::findAll);

        return listOfActors;
    }

    public Actor getActor(Short id)
    {
        Actor actor = actorRepository.findById(id)
                    .orElseThrow(() -> new ActorNotFoundException(String.format("Unsuccessful get request, Actor ID %s not found", id)));

        return actor;
    }

    public Actor createActor(String firstName, String lastName)
    {
        Actor actor = new Actor();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);

        Actor savedActor = actorRepository.save(actor);

        log.info(String.format("Actor Created: %s", actor.getId()));

        return savedActor;
    }

    public Actor updateActor(Short id, Optional<String> firstName, Optional<String> lastName)
    {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(String.format("Unsuccessful update request, Actor ID %s not found", id)));

        firstName.ifPresent(actor::setFirstName);
        lastName.ifPresent(actor::setLastName);
        Actor updatedActor = actorRepository.save(actor);

        log.info(String.format("Actor Updated: %s", actor.getId()));

        return updatedActor;
    }

    public void deleteActor(Short id)
    {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(String.format("Unsuccessful delete request, Actor ID %s not found", id)));

        actorRepository.delete(actor);

        log.info(String.format("Actor Deleted: %s", actor.getId()));
    }

}
