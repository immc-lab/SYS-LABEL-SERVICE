package com.label.core.pojo.vo.admin;

import lombok.Data;

@Data
public class DisableAccountKeyReq {

    private String userKey;

    private String state;
}
