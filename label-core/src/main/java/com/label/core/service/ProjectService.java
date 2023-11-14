package com.label.core.service;

import com.label.core.pojo.vo.project.SaveProjectReq;

import java.util.List;

public interface ProjectService {

    public boolean saveProject(SaveProjectReq req,String key);

    public List<SaveProjectReq> getProjectList();
}
