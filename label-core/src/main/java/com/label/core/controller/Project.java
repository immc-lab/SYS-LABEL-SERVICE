package com.label.core.controller;

import com.label.common.result.R;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import com.label.core.pojo.vo.project.SaveProjectReq;
import com.label.core.service.ProjectService;
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
@RequestMapping("/project")
public class Project {
        @Resource
        private ProjectService projectService ;
        //上传项目列表
        @PostMapping("/core/saveProjectData")
        public R saveProjectData(@RequestBody SaveProjectReq req){
            //生成key
            try{
                String key = SnowflakeIdUtil.getId();
                projectService.saveProject(req,key);
            }catch (Exception e){
                log.error("上传项目失败！");
                return R.error();
            }
            return R.ok();
        }
//      获取项目列表
        @PostMapping("/core/getProjectList")
        public R getProjectList(){
            List<SaveProjectReq> list = new ArrayList<>();
            try {
                list = projectService.getProjectList();
            }catch (Exception e){
                log.error("获取项目列表失败！");
                return R.error();
            }
            return R.ok().data(list);
        }
}

