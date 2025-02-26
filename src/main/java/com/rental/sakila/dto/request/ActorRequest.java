package com.rental.sakila.dto.request;

import jakarta.annotation.Nullable;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ActorRequest {
    public Optional<Short> id;

    public String firstName;
    public String lastName;
}
