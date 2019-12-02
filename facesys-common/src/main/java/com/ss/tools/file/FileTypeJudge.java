package com.ss.tools.file;

import java.io.IOException;


public class FileTypeJudge {

    public static FileType getType(byte[] b) throws IOException {
        byte[] dest = new byte[28];
        System.arraycopy(b, 0, dest, 0, 28);
        String fileHead = bytesToHex(dest);
        if (fileHead != null && fileHead.length() != 0) {
            fileHead = fileHead.toUpperCase();
            FileType[] fileTypes = FileType.values();
            FileType[] var4 = fileTypes;
            int var5 = fileTypes.length;

            for (int var6 = 0; var6 < var5; var6++) {
                FileType type = var4[var6];
                if (fileHead.startsWith(type.getValue())) {
                    return type;
                }
            }

            return null;
        }
        return null;
    }


    public static String bytesToHex(byte[] src) {
        StringBuilder str = new StringBuilder();
        if (src != null && src.length > 0) {
            for (int i = 0; i < src.length; i++) {
                String hv = Integer.toHexString(src[i] & 0xFF);
                if (hv.length() < 2) {
                    str.append("0");
                }

                str.append(hv);
            }

            return str.toString();
        }
        return null;
    }

}
