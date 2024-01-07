package com.label.core.pojo.vo.project;

import lombok.Data;

@Data
public class GetMissionByUserKeyReq {

    private String userKey;
    //获取需要标注的人员的传 0，获取质检的标1
    private String type;
    //团队key
    private String teamKey;

}
