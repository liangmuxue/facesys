package com.ss.facesys.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class OapDateUtil {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYYMMDDHH = "yyyy-MM-dd HH:00:00";
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static final DateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }


    public static Date getStartTimeOfTheDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }


    public static Date getEndTimeOfTheDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(11, 23);
        c.set(12, 59);
        c.set(13, 59);
        c.set(14, 0);
        return c.getTime();
    }


    public static Date getCurrentMonday() {
        int monday = getMonday();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, monday);
        return getStartTimeOfTheDate(currentDate.getTime());
    }


    public static Date getPreviousSunday() {
        int monday = getMonday();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, monday + 6);
        return getEndTimeOfTheDate(currentDate.getTime());
    }

    private static int getMonday() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(7);
        if (dayOfWeek == 1) {
            return -6;
        }
        return 2 - dayOfWeek;
    }


    public static Date getDayBeforeN(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -1 * n);
        return calendar.getTime();
    }


    public static Date getDayAfterN(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, n);
        return calendar.getTime();
    }


    public static Date getMonthAfterN(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, n);
        return calendar.getTime();
    }


    public static final String dtToSimple(Date date) {
        if (date == null) {
            return "";
        }
        return getFormat(YYYY_MM_DD).format(date);
    }

    public static final String dateFormat(Date date, String formatStr) {
        if (date == null) {
            return "";
        }
        return getFormat(formatStr).format(date);
    }

    public static final String dtHHToSimple(Date date) {
        if (date == null) {
            return "";
        }
        return getFormat(YYYYMMDDHH).format(date);
    }

    public static final Date dtHHToDate(Date date) {
        Date result = null;
        try {
            if (date == null) {
                return null;
            }
            String s = getFormat(YYYYMMDDHH).format(date);
            result = getFormat(YYYYMMDDHH).parse(s);
        } catch (ParseException e) {
            result = null;
        }
        return result;
    }

    public static final String dtDayToSimple(Date date) {
        if (date == null) {
            return "";
        }
        return getFormat(YYYYMMDD).format(date);
    }

    public static final String parseTimestamp(Long time) {
        Date date = new Date(time.longValue());
        if (time == null) {
            return "";
        }
        return getFormat(YYYYMMDDHHMMSS).format(date);
    }


    public static final String parseYYYYMM(Long time) {
        Date date = new Date(time.longValue());
        if (time == null) {
            return "";
        }
        return getFormat(YYYYMM).format(date);
    }

    public static final String parseYYYYMM(Calendar calendar) {
        Date date = calendar.getTime();
        return getFormat(YYYYMM).format(date);
    }

    public static final int countMonths(Long beforeTime, Long afterTime) {
        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();

        before.setTime(new Date(beforeTime.longValue()));
        after.setTime(new Date(afterTime.longValue()));

        int result = after.get(2) - before.get(2);
        int month = (after.get(1) - before.get(1)) * 12;

        return result + month;
    }


    public static final List<String> getMonths(Long beforeTime, Long afterTime) {
        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();

        before.setTime(new Date(beforeTime.longValue()));
        after.setTime(new Date(afterTime.longValue()));

        before.set(before.get(1), before.get(2), 1);
        after.set(after.get(1), after.get(2), 2);

        return findMonth(before, after);
    }

    public static final int diffMonth(Calendar before, Calendar after) {
        int result = after.get(2) - before.get(2);
        int month = (after.get(1) - before.get(1)) * 12;
        return result + month;
    }

    public static final List<String> findMonth(Calendar before, Calendar after) {
        List<String> ret = new ArrayList<String>();
        do {
            ret.add(parseYYYYMM(before));
            before.add(2, 1);
        } while (before.compareTo(after) < 0);

        return ret;
    }

}
