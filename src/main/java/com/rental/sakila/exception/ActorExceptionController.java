package com.rental.sakila.exception;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@CommonsLog
@ControllerAdvice
public class ActorExceptionController
{
    @ExceptionHandler(value = ActorNotFoundException.class)
    public ResponseEntity<Object> exception(ActorNotFoundException e)
    {
        log.warn(e.getMessage());

        return new ResponseEntity<>("Actor not found", HttpStatus.NOT_FOUND);
    }
}
