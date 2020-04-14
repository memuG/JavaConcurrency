package com.przemo.concurrency.forkjoinpool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);

        Task task = new Task(products, 0, products.size(), 0.20);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // async call to execute
        forkJoinPool.execute(task);

        do {
            System.out.println("***********************");
            System.out.println("Main: Parallelism: " + forkJoinPool.getParallelism());
            System.out.println("Main: Active Threads: " + forkJoinPool.getActiveThreadCount());
            System.out.println("Main: Thread count: " + forkJoinPool.getQueuedTaskCount());
            System.out.println("Main: Steal count: " + forkJoinPool.getStealCount());
            System.out.println("***********************");
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        // important - close the pool
        forkJoinPool.shutdown();

        if (task.isCompletedNormally()) {
            System.out.println("Main: Task has been processed normally.\n");
        }
        System.out.println("Main: End of the program.\n");
    }
}
