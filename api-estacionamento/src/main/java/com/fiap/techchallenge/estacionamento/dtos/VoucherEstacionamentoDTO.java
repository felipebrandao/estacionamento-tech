package com.fiap.techchallenge.estacionamento.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.estacionamento.enums.FormaDePagamentoEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VoucherEstacionamentoDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID idVeiculoEstacionado;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataHoraRegistro;
    @JsonProperty
    private Long qtdeDeHorasEstacionado;
    @JsonProperty
    private FormaDePagamentoEnum formaDePagamento;
}
