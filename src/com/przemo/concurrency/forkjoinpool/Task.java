package com.przemo.concurrency.forkjoinpool;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {
    private List<Product> products;

    private int first;
    private int last;

    private double increment;


    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        // if the batch of elements lower 10 -> perform computation
        if (last - first < 10) {
            System.out.println("Performing computation...");
            updatePrices();
        } else {
            // create two tasks for each half of the work -> invokeAll
            int middle = (last + first) / 2;
            System.out.println("Task: pending tasks: " + getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            invokeAll(t1, t2);
        }
    }

    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product p = products.get(i);
            p.setPrice(p.getPrice() * (1 + increment));
        }
    }
}
