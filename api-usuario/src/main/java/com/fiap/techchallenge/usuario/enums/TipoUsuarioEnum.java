package com.fiap.techchallenge.usuario.enums;

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
