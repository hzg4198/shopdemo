package com.cuit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns = "/admin/*")
public class LoginFilter implements Filter {


    /**
     * Default constructor.
     */
    public LoginFilter() {
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
        //放行
        String[] urls={"/index.jsp","/css/","/imgs/","/register.jsp"};
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse rep=(HttpServletResponse) response;
        String url=req.getRequestURI();
        for(String url2:urls){
            if(url.contains(url2)||url.equals("/017Shop/admin/")){
                chain.doFilter(request, response);
                return;
            }
        }
        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");
        Object autologin = session.getAttribute("autologin");
        if(username!=null){
            //放行
            request.setAttribute("username",username);
            chain.doFilter(request, response);
            //request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
        }else{
            //没有登录  就给提示信息
            request.setAttribute("msg", "您尚未登录，请先登录后再访问");
            //转发
            request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}
