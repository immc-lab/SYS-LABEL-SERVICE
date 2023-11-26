package com.label.core.pojo.vo.team;

import lombok.Data;

@Data
public class TeamItem {
    //团队名称
    private String teamName;

    private String creator;
    //团队唯一id
    private String teamKey;

    private String teamMessage;
    //创建时间
    private String createTime;
    //管理者名称
    private String managerName;
    //管理者key值
    private String managerKey;

    private String id;

}
