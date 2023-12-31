package com.label.core.service;

import com.label.core.pojo.vo.admin.ManagerItem;
import com.label.core.pojo.vo.team.AllocationMissionReq;
import com.label.core.pojo.vo.team.SaveOrUpDateTeamReq;
import com.label.core.pojo.vo.team.TeamItem;

import java.util.List;

public interface TeamService {


    List<TeamItem> getAllTeam();

    void saveOrUpDateTeam(SaveOrUpDateTeamReq req);

    Long checkNameRepeat(String teamName);

    List<ManagerItem> getManagerByTeamKey(String teamKey);

    TeamItem getTeamByKey(String teamKey);

    void allocationMission(AllocationMissionReq req);

    String getTeamNameByKey(String item);
}
