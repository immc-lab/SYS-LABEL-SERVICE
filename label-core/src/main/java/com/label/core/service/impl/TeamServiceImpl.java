package com.label.core.service.impl;

import com.label.common.util.GetLocalDataTime;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.mapper.TeamMapper;
import com.label.core.pojo.vo.team.SaveOrUpDateTeamReq;
import com.label.core.pojo.vo.team.TeamItem;
import com.label.core.service.TeamService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;


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
            teamMapper.saveOrUpDateTeam(managerKey,managerName,req, SnowflakeIdUtil.getId(), GetLocalDataTime.getTime());
        }else {
            teamMapper.updateTeam(managerKey,managerName,req);
        }
    }

    @Override
    public Long checkNameRepeat(String teamName) {
        return teamMapper.checkNameRepeat(teamName);
    }
}
