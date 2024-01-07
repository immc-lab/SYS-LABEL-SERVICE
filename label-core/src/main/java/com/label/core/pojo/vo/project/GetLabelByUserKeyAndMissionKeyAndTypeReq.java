package com.label.core.pojo.vo.project;

import lombok.Data;

@Data
public class GetLabelByUserKeyAndMissionKeyAndTypeReq {

    private String userKey;

    private String type;

    private String missionKey;

}
