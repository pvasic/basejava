package com.javaops.web.run;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainHW12 {

    public static void main(String[] args) {

//        реализовать метод через стрим int minValue(int[] values).
//        Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число, составленное из этих уникальных цифр.
//        Не использовать преобразование в строку и обратно. Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
        System.out.println(minValue(new int[]{9, 1, 2, 3, 3, 2, 3}));

//        реализовать метод List<Integer> oddOrEven(List<Integer> integers).
//        если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
//        Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
        oddOrEven(Arrays.asList(101, 9, 1, 2, 3, -49, 3, 0, 2, 3)).forEach(System.out::println);
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).
                distinct().
                sorted().
                reduce(0, (left, right) -> left * 10 + right);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        AtomicInteger sum = new AtomicInteger();
        return integers.stream()
                .peek(sum::addAndGet)
                .collect(Collectors.partitioningBy(val -> ((val & 1) == 1)))
                .get(((sum.intValue() & 1) == 1));
    }
}
