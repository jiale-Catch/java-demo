package com.jiale.aopjdkproxy.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.aopjdkproxy.domain.LoginUser;
import com.jiale.aopjdkproxy.domain.UserEntity;
import com.jiale.aopjdkproxy.mpper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserEntity user = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
            if (user == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
            return new LoginUser(user);

    }
}
