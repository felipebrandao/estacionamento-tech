package com.fiap.techchallenge.estacionamentoscheduler.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class LocalEstacionamentoDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    @JsonProperty
    private String logradouro;
    @JsonProperty
    private String bairro;
    @JsonProperty
    private String cep;
    @JsonProperty
    private String intervaloDeNumero;
}
