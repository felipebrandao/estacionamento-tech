package com.fiap.techchallenge.auth.utils;

import com.fiap.techchallenge.auth.exceptions.InvalidTokenException;

public class JwtUtils {

    private static final String BEARER_PREFIX = "Bearer ";

    private JwtUtils() {
    }

    public static String extractToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            throw new InvalidTokenException("Autorização inválida");
        }
        return authorizationHeader.substring(BEARER_PREFIX.length());
    }
}
