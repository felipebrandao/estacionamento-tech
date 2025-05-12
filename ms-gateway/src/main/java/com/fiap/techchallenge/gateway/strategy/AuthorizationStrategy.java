package com.fiap.techchallenge.gateway.strategy;

import org.springframework.http.HttpMethod;

public interface AuthorizationStrategy {
    boolean authorize(String requestPath, HttpMethod requestMethod, String userType);
}
