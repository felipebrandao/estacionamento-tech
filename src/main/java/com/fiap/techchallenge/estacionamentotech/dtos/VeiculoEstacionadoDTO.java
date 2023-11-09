package com.fiap.techchallenge.estacionamentotech.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class VeiculoEstacionadoDTO {
    @JsonProperty
    private Long idVeiculo;
    @JsonProperty
    private Long idLocalEstacionamento;
    @JsonProperty
    private LocalDateTime dataHoraInicio;
    @JsonProperty
    private List<VoucherEstacionamentoDTO> voucherEstacionamento = new ArrayList<>();
}
