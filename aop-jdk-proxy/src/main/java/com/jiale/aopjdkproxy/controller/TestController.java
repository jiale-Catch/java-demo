package com.jiale.aopjdkproxy.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.jiale.aopjdkproxy.domain.User;
import com.jiale.aopjdkproxy.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    @Resource
    private UserService userService;
    @GetMapping("/user")
    public String test(@RequestParam String name){
        List<User> userList = userService.findUserList();
        JSON parse = JSONUtil.parse(userList);
        return parse.toString();
    }
}