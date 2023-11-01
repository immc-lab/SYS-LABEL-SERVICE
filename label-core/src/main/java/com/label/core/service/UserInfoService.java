package com.label.core.service;

import com.label.core.pojo.entity.CurrentUserMessage;
import com.label.core.pojo.vo.admin.RegisterReq;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface UserInfoService {
    public String login(String account,String passWord);

    public boolean register(RegisterReq registerReq);

    public CurrentUserMessage getCurrentUserMessage(HttpServletRequest request);
}

