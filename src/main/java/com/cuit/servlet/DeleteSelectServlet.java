package com.cuit.servlet;

import com.cuit.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteSelectServlet", value = "/DeleteSelectServlet")
public class DeleteSelectServlet extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectIds = request.getParameter("selectIds");
        String[] ids = selectIds.split(",");
        int affectedRows = 0;
        for (String id : ids) {
            int i = productService.deleteById(Integer.parseInt(id));
            affectedRows += i;
        }
        response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp?success="+((affectedRows == ids.length)?"true":"false"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
