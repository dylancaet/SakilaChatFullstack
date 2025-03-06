package com.rental.sakila.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental.sakila.websocket.events.GameEvent;
import com.rental.sakila.websocket.events.player.PlayerAssign;
import com.rental.sakila.websocket.events.player.PlayerConnect;
import com.rental.sakila.websocket.jwt.JwtService;
import io.micrometer.common.lang.NonNullApi;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@CommonsLog
@Component
public class WebSocketMessageHandler extends TextWebSocketHandler
{
    /*  CopyOnWriteArrayList<> A thread-safe variant of ArrayList in which all mutative operations (add, set, and so on)
        are implemented by making a fresh copy of the underlying array.

        ConcurrentHashMap<> A thread-safe variant that supports high-performance concurrent reads/writes.
    */
    private final List<WebSocketSession> sessions;
    private final Map<String, WebSocketSession> sessionMap;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    public WebSocketMessageHandler()
    {
        this.sessions = new CopyOnWriteArrayList<>();
        this.sessionMap = new ConcurrentHashMap<>();
        this.jwtService = new JwtService();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception
    {
        String payload = message.getPayload();
//        session.sendMessage(new TextMessage("Received: " + payload));

    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception
    {
        sessions.add(session);
        log.info(String.format("WS: Player joined: %s", session.getRemoteAddress()));

        /* Respond with token */
        String id = UUID.randomUUID().toString();
        PlayerAssign playerAssign = new PlayerAssign(id, jwtService.generateToken(id));
        GameEvent<PlayerAssign> playerAssignEvent = new GameEvent<>("PLAYER_ASSIGN", playerAssign);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(playerAssignEvent)));

        /* Broadcast player join */
//        PlayerConnect player = new PlayerConnect(777, "Alice");
//        GameEvent<PlayerConnect> event = new GameEvent<PlayerConnect>("PLAYER_CONNECT", player);
//        String json = objectMapper.writeValueAsString(event);
//        broadcastAll(json);
    }


    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception
    {
        sessions.remove(session);
        log.info(String.format("WS: Player disconnected: %s closed", session.getRemoteAddress()));
    }



    private void broadcastAll(String message)
    {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException _) {

            }
        }
    }
}

