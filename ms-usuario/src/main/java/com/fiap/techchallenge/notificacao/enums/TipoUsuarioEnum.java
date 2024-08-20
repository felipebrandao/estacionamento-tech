package com.fiap.techchallenge.notificacao.enums;

import lombok.Getter;

@Getter
public enum TipoUsuarioEnum {

    COMUM("Comum"),
    FISCAL("Fiscal");

    private final String tipoUsuario;

    TipoUsuarioEnum(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
