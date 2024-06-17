package com.jiale.springsecurity.moudle.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Claims {
    /**
     * 主题
     */
    private String sub;

    /**
     * 签发时间
     */
    private Long iat;

    /**
     * 过期时间
     */
    private Long exp;

    /**
     * JWT ID
     */
    private String jti;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户状态(1:正常;0:禁用)
     */
    private String status;

    /**
     * 用户角色
     */
    private List<String> roles;

    /**
     * 权限列表
     */
    private List<String> permissions;

}
