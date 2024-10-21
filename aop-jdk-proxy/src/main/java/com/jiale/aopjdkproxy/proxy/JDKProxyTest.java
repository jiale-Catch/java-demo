package com.jiale.aopjdkproxy.proxy;

import com.jiale.aopjdkproxy.service.UserService;
import com.jiale.aopjdkproxy.service.impl.UserServiceImpl;

public class JDKProxyTest {


        /**
         * main interface.
         *
         * @param args args
         */
        public static void main(String[] args) {
            // proxy
            UserService userService = new UserProxy(new UserServiceImpl()).getServiceProxy();

            // call methods
            userService.findUserList();
            userService.addUser();
        }

}
