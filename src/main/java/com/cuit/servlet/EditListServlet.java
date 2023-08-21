package com.cuit.servlet;

import com.cuit.entity.Category;
import com.cuit.entity.Product;
import com.cuit.service.impl.CategoryServiceImpl;
import com.cuit.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditListServlet", value = "/EditListServlet")
public class EditListServlet extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    CategoryServiceImpl categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        List<Product> productList = productService.findAllProduct();
        List<Category> category = categoryService.getCategory();
        request.setAttribute("productList",productList);
        request.setAttribute("categoryList",category);
        request.getRequestDispatcher("/admin/product/edit.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
