package com.label.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.label.core.controller.Model;
import com.label.core.mapper.LabelDataMapper;
import com.label.core.pojo.vo.Label.*;

import com.label.core.pojo.vo.model.SaveModelDataReq;
import com.label.core.pojo.vo.project.MissionList;
import com.label.core.service.LabelDataService;
import com.label.core.service.ModelService;
import com.label.core.service.ProjectService;
import com.label.core.util.*;
import com.mysql.cj.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Log4j2
public class LabelDataServiceImpl extends ServiceImpl<LabelDataMapper, LabelDataItem> implements LabelDataService {
    @Autowired
    private
    LabelDataMapper labelDataMapper;

    @Autowired
    ModelService modelService;

    @Autowired
    ProjectService projectService;

    @Override
    public Page<LabelDataItem> getMusicLabelPageList(Page<LabelDataItem> page, String UserId) {
        Page<LabelDataItem> labelPage = new Page<>();
        QueryWrapper<LabelDataItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_userId", UserId);
        labelPage = baseMapper.selectPage(page, queryWrapper);
        return labelPage;
    }

    @Override
    public String getLabelAudioDataByKey(String key) {
        String url = labelDataMapper.getLabelAudioDataByKey(key);
        if (StringUtils.isNullOrEmpty(url)) {
            return "";
        }
        return url;
    }

    @Override
    public boolean handleCommitAudioData(AudioLabelDataCommitReq req) {

        labelDataMapper.remove(req.getKey());
        labelDataMapper.batchInsert(req.getKey(), req.getCommitList());
        if ("submit".equals(req.getType())) {
            labelDataMapper.upData(req.getText(), req.getKey());
        }

        return true;
    }

    @Override
    public String getSaveEditDataByKey(String key) {
        return labelDataMapper.getSaveEditData(key);
    }

    @Override
    public boolean saveLabelData(String key, String json) {
        try {
            labelDataMapper.saveLabelData(key, json);
        } catch (Exception e) {
            log.error("更新保存的标注失败！");
            return false;
        }
        return true;

    }

    @Override
    public List<FileTransferItem> exportExcelDataByKey(ExportExcelDataReq req) throws IOException, InterruptedException {
        //测试数据
//        String model_json =  modelService.getModelByKey("516027987037130752");
//        String data_json = labelDataMapper.getSaveEditData("123456788");
//        String path1= "D://";
//        XmlDataItem xmlDataItem = new XmlDataItem();
//        xmlDataItem.setDataJson(data_json);
//        xmlDataItem.setPath(path1);
//        List<XmlDataItem> list = new ArrayList<>();
//        list.add(xmlDataItem);
        //测试数据
        List<FileTransferItem> pathList = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(req.getMissionKey())) {
            String missionName = projectService.getMissionByKey(req.getMissionKey(),null).getMissionName();
            pathList.add(exportExcelDataByMissionKey(req.getMissionKey(), missionName));
        } else if (!StringUtils.isNullOrEmpty(req.getProjectKey())) {
            //获取项目下所有任务key
            for (MissionList mission : projectService.getMissionListByProjectKey(req.getProjectKey())) {
                String missionKey = mission.getKey();
                String missionName = mission.getMissionName();
                pathList.add(exportExcelDataByMissionKey(missionKey, missionName));
            }
        }
        return pathList;
    }

    @Override
    public List<AudioDataItem> getAudioByMissionKey(String missionKey) {
        return labelDataMapper.getAudioByMissionKey(missionKey);
    }

    @Override
    public int getAudioCountByMissionKey(String missionKey) {
        return labelDataMapper.getAudioCountByMissionKey(missionKey);
    }

    // 单个任务导出，返回Excel存储地址
    private FileTransferItem exportExcelDataByMissionKey(String missionKey, String missionName) throws IOException, InterruptedException {
        FileTransferItem fileTransferItem = new FileTransferItem();
        String savePath = "E://xml/" + missionName + ".xlsx";
        SaveModelDataReq model = new SaveModelDataReq();
        //音频数据
        List<LabelDataMessage> dataMessageList = new ArrayList<>();
        //获取任务
        List<XmlDataItem> list = labelDataMapper.getLabelJsonDataByMissionKey(missionKey);

        //获取任务名称

//        MissionList mission = projectService.getMissionByKey(missionKey);
        // 存储地址


        //转为objet对象,调用
        for (XmlDataItem item : list) {
            String path = item.getPath();
            String dataJson = item.getDataJson();
            LabelDataMessage dataMessage = new LabelDataMessage();
            dataMessage.setPath(path);
            dataMessage.setItem(JsonToObject.action(dataJson, SaveLabelDataRes.class));
            dataMessageList.add(dataMessage);
        }
//        获取此项目应用的模板
        model = JsonToObject.action(labelDataMapper.getModelByMissionKey(missionKey), SaveModelDataReq.class);
        ThreadExportExcel.ThreadAction(dataMessageList, model, savePath);
        fileTransferItem.setPath(savePath);
        fileTransferItem.setFileName(missionName);
        return fileTransferItem;
    }
}
