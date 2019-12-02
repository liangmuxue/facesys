package com.ss.util.nasstorage.uuid;

import java.util.UUID;


public class UUIDUtils {

    public static String get32UUID() {
        return get36UUID().replace("-", "");
    }

    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

}
