package com.label.core.pojo.vo.Label;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetSaveEditDataItem {
    @JsonProperty("id")
    private String labelId;
    @JsonProperty("labelText")
    private String labelAreaText;
    @JsonProperty("talk")
    private String labelTalk;
    private String beginTime;
    private String endTime;
}
