package com.rental.sakila.websocket;

import io.micrometer.common.lang.NonNullApi;
import lombok.NonNull;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@CommonsLog
public class WebSocketMessageHandler extends TextWebSocketHandler
{
    /*  CopyOnWriteArrayList<> A thread-safe variant of ArrayList in which all mutative operations (add, set, and so on)
        are implemented by making a fresh copy of the underlying array.
    */
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
    private final Random random = new Random();

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception
    {
        String payload = message.getPayload();
//        session.sendMessage(new TextMessage("Received: " + payload));

        random.setSeed(stringToSeed(session.getId()));
        int userName = random.nextInt(1000);

        broadcastAll(String.format("%s: %s", userName, payload));
    }

    @Override

    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception
    {
        sessions.add(session);
        log.info(String.format("WS: Connection %s established", session.getRemoteAddress()));
    }


    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception
    {
        sessions.remove(session);
        log.info(String.format("WS: Connection %s closed", session.getRemoteAddress()));
    }
    
    public void broadcastAll(String message)
    {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException _) {

            }
        }
    }

    static long stringToSeed(String s) {
        if (s == null) {
            return 0;
        }
        long hash = 0;
        for (char c : s.toCharArray()) {
            hash = 31L*hash + c;
        }
        return hash;
    }
}

