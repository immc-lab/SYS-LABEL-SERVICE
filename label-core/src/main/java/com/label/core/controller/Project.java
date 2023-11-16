package com.label.core.controller;

import com.label.common.result.R;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import com.label.core.pojo.vo.project.*;
import com.label.core.service.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/project")
public class Project {
    @Resource
    private ProjectService projectService;

    //上传项目列表
    @PostMapping("/core/saveProjectData")
    public R saveProjectData(@RequestBody SaveProjectReq req) {
        //生成key
        try {
            String key = SnowflakeIdUtil.getId();
            projectService.saveProject(req, key);
        } catch (Exception e) {
            log.error("上传项目失败！");
            return R.error();
        }
        return R.ok();
    }

    //获取项目列表
    @PostMapping("/core/getProjectList")
    public R getProjectList() {
        List<SaveProjectReq> list = new ArrayList<>();
        try {
            list = projectService.getProjectList();
        } catch (Exception e) {
            log.error("获取项目列表失败！");
            return R.error();
        }
        return R.ok().data(list);
    }

    //单个数据保存
    @PostMapping("/core/saveProjectAudioData")
    public R saveProjectAudioData(@RequestBody SaveProjectAudioDataReq req, HttpServletRequest request) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        String key = SnowflakeIdUtil.getId();
        FileOutputStream fos = null;
        String folderName = "D:/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "/";

        // 创建文件夹
        File folder = new File(folderName);
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                return R.error();
            }
            ;
        }
        try {
            String path = folderName + key + "."+req.getFormat();
            String name = key + "."+req.getFormat();
            byte[] mp3Data = decoder.decodeBuffer(req.getAudioBase64());
            fos = new FileOutputStream(path);
            fos.write(mp3Data);
            fos.close();
            AudioDataItem item = new AudioDataItem();
            item.setPath(path);
            item.setAudioName(name);
            item.setProjectKey(req.getProjectKey());
            item.setFormat(req.getFormat());
            item.setMissionKey(req.getMissionKey());
            item.setModel_key(req.getModelKey());
            Long  succeedCount = (Long) request.getSession().getAttribute("succeedCount");
            List<AudioDataItem> audioList = (List<AudioDataItem>) request.getSession().getAttribute("audioList");
            if(audioList != null){
                audioList.add(item);
                request.getSession().setAttribute("audioList",audioList);
                request.getSession().setAttribute("succeedCount",succeedCount+1);
            }else {
                List<AudioDataItem> newAudioList = new ArrayList<>();
                newAudioList.add(item);
                request.getSession().setAttribute("audioList",newAudioList);
                request.getSession().setAttribute("succeedCount",1);
            }

            if ("1".equals(req.getLast())) {
                //保存数据，并更新任务和项目总条数
                long count = 0L;
                if(audioList != null){
                     count = (long) audioList.size();
                }
                projectService.saveAudioDataByKey(audioList);
                projectService.updateProjectCount(count,req.getProjectKey());
                projectService.updateMissionCount(count,req.getProjectKey());
                return R.ok().data(request.getSession().getAttribute("succeedCount"));
            }

        } catch (IOException ignored) {
        } catch (Exception e){
            return R.error();
        } finally {
            if (fos != null) {
                fos.close();
            }

        }


        return null;
    }

    //新建或更新任务
    @PostMapping("/core/saveNewMission")
    public R saveNewMission(SaveNewMissionReq req) {
        String key = SnowflakeIdUtil.getId();
        if(projectService.saveNewMission(req,key)){
            return R.ok();
        };
        return R.error();
    }


    //通过项目key获取任务列表
    @PostMapping("/core/getMissionListByProjectKey")
    public R saveNewMission(getMissionListReq req) {
        List<MissionList> list = new ArrayList<>();
        try {
            list = projectService.getMissionListByProjectKey(req.getProjectKey());
        } catch (Exception e) {
            log.error("获取任务列表失败！");
            return R.error();
        }
        return R.ok().data(list);
    }
}

