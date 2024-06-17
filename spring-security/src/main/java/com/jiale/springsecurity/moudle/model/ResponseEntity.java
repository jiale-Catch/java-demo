package com.jiale.springsecurity.moudle.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class ResponseEntity<T> {

    private int code = 200;

    private String message = "成功";

    private String errorMsg = "";

    private T data;

    /**
     * 成功状态码
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败状态码
     */
    public static final Integer ERROR = 500;


    private static HashMap<Integer, String> ERROR_CODE = new HashMap<Integer, String>() {
        {
            put(100, "暂无数据");
            put(200, "成功");
            put(300, "失败");
            put(500, "失败状态码");
            put(10000, "通用错误");
            ///用户类
            put(10001, "用户名或密码错误");
            put(10002, "登录状态已过期");
            put(10003, "注册用户已存在");
            put(10004, "账号已被锁定,请在一小时后重试");
            put(10005, "旧密码错误");
            put(10006, "用户名已存在");
            put(10007, "ip没有权限");
            put(10008, "token无效");
            put(10009, "token失效");

            ///操作权限类
            put(20001, "无操作权限");
            ///参数类
            put(30001, "非法参数");
            put(30002, "缺少必要参数");
            put(40001, "添加数据失败");
            put(40002, "更新数据失败");
            put(40003, "删除数据失败");
            put(40004, "添加数据失败,对象已经存在，建议修改或者删除");
            put(50001, "不存在的对象");
            put(99999, "无任何资源权限");

            put(990000, "系统错误");
        }
    };
}
