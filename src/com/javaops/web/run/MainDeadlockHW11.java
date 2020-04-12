package com.javaops.web.run;

public class MainDeadlockHW11 {
    public static void main(String[] args) {

        TestSingleton testSingleton = TestSingleton.getInstance();

        new Thread(() ->{
            synchronized (testSingleton) {
                System.out.printf("%s  %s",Thread.currentThread().getName(), testSingleton.getConnectDataBase());
            }
        }, "Thread-1").start();
    }
}
