package com.est.curdsample.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {

    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static LocalDate convertToLocalDate(String localDateStr) {
        return LocalDate.parse(localDateStr, pattern);
    }

    public static String convertToString(LocalDate localDate) {
        return localDate.format(pattern);
    }
    public static String convertToString(LocalDateTime localDateTime) {
        return localDateTime.format(pattern);
    }
}
