package com.cuit.service.impl;

import com.cuit.entity.User;
import com.cuit.service.UserService;
import com.cuit.utils.IdUtils;
import com.cuit.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> verifyPassword(User user) {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        Map<String,Object> params = new HashMap<>();
        params.put("username",user.getUsername());
        params.put("password",user.getPassword());
        return sqlSession.selectList("VerifyPassword",params);
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        return sqlSession.selectList("findAll");
    }

    @Override
    public List<User> queryOne(User user) {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        return sqlSession.selectList("queryOne",user.getUsername());
    }

    @Override
    public boolean queryByName(String username) {
        SqlSession session = SqlSessionUtils.getSession();
        return session.selectList("queryOne", username).isEmpty();
    }

    @Override
    public int insertConsumer(User user) {
        SqlSession sqlSession = SqlSessionUtils.getSession();
        Map<String,Object> params = new HashMap<>();
        params.put("uid", IdUtils.getUid());
        params.put("username",user.getUsername());
        params.put("password",user.getPassword());
        int insertConsumer = sqlSession.insert("insertConsumer", params);
        sqlSession.commit();
        return insertConsumer;
    }


}
