package com.jiale.aopjdkproxy.aspect.handle;

import com.jiale.aopjdkproxy.annotation.IgnoreAccess;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Order(0)
@Slf4j
public class IgnoreAccessHandle {

    @Resource
    private final ApplicationContext applicationContext;

    public IgnoreAccessHandle(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public List<String> getIgnoreList(){
       Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        List<String> ignoreList = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            IgnoreAccess annotation = infoEntry.getValue().getMethodAnnotation(IgnoreAccess.class);
            if (annotation != null) {
                PathPatternsRequestCondition pathPatternsCondition = infoEntry.getKey().getPathPatternsCondition();
                if(pathPatternsCondition == null){
                    continue;
                }
                Set<String> patterns = pathPatternsCondition.getPatterns().stream().map(PathPattern::getPatternString).collect(Collectors.toSet());
                patterns.forEach(pattern -> {
                    log.info("忽略请求路径:{}", pattern);
                });
                ignoreList.addAll(patterns);
            }
        };
        return ignoreList;
    }


}
