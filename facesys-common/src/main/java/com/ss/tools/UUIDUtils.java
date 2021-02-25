package com.ss.tools;

import java.util.UUID;


public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getRangeString(int length) {
        return getUUID().substring(0, length);
    }

    /**
     * 按照规则生成ID
     * @param libId
     * @param datetime
     * @param seq
     * @return
     */
    public static Long encodeToFaceId(Integer libId, Integer datetime, Integer seq) {
        //规则：libId--占6位 seq--4位 合并成一个int，然后再与时间戳int进行mask合并为long
        Long part = new Long(libId * 10000 + seq);
        Long l = (part << 32) | (datetime & 0xffffffffL);
        return l;
    }

}
