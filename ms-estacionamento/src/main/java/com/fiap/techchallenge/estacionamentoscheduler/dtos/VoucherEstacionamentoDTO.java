package com.fiap.techchallenge.estacionamentoscheduler.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.estacionamentoscheduler.enums.FormaDePagamentoEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoucherEstacionamentoDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataHoraRegistro;
    @JsonProperty
    private Long qtdeDeHorasEstacionado;
    @JsonProperty
    private FormaDePagamentoEnum formaDePagamento;
}
