package com.ss.isc.sync.data.ftp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FtpDateUtil {

    public static final SimpleDateFormat FORMAT_SECOND = new SimpleDateFormat("yyyyMMddHHmmss");

    public static final SimpleDateFormat FORMAT_DAY = new SimpleDateFormat("yyyyMMdd");


    public static String getTimeRegex(int forward, int type) {
        int second = 5;
        String resultStr = null;
        Calendar c = Calendar.getInstance();
        c.add(type, forward);
        if (13 == type) {

            StringBuffer sbf = new StringBuffer();
            sbf.append("(");
            for (int i = 0; i < second; i++) {
                sbf.append(FORMAT_SECOND.format(c.getTime()));
                if (i < second - 1) {
                    sbf.append("|");
                }
                c.add(13, 1);
            }
            sbf.append(")");
            resultStr = sbf.toString();
        } else if (5 == type) {
            resultStr = FORMAT_DAY.format(c.getTime());
        }

        return resultStr;
    }

    public static String formatDate(long time, String dateFormat) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

}
