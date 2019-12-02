package com.ss.tools;

import org.apache.commons.codec.digest.DigestUtils;


public class MD5Utils {

    public static String encode(String str) {
        return DigestUtils.md5Hex(str);
    }

}
