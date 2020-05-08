package com.javaops.run;

/**
 * @author Vasichkin Pavel
 */
public class TestSingleton {
    private static TestSingleton instance;

    private final String connectDataBase = "Connect";

    public static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    private TestSingleton() {
    }

    public  String getConnectDataBase() {
        return connectDataBase;
    }

//    public static void main(String[] args) {
//        TestSingleton.getInstance().toString();
//        Singleton instance = Singleton.valueOf("INSTANCE");
//        System.out.println(instance.ordinal()); // ordinal number
//
//        for (SectionType type : SectionType.values()) {
//            System.out.println(type.getTitle());
//        }
//    }
//
//    public enum Singleton {
//        INSTANCE

//    }
}
