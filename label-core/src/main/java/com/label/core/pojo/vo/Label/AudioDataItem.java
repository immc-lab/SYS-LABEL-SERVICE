package com.label.core.pojo.vo.Label;

import lombok.Data;

@Data
public class AudioDataItem {
    // 音频格式
    private String format;
   // 音频编号
    private String id;
    //状态
    private String state;
    //音频名称
    private String audioName;
   // 属于哪个
    private String belongUserName;
   //属于哪个质检员
    private String belongCheckerKey;
   // 音频名称
    private String audioKey;
   // 模板参数
    private String modelKey;
    //音频地址
    private String url;
    // 质检人
    private String belongCheckerName;


}
