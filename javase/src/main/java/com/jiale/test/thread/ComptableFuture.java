package com.jiale.test.thread;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ComptableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<CompletableFuture<?>> futureTasks = new ArrayList<>();
        futureTasks.add(CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName() + ":hello")));
        futureTasks.add(CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ":hello");
            try {
                Thread.sleep(2000);
                System.out.println("lines");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        futureTasks.add(CompletableFuture.supplyAsync(()-> {
            System.out.println(Thread.currentThread().getName() + ":hello");
            try {
                Thread.sleep(4000);
                System.out.println("lines");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return  200 ;
        }));
        CompletableFuture<Void> future = CompletableFuture.allOf(futureTasks.toArray(new CompletableFuture[0]));
        future.join();
    }
}
