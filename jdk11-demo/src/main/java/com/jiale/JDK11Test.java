package com.jiale;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;

/**
 * jdk11 一些新特性的测试
 */
public class JDK11Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        testStringApi();
        testCollection();
        testHttpClient();
        threadTest();
        test();
    }

    /**
     * 测试String新增的方法
     */
    public static void testStringApi() {
        //判断字符串是否空白
        System.out.println(" ".isBlank());
        //去除首尾空白 (与trim()存在区别，可去除所有的空格，包括中文空格)
        System.out.println(" javaTask ".strip());

        System.out.println("javaTask ".stripTrailing());
        System.out.println(" javaTask".stripLeading());

        // 复制字符串
        System.out.println("Java".repeat(3));
    }

    public static void testCollection() {
        var list = Arrays.asList("a", "b", "c");
        List<String> strings = List.copyOf(list);
        var list1 = List.of("a", "b", "c");
        List<String> strings1 = List.copyOf(list1);
        System.out.println(strings == list);
        System.out.println(strings1 == list1);
    }

    public static void testHttpClient() {
        //便捷创建 httpClient对象
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest requestSimple = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).build();
        try {
            //同步发送
            HttpResponse<String> send = httpClient.send(requestSimple, HttpResponse.BodyHandlers.ofString());
            System.out.println(send.body());
            httpClient.sendAsync(requestSimple, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void test() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        Map<String,String> params=new HashMap<>();
        params.put("ip","187.43.23.43");  //
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://ip.ws.126.net/ipquery?ip=187.43.23.43")).GET().build();

        HttpResponse send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = (String) send.body();
        // 字符串解析
        String resultStr = body;
        int provinceNum = resultStr.indexOf("lo") +4 ;
        int cityNum = resultStr.indexOf("lc")  +4;
        String province = resultStr.trim().substring(provinceNum,provinceNum+3);
        String city = resultStr.trim().substring(cityNum,cityNum+3);
        System.out.println("province:"+province+" , city:" +city);
    }

    private static final Object LOCK = new Object();

    private static int number = 1;
    private static final int MAX = 100;

    public static void threadTest() {
        Thread oddThread = new Thread(() -> {
            while (number <= MAX) {
                synchronized (LOCK) {
                    if (number % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + number);
                        number++;
                        LOCK.notify();
                    } else {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "OddThread");
        Thread evenThread = new Thread(() -> {
            while (number <= MAX) {
                synchronized (LOCK) {
                    if (number % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + number);
                        number++;
                        LOCK.notify();
                    } else {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "EvenThread");
        oddThread.start();
        evenThread.start();
    }

    private static volatile boolean isOdd = true; // true means Odd thread should print, false means Even thread should print

    public static void test2() {
        Thread oddThread = new Thread(() -> {
            while (number <= MAX) {
                if (isOdd) {
                    System.out.println(Thread.currentThread().getName() + " : " + number);
                    number++;
                    isOdd = false;
                }
            }
        }, "OddThread");
        Thread evenThread = new Thread(() -> {
            while (number <= MAX) {
                if (!isOdd) {
                    System.out.println(Thread.currentThread().getName() + " : " + number);
                    number++;
                    isOdd = true;
                }
            }
        }, "EvenThread");
        oddThread.start();
        evenThread.start();
    }


}
