package com.przemo.concurrency.concurrentcollections;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements Runnable {
    private final ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            System.out.println(Thread.currentThread().getName() + " first: " + list.pollFirst());
            System.out.println(Thread.currentThread().getName() + "last: " + list.pollLast());
        }
    }
}
