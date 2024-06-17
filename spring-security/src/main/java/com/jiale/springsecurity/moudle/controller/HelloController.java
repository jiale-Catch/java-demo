package com.jiale.springsecurity.moudle.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.springsecurity.framework.config.SecurityConfig;
import com.jiale.springsecurity.moudle.mapper.UserMapper;
import com.jiale.springsecurity.moudle.model.Claims;
import com.jiale.springsecurity.moudle.model.ResponseEntity;
import com.jiale.springsecurity.moudle.model.entity.UserEntity;
import com.jiale.springsecurity.moudle.utils.JWTUtil;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
    @Resource
    private UserMapper sysUserMapper;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("login")
    public ResponseEntity<Object> login(UserEntity sysUser ) {
        ResponseEntity<Object> response = new ResponseEntity<>();
        // 获取用户密码
        String username = sysUser.getUsername();
        String password = sysUser.getPassword();


        // 用户认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        // 认证成功后，返回用户信息及用户角色信息
        UserEntity user = sysUserMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username));



        JSONObject data = new JSONObject();
        data.put("user",user);

        // jwt实体数据
        Claims claims = Claims.builder()
                .username(sysUser.getUsername())
                .build();

        JWTUtil jwtUtil = new JWTUtil();
        String token = jwtUtil.createToken(claims);

        data.put("token",token);

        response.setCode(200);
        response.setData(data);
        return response;
    }
}
