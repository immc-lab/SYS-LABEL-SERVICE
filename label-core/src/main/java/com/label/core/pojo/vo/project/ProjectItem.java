package com.label.core.pojo.vo.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProjectItem {

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
    //唯一id
    @JsonProperty("projectKey")
    private String key;
    //创建人
    private String creator;
    //通过条数
    private String pass;
    // 未通过条数
    private String nopass;

    //总条数
    private String total;


}
