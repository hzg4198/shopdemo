package com.cuit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter",urlPatterns = "/admin/*")
public class AutoLoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public AutoLoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse rep=(HttpServletResponse) response;
        //用户登录信息
        Object username = req.getSession().getAttribute("username");
        //如果已经登录 放行 不需要自动登录
        if(username!=null){
            chain.doFilter(req, rep);
            return;
        }
        //获得自动登录的cookie信息
        Cookie[] cookies = req.getCookies();
        Cookie userCookie=null;
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if("autologin".equals(cookie.getName())){
                    userCookie=cookie;
                    break;
                }
            }
        }
        //判断自动登录的cookie是否存在，如果没有cokie  不需要自动登录
        if(userCookie==null){
            chain.doFilter(req, rep);
            return;
        }
        //如果有信息
        String[] u=userCookie.getValue().split("@");
        String usernameC=u[0];
        String passwordC=u[1];

//        userService.verifyPassword(usernameC, passwordC);
//        if(list.size()==0){
//            chain.doFilter(req, rep);
//            return;
//        }
        //自动登录
        req.getSession().setAttribute("username", usernameC);
        // pass the request along the filter chain
        chain.doFilter(req, rep);
    }
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}
