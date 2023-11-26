package com.label.core.pojo.vo.Label;

import lombok.Data;

@Data
public class ExportExcelDataReq {
    //项目对应的key
    String projectKey;
    //任务对应的key
    String missionKey;

}
