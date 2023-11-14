package com.label.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.label.core.pojo.vo.project.SaveProjectReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<SaveProjectReq> {

    Long updateProject (@Param("req") SaveProjectReq req,@Param("key") String key);

    List<SaveProjectReq> getProjectList();

}
