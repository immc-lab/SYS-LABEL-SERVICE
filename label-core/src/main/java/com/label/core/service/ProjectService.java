package com.label.core.service;

import com.label.core.pojo.vo.project.AudioDataItem;
import com.label.core.pojo.vo.project.MissionList;
import com.label.core.pojo.vo.project.SaveNewMissionReq;
import com.label.core.pojo.vo.project.SaveProjectReq;

import java.util.List;

public interface ProjectService {

    public boolean saveProject(SaveProjectReq req,String key);

    public List<SaveProjectReq> getProjectList();

    public void saveAudioDataByKey(List<AudioDataItem> item);

    List<MissionList> getMissionListByProjectKey(String projectKey);

    boolean saveNewMission(SaveNewMissionReq req,String key);

    void updateProjectCount(long count,String key);

    void updateMissionCount(long count,String key);

    int checkNameReapet(String missionName);

    void updateMission(SaveNewMissionReq req);

    void deleteMissionByKey(String missionKey);

    void updateProject(SaveProjectReq req);

    MissionList getMissionByKey(String key,String teamKey);

    List<MissionList> getMissionByUserKey(String userKey, String type,String teamKey);
}
