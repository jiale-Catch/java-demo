package com.jiale.springsecurity.moudle.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.springsecurity.moudle.mapper.UserMapper;
import com.jiale.springsecurity.moudle.model.entity.UserEntity;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper usersMapper;
    // 自定义认证逻辑
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.构造查询条件
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>().eq("username",username);
        // 2.查询用户
        UserEntity users = usersMapper.selectOne(wrapper);
        // 3.封装为UserDetails对象
        UserDetails userDetails = User
                .withUsername(users.getUsername())
                .password(users.getPassword())
                .authorities("admin")
                .build();
        // 4.返回封装好的UserDetails对象
        return userDetails;
    }
}
