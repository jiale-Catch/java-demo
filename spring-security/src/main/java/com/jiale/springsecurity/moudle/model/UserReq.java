package com.jiale.springsecurity.moudle.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.memory.UserAttribute;

import java.util.Collection;

public class UserReq extends User {
    SysUserService userAttribute;
    public UserReq(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        userAttribute.
        super(username, password, authorities);
    }

}
