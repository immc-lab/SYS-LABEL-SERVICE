package com.label.core.pojo.vo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChildrenItem implements Serializable {
    private String key;
    private String id;
    private boolean unAdd;
    private boolean isChildren;
    private boolean isNecessary;
    //name名称
    private String textValue;
    private String typeValue;
    private String[] tabValue;
    private List<TabItem> tabOptions = new ArrayList<>();
    private List<String> linkValue = new ArrayList<>();
    private List<TabItem> linkOptions = new ArrayList<>();
}
