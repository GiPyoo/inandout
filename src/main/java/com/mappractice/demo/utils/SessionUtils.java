package com.mappractice.demo.utils;

import com.mappractice.demo.domain.Account;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static final String USER_SESSION_KEY = "sessionedUser";

    public static boolean isLogin(HttpSession session) {
        if (session.getAttribute(USER_SESSION_KEY) == null) {
            return false;
        }
        return true;
    }

    public static Object getLoginUser(HttpSession session) {
        if (!isLogin(session)) {
            return null;
        }
        return (Account) session.getAttribute(USER_SESSION_KEY);
    }
}
