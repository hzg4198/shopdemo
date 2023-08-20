package com.cuit.servlet;

import com.cuit.entity.User;
import com.cuit.service.impl.UserServiceImpl;
import com.cuit.utils.VerifyCode;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static UserServiceImpl userService;
    static {userService = new UserServiceImpl();}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先验证验证码是否正确
        boolean verify = VerifyCode.verify(request);
        if(!verify){
            request.setAttribute("msg","验证码错误");
            request.getRequestDispatcher("/admin/index.jsp?"+new Date().getTime()).forward(request,response);
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        List<User> users = userService.verifyPassword(user);
        if(users.isEmpty()){
            request.setAttribute("msg","用户名或密码错误");
            request.getRequestDispatcher("/admin/index.jsp?"+new Date().getTime()).forward(request,response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("username",user.getUsername());
            response.sendRedirect(request.getContextPath()+"/admin/home.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
