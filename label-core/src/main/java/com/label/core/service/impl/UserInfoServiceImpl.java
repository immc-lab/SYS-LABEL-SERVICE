package com.label.core.service.impl;

import com.label.common.util.GetLocalDataTime;
import com.label.common.util.SnowflakeIdUtil;
import com.label.core.mapper.UserAccountMapper;
import com.label.core.pojo.entity.CurrentUserMessage;
import com.label.core.pojo.entity.UserAccount;
import com.label.core.pojo.vo.admin.GetAllUserRes;
import com.label.core.pojo.vo.admin.RegisterReq;
import com.label.core.pojo.vo.admin.SaveNewUserReq;
import com.label.core.pojo.vo.admin.UserItem;
import com.label.core.service.UserInfoService;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
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
    public String login(String account, String passWord,HttpServletRequest request) {
        List<UserAccount> userAccounts = userInfo.userLogin(account, passWord);
        if (userAccounts.size() <= 0) {
            return "";
        } else {
            //状态不正常
            if(!"1".equals(userAccounts.get(0).getState())){
                return "409";
            }
            //记录登录的时间和ip地址;
            userInfo.updateUserLoginMessage(GetLocalDataTime.getTime(),request.getRemoteAddr(),userAccounts.get(0).getUserKey());
            return userAccounts.get(0).getUserKey();
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

    @Override
    public void saveOrUpdateNewUser(SaveNewUserReq req) {
            String roles = String.join(",", req.getRoles());
            String manageTeam = String.join(",", req.getManageTeam());
            String belongTeam = String.join(",", req.getBelongTeam());
            String belongTeamName = String.join(",",req.getBelongTeamName());
            String manageTeamName = String.join(",",req.getManageTeamName());
            String rolesName = String.join(",",req.getRolesName());
            if ("update".equals(req.getType())) {
                userInfo.updateUser(roles, manageTeam, belongTeam,belongTeamName,manageTeamName,req,rolesName);
            } else {
                String user_key = SnowflakeIdUtil.getId();
                userInfo.saveNewUser(roles, manageTeam, belongTeam, user_key, req,belongTeamName,manageTeamName,rolesName,GetLocalDataTime.getTime());
            }
    }

    @Override
    public Long duplicateChecking(String account) {
        return userInfo.duplicateCheck(account);
    }

    @Override
    public List<UserItem> getAllUser() {
       return userInfo.getAllUser();
    }

    @Override
    public void disableAccountByKey(String userKey,String state) {

        userInfo.disableAccount(userKey,state);


    }
}
