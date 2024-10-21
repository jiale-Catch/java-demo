package com.jiale.aopjdkproxy.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.jiale.aopjdkproxy.domain.User;
import com.jiale.aopjdkproxy.domain.UserEntity;
import com.jiale.aopjdkproxy.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    @Resource
    private UserService userService;
    @GetMapping("/user")
    public String test(@RequestParam String name){
        List<UserEntity> userList = userService.findUserList();
        JSON parse = JSONUtil.parse(userList);
        return parse.toString();
    }

    @PostMapping("/add")
    public String addUser(){
        userService.addUser();
        return "ok";
    }
}