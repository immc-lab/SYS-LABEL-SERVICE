package com.label.core.pojo.vo.Label;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class commitItem {

    private String id;
    private String labelText;
    private String talk;
    private String beginTime;
    private String endTime;
}
