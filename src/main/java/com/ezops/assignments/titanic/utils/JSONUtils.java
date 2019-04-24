package com.ezops.assignments.titanic.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * JSON related，used for javabean and json String convertion
 * @author yao.chen
 */
public class JSONUtils {
    private static Logger logger = LoggerFactory.getLogger(JSONUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * change JavaBean to Json String
     *
     * @param obj
     * @return Json String
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error While Transform JavaBean to Json: {}", e.getMessage());
        }
        return null;
    }


    /**
     *  change Json String to Java Object
     *
     * @param jsonStr
     * @param cls
     * @param <T>
     * @return  Java Object
     */
    public static <T> T readValue(String jsonStr, Class<T> cls) {
        try {
            return objectMapper.readValue(jsonStr, cls);
        } catch (IOException e) {
            logger.error("Error While Transform JsonString to JavaBean: {}\n {}", e.getMessage(),
                    e.getStackTrace());
        }
        return null;
    }

    /**
     * change Json String to Java Object with a valueType
     *
     * @param jsonStr
     * @param valueTypeRef
     * @param <T>
     * @return Java Object
     */
    public static <T> T readValue(String jsonStr, TypeReference valueTypeRef) {
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (IOException e) {
            logger.error("Error While Transform JsonString to JavaBean: {}\n {}", e.getMessage(),
                    e.getStackTrace());
        }
        return null;
    }
}
