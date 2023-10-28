package com.takeout.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
        HttpSession session = request.getSession();
        String loginUser = (String) session.getAttribute("user");
        System.out.println("Interceptor: " + request.getSession().getId());
        System.out.println(request.getRequestURI());
        System.out.println(loginUser);
        if(loginUser==null){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\":\"403\",\"message\":\"请先登录！\"}");
//            response.sendRedirect("/TakeoutSystem/login.html");
            return false;
        }
        return true;
    }
}
