package com.label.core.pojo.vo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.scene.control.Tab;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class DataItem implements Serializable {
    @JsonProperty("children")
    private List<ChildrenItem> children = new ArrayList<>();

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
    private List<String> tabValue = new ArrayList<>();

    @JsonProperty("tabOptions")
    private List<TabItem> tabOptions = new ArrayList<>();

}
