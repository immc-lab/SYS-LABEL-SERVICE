package com.label.core.pojo.vo.Label;

import lombok.Data;

import java.util.List;

@Data
public class GetMusicLabelListRes {
    List<LabelDataItem> labelDataList;
    long total;
    long current;
    long size;
    long pages;
}
