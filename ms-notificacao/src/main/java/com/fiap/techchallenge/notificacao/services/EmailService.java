package com.fiap.techchallenge.notificacao.services;

public interface EmailService {
    void enviarEmail(String email, String assunto, String mensagem);
}
