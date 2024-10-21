package com.jiale.aopjdkproxy.service;

import com.jiale.aopjdkproxy.domain.User;
import com.jiale.aopjdkproxy.domain.UserEntity;

import java.util.List;

public interface UserService {
     List<UserEntity> findUserList();

     void addUser();
}
