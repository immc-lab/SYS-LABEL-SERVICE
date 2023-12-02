package com.label.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.label.core.pojo.vo.admin.ManagerItem;
import com.label.core.pojo.vo.team.SaveOrUpDateTeamReq;
import com.label.core.pojo.vo.team.TeamItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper extends BaseMapper<TeamItem> {

    List<TeamItem> getAllTeam();

    Long checkNameRepeat(@Param("teamName") String teamName);

    void saveOrUpDateTeam(@Param("managerKey") String managerKey,
                          @Param("managerName")String managerName,
                          @Param("req")SaveOrUpDateTeamReq req,
                          @Param("key") String key,
                          @Param("createTime") String createTime
                          );


    void updateTeam(@Param("managerKey") String managerKey,@Param("managerName")String managerName,@Param("req") SaveOrUpDateTeamReq req);

    List<ManagerItem> getManagerByTeamKey(@Param("teamKey") String teamKey);

    List<TeamItem> getTeamByKey(@Param("teamKey") String teamKey);
}
