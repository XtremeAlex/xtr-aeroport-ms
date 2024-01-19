package com.xtremealex.aeroport.common.models;

import lombok.Getter;

@Getter
public enum ErrorCode {

    E0("E0", "KO"),
    E1("E1", "OK");

    private final String code;
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ErrorCode getFromString(String code) {
        for (ErrorCode value : ErrorCode.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

}
