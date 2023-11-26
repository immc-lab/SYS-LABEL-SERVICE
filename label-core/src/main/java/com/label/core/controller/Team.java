package com.label.core.controller;

import com.label.common.result.R;
import com.label.common.result.ResponseEnum;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.pojo.vo.project.SaveProjectReq;
import com.label.core.pojo.vo.team.SaveOrUpDateTeamReq;
import com.label.core.pojo.vo.team.TeamItem;
import com.label.core.service.TeamService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/team")
public class Team {

    @Resource
    private TeamService teamService;
    @PostMapping("/core/getAllTeam")
    public R getAllTeam() {
        List<TeamItem> teamList = new ArrayList<>();
        try {
            teamList = teamService.getAllTeam();
        } catch (Exception e) {
            log.error("获取项目失败！");
            return R.error();
        }
        return R.ok().data(teamList);
    }


    @PostMapping("/core/saveOrUpDateTeam")
    public R saveOrUpDateTeam(@RequestBody SaveOrUpDateTeamReq req) {
        //生成key
        try {
            if("insert".equals(req.getType())) {
                if (teamService.checkNameRepeat(req.getTeamName()) >= 1) {
                    return R.setResult(ResponseEnum.ACCOUNT_REPEAT);
                }else {
                    teamService.saveOrUpDateTeam(req);
                }
            }else {
                teamService.saveOrUpDateTeam(req);
            }
        } catch (Exception e) {
            log.error("保存团队失败");
            return R.error();
        }
        return R.ok();
    }
}
