package com.learning.recruiter.util;

import java.util.UUID;

public class Util {
    private Util() {}

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static String cleanString(String data) {
        return null != data ? data.trim().toLowerCase() : null;
    }
}
