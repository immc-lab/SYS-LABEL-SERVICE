package com.label.core.pojo.vo.team;

import lombok.Data;

import java.util.List;
@Data
public class AllocationMissionReq {
    //标注员列表
    private List<String> labeler;
    //质检员列表
    private List<String> checker;
    //r任务唯一标志
    private String missionKey;
}
