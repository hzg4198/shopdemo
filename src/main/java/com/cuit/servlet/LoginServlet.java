package com.cuit.servlet;

import com.cuit.entity.User;
import com.cuit.utils.CheckCodeUtil;
import com.cuit.utils.ServletUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先验证验证码是否正确
        if(!request.getParameter("verify").equalsIgnoreCase(((String)getServletContext().getAttribute("verify")))){
            request.setAttribute("msg","验证码错误");
            request.getRequestDispatcher("/admin/index.jsp?"+new Date().getTime()).forward(request,response);
            return;
        }
        SqlSession session = ServletUtils.getSession();
        Map<String,Object> params = new HashMap<>();
        params.put("username",request.getParameter("username"));
        params.put("password",request.getParameter("password"));
        List<User> verifyPassword = session.selectList("VerifyPassword", params);
        if(verifyPassword.isEmpty()){
            request.setAttribute("msg","用户名或密码错误");
            request.getRequestDispatcher("/admin/index.jsp?"+new Date().getTime()).forward(request,response);
        }else {
            System.out.println("登录成功");
            response.sendRedirect(request.getContextPath()+"/admin/home.jsp");
        }
        //登陆成功

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
