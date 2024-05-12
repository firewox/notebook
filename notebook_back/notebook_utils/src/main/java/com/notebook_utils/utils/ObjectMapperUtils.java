package com.notebook_utils.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @Author: Liyutian
 * @Date:Create：in 2022/5/27 9:08
 */
public final class ObjectMapperUtils {
    public static final ObjectMapper mapper = new ObjectMapper();

    private static JsonNode jsonNode;

    public static JsonNode readTree(String info){
        try {
            jsonNode = mapper.readTree(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonNode;
    }
}
