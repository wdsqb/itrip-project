package com.hzl.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : json转换工具类
 * @date : 2020-11-16 15:07
 */
public class JsonUtil {

    /**
     * 将object转换成json字符串
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String objectToJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        json = objectMapper.writeValueAsString(object);
        return json;
    }

    /**
     * 将json字符串转换成t对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T jsonStringToObject(String json,Class<T> tClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }
}
