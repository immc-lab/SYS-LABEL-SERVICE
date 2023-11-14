package com.label.core.pojo.vo.project;

import lombok.Data;

import java.util.List;

@Data
public class GetProjectListRes {
    private List<SaveProjectReq> list;
}
