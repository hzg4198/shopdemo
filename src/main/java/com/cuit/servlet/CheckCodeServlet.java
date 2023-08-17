package com.cuit.servlet;

import com.cuit.utils.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckCodeServlet", value = "/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        String s = CheckCodeUtil.outputVerifyImage(100, 35, outputStream, 4);
        HttpSession session = request.getSession();
        session.setAttribute("verify",s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
