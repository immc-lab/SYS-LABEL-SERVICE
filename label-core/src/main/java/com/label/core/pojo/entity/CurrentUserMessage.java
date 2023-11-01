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

    @JsonProperty("userid")
    String userId;
    String avatar;
    String email;
    String signature;
    String title;
    String group;
    String country;
    String access;
    String address;
    String phone;
    String name;
}
