package com.ss.tools;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);


    private static final String DATE_PATTERN = "(((0[1-9]|[12][0-9]|3[01])/((0[13578]|1[02]))|((0[1-9]|[12][0-9]|30)/(0[469]|11))|(0[1-9]|[1][0-9]|2[0-8])/(02))/([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}))|(29/02/(([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00)))";


    public static final long ONE_DAY_LONG = 86400000L;


    public static final String YYYYMMDD = "yyyyMMdd";


    public static boolean isDate(String str) {
        Pattern pattern = Pattern.compile(
                "(((0[1-9]|[12][0-9]|3[01])/((0[13578]|1[02]))|((0[1-9]|[12][0-9]|30)/(0[469]|11))|(0[1-9]|[1][0-9]|2[0-8])/(02))/([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}))|(29/02/(([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00)))");
        return pattern.matcher(str).matches();
    }


    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }


    public static String timestamp2String(Timestamp time, String pattern) {
        return DateFormatUtils.format(time.getTimestamp(), pattern);
    }


    public static String formatDate(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }


    public static Date formatDate(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            logger.error("日期格式转换失败", e);

            return null;
        }
    }


    public static Date defaultFormat(String str) {
        return formatDate(str, "yyyyMMdd");
    }


    public static int defaultFormat(Date date) {
        return Integer.valueOf(formatDate(date, "yyyyMMdd")).intValue();
    }


    public static int defaultFormat(long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return defaultFormat(cal.getTime());
    }


    public static String parseTimestamp(Timestamp time) {
        return parseDate(time.getTimestamp());
    }


    public static String parseDate(Date date) {
        return formatDate(date, "yyyyMMdd");
    }


    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(1);
    }
    public static String defaultStrFormat(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    //获取当前日期前n个月的第一天和最后一天(num=0本月,num=1前一个月) maomaochong
    public static Map getThisMouthDay(int num) {
        Map datatime = new HashMap();
        Calendar calendar = Calendar.getInstance();
        //获得当前月
        int month = calendar.get(Calendar.MONTH);
        //获得当前月的上一个月
        calendar.set(Calendar.MONTH, month - num);

        //获得当前月的上一个月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date StartDate = calendar.getTime();
        String strStartDate = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(StartDate);
        System.out.println(strStartDate);

        //获得当前月的上一个月的最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date EndDate = calendar.getTime();
        String strEndDate = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(EndDate);
        System.out.println(strEndDate);

        datatime.put("strStartDate", strStartDate);
        datatime.put("strEndDate", strEndDate);
        return datatime;
    }
    //获取n个月之前的第一天
    public static Map getMouthDay(int num) {
        Map datatime = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -num);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        datatime.put("strEndDate",format.format(calendar.getTime()));
        return datatime;
    }
    //获取本周的开始时间
    public static String getBeginDayOfWeek() {
        Calendar cal =Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        return df.format(cal.getTime());
    }
    //n天前的日期
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    //n天前的日期
    public static String getPastDateTime(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = format.format(today);
        return result;
    }

    //将时间转化为时间戳  13位
    public static long getPastTimeStamp(String str) {
        SimpleDateFormat simpleDateFormat =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date dt1;
        try {
            dt1 = simpleDateFormat .parse(str);
            long ts1 = dt1.getTime();
            System.out.println(str+"时间戳" + ts1);
            return ts1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //根据生日计算年龄
    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }


}
