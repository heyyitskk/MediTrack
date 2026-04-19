package com.airtribe.meditrack.util;

public class Validator {
    public static void requireNonNull(Object o, String message) {
        if (o == null)
            throw new IllegalArgumentException(message);
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
