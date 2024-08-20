package com.fiap.techchallenge.gateway.strategy;

import org.springframework.http.HttpMethod;

public interface AutorizacaoStrategy {
    boolean autorizar(String requestPath, HttpMethod requestMethod, String tipoUsuario);
}
