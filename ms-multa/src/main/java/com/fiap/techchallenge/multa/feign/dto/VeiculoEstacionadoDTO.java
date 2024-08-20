package com.fiap.techchallenge.multa.feign.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class VeiculoEstacionadoDTO {
    private UUID idVeiculo;
    private UUID idLocalEstacionamento;
    private LocalDateTime dataHoraInicio;
    private List<VoucherEstacionamentoDTO> voucherEstacionamento = new ArrayList<>();
}
