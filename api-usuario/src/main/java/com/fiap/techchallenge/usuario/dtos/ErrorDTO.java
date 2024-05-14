package com.fiap.techchallenge.usuario.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private Instant timestamp;
    private int status;
    private String mensagem;

}
