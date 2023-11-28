package com.label.core.pojo.vo.team;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SaveOrUpDateTeamReq {
    private String teamName;
    //team的唯一标志
    private String teamKey;

    private List<String> managerName;

    private String creator;

    private String teamMessage;
    // 插入还是更新 “insert” 插入，“update” 更新
    private String type;

    private List<String> managerKey;
    //增加的管理员key
    private List<String> increased = new ArrayList<>();
    //减少了管理员key
    private List<String> decrease = new ArrayList<>();

}
