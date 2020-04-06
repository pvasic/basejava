package com.javaops.web.run;

import com.javaops.web.exception.InsufficientFundsException;

import java.math.BigDecimal;

/**
 * @author Vasichkin Pavel
 */
public class MainDeadlock {

    public static void main(String[] args) {
        Account a1 = new Account(BigDecimal.valueOf(2000000.));
        Account a2 = new Account(BigDecimal.valueOf(3000000.));

        BigDecimal value = BigDecimal.valueOf(1);

        Thread t1 = new Thread(new TransferMoney(a1, a2, value));
        Thread t2 = new Thread(new TransferMoney(a2, a1, value));

        t1.start();
        t2.start();

    }

    private static class Account {
        private BigDecimal count;

//        private int id;

        public Account(BigDecimal count) {
            this.count = count;
        }

        public void deposit(BigDecimal value) {
            count = count.add(value);
        }

        public void credit(BigDecimal value){
            if (count.compareTo(value) < 0) {
                throw new InsufficientFundsException("count = " + count + ". value = " + value);
            } else {
                count = count.subtract(value);
            }
        }
    }

    private static void transfer(Account a1, Account a2, BigDecimal value){
        synchronized (a1) {
            synchronized (a2) {
                a1.credit(value);
                a2.deposit(value);
                System.out.println(Thread.currentThread().getName() + "  a1 count = " + a1.count);
                System.out.println(Thread.currentThread().getName() + "  a2 count = " + a1.count);
            }
        }
    }

    private static final class TransferMoney implements Runnable {

        private Account a1;
        private Account a2;
        private BigDecimal value;

        public TransferMoney(Account a1, Account a2, BigDecimal value) {
            this.a1 = a1;
            this.a2 = a2;
            this.value = value;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                try {
                    transfer(a1, a2, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
