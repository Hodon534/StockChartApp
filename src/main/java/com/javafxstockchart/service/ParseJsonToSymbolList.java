package com.javafxstockchart.service;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javafxstockchart.model.Pojo.TimeSeries.PojoTimeSeries;

public class ParseJsonToSymbolList {
    private static ObjectMapper objectMapper = createMapper();
    private static ObjectMapper createMapper(){
        ObjectMapper initialObjectMapper = new ObjectMapper();
        initialObjectMapper.findAndRegisterModules();
        return initialObjectMapper;
    }

    public static PojoTimeSeries parseJsonToObject(String input) throws IOException {
        PojoTimeSeries pojo = objectMapper.readValue(input, PojoTimeSeries.class);
        return pojo;
    }
}



