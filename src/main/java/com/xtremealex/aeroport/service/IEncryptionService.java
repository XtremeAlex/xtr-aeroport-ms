package com.xtremealex.aeroport.service;

public interface IEncryptionService {

    String encode(String value);

    String encode(Long value);

    String decodeToString(String value);

    Long decodeToLong(String value);

}
