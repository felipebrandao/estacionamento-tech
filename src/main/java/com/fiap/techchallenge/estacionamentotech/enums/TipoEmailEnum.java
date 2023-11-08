package com.fiap.techchallenge.estacionamentotech.enums;

public enum TipoEmailEnum {
    REGISTRO("/templates/email-notificacao-estacionamento.html"),
    EXPIRACAO("/templates/email-notificacao-expiracao-estacionamento.html"),
    EXPIRADO("/templates/email-notificacao-expirado-estacionamento.html");

    private String caminho;

    TipoEmailEnum(String caminho) {
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }
}
