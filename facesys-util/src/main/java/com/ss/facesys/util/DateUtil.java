package com.ss.facesys.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static final String YEAR_FORMAT = "yyyy";
    private static final String DEFALT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFALT_HOUR_FORMAT = "HH";
    private static final String DEFALT_MINUTE_FORMAT = "mm";
    private static final String DEFALT_SECOND_FORMAT = "ss";
    private static final String DEFALT_HOURMIN_FORMAT = "HH:mm";
    private static final String DEFALT_HOURMINSECOND_FORMAT = "HH:mm:ss";
    private static final String DEFUALT_TIMESTAMPE_FORMAT = "yyyy-MM-dd hh:mm:ss sss";
    public static final String DATE_FORMATE_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    private static final String DATE_FORMATE_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATE_YYMMDD = "yyMMdd";
    public static final String DATE_FORMATE_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMATE_YYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATE_TIME_STR = " 00:00:00";

    // 新增变量-ys-20190711
    private static Date today;
    private static Calendar calendar;
    private static GregorianCalendar cal;


    public static String getCurrentSqlTimestampString() {
        today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DEFUALT_TIMESTAMPE_FORMAT);
        return sdf.format(today);
    }


    public static String getCurrentSqlTimestampString(String formatStr) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(today);
    }


    public static String getDateStringByFormat(String format) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(today);
    }

    public static String getDateStringByFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


    public static String formatTimeStamp(Timestamp time, String dateFormat) {
        if (time == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(time);
    }


    public static Timestamp getCurrentSqlTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }


    public static String getCurrentDay() {
        today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("DEFALT_DATE_FORMAT");
        return sdf.format(today);
    }

    public static String getCurrentDay(String format) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(today);
    }


    public static String getYesterdayCurrentDay() {
        calendar = Calendar.getInstance();
        calendar.add(5, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_DATE_FORMAT);
        return sdf.format(calendar.getTime());
    }

    public static String getYesterdayCurrentTime() {
        calendar = Calendar.getInstance();
        calendar.add(5, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(DEFUALT_TIMESTAMPE_FORMAT);
        return sdf.format(calendar.getTime());
    }


    public static String getCurrentDayTime() {
        today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_YYYYMMDDHHMMSS);
        return sdf.format(today);
    }


    public static String formatTimeStampDefault(Timestamp time) {
        if (time == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFUALT_TIMESTAMPE_FORMAT);
        return sdf.format(time);
    }


    public static String formatDateDefault(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_DATE_FORMAT);
        return sdf.format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_YYYYMMDDHHMMSS);
        return sdf.format(date);
    }


    public static Date formatString(String date, String str) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date date2 = null;
        try {
            date2 = sdf.parse(date);
        } catch (ParseException e) {
            logger.error(e.toString(), e);
        }
        return date2;
    }


    public static String getCurrentDate(String formater) {
        SimpleDateFormat format = new SimpleDateFormat(formater);
        return format.format(new Date());
    }


    public static String formatDateByDateFormate(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }


    public static String getCurrentYear() {
        today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(YEAR_FORMAT);
        return sdf.format(today);
    }

    public static Date formatDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat(DEFALT_DATE_FORMAT);
        return df.parse(date);
    }


    public static String getDateAfterDays(int days) {
        Calendar date = Calendar.getInstance();
        date.add(5, days);
        SimpleDateFormat simpleDate = new SimpleDateFormat(DEFALT_DATE_FORMAT);
        return simpleDate.format(date.getTime());
    }

    /**
     * 获取指定年限差后的日期
     *
     * @param years
     * @param format 返回指定格式字符串，不指定默认格式 "yyyyMMdd"
     * @return
     */
    public static String getDateAfterYears(int years, String format) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.YEAR, years);
        SimpleDateFormat simpleDate = new SimpleDateFormat(StringUtils.isBlank(format) ? DATE_FORMATE_YYYYMMDD : format);
        return simpleDate.format(date.getTime());
    }


    public static String addDays(String source, int days) {
        Date date = null;
        try {
            date = formatDate(source);
        } catch (ParseException parseException) {
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, days);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFALT_DATE_FORMAT);
        return dateFormat.format(calendar.getTime());
    }


    public static boolean isValidDate(String str, String format) {
        boolean convertSuccess = true;

        SimpleDateFormat sf = new SimpleDateFormat(format);

        try {
            sf.setLenient(false);
            sf.parse(str);
        } catch (ParseException e) {

            convertSuccess = false;
        }
        return convertSuccess;
    }


    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0L;
        SimpleDateFormat format = new SimpleDateFormat(DEFALT_DATE_FORMAT);


        try {
            Date beginDate = format.parse(beginDateStr);
            Date endDate = format.parse(endDateStr);
            day = (endDate.getTime() - beginDate.getTime()) / 86400000L;
        } catch (ParseException e) {
            throw new RuntimeException("传入日期参数有误!", e);
        }
        return day;
    }


    public static long getDaySub(Date beginDate, Date endDate) {
        long day = 0L;
        return (endDate.getTime() - beginDate.getTime()) / 86400000L;
    }


    public static Date getDayBegin() {
        cal = new GregorianCalendar();
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }


    public static Date getDayEnd() {
        cal = new GregorianCalendar();
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        return cal.getTime();
    }


    public static Date getBeginDayOfYesterday() {
        cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(5, -0);
        return cal.getTime();
    }


    public static Date getEndDayOfYesterDay() {
        cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(5, -0);
        return cal.getTime();
    }

    public static Date getBeginDayOfYesterday(int i) {
        cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(5, -i);
        return cal.getTime();
    }


    public static Date getEndDayOfYesterDay(int i) {
        cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(5, -i);
        return cal.getTime();
    }


    public static Date getEndDayOfSomeDay(Date day) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        return cal.getTime();
    }


    public static Date getBeginDayOfSomeday(int d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(5, -d);
        return cal.getTime();
    }


    public static Date getEndDayOfSomeDay(int d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(5, -d);
        return cal.getTime();
    }


    public static long[] getStartAndEnd(int gap) {
        long[] time = new long[2];
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(5, gap);
        time[0] = calendar.getTimeInMillis();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.add(13, -1);
        time[1] = calendar2.getTimeInMillis();
        return time;
    }


    public static Date[] getStartAndEndDate(int gap) {
        Date[] time = new Date[2];
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(5, gap * -1);
        time[0] = calendar.getTime();
        calendar.add(5, gap);
        calendar.add(13, -1);
        time[1] = calendar.getTime();
        return time;
    }


    public static String getHour(long time) {
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_HOUR_FORMAT);
        return sdf.format(d);
    }


    public static String getMinute(long time) {
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_MINUTE_FORMAT);
        return sdf.format(d);
    }


    public static String getSecond(long time) {
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_SECOND_FORMAT);
        return sdf.format(d);
    }


    public static String getHourMinute(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_HOURMIN_FORMAT);

        return sdf.format(time);
    }


    public static String getHourMinuteSecond(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_HOURMINSECOND_FORMAT);

        return sdf.format(time);
    }


    public static boolean compareDate(Date sd, Date cd, int send) {
        long st = sd.getTime() - ((send + 1) * 1000);
        long et = sd.getTime() + ((send + 1) * 1000);
        return (cd.getTime() > st && cd.getTime() < et);
    }


    public static Date[] getStartAndEnd(Date date, int send) {
        Date btime = new Date();
        btime.setTime(date.getTime() - (send * 1000));
        Date etime = new Date();
        etime.setTime(date.getTime() + (send * 1000));
        return new Date[]{btime, etime};
    }

    public static String getHourMinute1(Long captureTime3) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_HOURMIN_FORMAT);

        return sdf.format(captureTime3);
    }


    public static boolean compareDate(Date date1, Date date2) {
        return (date1.getTime() < date2.getTime());
    }

}
