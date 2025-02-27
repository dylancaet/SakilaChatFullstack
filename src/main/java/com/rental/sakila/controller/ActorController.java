package com.rental.sakila.controller;

import com.rental.sakila.dto.request.ActorRequest;
import com.rental.sakila.dto.request.ActorValidation;
import com.rental.sakila.dto.response.ActorResponse;
import com.rental.sakila.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
    Design choices:
        - Decoupled Controller logic into Services
        - Exceptions are handled in the service, not in the controller
        - RequestMapping routes are stored in a single file, for better readability and accessibility
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/sakila/actors")
public class ActorController
{
    private final ActorService actorService;

    /* GET List */
    @GetMapping
    public List<ActorResponse> getActorList(@RequestParam(required = false) Optional<String> name)
    {
        var actorList = actorService.getActorList(name).stream().map(ActorResponse::from).toList();
        return actorList;
    }

    /* GET Individual */
    @GetMapping("/{id}")
    public ActorResponse getActorIndividual(@PathVariable Short id)
    {
        var actorFetched = actorService.getActor(id);
        return ActorResponse.from(actorFetched);
    }

    /* POST */
    @PostMapping
    public ActorResponse createActor(@Validated(ActorValidation.Create.class) @RequestBody ActorRequest request)
    {
        var actorCreated = actorService.createActor(request.getFirstName(), request.getLastName());
        return ActorResponse.from(actorCreated);
    }

    /* PATCH */
    @PatchMapping
    public ActorResponse updateActor(@Validated(ActorValidation.Update.class) @RequestBody ActorRequest request)
    {
        var actorUpdated = actorService.updateActor(request.getId(), Optional.ofNullable(request.getFirstName()), Optional.ofNullable(request.getLastName()));
        return ActorResponse.from(actorUpdated);
    }

    /* DELETE */
    @DeleteMapping("/{id}")
    public void deleteActor(@Validated(ActorValidation.Delete.class) @PathVariable Short id)
    {
        actorService.deleteActor(id);
    }
}
