package com.cuit.dao;

import com.cuit.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> VerifyPassword(@Param("username") String username,@Param("password")  String password);
    List<User> findAll();
    List<User> queryOne(@Param("username") String username);
    int insertConsumer(@Param("uid") String uid, @Param("username") String username,@Param("password") String password);

}
