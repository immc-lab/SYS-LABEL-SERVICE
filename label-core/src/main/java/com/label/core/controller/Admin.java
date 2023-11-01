package com.label.core.controller;

import com.label.common.exceptionhandler.Assert;
import com.label.common.result.R;
import com.label.common.result.ResponseEnum;
import com.label.core.pojo.entity.CurrentUserMessage;
import com.label.core.pojo.vo.admin.RegisterReq;
import com.label.core.pojo.vo.admin.LoginReq;
import com.label.core.service.UserInfoService;
import com.mysql.cj.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
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
    public R login(@RequestBody LoginReq loginReq, HttpServletRequest request) {
        String userId = userInfoService.login(loginReq.getAccount(), loginReq.getPassword());
        if (!StringUtils.isNullOrEmpty(userId)) {
            //添加session
            HttpSession session = request.getSession();
            //拦截器检查 存储用户id
            session.setAttribute("loginUser", userId);
            return R.ok().message(ResponseEnum.SUCCESS.getMessage());
        } else {
            return R.error().message(ResponseEnum.LOGIN_PASSWORD_ERROR.getMessage());
        }
    }

    //获取当前用户信息
    @PostMapping("/core/getAdminMessage")
    public R getCurrentUserMessage(HttpServletRequest request) {
        CurrentUserMessage userMessage = userInfoService.getCurrentUserMessage(request);
        if (userMessage != null) {
            userMessage.setAccess("Admin");
            return R.ok().data(userMessage);
        } else {
            return R.error();
        }
    }
}
