package com.label.core.pojo.vo.admin;

import lombok.Data;

@Data
public class UserItem {

    private String userAccount;

    private String name;

    private String userKey;

    private String id;

    private String state;

    private String belongTeamKey;

    private String manageTeamKey;

    private String belongTeamName;

    private String manageTeamName = "";

    private String roles;

    private String rolesName = "";

    private String createTime;

    private String lastLoginTime;

    private String ip;



}
