package com.cuit.service.impl;

import com.cuit.entity.Product;
import com.cuit.service.ProductService;
import com.cuit.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAllProduct() {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        return sqlSession.selectList("findAllProduct");
    }
}
