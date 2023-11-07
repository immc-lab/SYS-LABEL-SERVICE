package com.label.core.service.impl;

import com.label.core.mapper.ModelDataMapper;
import com.label.core.pojo.vo.model.GetModelByKeyRes;
import com.label.core.pojo.vo.model.Model;
import com.label.core.service.ModelService;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
@Log4j2
@Service
public class ModeServiceImpl implements ModelService {
    @Autowired
    ModelDataMapper mapper;
    @Override
    public boolean updateModel(String key, String json,String name) {
        //更新时间
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);
            Long num = mapper.updateModel(key,name,formattedDateTime, json);
        }catch (Exception e){
            log.error("模型更新失败，请查看更新时间是否正确");
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByKey(String key) {
        try {
            mapper.DeleteModelByKey(key);
        }catch (Exception e){
            log.error(e);
            log.error("模型删除失败！");
            return false;
        }
        return true;
    }

    @Override
    public boolean insertModel(String key, String name, String json) {
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);
            mapper.insertIntoModel(key, formattedDateTime, name, formattedDateTime, json);
        }catch (Exception e){
            log.error("新增模型失败！");
            log.error(e);
            return false;
        }

        return true;
    }

    @Override
    public List<Model> getAllModel() {
        return mapper.getAllModel();

    }

    @Override
    public String getModelByKey(String key) {
        return mapper.getModelByKey(key);
    }

    @Override
    public boolean setMainModel(String key) {
        try {
            mapper.setMainModel(key);
        }catch (Exception e){
            log.error("更新主模型失败！");
            return false;
        }
        return true;
    }
}
