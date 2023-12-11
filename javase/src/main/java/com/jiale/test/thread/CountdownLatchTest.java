package com.jiale.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        testCountdownLatch();
    }
    public static void testCountdownLatch() throws InterruptedException {
        CountDownLatch countdownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行任务");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"结束执行任务");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                countdownLatch.countDown();
            }
        });
        executorService.submit(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行任务");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"结束执行任务");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                countdownLatch.countDown();
            }
        },countdownLatch);
        executorService.submit(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行任务");
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName()+"结束执行任务");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                countdownLatch.countDown();
            }
        },countdownLatch);
        countdownLatch.await();
        System.out.println(countdownLatch.getCount());
        executorService.shutdown();

    }
}
