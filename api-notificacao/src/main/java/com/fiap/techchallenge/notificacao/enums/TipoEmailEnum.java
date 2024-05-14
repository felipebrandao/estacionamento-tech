package com.fiap.techchallenge.notificacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEmailEnum {
    REGISTRO("Estacionamento Tech - Registro de Estacionamento", "/templates/email-notificacao-estacionamento.html"),
    EXPIRACAO("Estacionamento Tech - Aviso de Expiração", "/templates/email-notificacao-expiracao-estacionamento.html"),
    EXPIRADO("Estacionamento Tech - Expirado", "/templates/email-notificacao-expirado-estacionamento.html");

    private final String assunto;
    private final String caminho;
}
