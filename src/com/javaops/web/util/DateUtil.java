package com.javaops.web.util;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author Vasichkin Pavel
 */
public class DateUtil {
    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
}
