package com.label.core.controller;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UserSessionManager {
    private static Map<String, HttpSession> loggedInUsers = new HashMap<>();

    public synchronized static boolean isUserLoggedIn(String userKey) {
        return loggedInUsers.containsKey(userKey);
    }

    public synchronized static void loginNewUser(HttpSession session, String userKey) {
        // 判断该用户名是否已经存在于已登录用户列表
            // 如果已经存在，则挤掉之前登录的用户
        if(loggedInUsers.containsKey(userKey)) {
            HttpSession previousSession = loggedInUsers.get(userKey);
            previousSession.invalidate();
            loggedInUsers.remove(userKey);
        }
        loggedInUsers.put(userKey, session);
    }

    public synchronized static void logoutUser(String userKey) {
        loggedInUsers.remove(userKey);
    }
}
