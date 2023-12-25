package com.zero.iweiojbackend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Json 转化工具类
 *
 * @author ZERO
 * @date 2023/1/27
 */
public class JsonUtil {

    /**
     * 返回 JSON 序列
     * @param object 预处理对象
     * @return JSON 序列
     */
    public static String toJsonString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String res;
        try {
            res = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return  res;
    }

    public static <T> T toObject(String value, Class<T> zClass) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        T result;
        try {
            result = mapper.readValue(value, zClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        return result;
    }

}
