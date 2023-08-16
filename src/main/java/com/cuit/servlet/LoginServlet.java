package com.cuit.servlet;

import com.cuit.dao.UserDao;
import com.cuit.entity.User;
import com.cuit.utils.CheckCodeUtil;
import com.cuit.utils.ServletUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先验证验证码正确
        if(!request.getParameter("verify").equals( getServletContext().getAttribute("verify"))){
            System.out.println("验证码错误");
            request.setAttribute("msg","验证码错误");
            request.getRequestDispatcher("/admin/index.jsp").forward(request,response);
        }
        SqlSession session = ServletUtils.getSession();
        Map<String,Object> params = new HashMap<>();
        params.put("username",request.getParameter("username"));
        params.put("password",request.getParameter("password"));
        List<User> verifyPassword = session.selectList("VerifyPassword", params);
        //没有查询到
        if(verifyPassword == null){
            System.out.println("用户名或密码错误");
            request.setAttribute("msg","用户名或密码错误");
            request.getRequestDispatcher("/admin/index.jsp").forward(request,response);
        }
        //登陆成功
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
