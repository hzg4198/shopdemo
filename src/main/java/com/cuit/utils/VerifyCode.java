package com.cuit.utils;

import javax.servlet.http.HttpServletRequest;

public class VerifyCode {
    //验证验证码是否正确
    public static boolean verify(HttpServletRequest request){
        return request.getParameter("verify").equalsIgnoreCase((String) request.getSession().getAttribute("verify"));
    }
}
