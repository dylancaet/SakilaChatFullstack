package com.rental.sakila.websocket;

import com.rental.sakila.route.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer
{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
        registry.addHandler(webSocketHandler(), Route.WebSocket.TEXT_MESSAGE).setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler webSocketHandler()
    {
        return new WebSocketMessageHandler();
    }
}
