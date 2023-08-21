package com.cuit.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cuit.entity.PageBean;
import com.cuit.entity.Product;
import com.cuit.service.ProductService;
import com.cuit.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

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

    @Override
    public int updateProduct(Product product) {
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
//        params.put("pimage", product.getPimage());
        params.put("pdate",DateUtil.now());

        int updateProduct = sqlSession.update("updateProduct", params);
        sqlSession.commit();
        return updateProduct;
    }

    @Override
    public int deleteById(int pid) {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        int deleteById = sqlSession.delete("deleteById", pid);
        sqlSession.commit();
        return deleteById;
    }

    @Override
    public int getTotalRecord() {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        return sqlSession.selectOne("getTotalRecord");
    }

    @Override
    public List<Product> findAllByWord(String word) {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        return sqlSession.selectList("findAllByWord", word);
    }

    @Override
    public List<Product> queryPage(int start, int pageSize) {
        return null;
    }

    @Override
    public PageBean findAll(String cid, String keyWord, String pageNumberStr, String pageSizeStr) {
        Map<String,Object> params = new HashMap<>();
        if (cid != null){
            boolean flag = cid.equals("") || cid == null;
            params.put("cid", flag? null:Integer.parseInt(cid));
        }else {
            params.put("cid",null);
        }
        params.put("keyWord",keyWord);
        Integer pageNumber;
        Integer pageSize = Integer.parseInt(pageSizeStr);
        if(pageNumberStr==null||pageSizeStr.equals("")){
            pageNumber = 1;
        }else {
            pageNumber = Integer.parseInt(pageNumberStr);
        }
        int totalRecord = getTotalRecord();
        int totalPage = (totalRecord+pageSize-1)/pageSize;
        //数据库查询的时候需要的开始索引
        int startIndex=(pageNumber-1)*pageSize;
        PageBean pageBean = new PageBean(pageNumber, pageSize, totalRecord, totalPage, startIndex);
        params.put("start", startIndex);
        params.put("pageSize", pageSize);
        List<Product> list =  SqlSessionUtils.getSession().selectList("queryPage",params);
        pageBean.setData(list);
        return pageBean;
    }
}
