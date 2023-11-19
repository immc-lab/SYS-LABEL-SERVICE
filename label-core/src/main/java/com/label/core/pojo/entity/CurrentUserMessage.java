package com.label.core.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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

    private String  teamKey;
    // 账户状态  1 正常，0，禁用
    private String  state;
    //账户类型  0 超管，1，项目管理员,2 团队管理员, 3，质检员,4标注员 多角色用户可以以逗号隔开
    private String type;

    private String name;

    private String userAccount;
}
