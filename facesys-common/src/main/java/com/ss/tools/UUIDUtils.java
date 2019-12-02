package com.ss.tools;

import java.util.UUID;


public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getRangeString(int length) {
        return getUUID().substring(0, length);
    }

}
