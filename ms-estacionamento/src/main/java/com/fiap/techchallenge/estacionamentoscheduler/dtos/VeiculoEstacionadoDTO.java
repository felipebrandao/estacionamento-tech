package com.fiap.techchallenge.estacionamentoscheduler.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class VeiculoEstacionadoDTO {
    @JsonProperty
    private UUID idVeiculo;
    @JsonProperty
    private UUID idLocalEstacionamento;
    @JsonProperty
    private LocalDateTime dataHoraInicio;
    @JsonProperty
    private List<VoucherEstacionamentoDTO> voucherEstacionamento = new ArrayList<>();
}
