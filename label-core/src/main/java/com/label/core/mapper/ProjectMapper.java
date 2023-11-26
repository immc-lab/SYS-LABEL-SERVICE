package com.label.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.label.core.pojo.vo.project.AudioDataItem;
import com.label.core.pojo.vo.project.MissionList;
import com.label.core.pojo.vo.project.SaveNewMissionReq;
import com.label.core.pojo.vo.project.SaveProjectReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<SaveProjectReq> {

    Long updateProject (@Param("req") SaveProjectReq req,@Param("key") String key);

    List<SaveProjectReq> getProjectList();


    List<MissionList> getMissionListByProjectKey(@Param("projectKey") String projectKey);

    Long saveNewMission(@Param("req") SaveNewMissionReq req, @Param("key") String key,@Param("createTime")String createTime);

    Long saveAudioDataBykey(List<AudioDataItem> item);

    Long updateProjectTotal(@Param("count") long count,@Param("key") String key);

    Long updateMissionCount(@Param("count")long count,@Param("key") String key);

    Long updateProjectPass(@Param("count")long count,@Param("key") String key);

    Long updateMissionPass(@Param("count")long count,@Param("key") String key);

    int checkReapet(@Param("name") String name);

    void updateMission(@Param("req") SaveNewMissionReq req);

    void deleteMissionByKey(@Param("missionKey") String missionKey);

    void updateProjectByKey(@Param("req") SaveProjectReq req);

    List<MissionList> getMissionByKey(@Param("key") String key);
}
