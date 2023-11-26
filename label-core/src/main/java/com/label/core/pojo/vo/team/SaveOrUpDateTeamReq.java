package com.label.core.pojo.vo.team;

import lombok.Data;

import java.util.List;

@Data
public class SaveOrUpDateTeamReq {
    private String teamName;

    private List<String> managerName;

    private String creator;

    private String teamMessage;
    // 插入还是更新 “insert” 插入，“update” 更新
    private String type;

    private List<String> managerKey;

}
