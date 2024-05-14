package com.fiap.techchallenge.veiculo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class VeiculoDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    @JsonProperty
    private String marca;
    @JsonProperty
    private String modelo;
    @JsonProperty
    private String placa;
}
