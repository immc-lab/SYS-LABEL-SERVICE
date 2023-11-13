package com.label.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.label.common.result.R;
import com.label.core.pojo.vo.Label.*;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import com.label.core.service.LabelDataService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@Log4j2
@RequestMapping("/label")
public class Label {
    @Resource
    private LabelDataService labelDataService;
    //    用户注册
    @PostMapping("/core/getMusicLabelList")
    public R getMusicLabelList(@RequestBody GetMusicLabelListReq req, HttpServletRequest request) {
        GetMusicLabelListRes res = new GetMusicLabelListRes();
        String userId = (String) request.getSession().getAttribute("loginUser");
        Page<LabelDataItem> LabelMusicPage = new Page<>(req.getPage(), req.getLimit());
        Page<LabelDataItem> musicLabelPageList = labelDataService.getMusicLabelPageList(LabelMusicPage, "17855602815");
//        System.out.println(musicLabelPageList);
        res.setLabelDataList(musicLabelPageList.getRecords());
        res.setTotal(musicLabelPageList.getTotal());
        res.setCurrent(musicLabelPageList.getCurrent());
        res.setSize(musicLabelPageList.getSize());
        res.setPages(musicLabelPageList.getPages());
        return R.ok().data(res);
    }
//获取音频数据  base64转
    @PostMapping("/core/getLabelAudioDataByKey")
    public R getLabelAudioDataByKey(@RequestBody GetLabelAudioDataByKeyReq req, HttpServletRequest request) throws IOException {
        //获取音频服务器地址
        String url = labelDataService.getLabelAudioDataByKey(req.getKey());
        if(StringUtils.isNullOrEmpty(url)){
            return R.error().message("获取音频失败！");
        }
        FileInputStream inputFile = null;
        //获取文件并转为base64传给前端
        try {
            File file = new File(url);
            if(file.exists()) {
                inputFile = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length()];
                inputFile.read(buffer);
                inputFile.close();
                return R.ok().data(new BASE64Encoder().encode(buffer));
            }else {
                return R.error().message("文件服务器获取失败，请检查文件存储地址是否正确");
            }
        }catch (Exception e){
            return R.error().message("读取文件失败！");
        }finally {
           if(inputFile!=null){
               inputFile.close();
           }
        }
    }
//保存或者提交编辑的数据
    @PostMapping("/core/saveOrSubmitLabelData")
    public R audioLabelDataCommit(@RequestBody AudioLabelDataCommitReq req){
        System.out.println(req);
        boolean isOk = labelDataService.handleCommitAudioData(req);
        if(isOk){
            return R.ok();
        }
       return R.error();
    }

    @PostMapping("/core/getSaveEditData")
    public R getSaveEditData(@RequestBody SaveLabelDataRes req) {
        System.out.println(req);
        String modelJson = null;
        SaveLabelDataRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            modelJson = labelDataService.getSaveEditDataByKey(req.getKey());
            if (!StringUtils.isNullOrEmpty(modelJson)) {
                res = objectMapper.readValue(modelJson, SaveLabelDataRes.class);
            }
        } catch (Exception e) {
            log.error("通过key查询保存的编辑数据失败!");
            return R.error();
        }
        return R.ok().data(res);
    }


    @PostMapping("/core/saveEditData")
    public R saveEditData(@RequestBody SaveLabelDataRes req) throws JsonProcessingException {
        System.out.println(req);
        SaveModelDataReq res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(req);
        try {
             labelDataService.saveLabelData(req.getKey(),json);
        } catch (Exception e) {
            log.error("通过key查询保存的编辑数据失败!");
            return R.error();
        }
        return R.ok();
    }
}
