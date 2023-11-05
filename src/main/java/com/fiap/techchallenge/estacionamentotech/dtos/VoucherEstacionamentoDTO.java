package com.fiap.techchallenge.estacionamentotech.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.estacionamentotech.enums.FormaDePagamentoEnum;
import lombok.Data;

@Data
public class VoucherEstacionamentoDTO {

    @JsonProperty
    private Long qtdeDeHorasEstacionado;
    @JsonProperty
    private FormaDePagamentoEnum formaDePagamento;

}
