package com.label.core.pojo.vo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;



@Data
public class Model {
    @JsonProperty("key")
    private String modelId;

    private String createTime;

    private String updateTime;

    private String modelName;
}
