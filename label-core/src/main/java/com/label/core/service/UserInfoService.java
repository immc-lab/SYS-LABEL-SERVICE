package com.label.core.service;

import com.label.core.pojo.entity.CurrentUserMessage;
import com.label.core.pojo.vo.admin.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UserInfoService {
    public String login(String account,String passWord,HttpServletRequest request);

    public boolean register(RegisterReq registerReq);

    public CurrentUserMessage getCurrentUserMessage(HttpServletRequest request);

    void saveOrUpdateNewUser(SaveNewUserReq req);


    Long duplicateChecking(String account);

    List<UserItem> getAllUser();

    void disableAccountByKey(String userKey,String state);

    List<ManagerItem> getAllManager();
}

