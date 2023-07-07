package com.jiale.aopjdkproxy.service;

import com.jiale.aopjdkproxy.domain.User;

import java.util.List;

public interface UserService {
     List<User> findUserList();

     void addUser();
}
