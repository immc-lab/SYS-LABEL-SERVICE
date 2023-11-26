package com.label.core.pojo.vo.project;

import lombok.Data;

@Data
public class SaveNewMissionReq {

    //任务，名称
    private String missionName;

    //任务分配的团队key
    private String teamKey;

    //团队名称
    private String teamName;

    //对应的项目key
    private String projectKey;

   //创建人
    private String creator;

    private String modelName;

    private String modelKey;

    private String type;

}
