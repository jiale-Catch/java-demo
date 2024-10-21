package com.jiale.aopjdkproxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiale.aopjdkproxy.domain.User;
import com.jiale.aopjdkproxy.domain.UserEntity;
import com.jiale.aopjdkproxy.mpper.UserMapper;
import com.jiale.aopjdkproxy.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public List<UserEntity> findUserList() {
        return userMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public void addUser() {
        System.out.println("新增一个用户"+new User("zijiale",18));
    }
}
