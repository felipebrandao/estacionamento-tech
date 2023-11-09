package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.EmailEstacionamentoDTO;
import jakarta.mail.MessagingException;

public interface EmailService {
    void enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(EmailEstacionamentoDTO emailEstacionamentoDTO) throws MessagingException;
}
