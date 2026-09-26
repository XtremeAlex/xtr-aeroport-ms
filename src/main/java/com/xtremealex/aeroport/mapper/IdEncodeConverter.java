package com.xtremealex.aeroport.mapper;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class IdEncodeConverter extends AbstractConverter<Long, String> {


    @Override
    protected String convert(Long value) {
        return Base64.getEncoder().encodeToString(value.toString().getBytes());
    }

}
