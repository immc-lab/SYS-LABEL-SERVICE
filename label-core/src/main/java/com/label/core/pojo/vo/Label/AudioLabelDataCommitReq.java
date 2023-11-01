package com.label.core.pojo.vo.Label;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AudioLabelDataCommitReq implements Serializable {

    private List<commitItem> commitList;

    private String key;

    private String type;

    private String text;

}
