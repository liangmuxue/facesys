package com.ss.tools;


public class HexUtils {

    public static String bytesToHex(byte[] src) {
        StringBuilder str = new StringBuilder();

        if (src == null || src.length <= 0) {
            return null;
        }

        for (int i = 0; i < src.length; i++) {
            String hv = Integer.toHexString(src[i] & 0xFF);
            if (hv.length() < 2) {
                str.append("0");
            }

            str.append(hv);
        }

        return str.toString();
    }

}
