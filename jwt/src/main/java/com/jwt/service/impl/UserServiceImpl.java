package com.jwt.service.impl;

import com.jwt.entity.User;
import com.jwt.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User checkUser(String username, String password) {
        //模拟从数据库中查询
        return new User(1,"suchong","suchong123");
    }
}
