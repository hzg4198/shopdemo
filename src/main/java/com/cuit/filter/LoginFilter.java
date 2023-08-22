package com.cuit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        if(requestURI.contains("/login.jsp")||requestURI.contains("/LoginServlet")){
            chain.doFilter(request,response);
            return;
        }else {
            Object username = req.getSession().getAttribute("username");
            if(username!=null){
                chain.doFilter(request,response);
            }else {
                request.setAttribute("message","您尚未登录，请登录");
                request.getRequestDispatcher("/").forward(request,response);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
