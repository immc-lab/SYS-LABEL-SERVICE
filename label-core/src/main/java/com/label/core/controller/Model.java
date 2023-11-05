package com.label.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.label.common.result.R;
import com.label.core.pojo.vo.Label.GetMusicLabelListReq;
import com.label.core.pojo.vo.Label.GetMusicLabelListRes;
import com.label.core.pojo.vo.Label.LabelDataItem;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import com.label.core.service.LabelDataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Log4j2
@RequestMapping("/model")
public class Model {
    @Resource
    private LabelDataService labelDataService;

    //    用户注册
    @PostMapping("/core/saveModelData")
    public R SaveModelData(@RequestBody SaveModelDataReq req) throws JsonProcessingException {
        //json格式保存
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(req);
        System.out.println(objectMapper.readValue(json, SaveModelDataReq.class));
        return null;
    }

}