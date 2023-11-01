package com.label.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.label.core.mapper.LabelDataMapper;
import com.label.core.pojo.vo.Label.*;
import com.label.core.service.LabelDataService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Log4j2
public class LabelDataServiceImpl extends ServiceImpl<LabelDataMapper,LabelDataItem> implements LabelDataService  {
    @Autowired
    private
    LabelDataMapper labelDataMapper;
    @Override
    public Page<LabelDataItem> getMusicLabelPageList(Page<LabelDataItem> page, String UserId) {
        Page<LabelDataItem> labelPage = new Page<>();
        QueryWrapper<LabelDataItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_userId",UserId);
       labelPage = baseMapper.selectPage(page,queryWrapper);
        return labelPage;
    }

    @Override
    public String getLabelAudioDataByKey(String key) {
        String url = labelDataMapper.getLabelAudioDataByKey(key);
        if(StringUtils.isNullOrEmpty(url)){
            return "";
        }
        return url;
    }

    @Override
    public boolean handleCommitAudioData(AudioLabelDataCommitReq req) {

            labelDataMapper.remove(req.getKey());
            labelDataMapper.batchInsert(req.getKey(), req.getCommitList());
            if("submit".equals(req.getType())){
                labelDataMapper.upData(req.getText(),req.getKey());
            }

        return true;
    }

    @Override
    public List<GetSaveEditDataItem> getSaveEditDataByKey(String key) {
         return labelDataMapper.getSaveEditData(key);
    }
}
