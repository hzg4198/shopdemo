package com.cuit.dao;

import com.cuit.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAllProduct();
}
