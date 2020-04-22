package com.javaops.web.run;

import java.util.*;
import java.util.stream.Collectors;

public class MainHW12 {

    private static int min;

    public static void main(String[] args) {

//        TODO
//        реализовать метод через стрим int minValue(int[] values).
//        Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число, составленное из этих уникальных цифр.
//        Не использовать преобразование в строку и обратно. Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
        System.out.println(minValue(new int[]{9,1, 2, 3, 3, 2, 3}));

//        todo
//        реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
//        Сложность алгоритма должна быть O(N). Optional - решение в один стрим.


    }

    private static int minValue(int[] values) {
       return min = Arrays.stream(values).
                boxed().
                sorted().
                collect(Collectors.toCollection(LinkedHashSet::new)).
                stream().
                reduce(0, (left, right) -> left*10 + right);
    }
}
