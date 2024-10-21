package com.jiale.aopjdkproxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.jiale.aopjdkproxy.mpper")
public class AopJdkProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopJdkProxyApplication.class, args);
    }

}
