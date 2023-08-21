package com.cuit.servlet;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cuit.entity.User;
import com.cuit.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckUsernameServlet", value = "/CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    private static final List<User> allUser = new UserServiceImpl().findAll();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean flag1 = false;
        for (User user : allUser) {
            if(user.getUsername().equalsIgnoreCase(username)){
                flag1 = true;
                break;
            }
        }
        JSONObject obj = JSONUtil.createObj();
        obj = flag1 ? obj.set("available",false) : obj.set("available",true);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(obj.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
