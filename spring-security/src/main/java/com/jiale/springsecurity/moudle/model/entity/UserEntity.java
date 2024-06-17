package com.jiale.springsecurity.moudle.model.entity;

import lombok.Data;

@Data
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private String phone;
}
