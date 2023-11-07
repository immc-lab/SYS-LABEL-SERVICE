package com.label.core.pojo.vo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SaveModelDataReq implements Serializable {
    //看是更新还是新插入
    @JsonProperty("type")
    private String type;
    //模型名称
    @JsonProperty("name")
    private String name;
    //若是更新则有key
    @JsonProperty("key")
    private String key;
    //是否区分区域
    @JsonProperty("area")
    private String area;
    //全局标参数
    @JsonProperty("globalData")
    private List<DataItem> globalData;
    //局域标注参数
    @JsonProperty("areaData")
    private List<DataItem> areaData;
}
