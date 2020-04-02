package com.javaops.web.run;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasichkin Pavel
 */
public class MainConcurrency {
    public static final int THREAD_NUMBER = 10000;
    private static int counter;
    private static final Object LOCK = new Object();


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + Thread.currentThread().getState());
            }
        }).start();

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        System.out.println(thread0.getState());

        List<Thread> threads = new ArrayList<>(THREAD_NUMBER);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    increment();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);

    }

    private static void increment() {
        double a = Math.sin(13.);
        synchronized (LOCK) {
            counter++;
        }
    }
}

