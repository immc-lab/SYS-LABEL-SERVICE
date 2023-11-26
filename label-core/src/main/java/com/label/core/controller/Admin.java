package com.label.core.controller;

import com.label.common.exceptionhandler.Assert;
import com.label.common.result.R;
import com.label.common.result.ResponseEnum;
import com.label.common.util.Sha256;
import com.label.core.pojo.entity.CurrentUserMessage;
import com.label.core.pojo.vo.admin.*;
import com.label.core.service.UserInfoService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/admin")
public class Admin {
    @Resource
    private UserInfoService userInfoService;

    //    用户注册
    @PostMapping("/core/register")
    public R register(@RequestBody RegisterReq registerReq, HttpServletRequest request) {
        boolean isCheck = (boolean) request.getSession().getAttribute("isChecked");
        Assert.isTrue(isCheck, ResponseEnum.Check_Code_ERROR);
        request.getSession().removeAttribute("isChecked");
        boolean ok = userInfoService.register(registerReq);
        if (ok) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    //    用户登录
    @PostMapping("/core/login")
    public R login(@RequestBody LoginReq loginReq, HttpServletRequest request) throws NoSuchAlgorithmException {
        String userKey = userInfoService.login(loginReq.getAccount(),Sha256.cryBySh256(loginReq.getPassword()),request);
        if (!StringUtils.isNullOrEmpty(userKey)) {
            if("409".equals(userKey)){
                return R.setResult(ResponseEnum.ACCOUNT_DISABLE);
            }
            //添加session
            HttpSession session = request.getSession();
            //拦截器检查 存储用户id
            session.setAttribute("loginUser", userKey);
            return R.setResult(ResponseEnum.SUCCESS);
        } else {
            return R.setResult(ResponseEnum.LOGIN_PASSWORD_ERROR);
        }
    }

    //获取当前用户信息
    @PostMapping("/core/getAdminMessage")
    public R getCurrentUserMessage(HttpServletRequest request) {
        CurrentUserMessage userMessage = userInfoService.getCurrentUserMessage(request);
        if (userMessage != null) {
            return R.ok().data(userMessage);
        } else {
            return R.error();
        }
    }

//新建或者更新用户
    @PostMapping("/core/saveOrUpdateNewUser")
    public R saveNewUser(@RequestBody SaveNewUserReq req) {
        try {
            if ("insert".equals(req.getType())) {
                Long count = userInfoService.duplicateChecking(req.getAccount());
                if (count >= 1) {
                    return R.error().message("账号重复请重新选择！");
                } else {
                    userInfoService.saveOrUpdateNewUser(req);
                }
            } else {
                userInfoService.saveOrUpdateNewUser(req);
            }
        } catch (Exception e) {
            log.error("更新或者新建用户失败！");
            return R.error().message("更新用户失败！请稍候重试！");
        }
        return R.ok();
    }


    //获取所有用户信息
    @PostMapping("/core/getAllUser")
    public R getAllUser() {
        //校验管理员身份
        List<UserItem> userList = new ArrayList<>();
        try{
            userList = userInfoService.getAllUser();
        }catch (Exception e){
            return R.error();
        }
        return R.ok().data(userList);
    }

    @PostMapping("/core/disableAccountByKey")
    public R disableAccountByKey(@RequestBody  DisableAccountKeyReq req) {
        try{
            userInfoService.disableAccountByKey(req.getUserKey(), req.getState());
        }catch (Exception e){
            return R.error().message("系统忙！请稍候重试！");
        }
        return R.ok();
    }

    @PostMapping("/core/getAllManager")
    public R getAllManager() {
        List<ManagerItem> managerList = new ArrayList<>();
        try{
            managerList = userInfoService.getAllManager();
        }catch (Exception e){
            return R.error().message("系统忙！请稍候重试！");
        }
        return R.ok().data(managerList);
    }
}
