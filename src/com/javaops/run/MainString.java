package com.javaops.run;

import java.util.Date;
import java.util.UUID;

public class MainString {

    public static void main(String[] args) {

        String[] strArray = {"1", "2", "3", "4", "5"};
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strArray) {
            stringBuilder.append(str).append(",");
        }
        System.out.println(stringBuilder.toString());

        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);// In string pool нет 2-х одинаковых строк

        String s3 = "abc";
        String s4 = "c";
        String s5 = "ab" + s4;
        System.out.println(s3 == s5);// s4 создается на этапе компиляции, а s5 в runtime

        String s6 = "abc";
        String s7 = "c";
        String s8 = ("ab" + s4).intern();
        System.out.println(s6 == s8);// intern принудительно проверяет в string pool если есть строка, то новую он не создает

        String s = "Hello";
        char ch = s.charAt(3);
        System.out.println(ch);

        int n = s.codePointAt(3);
        System.out.println(n); // Index unicode

        String s9 = s.concat(" Pavel!");
        System.out.println(s9);

        System.out.println(s9.contains("Pa"));
        System.out.println(s9.compareToIgnoreCase("hello pavel!")); // ???

        System.out.println(s9.endsWith("l!")); // true, if current string suffix exist or ==

        Date d = new Date();
        String s10 = String.format("%1$50s   It's I am    Day = %2$td-%2$tm-%2$ty", s9, d);
        System.out.println(s10);

        String[] array = s10.trim().split("\\s*"); // String regex
        for (String s11 : array) {
            System.out.print(s11);
        }

        System.out.println();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("hello").append(true).append(7565.56485).append('&');
        System.out.println(stringBuilder1.toString());

        System.out.println(UUID.randomUUID().toString());
    }
}
