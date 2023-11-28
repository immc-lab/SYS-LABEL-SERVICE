package com.label.core.pojo.vo.admin;

import lombok.Data;

import java.util.List;

@Data
public class GetAllUserByTeamKeyRes {

    private List<UserItem> list;

    private int userCount;
}
