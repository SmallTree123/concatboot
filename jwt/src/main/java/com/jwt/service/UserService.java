package com.jwt.service;

import com.jwt.entity.User;

public interface UserService {
    User checkUser(String username, String password);

}
