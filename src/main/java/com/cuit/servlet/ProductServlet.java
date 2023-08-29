package com.cuit.servlet;

import com.cuit.entity.Category;
import com.cuit.entity.PageBean;
import com.cuit.service.impl.CategoryServiceImpl;
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
    ProductServiceImpl productService = new ProductServiceImpl();
    CategoryServiceImpl categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        /*Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.nextElement()!=null){
            String s = headerNames.nextElement();
            System.out.println(s+"="+request.getHeader(s));
        }*/
        //是否可以依据不同的请求地址转发到对应的地址？
        //暂时只有cid 和 名称
        String cid = request.getParameter("cid");
        String keyWord = request.getParameter("word");
        String pageNumber = request.getParameter("pageNumber");

        String pageSize="5";
        PageBean pageBean = productService.findAll(cid, keyWord, pageNumber, pageSize);
        System.out.println(pageBean);

        request.setAttribute("pageBean", pageBean);
        request.setAttribute("pname",keyWord);
        request.setAttribute("cid",cid);


//        List<Product> productList = productService.findAllProduct();
        List<Category> category = categoryService.getCategory();
//        request.setAttribute("productList",productList);
        request.setAttribute("categoryList",category);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
