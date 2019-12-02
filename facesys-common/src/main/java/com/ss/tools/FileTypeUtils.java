package com.ss.tools;

import com.ss.enums.FileTypeEnum;


public class FileTypeUtils {

    public static String getType(byte[] b) {
        byte[] dest = new byte[28];
        System.arraycopy(b, 0, dest, 0, 28);

        String fileHead = HexUtils.bytesToHex(dest);
        if (fileHead == null || fileHead.length() == 0) {
            return null;
        }

        fileHead = fileHead.toUpperCase();
        FileTypeEnum[] fileTypes = FileTypeEnum.values();

        for (FileTypeEnum type : fileTypes) {
            if (fileHead.startsWith(type.getValue())) {
                return type.name();
            }
        }

        return null;
    }

}
