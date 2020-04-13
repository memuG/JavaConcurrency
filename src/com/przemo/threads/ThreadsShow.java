package com.przemo.threads;

public class ThreadsShow {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from MyThread!");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello from MyRunnable");
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread myThread = new MyThread();

        Runnable r = () -> {System.out.println("Custom in-place Runnable");};

        // Thread anonymousThread = new Thread(new Runnable(
        //     {
        //         public void run() {
        //             System.out.println("Anonymous runnable!");
        //         }
            
        //     };

        // ))
        Thread runnablThread = new Thread(new MyRunnable(), "MyRunnableThread");

        myThread.start();
        runnablThread.start();
        System.out.println("Main continues...");
    }
}