package com.fiap.techchallenge.gateway.strategy;

import org.springframework.http.HttpMethod;

public class AutorizacaoUsuarioFiscal implements AutorizacaoStrategy {
    @Override
    public boolean autorizar(String requestPath, HttpMethod requestMethod, String tipoUsuario) {
        if(requestPath.startsWith("/api/veiculo")) {
            return false;
        }
        return true;
    }
}
