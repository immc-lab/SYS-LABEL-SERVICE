package com.label.core.service;

import com.label.core.pojo.entity.CurrentUserMessage;
import com.label.core.pojo.vo.admin.GetAllUserRes;
import com.label.core.pojo.vo.admin.RegisterReq;
import com.label.core.pojo.vo.admin.SaveNewUserReq;
import com.label.core.pojo.vo.admin.UserItem;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface UserInfoService {
    public String login(String account,String passWord,HttpServletRequest request);

    public boolean register(RegisterReq registerReq);

    public CurrentUserMessage getCurrentUserMessage(HttpServletRequest request);

    void saveOrUpdateNewUser(SaveNewUserReq req);


    Long duplicateChecking(String account);

    List<UserItem> getAllUser();

    void disableAccountByKey(String userKey,String state);

}

