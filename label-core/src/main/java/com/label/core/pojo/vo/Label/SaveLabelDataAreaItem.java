package com.label.core.pojo.vo.Label;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SaveLabelDataAreaItem {
    @JsonProperty("id")
    private String id;

    @JsonProperty("startTime")
    private String startTime;

    @JsonProperty("endTime")
    private String endTime;

    @JsonProperty("saveData")
    private List<SaveLabelDataItem> saveData;
}
