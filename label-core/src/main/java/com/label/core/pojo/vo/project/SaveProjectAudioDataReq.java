package com.label.core.pojo.vo.project;

import lombok.Data;

@Data
public class SaveProjectAudioDataReq {

    private String audioBase64;

    //对应项目的key
    private String projectKey;

    //对应任务key
    private String missionKey;

    //音频格式，默认为MP3
    private String format;

    private String modelKey;

    //看下是否传完
    private String last;


}
