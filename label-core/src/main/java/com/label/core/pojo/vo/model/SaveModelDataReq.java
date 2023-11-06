package com.label.core.pojo.vo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SaveModelDataReq implements Serializable {
    //看是更新还是新插入
    private String type;
    //模型名称
    private String name;
    //若是更新则有key
    private String key;
    //全局标参数
    private List<DataItem> globalData;
    //局域标注参数
    private List<DataItem> areaData;
}
