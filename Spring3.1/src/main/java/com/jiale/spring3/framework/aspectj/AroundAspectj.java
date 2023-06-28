package com.jiale.spring3.framework.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;
@Component
@Aspect
public class AroundAspectj {
    /**
     * 环绕通知
     * @param joinPoint 切入点 可以从这里面获取到切入的参数，joinPoint.arg()
     * @return
     * @throws Throwable
     */
    @Around("execution(public * com.jiale.spring3.moudle.testService.testAround())")
    public Object doSomething(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("i do something in this function");
        return joinPoint.proceed();
    }
    @Before("execution(public * com.jiale.spring3.moudle.testService.testAround())")
    public Object doSomethingBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("i do something before this function");
        return joinPoint;
    }

    @After("execution(public * com.jiale.spring3.moudle.testService.testAround())")
    public Object doSomethingAfter(JoinPoint joinPoint) throws Throwable {
        System.out.println("i do something after this function");
        return joinPoint;
    }
}
