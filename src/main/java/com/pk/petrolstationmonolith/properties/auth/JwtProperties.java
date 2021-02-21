package com.pk.petrolstationmonolith.properties.auth;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.security.Key;
import java.util.UUID;

@ConfigurationProperties("jwt")
public class JwtProperties {

    /**
     * JWT expiration time in minutes.
     */
    private long expirationTime;

    public long getExpirationTime() {
        return expirationTime * 60 * 1000;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Bean
    public Key jwtSecret() {
        String secret = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        byte[] keyBytes = Decoders.BASE64.decode(secret.replaceAll("-", "x"));
        return Keys.hmacShaKeyFor(keyBytes);
    }

}