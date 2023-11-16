package com.label.core.pojo.vo.project;

import lombok.Data;

@Data
public class MissionList {
    //任务名称
    private String missionName;
    //所属团队名称
    private String teamName;
    //总条数
    private String total;
    //通过条数
    private String passCount;
    //创建时间
    private String createTime;
    //key
    private String key;
    //所属团队key
    private String teamKey;
    //项目key
    private String projectKey;
}
