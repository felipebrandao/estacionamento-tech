package com.fiap.techchallenge.estacionamentotech.enums;

public enum TipoUsuario {

    COMUM("C"),
    FISCAL("F");

    private String tipoUsuario;

    TipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

}
