package com.label.core.service.impl;

import com.label.common.result.R;
import com.label.common.util.RandomUtils;
import com.label.core.service.SendMailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public boolean sendMail(String to, HttpServletRequest request) {
        try {
            String redisKey = request.getSession().getId();
            StringBuilder s = new StringBuilder(RandomUtils.getFourBitRandom());
            log.info("验证码为{}", s);
            redisTemplate.opsForValue().set(redisKey, s.toString(), 120, TimeUnit.SECONDS);
            log.info(redisTemplate.opsForValue().get(redisKey));;
            String contest = "验证码为:" + s + "   " + "一分钟后失效!";
            SimpleMailMessage message = new SimpleMailMessage();
            String from = "1933320948@qq.com";
            message.setFrom(from);
            message.setTo(to);
            String registerSubject = "标注系统注册";
            message.setSubject(registerSubject);
            message.setText(contest);
            javaMailSender.send(message);
        }catch (Exception e){
            log.info("发短信失败！");
            return false;
        }
            return true;
    }

    @Override
    public boolean checkCode(String code,HttpServletRequest request) {
        String redisKey = request.getSession().getId();
        String redisValue = redisTemplate.opsForValue().get(redisKey);
        return code.equals(redisValue);
    }


}
