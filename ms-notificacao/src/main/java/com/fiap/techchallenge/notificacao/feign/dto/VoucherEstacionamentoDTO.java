package com.fiap.techchallenge.notificacao.feign.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoucherEstacionamentoDTO {

    private LocalDateTime dataHoraRegistro;
    private Long qtdeDeHorasEstacionado;
    private String formaDePagamento;
}
