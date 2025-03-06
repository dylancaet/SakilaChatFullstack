package com.rental.sakila.websocket.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtService
{
    // TODO .env
    private final String SECRET_KEY = "ITSASECRETFORGETABOUTNOTMEBUTTHEKEYYOUGETME";

    public String generateToken(String playerId) {
        return Jwts.builder()
                .subject(playerId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
