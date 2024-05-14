package com.fiap.techchallenge.estacionamento.producer.dto;

import com.fiap.techchallenge.estacionamento.producer.enums.TipoEmailEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacaoDTO {

    private UUID idNotificacao;
    private UUID idVeiculo;
    private TipoEmailEnum tipoEmail;
    private LocalDateTime dataHoraEnvioFila;
}
