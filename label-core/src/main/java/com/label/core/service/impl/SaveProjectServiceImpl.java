package com.label.core.service.impl;

import com.label.core.mapper.ProjectMapper;
import com.label.core.pojo.vo.project.AudioDataItem;
import com.label.core.pojo.vo.project.MissionList;
import com.label.core.pojo.vo.project.SaveNewMissionReq;
import com.label.core.pojo.vo.project.SaveProjectReq;
import com.label.core.service.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public void saveAudioDataByKey(List<AudioDataItem> item) {
        projectMapper.saveAudioDataBykey(item);
    }

    @Override
    public List<MissionList> getMissionListByProjectKey(String projectKey) {
        return projectMapper.getMissionListByProjectKey(projectKey);
    }

    @Override
    public boolean saveNewMission(SaveNewMissionReq req,String key) {
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);
            projectMapper.saveNewMission(req, key,formattedDateTime);
        }catch (Exception e){
            log.error("新建任务失败！");
            return false;
        }
        return true;
    }

    @Override
    public void updateProjectCount(long count,String key) {
        projectMapper.updateProjectTotal(count,key);

    }

    @Override
    public void updateMissionCount(long count,String key) {
        projectMapper.updateMissionCount(count,key);
    }

    @Override
    public int checkNameReapet(String name) {
       return projectMapper.checkReapet(name);
    }

    @Override
    public void updateMission(SaveNewMissionReq req) {
        projectMapper.updateMission(req);
    }

    @Override
    public void deleteMissionByKey(String missionKey) {
        projectMapper.deleteMissionByKey(missionKey);
    }

    @Override
    public void updateProject(SaveProjectReq req) {
        projectMapper.updateProjectByKey(req);
    }

    @Override
    public MissionList getMissionByKey(String key) {
        List<MissionList> missionList = projectMapper.getMissionByKey(key);
        return missionList.get(0);
    }
}
