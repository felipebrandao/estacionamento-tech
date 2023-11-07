package com.fiap.techchallenge.estacionamentotech.enums;

public enum TipoUsuarioEnum {

    COMUM("Comum"),
    FISCAL("Fiscal");

    private String tipoUsuario;

    TipoUsuarioEnum(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

}
