package com.javaops.web.run;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainHW12 {

    public static void main(String[] args) {

//        TODO
//        реализовать метод через стрим int minValue(int[] values).
//        Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число, составленное из этих уникальных цифр.
//        Не использовать преобразование в строку и обратно. Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
        System.out.println(minValue(new int[]{9, 1, 2, 3, 3, 2, 3}));

//        todo
//        реализовать метод List<Integer> oddOrEven(List<Integer> integers).
//        если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
//        Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
        oddOrEven(Arrays.asList(100, 9, 1, 2, 3, -49, 3, 0, 2, 3)).forEach(System.out::println);
//        oddOrEven2(Arrays.asList(100, 9, 1, 2, 3, -49, 3, 0, 2, 3)).forEach(System.out::println);


    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).
                boxed().
                distinct().
                sorted().
                reduce(0, (left, right) -> left * 10 + right);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        AtomicInteger sum = new AtomicInteger();
        integers.forEach(val -> {
            sum.addAndGet(val);
            if ((val & 1) != 1) {
                odd.add(val);
            } else {
                even.add(val);
            }
        });
        if ((sum.intValue() & 1) != 1) {
            return odd;
        } else {
            return even;
        }
    }

//    private static List<Integer> oddOrEven2(List<Integer> integers) {
//
//    }
}
