package com.label.core.service;

import com.label.core.pojo.vo.team.SaveOrUpDateTeamReq;
import com.label.core.pojo.vo.team.TeamItem;

import java.util.List;

public interface TeamService {


    List<TeamItem> getAllTeam();

    void saveOrUpDateTeam(SaveOrUpDateTeamReq req);

    Long checkNameRepeat(String teamName);

}
