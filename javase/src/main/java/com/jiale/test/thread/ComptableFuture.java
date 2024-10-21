package com.jiale.test.thread;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ComptableFuture {
    public static void main(String[] args)  {
       test();

        System.out.println("hello");
    }


    public static void  test(){
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        Future<Object> future = threadPoolExecutor.submit(() -> {
            doSomething();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":hello");
            return null;
        });
        ThreadUtils.execute(() -> {
            try {
                future.get(1, TimeUnit.SECONDS);
            } catch (Exception e) {
                future.cancel(true);
                System.out.println("线程取消成功：" + future.isCancelled());
                System.out.println("目前线程数为： "+threadPoolExecutor.getActiveCount() + ":hello");
            }
        });
        System.out.println("目前线程数为： "+threadPoolExecutor.getActiveCount() + ":hello");

    }

    public static void doSomething() throws InterruptedException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("线程被中断" + e.getMessage());
            throw new InterruptedException(e.getMessage());
        }
        System.out.println("doSomething");
    }
    public static ThreadPoolExecutor getThreadPoolExecutor(){
        return new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }
}
