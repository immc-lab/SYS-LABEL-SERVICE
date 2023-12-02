package com.label.core.service.impl;

import com.label.common.util.GetLocalDataTime;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.mapper.TeamMapper;
import com.label.core.pojo.vo.admin.ManagerItem;
import com.label.core.pojo.vo.team.SaveOrUpDateTeamReq;
import com.label.core.pojo.vo.team.TeamItem;
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
}
