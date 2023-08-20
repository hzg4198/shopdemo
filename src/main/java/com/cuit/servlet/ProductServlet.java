package com.cuit.servlet;

import com.cuit.entity.Product;
import com.cuit.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private  ProductServiceImpl productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        /*Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.nextElement()!=null){
            String s = headerNames.nextElement();
            System.out.println(s+"="+request.getHeader(s));
        }*/
        //是否可以依据不同的请求地址转发到对应的地址？
        List<Product> productList = productService.findAllProduct();
        request.setAttribute("productList",productList);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
