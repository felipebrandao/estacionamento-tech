package com.fiap.techchallenge.estacionamento.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

    private Instant timestamp;
    private int status;
    private String mensagem;
}
