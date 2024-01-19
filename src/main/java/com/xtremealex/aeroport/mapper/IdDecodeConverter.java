package com.xtremealex.aeroport.mapper;

import org.modelmapper.AbstractConverter;

import java.util.Base64;

public class IdDecodeConverter extends AbstractConverter<String, Long> {


    @Override
    protected Long convert(String source) {
        return Long.parseLong(new String(Base64.getDecoder().decode(source)));
    }

}
