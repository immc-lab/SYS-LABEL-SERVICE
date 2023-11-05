package com.label.core.pojo.vo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SaveModelDataReq implements Serializable {
    private List<DataItem> globalData;

    private List<DataItem> areaData;
}
