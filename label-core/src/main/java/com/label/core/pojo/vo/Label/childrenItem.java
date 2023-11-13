package com.label.core.pojo.vo.Label;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class childrenItem {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("label")
    private String label;

    @JsonProperty("linkValue")
    private List<String> linkValue;

    @JsonProperty("value")
    private List<String> value;
}
