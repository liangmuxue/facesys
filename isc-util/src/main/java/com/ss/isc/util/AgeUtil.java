package com.ss.isc.util;

import java.util.Calendar;
import java.util.Date;

public class AgeUtil {

    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(1);
        int monthNow = cal.get(2);
        int dayOfMonthNow = cal.get(5);
        cal.setTime(birthDay);

        int yearBirth = cal.get(1);
        int monthBirth = cal.get(2);
        int dayOfMonthBirth = cal.get(5);
        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }

    public static boolean isOld(String birthDay, int age) {
        String[] bri = birthDay.split("-");
        Calendar cNow = Calendar.getInstance();
        int yearNow = cNow.get(1);
        if (yearNow - Integer.valueOf(bri[0]).intValue() >= age) {
            return true;
        }
        return false;
    }

}
