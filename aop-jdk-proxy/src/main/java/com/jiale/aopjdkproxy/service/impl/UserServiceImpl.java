package com.jiale.aopjdkproxy.service.impl;

import com.jiale.aopjdkproxy.domain.User;
import com.jiale.aopjdkproxy.service.UserService;

import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> findUserList() {
        return Collections.singletonList(new User("zijiale",18));
    }

    @Override
    public void addUser() {
        System.out.println("新增一个用户"+new User("zijiale",18));
    }
}
