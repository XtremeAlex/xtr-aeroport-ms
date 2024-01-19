package com.xtremealex.aeroport.mapper;

import org.modelmapper.AbstractConverter;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class ListIdEncodeConverter extends AbstractConverter<List<Long>, List<String>> {

    @Override
    protected List<String> convert(List<Long> source) {
        if (source == null) {
            return null;
        }

        return source.stream()
                .map(id -> Base64.getEncoder().encodeToString(id.toString().getBytes()))
                .collect(Collectors.toList());
    }
}