package com.label.core.pojo.vo.project;

import lombok.Data;

@Data
public class SaveProjectReq {

    //项目名称
    private String projectName;
   //开始时间
    private String startTime;
   //结束时间
    private String endTime;
    //项目类型
    private String projectType;
   //项目地区
    private String projectArea;
}
