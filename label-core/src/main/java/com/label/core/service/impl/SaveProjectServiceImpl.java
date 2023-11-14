package com.label.core.service.impl;

import com.label.core.mapper.ProjectMapper;
import com.label.core.pojo.vo.project.SaveProjectReq;
import com.label.core.service.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class SaveProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public boolean saveProject(SaveProjectReq req,String key) {
        try{
            projectMapper.updateProject(req,key);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public List<SaveProjectReq> getProjectList() {
        return  projectMapper.getProjectList();
    }
}
