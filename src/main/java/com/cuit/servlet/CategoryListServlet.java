package com.cuit.servlet;

import com.cuit.entity.Category;
import com.cuit.service.impl.CategoryServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryListServlet", value = "/CategoryListServlet")
public class CategoryListServlet extends HttpServlet {
	private final CategoryServiceImpl categoryService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		List<Category> category = categoryService.getCategory();
		request.setAttribute("categoryList", category);
		request.getRequestDispatcher("/admin/product/add.jsp").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}
