package com.label.core.pojo.vo.Label;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SaveLabelDataAreaItem {
    @JsonProperty("id")
    private String id;

    @JsonProperty("saveData")
    private List<SaveLabelDataItem> saveData;
}
