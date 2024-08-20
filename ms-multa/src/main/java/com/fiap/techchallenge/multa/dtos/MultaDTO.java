package com.fiap.techchallenge.multa.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class MultaDTO {
    private UUID idVeiculo;
    private UUID idLocalEstacionamento;
}
