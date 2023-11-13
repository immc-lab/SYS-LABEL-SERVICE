package com.label.core.pojo.vo.Label;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SaveLabelDataRes implements Serializable {
    private String key;

    private List<SaveLabelDataAreaItem> areaSaveData;

    private List<SaveLabelDataItem> globalSaveData ;
}
