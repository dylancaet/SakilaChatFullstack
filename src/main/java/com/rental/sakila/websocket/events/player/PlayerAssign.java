package com.rental.sakila.websocket.events.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerAssign
{
    private final String playerId;
    private final String token;
}
