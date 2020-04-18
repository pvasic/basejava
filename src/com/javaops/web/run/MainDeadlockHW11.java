package com.javaops.web.run;

public class MainDeadlockHW11 {

    private static volatile int counter = 0;

    private static final Object ACCOUNT1 = new Object();
    private static final Object ACCOUNT2 = new Object();

    public static void main(String[] args) {
        deadlock01();
        deadlock02();
    }

    private static void deadlock02() {
        Thread t0 = new Thread(() -> {
            synchronized (ACCOUNT2) {
                synchronized (ACCOUNT1) {
                    counter++;
                }
            }
        });
        new Thread(() -> {
            synchronized (ACCOUNT1) {
                t0.start();
                try {
                    t0.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (ACCOUNT2) {
                    counter++;
                }
            }
        }).start();
    }

    private static void deadlock01() {
        new Thread(() -> {
            synchronized (ACCOUNT1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (ACCOUNT2) {
                    counter++;
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (ACCOUNT2) {
                synchronized (ACCOUNT1) {
                    counter++;
                }
            }
        }).start();
    }
}
