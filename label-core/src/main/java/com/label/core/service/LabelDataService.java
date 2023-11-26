package com.label.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.label.core.pojo.vo.Label.*;

import java.io.IOException;
import java.util.List;

public interface LabelDataService {

    public Page<LabelDataItem> getMusicLabelPageList(Page<LabelDataItem> page,String UserId);

    public String getLabelAudioDataByKey(String key);

    public boolean handleCommitAudioData( AudioLabelDataCommitReq req);

    public String getSaveEditDataByKey(String key);

    public boolean saveLabelData (String key,String json);

    List<FileTransferItem> exportExcelDataByKey(ExportExcelDataReq req) throws IOException, InterruptedException;

}
