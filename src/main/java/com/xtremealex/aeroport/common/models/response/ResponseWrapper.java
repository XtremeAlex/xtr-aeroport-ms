package com.xtremealex.aeroport.common.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> implements Serializable {


    private String code;

    private String description;

    private String message;

    private T filters;

    private T payload;
}