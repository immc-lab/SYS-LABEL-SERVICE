package com.label.core.pojo.vo.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SaveProjectReq {

    //项目名称
   @JsonProperty("projectName")
    private String name;
   //开始时间
    private String startTime;
   //结束时间
    private String endTime;
    //项目类型
    private String projectType;
   //项目地区
    private String projectArea;
   //创建人
    private String creator;

    private String key;
    //类型，看是修还是新增
    private String type;
}
