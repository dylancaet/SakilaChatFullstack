package com.rental.sakila.dto.response;

import com.rental.sakila.entity.Actor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ActorResponse {
    private final Short id;
    private final String firstName;
    private final String lastName;
    private final String fullName;

    public static ActorResponse from(Actor actor) {
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getFullName());
    }
}
