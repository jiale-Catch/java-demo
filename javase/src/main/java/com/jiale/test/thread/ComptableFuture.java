package com.jiale.test.thread;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ComptableFuture {
    public static void main(String[] args) {
        ArrayList<CompletableFuture<?>> futureTasks = new ArrayList<>();
        futureTasks.add(CompletableFuture.runAsync(()-> System.out.println(Thread.currentThread().getName()+":hello")));
        futureTasks.add(CompletableFuture.runAsync(()-> {System.out.println(Thread.currentThread().getName()+":hello");
            try {
                Thread.sleep(2000);
                System.out.println("lines");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        CompletableFuture<Void> future = CompletableFuture.allOf(futureTasks.toArray(new CompletableFuture[0]));
        future.join();
    }
}
