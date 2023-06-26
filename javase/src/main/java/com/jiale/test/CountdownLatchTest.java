package com.jiale.test;

import java.util.concurrent.CountDownLatch;

public class CountdownLatchTest {
    public static void main(String[] args) {
        testCountdownLatch();
    }
    public static void testCountdownLatch(){
        CountDownLatch countdownLatch = new CountDownLatch(3);
        ThreadUtils.submit(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行任务");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"结束执行任务");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },countdownLatch);
        ThreadUtils.submit(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行任务");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"结束执行任务");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },countdownLatch);
        ThreadUtils.submit(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行任务");
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName()+"结束执行任务");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },countdownLatch);
    }
}
