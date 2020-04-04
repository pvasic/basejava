package com.javaops.web.util;

/**
 * @author Vasichkin Pavel
 */
public class LazySingleton {
    volatile private static LazySingleton INSTANCE;

    private LazySingleton() {
    }

//    public static LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new LazySingleton();
//        }
//        return INSTANCE;
//    }

    private static class LazySingletonHolder{
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
    }
}
