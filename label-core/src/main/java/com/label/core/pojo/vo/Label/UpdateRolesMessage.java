package com.label.core.pojo.vo.Label;

import lombok.Data;

@Data
public class UpdateRolesMessage {
    //当前选择的管理团队
    private String currentTeam;
    //当前角色
    private String currentRole;
    //回显用户下拉模块
    private String roleType;
    //需要修改的用户key
    private String userKey;

}
