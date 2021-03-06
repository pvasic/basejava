package com.javaops.run;

import com.javaops.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Vasichkin Pavel
 * Invoke toString() method via reflection
 */

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

        Method method = Class.forName("com.javaops.model.Resume").getMethod("toString");
        System.out.println(method.toString());
        Resume resume = new Resume("uuid");
        System.out.println(method.invoke(resume));
    }
}
