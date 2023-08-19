package com.cuit.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cuit.entity.Product;
import com.cuit.service.ProductService;
import com.cuit.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAllProduct() {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        return sqlSession.selectList("findAllProduct");
    }

    @Override
    public int insertProduct(Product product) {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        Map<String,Object> params = new HashMap<>();
        params.put("cid", product.getCid());
        params.put("pname", product.getPname());
        params.put("pid", product.getPid());
        params.put("is_hot", product.getIs_hot());
        params.put("market_price", product.getMarket_price());
        params.put("shop_price", product.getShop_price());
        params.put("pdesc", product.getPdesc());
        params.put("pflag", product.getPflag());
        params.put("pimage", product.getPimage());
        params.put("pdate",DateUtil.now());

        int insertProduct = sqlSession.insert("insertProduct", params);
        sqlSession.commit();
        return insertProduct;
    }
}
