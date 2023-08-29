package com.cuit.dao;

import com.cuit.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAllProduct();
    int insertProduct(int pid,String pname,Double market_price,Double shop_price,String pimage,String pdate,int is_hot,String pdesc,int pflag,String cid);
    int updateProduct(int pid,String pname,Double market_price,Double shop_price,String pimage,String pdate,int is_hot,String pdesc,int pflag,String cid);
    int deleteById(int pid);
    int getTotalRecord();
    int getTotalRecordByCondition(Integer cid,String word);
    List<Product> findAllByWord(String word);
    List<Product> queryPage(Integer cid, String word, int start, int pageSize);

}
