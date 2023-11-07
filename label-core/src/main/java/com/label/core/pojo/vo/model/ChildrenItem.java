package com.label.core.pojo.vo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChildrenItem implements Serializable {
    @JsonProperty("key")
    private String key;

    @JsonProperty("id")
    private String id;

    @JsonProperty("unAdd")
    private boolean unAdd;

    @JsonProperty("isChildren")
    private boolean isChildren;

    @JsonProperty("isNecessary")
    private boolean isNecessary;

    @JsonProperty("textValue")
    private String textValue;

    @JsonProperty("typeValue")
    private String typeValue;

    @JsonProperty("tabValue")
    private String[] tabValue;

    @JsonProperty("tabOptions")
    private List<TabItem> tabOptions = new ArrayList<>();

    @JsonProperty("linkValue")
    private List<String> linkValue = new ArrayList<>();

    @JsonProperty("linkOptions")
    private List<TabItem> linkOptions = new ArrayList<>();
}
