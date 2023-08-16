package com.cuit.servlet;

import com.cuit.dao.UserDao;
import com.cuit.entity.User;
import com.cuit.utils.ServletUtils;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {
    @Resource
    private UserDao userDao;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = ServletUtils.getSession();
        UserDao mapper = session.getMapper(UserDao.class);
        List<User> users = mapper.VerifyPassword("aaa", "aaa");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
