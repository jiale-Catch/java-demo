package com.jiale.aopjdkproxy.proxy;

import com.jiale.aopjdkproxy.service.UserService;
import com.jiale.aopjdkproxy.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserProxy {

    private UserService target;

    public UserProxy(UserServiceImpl target){
        super();
        this.target = target;
    }


    public UserService getServiceProxy(){
        UserService proxy;
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class[] interfaces = new Class[]{UserService.class};
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
                String methodName = method.getName();
                // log - before method
                System.out.println("[before] execute method: " + methodName);

                // call method
                Object result = null;
                try {
                    // 前置通知
                    result = method.invoke(target, objects);
                    // 返回通知, 可以访问到方法的返回值
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    // 异常通知, 可以访问到方法出现的异常
                }
                // 后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值

                // log - after method
                System.out.println("[after] execute method: " + methodName + ", return value: " + result);
                return result;
            }

            };
            proxy = (UserService) Proxy.newProxyInstance(classLoader,interfaces,handler);
            return proxy;
        }

}
