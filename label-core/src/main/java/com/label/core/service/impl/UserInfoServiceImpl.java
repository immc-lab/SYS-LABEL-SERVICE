package com.label.core.service.impl;

import com.label.common.util.SnowflakeIdUtil;
import com.label.core.mapper.UserAccountMapper;
import com.label.core.pojo.entity.CurrentUserMessage;
import com.label.core.pojo.entity.UserAccount;
import com.label.core.pojo.vo.admin.RegisterReq;
import com.label.core.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserAccountMapper userInfo;

    @Override
    public String login(String account, String passWord) {
        List<UserAccount> userAccounts = userInfo.userLogin(account, passWord);
        if (userAccounts.size() <= 0) {
            return "";
        } else {
            return userAccounts.get(0).getUserId();
        }
    }

    @Override
    public boolean register(RegisterReq registerReq) {
        int line = userInfo.userRegister(registerReq.getAccount(), registerReq.getPassWord(), registerReq.getName(), SnowflakeIdUtil.getId());
        if (line <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public CurrentUserMessage getCurrentUserMessage(HttpServletRequest request) {
        return userInfo.getCurrentUserMessage((String) request.getSession().getAttribute("loginUser"));
    }
}
