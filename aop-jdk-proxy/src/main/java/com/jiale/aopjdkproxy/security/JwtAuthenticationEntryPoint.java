package com.jiale.aopjdkproxy.security;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jiale.aopjdkproxy.domain.resp.ResponseStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt 认证进入点 【认证失败处理类 返回未授权】
 *
 * @author 资家乐
 * @date 2023/7/27 14:08
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        String msg = String.format("请求访问：%s，认证失败，无法访问系统资源", request.getRequestURI());
        response.setStatus(ResponseStatus.SUCCESS.getResponseCode());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", ResponseStatus.HTTP_STATUS_401.getResponseCode());
        result.put("msg", msg);
        result.put("data", "token无效或过期,请重新登录");
        response.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
