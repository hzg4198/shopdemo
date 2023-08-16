package com.cuit.servlet;

import com.cuit.entity.User;
import com.cuit.utils.CheckCodeUtil;
import com.cuit.utils.IdUtils;
import com.cuit.utils.ServletUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先验证验证码是否正确
		if(!request.getParameter("verify").equalsIgnoreCase((String) getServletContext().getAttribute("verify"))){
			request.setAttribute("msg","验证码错误");
			request.getRequestDispatcher("/admin/register.jsp?"+new Date().getTime()).forward(request, response);
			return;
		//用户名或密码不能为空
		}else if (request.getParameter("username").isEmpty()||request.getParameter("password").isEmpty()){
			request.setAttribute("msg","用户名或密码不允许");
			request.getRequestDispatcher("/admin/register.jsp?"+new Date().getTime()).forward(request,response);
			return;
		}
		SqlSession session = ServletUtils.getSession();
		List<User> queryOne = session.selectList("queryOne", request.getParameter("username"));
		if(!queryOne.isEmpty()){
			request.setAttribute("msg","用户名已存在");
			request.getRequestDispatcher("/admin/register.jsp?"+new Date().getTime()).forward(request,response);
		}else {
			Map<String ,Object> params = new HashMap<>();
			params.put("uid",IdUtils.getUid());
			params.put("username",request.getParameter("username"));
			params.put("password",request.getParameter("password"));
			int ans = session.insert("insertConsumer", params);
			if(ans > 0){
				session.commit();
				response.sendRedirect(request.getContextPath()+"/admin/index.jsp?msg=Register success,please Login");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}
