package com.label.core.service.impl;

import com.label.common.util.GetLocalDataTime;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.mapper.TeamMapper;
import com.label.core.pojo.vo.admin.ManagerItem;
import com.label.core.pojo.vo.team.AllocationMissionReq;
import com.label.core.pojo.vo.team.SaveOrUpDateTeamReq;
import com.label.core.pojo.vo.team.TeamItem;
import com.label.core.service.LabelDataService;
import com.label.core.service.TeamService;
import com.label.core.service.UserInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LabelDataService labelDataService;

    @Override
    public List<TeamItem> getAllTeam() {
        return teamMapper.getAllTeam();
    }

    @Override
    public void saveOrUpDateTeam(SaveOrUpDateTeamReq req) {
        String managerName = String.join(",",req.getManagerName());
        String managerKey = String.join(",",req.getManagerKey());
        if("insert".equals(req.getType())){
            //团队名称查
            String teamKey = SnowflakeIdUtil.getId();
            teamMapper.saveOrUpDateTeam(managerKey,managerName,req,teamKey, GetLocalDataTime.getTime());
            for(String userKey:req.getIncreased()){
                userInfoService.addManagerKey(userKey,teamKey);
            }
        }else {
            teamMapper.updateTeam(managerKey,managerName,req);
            for(String userKey:req.getIncreased()){
                userInfoService.addManagerKey(userKey,req.getTeamKey());
            }
            for(String userKey:req.getDecrease()){
                userInfoService.removeManagerKey(userKey,req.getTeamKey()+",");
            }
        }
    }

    @Override
    public Long checkNameRepeat(String teamName) {
        return teamMapper.checkNameRepeat(teamName);
    }

    @Override
    public List<ManagerItem> getManagerByTeamKey(String teamKey) {
       return teamMapper.getManagerByTeamKey(teamKey);

    }

    @Override
    public TeamItem getTeamByKey(String teamKey) {
       return teamMapper.getTeamByKey(teamKey).get(0);
    }

    @Override
    public void allocationMission(AllocationMissionReq req) {
        //获取需要标注的音频总数
        int count = labelDataService.getAudioCountByMissionKey(req.getMissionKey());
        //分配标注员
        //每个人先分配音频数
        int labelerCount = req.getLabeler().size();
        int labelSize = count/labelerCount;
        int labelLast = count%labelerCount;
        for(int i = 0;i<labelerCount;i++){
            String name = userInfoService.getUserNameByKey(req.getLabeler().get(i));
            teamMapper.allocationLabeler(req.getLabeler().get(i),i<labelLast?labelSize+1:labelSize,name);
        }
        //质检员需要分配的条数
        int checkerCount = req.getChecker().size();
        int checkSize = count/checkerCount;
        int checkLast = count%checkerCount;
        for(int i = 0;i<checkerCount;i++){
            String name = userInfoService.getUserNameByKey(req.getLabeler().get(i));
            teamMapper.allocationChecker(req.getChecker().get(i),i<=checkLast?checkSize:checkSize+1,name);
        }
    }

    @Override
    public String getTeamNameByKey(String item) {
        return teamMapper.getTeamNameByKey(item).get(0);

    }
}
