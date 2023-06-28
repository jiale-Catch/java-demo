package com.jiale;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * jdk11 一些新特性的测试
 */
public class JDK11Test {
    public static void main(String[] args) {
        testStringApi();
        testCollection();
        testHttpClient();
    }

    /**
     * 测试String新增的方法
     */
    public static  void testStringApi(){
        //判断字符串是否空白
        System.out.println(" ".isBlank());
        //去除首尾空白 (与trim()存在区别，可去除所有的空格，包括中文空格)
        System.out.println(" javaTask ".strip());

        System.out.println("javaTask ".stripTrailing());
        System.out.println( " javaTask".stripLeading());

        // 复制字符串
        System.out.println("Java".repeat(3));
    }

    public static void testCollection(){
        var list = Arrays.asList("a","b","c");
        List<String> strings = List.copyOf(list);
        var list1 = List.of("a","b","c");
        List<String> strings1 = List.copyOf(list1);
        System.out.println(strings == list);
        System.out.println(strings1 == list1);
    }

    public static  void testHttpClient(){
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
}
