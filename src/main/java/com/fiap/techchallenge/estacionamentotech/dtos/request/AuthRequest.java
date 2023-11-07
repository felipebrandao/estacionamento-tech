package com.fiap.techchallenge.estacionamentotech.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
}
