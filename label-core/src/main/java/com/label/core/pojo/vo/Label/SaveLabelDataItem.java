package com.label.core.pojo.vo.Label;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.label.core.pojo.vo.model.TabItem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SaveLabelDataItem implements Serializable {

    @JsonProperty("key")
    private String key;

    @JsonProperty("id")
    private String id;

    @JsonProperty("value")
    private List<String> value;

    @JsonProperty("label")
    private String label;

    @JsonProperty("type")
    private String type;

    @JsonProperty("tabOptions")
    private List<TabItem> tabOptions;

    @JsonProperty("children")
    private List<childrenItem> children;




}
