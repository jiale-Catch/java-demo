package com.jiale.test;

public class ThreadTest {
    public static void main(String[] arg){
        threadTest();
    }

     private static void threadTest(){
            ThreadUtils.execute(()-> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"----->测试thread ");});
            ThreadUtils.execute(()-> System.out.println(Thread.currentThread().getName()+"----->测试thread2 "));
            ThreadUtils.execute(()-> System.out.println(Thread.currentThread().getName()+"----->测试thread 3"));
            ThreadUtils.execute(()-> System.out.println(Thread.currentThread().getName()+"----->测试thread 4"));
     }

}
