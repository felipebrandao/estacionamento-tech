package com.fiap.techchallenge.estacionamentotech.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VeiculoEstacionadoDTO {

    private Long veiculoId;
    private Long localEstacionamentoId;
    private LocalDateTime dataHoraInicio;
    private List<VoucherEstacionamentoDTO> voucherEstacionamento;

}
