package com.label.core.pojo.vo.Label;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@TableName("tb_label_data")
public class LabelDataItem {

    public String format;

    public String creator;
    @JsonProperty("key")
    public String id;

    public String createTime;
}
