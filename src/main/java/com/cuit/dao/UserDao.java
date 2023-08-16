package com.cuit.dao;

import com.cuit.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
//    @Select("select username,password from user where username = #{username} and password = #{password}")
    List<User> VerifyPassword(@Param("username") String username,@Param("password")  String password);
    List<User> findAll();
}
