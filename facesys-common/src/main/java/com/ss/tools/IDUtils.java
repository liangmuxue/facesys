package com.ss.tools;

import java.util.Date;


public class IDUtils {
    private static final String PATTERN = "yyyyMMddHHmmss";
    private static final long W = 1000L;
    private static byte[] LOCK = new byte[0];

    public static String createMonitorID(String orgCode) {
        long r = 0L;
        synchronized (LOCK) {
            r = (long) ((Math.random() + 1.0D) * 1000.0D);
        }

        return orgCode + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + String.valueOf(r);
    }
}
