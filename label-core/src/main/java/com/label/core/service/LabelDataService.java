package com.label.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.label.core.pojo.vo.Label.*;

import java.util.List;

public interface LabelDataService {

    public Page<LabelDataItem> getMusicLabelPageList(Page<LabelDataItem> page,String UserId);

    public String getLabelAudioDataByKey(String key);

    public boolean handleCommitAudioData( AudioLabelDataCommitReq req);

    public List<GetSaveEditDataItem> getSaveEditDataByKey(String key);
}
