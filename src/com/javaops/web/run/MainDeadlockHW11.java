package com.javaops.web.run;

public class MainDeadlockHW11 {

    private static volatile int counter = 0;

    private static final Object ACCOUNT1 = new Object();
    private static final Object ACCOUNT2 = new Object();

    public static void main(String[] args) {
        deadlock01();
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
