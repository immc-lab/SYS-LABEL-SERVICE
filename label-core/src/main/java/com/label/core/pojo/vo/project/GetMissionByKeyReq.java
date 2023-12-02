package com.label.core.pojo.vo.project;

import lombok.Data;

@Data
public class GetMissionByKeyReq {
    // 任务对应的key
    private String key;
    //团队对应的key
    private String teamKey;


}
