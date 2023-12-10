package com.label.core.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data

//name?: string;
//        avatar?: string;
//        userid?: string;
//        email?: string;
//        signature?: string;
//        title?: string;
//        group?: string;
//        tags?: { key?: string; label?: string }[];
//        notifyCount?: number;
//        unreadCount?: number;
//        country?: string;
//        access?: string;
//        geographic?: {
//        province?: { label?: string; key?: string };
//        city?: { label?: string; key?: string };
//        };
//        address?: string;
//        phone?: string;
public class CurrentUserMessage {
    //属于哪个团队
    private String  belongTeamKey;
    //属于哪个团队名称
    private String  belongTeamName;
    // 账户状态  1 正常，0，禁用
    private String  state;
    //账户类型  0 超管，1，项目管理员,2 团队管理员, 3，质检员,4标注员 多角色用户可以以逗号隔开
    private String roles;
    //角色名称
    private String rolesName;
    //团队管理员唯一标志
    private String manageTeamKey;
    //姓名
    private String name;
    //账号信息
    private String userAccount;
    //默认当前角色群权限最大的角色
    private String currentRole;
    //团队key对应便于切换角色团队
    private List<ManagerTeamItem>  managerTeamItems;
    // 属于哪个团队
    private  List<ManagerTeamItem> belongTeamItems;

}
