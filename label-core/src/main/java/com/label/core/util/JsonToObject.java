package com.label.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.label.core.pojo.vo.Label.SaveLabelDataRes;
import com.mysql.cj.util.StringUtils;
import org.apache.poi.ss.formula.functions.T;

public class JsonToObject {
    public static <T> T action(String json,Class<T> type) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, type);
    }
}
