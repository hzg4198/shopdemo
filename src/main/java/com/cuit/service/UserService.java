package com.cuit.service;

import com.cuit.entity.User;

import java.util.List;

public interface UserService {
    List<User> verifyPassword(User user);
    List<User> findAll();
    List<User> queryOne(User user);
    int insertConsumer(User user);
}
