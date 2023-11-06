package com.label.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.label.common.result.R;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.pojo.vo.Label.GetMusicLabelListReq;
import com.label.core.pojo.vo.Label.GetMusicLabelListRes;
import com.label.core.pojo.vo.Label.LabelDataItem;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import com.label.core.service.LabelDataService;
import com.label.core.service.ModelService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
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
        if("upDate".equals(req.getType())&& !StringUtils.isNullOrEmpty(req.getKey())){
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



}