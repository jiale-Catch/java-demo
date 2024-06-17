package com.jiale.springsecurity.framework.filter;

import com.jiale.springsecurity.moudle.model.Claims;
import com.jiale.springsecurity.moudle.model.ResponseEntity;
import com.jiale.springsecurity.moudle.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取当前请求的uri
        String uri = request.getRequestURI();
        logger.info("请求路径:" + uri);
        //判断是否是认证请求路径
        //是：直接放行
        if (uri.endsWith("/auth/login") || uri.endsWith("/auth/logout") || uri.startsWith("/swagger-ui")
                || uri.endsWith("doc.html") || uri.startsWith("/webjars/css") || uri.startsWith("/webjars/js")
                || uri.startsWith("/v3/api-docs") || uri.startsWith("/favicon.ico")
                || uri.startsWith("**/*.html") || uri.endsWith("/webjars/springfox-swagger-ui")
                || uri.startsWith("/swagger-resources")) {
            filterChain.doFilter(request, response);
            return;
        }

        //否：获取请求头中携带的token
        String authorization = request.getHeader("Authorization");
        logger.info("携带authorization:" + authorization);

        //判断是否携带token
        //否：抛出异常
        if (StringUtils.isBlank(authorization)) {
            logger.info("未查询到token");
            return;
        }

        String realToken = authorization.replace("Bearer ", "");


        //是：校验jwt有效性
        ResponseEntity responseEntity = JWTUtil.verifyToken(realToken);
        Claims data = (Claims) responseEntity.getData();

        if (ObjectUtils.isEmpty(data)) {
            logger.info("token失效");
            return;
        }

        // 验证token对象是否存在及验证token是否过期
        if (ObjectUtils.isEmpty(data)) {
            logger.info("token无效或者已经失效");
            return;
        }
        if (responseEntity.getCode() != 200) {
            logger.info("token无效");
            return;
        }

        filterChain.doFilter(request, response);
    }

}
