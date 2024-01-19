package com.xtremealex.aeroport.service.impl;

import com.xtremealex.aeroport.service.IEncryptionService;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncryptionService implements IEncryptionService {
    @Override
    public String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    @Override
    public String encode(Long value) {
        return encode(value.toString());
    }

    @Override
    public String decodeToString(String value) {
        if (value == null) {
            return null;
        }
        return new String(Base64.getDecoder().decode(value));

    }

    @Override
    public Long decodeToLong(String value) {
        return Long.parseLong(decodeToString(value));
    }
}
