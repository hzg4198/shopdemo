package com.cuit.service.impl;

import com.cuit.entity.Category;
import com.cuit.service.CategoryService;
import com.cuit.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> getCategory() {
		SqlSession session = SqlSessionUtils.getSession();
		return session.selectList("getCategory");
	}
}
