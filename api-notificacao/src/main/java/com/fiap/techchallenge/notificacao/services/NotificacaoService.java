package com.fiap.techchallenge.notificacao.services;

import com.fiap.techchallenge.notificacao.dtos.NotificacaoDTO;
import jakarta.mail.MessagingException;

public interface NotificacaoService {
    void enviarEmail(NotificacaoDTO notificacaoDTO) throws MessagingException;
}
