package com.przemo.concurrency.concurrentcollections;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];
        // Create 100 AddTask objects and execute them as Threads
        for (int i = 0; i < threads.length; i++) {
            AddTask at = new AddTask(list);
            threads[i] = new Thread(at);
            threads[i].start();
        }
        System.out.println("Main: AddTask threads [" + threads.length + "] have been launched");

        // Wait for all the AddTask Threads to complete
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main: Size of the List: " + list.size());

        // Create 100 PollTask objects and execute them as Threads
        for (int i = 0; i < threads.length; i++) {
            PollTask pt = new PollTask(list);
            threads[i] = new Thread(pt);
            threads[i].start();
        }
        System.out.println("Main: PollTask threads [" + threads.length + "] have been launched");

        // Wait for all the PollTask Threads to complete
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main: Size of the List: " + list.size());
    }
}
