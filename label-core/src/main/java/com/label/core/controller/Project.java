package com.label.core.controller;

import com.label.common.result.R;
import com.label.common.result.ResponseEnum;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import com.label.core.pojo.vo.project.*;
import com.label.core.service.ProjectService;
import com.mysql.cj.util.StringUtils;
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

    //新建项目或者更新项目
    @PostMapping("/core/saveProjectData")
    public R saveProjectData(@RequestBody SaveProjectReq req) {
        //生成key
        try {
            if ("insert".equals(req.getType())) {
                int count = projectService.checkNameReapet(req.getName());
                if (count >= 1) {
                    return R.setResult(ResponseEnum.ACCOUNT_REPEAT);
                } else {
                    String key = SnowflakeIdUtil.getId();
                    projectService.saveProject(req, key);
                }

            } else {
                projectService.updateProject(req);
            }

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
        }
        try {
            String path = folderName + key + "." + req.getFormat();
            String name = key + "." + req.getFormat();
            String base64DataWithoutPrefix = req.getAudioBase64().substring(req.getAudioBase64().indexOf(",") + 1);
            byte[] mp3Data = decoder.decodeBuffer(base64DataWithoutPrefix);
            fos = new FileOutputStream(path);
            fos.write(mp3Data);
            fos.close();
            AudioDataItem item = new AudioDataItem();
            item.setPath(path);
            item.setAudioName(name);
            item.setProjectKey(req.getProjectKey());
            item.setFormat(req.getFormat());
            item.setMissionKey(req.getMissionKey());
            item.setModelKey(req.getModelKey());
            item.setKey(key);
            int succeedCount = 0;
            List<AudioDataItem> audioList = null;
            if (request.getSession().getAttribute("succeedCount") != null) {
                succeedCount = (int) request.getSession().getAttribute("succeedCount");
            }

            if (request.getSession().getAttribute("audioList") != null) {
                audioList = (List<AudioDataItem>) request.getSession().getAttribute("audioList");
            }
            if (audioList != null) {
                audioList.add(item);
                request.getSession().setAttribute("audioList", audioList);
                request.getSession().setAttribute("succeedCount", succeedCount + 1);
            } else {
                List<AudioDataItem> newAudioList = new ArrayList<>();
                newAudioList.add(item);
                request.getSession().setAttribute("audioList", newAudioList);
                request.getSession().setAttribute("succeedCount", 1);
            }

            if ("1".equals(req.getLast())) {
                projectService.saveAudioDataByKey((List<AudioDataItem>) (request.getSession().getAttribute("audioList")));
                return R.ok().data(request.getSession().getAttribute("succeedCount"));
            }

        } catch (IOException ignored) {
        } catch (Exception e) {
            return R.error();
        } finally {
            if (fos != null) {
                fos.close();
            }

        }
        return R.ok();
    }

    //新建或更新任务
    @PostMapping("/core/saveNewMission")
    public R saveNewMission(@RequestBody SaveNewMissionReq req) {
        String key;
        int count = projectService.checkNameReapet(req.getMissionName());
        if (count >= 1) {
            return R.setResult(ResponseEnum.ACCOUNT_REPEAT);
        } else {
            key = SnowflakeIdUtil.getId();
            projectService.saveNewMission(req, key);
        }
        return R.ok().data(key);
    }


    @PostMapping("/core/deleteMissionByKey")
    public R deleteMissionByKey(DeleteMissionByKeyReq req) {
        try {
            projectService.deleteMissionByKey(req.getMissionKey());
        } catch (Exception e) {
            log.error("删除任务失败！");
            return R.error();
        }
        return R.ok();
    }


    //通过项目key获取任务列表
    @PostMapping("/core/getMissionListByProjectKey")
    public R saveNewMission(@RequestBody getMissionListReq req) {
        List<MissionList> list = new ArrayList<>();
        try {
            list = projectService.getMissionListByProjectKey(req.getProjectKey());
        } catch (Exception e) {
            log.error("获取任务列表失败！");
            return R.error();
        }
        return R.ok().data(list);
    }

    @PostMapping("/core/getMissionByKey")
    public R getMissionByKey(@RequestBody GetMissionByKeyReq req) {
        MissionList mission = new MissionList();
        try {
            mission = projectService.getMissionByKey(req.getKey(),req.getTeamKey());
        } catch (Exception e) {
            log.error("获取任务失败失败！");
            log.error(e);
            return R.error();
        }
        return R.ok().data(mission);
    }

    //删除任务或者项目

    
}