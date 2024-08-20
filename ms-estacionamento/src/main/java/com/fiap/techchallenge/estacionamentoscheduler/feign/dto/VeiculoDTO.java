package com.fiap.techchallenge.estacionamentoscheduler.feign.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class VeiculoDTO {
    private UUID id;
    private String marca;
    private String modelo;
    private String placa;
}
