package com.label.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.label.common.result.R;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.pojo.vo.model.DeleteModelByKeyReq;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import com.label.core.service.ModelService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/model")
public class Model {
    @Resource
    private ModelService modelservice ;

    //    用户注册
    @PostMapping("/core/saveModelData")
    public R SaveModelData(@RequestBody SaveModelDataReq req) throws JsonProcessingException {
        //json格式保存
        boolean ok = false;
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(req);
        if("update".equals(req.getType())&& !StringUtils.isNullOrEmpty(req.getKey())){
            ok = modelservice.updateModel(req.getKey(),json,req.getName());
        }
        if("add".equals(req.getType())){
            //雪花算法生成key
            String key = SnowflakeIdUtil.getId();
            ok = modelservice.insertModel(key,req.getName(),json);
        }
        if(ok){
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/core/getModelAll")
    public R getModelAll()  {
        List<com.label.core.pojo.vo.model.Model> models = modelservice.getAllModel();
        if(models!=null){
            return R.ok().data(models);
        }
         return R.error();
    }


//删除模型
    @PostMapping("/core/deleteModelByKey")
    public R deleteModelByKey(@RequestBody DeleteModelByKeyReq req)  {

        try {
            modelservice.deleteByKey(req.getKey());
        }catch (Exception e) {
            log.error("删除模板失败！");
            log.error(e);
            return R.error().message("删除模板失败！");
        }
        return R.ok();
    }

    @PostMapping("/core/getModelByKey")
    public R getModelByKey(@RequestBody DeleteModelByKeyReq req) {
        ObjectMapper objectMapper = new ObjectMapper();
        String modelJson;
        SaveModelDataReq res = null;
        try {
             modelJson = modelservice.getModelByKey(req.getKey());
             if(!StringUtils.isNullOrEmpty(modelJson)){
                  res = objectMapper.readValue(modelJson,SaveModelDataReq.class);
            }
        }catch (Exception e) {
            log.error("查找模板失败！");
            log.error(e);
            return R.error().message("查找模板失败！");
        }
        if(res != null){
            return R.ok().data(res);
        }
        return R.error();
    }

//设置主模型
    @PostMapping("/core/applyByKey")
    public R applyByKey(@RequestBody DeleteModelByKeyReq req)  {

        try {
            modelservice.setMainModel(req.getKey());
        }catch (Exception e) {
            log.error("更新主模板失败！");
            log.error(e);
            return R.error().message("更新主模板失败！");
        }
        return R.ok();
    }

    @PostMapping("/core/getMainModel")
    public R getMainModel()  {
        String modelJson;
        SaveModelDataReq res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            modelJson = modelservice.getMainModel();
            if(!StringUtils.isNullOrEmpty(modelJson)){
                res = objectMapper.readValue(modelJson,SaveModelDataReq.class);
            }
        }catch (Exception e) {
            log.error("获取主模板失败！");
            log.error(e);
            return R.error().message("获取主模板失败！");
        }
        return R.ok().data(res);
    }

}