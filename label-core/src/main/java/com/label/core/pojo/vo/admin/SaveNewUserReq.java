package com.label.core.pojo.vo.admin;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SaveNewUserReq {

    //账号
    private String account;
    // 姓名
    private String username;
    // 密码
    private String password;
    //更新时提供key
    private String user_key;
    //用户姓名
    //更新或者插入
    private String type;
    // 角色  1 项目主管 2 团队管理员  3 质检员 4 标注员
    private List<String> roles = new ArrayList<>();
    //如果是管理员，可以选择器管理多个团队
    private List<String> manageTeam = new ArrayList<>();
    //如果是标注员，可以有多个团队
    private List<String> belongTeam = new ArrayList<>();
    //所属团队名称
    private List<String> belongTeamName = new ArrayList<>();
    //管理团队名称
    private List<String> manageTeamName = new ArrayList<>();
    //角色名称
    private List<String> rolesName = new ArrayList<>();


}
