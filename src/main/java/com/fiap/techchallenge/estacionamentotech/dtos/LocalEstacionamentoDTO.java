package com.fiap.techchallenge.estacionamentotech.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalEstacionamentoDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty
    private String logradouro;
    @JsonProperty
    private String bairro;
    @JsonProperty
    private String cep;
    @JsonProperty
    private String intervaloDeNumero;
}
