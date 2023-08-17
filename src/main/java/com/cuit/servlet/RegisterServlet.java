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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static UserServiceImpl userService;
	static {userService = new UserServiceImpl();}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先验证验证码是否正确
		if(!VerifyCode.verify(request)){
			request.setAttribute("msg","验证码错误");
			request.getRequestDispatcher("/admin/register.jsp?"+new Date().getTime()).forward(request, response);
			return;
		//用户名或密码不能为空
		}else if (request.getParameter("username").isEmpty()||request.getParameter("password").isEmpty()){
			request.setAttribute("msg","用户名或密码不允许");
			request.getRequestDispatcher("/admin/register.jsp?"+new Date().getTime()).forward(request,response);
			return;
		}
		//验证通过，可以注册
		User user = new User();
		try {
			BeanUtils.populate(user,request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		List<User> users = userService.queryOne(user);
		if(!users.isEmpty()){
			request.setAttribute("msg","用户名已存在");
			request.getRequestDispatcher("/admin/register.jsp?"+new Date().getTime()).forward(request,response);
		}else {
			int ans = userService.insertConsumer(user);
			if(ans > 0){
				response.sendRedirect(request.getContextPath()+"/admin/index.jsp?msg=Register success,please Login");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}
