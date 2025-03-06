package com.rental.sakila.websocket.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameEvent<T>
{
    private String eventType;
    private T payload;
}
