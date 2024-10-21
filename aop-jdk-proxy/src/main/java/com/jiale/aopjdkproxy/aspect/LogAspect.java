package com.jiale.aopjdkproxy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LogAspect {

    /**
     * 环绕通知.
     *
     * @param pjp pjp
     * @return obj
     * @throws Throwable exception
     */
    @Around("execution(public * com.jiale.aopjdkproxy.service.UserService.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("-----------------------");
        System.out.println("环绕通知: 进入方法");
        Object o = pjp.proceed();
        System.out.println("环绕通知: 退出方法");
        return o;
    }

    /**
     * 前置通知.
     */
    @Before("execution(* com.jiale.aopjdkproxy.service.impl.UserServiceImpl.*(..))")
    public void doBefore() {
        System.out.println("前置通知");
    }

    /**
     * 后置通知.
     *
     * @param result return val
     */
    @AfterReturning(pointcut = "execution(* com.jiale.aopjdkproxy.service.impl.UserServiceImpl.findUserList())" , returning = "result")
    public void doAfterReturning(String result) {
        System.out.println("后置通知, 返回值: " + result);
    }

    /**
     * 异常通知.
     *
     * @param e exception
     */
    @AfterThrowing(pointcut =  "execution(* com.jiale.aopjdkproxy.service.impl.UserServiceImpl.*(..))" , throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println("异常通知, 异常: " + e.getMessage());
    }

    /**
     * 最终通知.
     */
    @After("execution(* com.jiale.aopjdkproxy.service.impl.UserServiceImpl.*(..))")
    public void doAfter() {
        System.out.println("最终通知");
    }
}
