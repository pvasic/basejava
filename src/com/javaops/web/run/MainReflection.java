package com.javaops.web.run;

import com.javaops.web.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume();
        System.out.println(resume);
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true); // НЕ ПОЛЬЗОВАТЬСЯ, разрешает менять private модификаторы
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "newuuid");
        System.out.println(resume);

        //TODO toString (via)across reflection (method - invoke - object) getAnnotation
    }
}
