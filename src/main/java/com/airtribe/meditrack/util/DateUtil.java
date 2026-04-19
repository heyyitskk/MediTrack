package com.airtribe.meditrack.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static LocalDateTime parse(String s) {
        return LocalDateTime.parse(s, ISO);
    }

    public static String format(LocalDateTime dt) {
        return dt.format(ISO);
    }
}
