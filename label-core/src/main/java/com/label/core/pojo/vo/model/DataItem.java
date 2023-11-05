package com.label.core.pojo.vo.model;

import javafx.scene.control.Tab;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class DataItem implements Serializable {
    private List<ChildrenItem> children = new ArrayList<>();
    private String key;
    private String id;
    private boolean unAdd;
    private boolean isChildren;
    private boolean isNecessary;
    //name名称
    private String textValue;
    private String typeValue;
    private List<String> tabValue = new ArrayList<>();
    private List<TabItem> tabOptions = new ArrayList<>();

}
