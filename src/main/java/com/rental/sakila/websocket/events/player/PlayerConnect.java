package com.rental.sakila.websocket.events.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerConnect
{
    private int id;
    private final String playerName;
}
