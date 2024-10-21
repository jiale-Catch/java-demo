package com.jiale.springsecurity.moudle.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private String phone;
}
