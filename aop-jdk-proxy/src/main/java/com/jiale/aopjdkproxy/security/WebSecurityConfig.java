package com.jiale.aopjdkproxy.security;

import cn.hutool.core.collection.CollectionUtil;
import com.jiale.aopjdkproxy.aspect.handle.IgnoreAccessHandle;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Resource
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationFilter;

    @Resource
    private UserDetailServiceImpl userDetailsService;

    @Resource
    private JwtAuthenticationDeniedHandler accessDeniedHandler;

    @Resource
    private IgnoreAccessHandle ignoreAccessHandle;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(new BCryptPasswordEncoder());
        //remember me
        authenticationManagerBuilder.eraseCredentials(false);
    }
    //安全过滤器链
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(requestMatcherRegistry ->
                        requestMatcherRegistry.
                                requestMatchers(ignoreAccessHandle.getIgnoreList().toArray(new String[0]))
                                .permitAll() //指定对于特定的登录路径（由 SecurityConstants.LOGIN_PATH 定义），允许所有用户访问，即无需进行认证和授权就可以访问该路径
                                .anyRequest().authenticated()//表示对于任何其他请求，都要求用户进行认证（即用户必须是已登录状态）
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> //用于配置异常处理
                        httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(authenticationEntryPoint) //设置认证入口点，当用户未认证但尝试访问需要认证的资源时，会由这个入口点来处理
                                .accessDeniedHandler(accessDeniedHandler) //设置访问拒绝处理器，当已认证用户没有足够的权限访问某个资源时，由这个处理器来处理
                )
                .userDetailsService(userDetailsService)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //设置会话创建策略为无状态
                .csrf(AbstractHttpConfigurer::disable) //禁用了跨站请求伪造（CSRF）防护
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //禁用了框架选项
        ;
        // JWT 校验过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
