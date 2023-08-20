package com.cuit.servlet;

import cn.hutool.core.net.multipart.MultipartFormData;
import cn.hutool.core.net.multipart.UploadFile;
import cn.hutool.core.net.multipart.UploadSetting;
import cn.hutool.extra.servlet.ServletUtil;
import com.cuit.entity.Product;
import com.cuit.service.impl.ProductServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "AddServlet", value = "/AddServlet")
public class AddServlet extends HttpServlet {
	private ProductServiceImpl productService = new ProductServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UploadSetting uploadSetting = new UploadSetting();
		MultipartFormData multipart = ServletUtil.getMultipart(request, uploadSetting);
		Map<String, String[]> paramMap = multipart.getParamMap();
		Product product = new Product();
		try {
			BeanUtils.populate(product,paramMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		//应该添加判断字段是否为空
		int i = productService.insertProduct(product);
		if(i > 0){
			System.out.println("插入成功");
		}
		long time = new Date().getTime();
		System.out.println(product);
		UploadFile[] files = multipart.getFiles("upload");
		for (UploadFile file : files) {
			String decodedFileName = new String(file.getFileName().getBytes(), StandardCharsets.UTF_8);
			System.out.println(decodedFileName);
//			InputStream fileInputStream = file.getFileInputStream();
//			File file1 = FileUtil.writeFromStream(fileInputStream, "/products/Pic" + time);
//			String absolutePath = file1.getAbsolutePath();
//			System.out.println(absolutePath);
//			fileInputStream.close();
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
