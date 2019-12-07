package com.javaops.web.run;

import com.javaops.web.model.Resume;
import com.javaops.web.storage.SortedArrayStorage;
import com.javaops.web.storage.Storage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        //TODO toString (via)across reflection (method - invoke - object)
        Method method = Class.forName("com.javaops.web.model.Resume").getMethod("toString");
        System.out.println(method.toString());
        Resume resume = new Resume("uuid");
        System.out.println(method.invoke(resume));




        //Field field = resume.getClass().getDeclaredFields()[0];
        //field.setAccessible(true);
        //System.out.println(field.getName());
        //System.out.println(field.get(resume));
        //field.set(resume, "newuuid");
        //System.out.println(resume);

        //Field field1 = Class.forName("com.javaops.web.storage.AbstractArrayStorage").getDeclaredField("STORAGE_LIMIT");
        //field1.setAccessible(true);
        //System.out.println(field1.getName());
        //System.out.println(field1.getInt(field1));
    }
}
