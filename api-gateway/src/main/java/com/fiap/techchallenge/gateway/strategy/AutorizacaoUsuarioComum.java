package com.fiap.techchallenge.gateway.strategy;

import org.springframework.http.HttpMethod;

public class AutorizacaoUsuarioComum implements AutorizacaoStrategy{
    @Override
    public boolean autorizar(String requestPath, HttpMethod requestMethod, String tipoUsuario) {
        if (requestPath.startsWith("/api/veiculo") && requestMethod == HttpMethod.POST) {
            return false;
        }
        return true;
    }
}
