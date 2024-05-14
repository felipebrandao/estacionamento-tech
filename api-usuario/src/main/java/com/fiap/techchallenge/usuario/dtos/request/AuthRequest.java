package com.fiap.techchallenge.usuario.dtos.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
