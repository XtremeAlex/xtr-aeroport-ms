package com.xtremealex.aeroport.common.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResponseWrapper<T> implements Serializable {


    private String code;

    private String description;

    private String message;

    private T filters;

    private T payload;
}