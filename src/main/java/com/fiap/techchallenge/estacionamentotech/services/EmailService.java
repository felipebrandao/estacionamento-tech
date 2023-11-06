package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import jakarta.mail.MessagingException;

public interface EmailService {
    void enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(VeiculoEstacionadoDTO registroVeiculoEstacionadoDTO) throws MessagingException;
}
