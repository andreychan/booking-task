package com.bookingtask.util;

import java.time.LocalDateTime;

public class Utils {

    public static String getTodayDate() {
        return LocalDateTime.now().toLocalDate().toString();
    }

    public static String getTodayDatePlusDays(int days) {
        return LocalDateTime.now().plusDays(days).toLocalDate().toString();
    }

}