package com.cuit.servlet;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.cuit.entity.Product;
import com.cuit.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchKeyWordServlet", value = "/SearchKeyWordServlet")
public class SearchKeyWordServlet extends HttpServlet {
    private final ProductServiceImpl productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        String keyWord = request.getParameter("keyWord");
        System.out.println(keyWord);
        List<Product> allByWord = productService.findAllByWord(keyWord);
        JSON parse = JSONUtil.parse(allByWord);
        response.getWriter().write(parse.toString());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
    }
}
