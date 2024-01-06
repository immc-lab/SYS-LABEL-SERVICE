package com.label.common.preHandle;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("拦截器拦截。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
//      获取进过拦截器的路径
            String requestURI = request.getRequestURI();

        //登录检查逻辑
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser !=null){
//          放行
            return true;
        }
//      拦截   就是未登录,自动跳转到登录页面，然后写拦截住的逻辑
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "登录已过期请重新登录！");
        return false;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("...");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("....");
    }
}
