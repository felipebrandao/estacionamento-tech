package com.fiap.techchallenge.auth.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserTypeEnum {

    COMUM("Comum"),
    FISCAL("Fiscal");

    private final String displayName;

    UserTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

}
