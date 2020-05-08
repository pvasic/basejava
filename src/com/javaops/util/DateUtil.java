package com.javaops.util;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author Vasichkin Pavel
 */
public class DateUtil {

    // NOW is special case. NULL is bad.
    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
    public static LocalDate of(String date) {
        return LocalDate.parse(date);
    }
}
