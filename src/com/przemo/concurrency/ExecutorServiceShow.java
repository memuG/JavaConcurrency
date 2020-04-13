package com.przemo.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import sun.jvm.hotspot.utilities.soql.Callable;

public class ExecutorServiceShow {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Submitted task - getting Future back");
            }
        });

        try {
            System.out.println("future.get() : " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Future<?> callablFuture = executorService.submit(new Callable() {
        //     @Override
        //     public Object call() throws Exception {
        //         System.out.println("Submitted task - getting Future back");
        //         return "Callable result";
        //     }
        // });

        try {
            System.out.println("future.get() : " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Main goes on...");
        executorService.shutdown();
    }

}