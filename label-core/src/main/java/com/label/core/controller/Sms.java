package com.label.core.controller;

import com.label.common.result.R;
import com.label.core.pojo.vo.sms.CheckMailMessageReq;
import com.label.core.pojo.vo.sms.SendMailMessageReq;
import com.label.core.service.SendMailService;
import com.label.core.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//验证手机号(邮箱)
@RestController
@RequestMapping("/sms")
public class Sms {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private SendMailService sendMailService;
//发送邮件验证码
    @PostMapping("/core/sendMailMessage")
    public R sendMailMessage(@RequestBody SendMailMessageReq req, HttpServletRequest request) {
        boolean ok = sendMailService.sendMail(req.getMailNumber(),request);
        if(ok){
            return R.ok();
        }else {
            return R.error();
        }
    }

//检验邮件验证码
    public R checkMailMessage(@RequestBody (required = false)CheckMailMessageReq req,HttpServletRequest request) {
        boolean ok = sendMailService.checkCode(req.getCode(),request);
        if(ok){
            //防越权校验
            request.getSession().setAttribute("isChecked",true);
            return R.ok();
        }else {
            return R.error();
        }
    }
}
