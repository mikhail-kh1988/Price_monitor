package com.pricemonitor.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONConverter {

    private static ObjectMapper objectMapper;
    private static Object obj;

    public JSONConverter(Object object) {
        this.objectMapper = new ObjectMapper();
        this.obj = object;
    }

    public String getJSON(){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
