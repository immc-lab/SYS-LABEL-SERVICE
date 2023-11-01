package com.label.core.service;

import javax.servlet.http.HttpServletRequest;

public interface SendMailService {

    public boolean sendMail(String to, HttpServletRequest request);

    public boolean checkCode(String code, HttpServletRequest request );
}
