package com.rental.sakila.exception;


public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException() {}

    public ActorNotFoundException(String msg) {
        super(msg);
    }
}
