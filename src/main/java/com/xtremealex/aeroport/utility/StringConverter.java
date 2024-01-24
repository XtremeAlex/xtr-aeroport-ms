package com.xtremealex.aeroport.utility;


import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class StringConverter implements IStringConverter{

    public String getId(Long value) {
        return value != null ? Base64.getEncoder().encodeToString(value.toString().getBytes()) : "null";
    }

    public Long setId(String source) {
        if (source == null) {
            return null;
        }
        try {
            return Long.parseLong(new String(Base64.getDecoder().decode(source)));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("L'ID stringa non è un valido.");
        }
    }
}
