package com.example.jwt.service;

import com.example.jwt.entity.UserEntity;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    private static final String SECRET_KEY = "TestDeJWTTokenOnSimpleLoginAndRegisterProject";
    private final int day = 86400000;

    public String generateToken(UserEntity user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + day))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Map<String, String> extractCredentials(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        String password = claims.get("password", String.class);

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);
        return credentials;
    }
}
