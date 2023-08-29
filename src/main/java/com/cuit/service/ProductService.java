package com.cuit.service;

import com.cuit.entity.PageBean;
import com.cuit.entity.Product;

import java.util.List;

public interface ProductService{
    List<Product> findAllProduct();
    int insertProduct(Product product);
    int updateProduct(Product product);
    int deleteById(int pid);
    int getTotalRecord();
    List<Product> findAllByWord(String word);
    List<Product> queryPage(Integer cid, String word, int start, int pageSize);

    PageBean findAll(String cid, String keyWord, String pageNumber, String pageSize);
    int getTotalRecordByCondition(Integer cid,String word);

}
