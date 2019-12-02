package com.ss.tools;

import com.ss.enums.GenderEnum;

import java.io.Serializable;
import java.util.Calendar;
import java.util.regex.Pattern;


public class ICardUtils {

    private static final String[] VC = {
            "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};


    private static final String[] WI = {
            "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};


    private static final String NUMERIC_PATTERM = "[0-9]*";


    public static boolean isNumeric(String strnum) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(strnum).matches();
    }


    private static boolean isVarifyCode(String ai, String str) {
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += Integer.parseInt(String.valueOf(ai.charAt(i))) * Integer.parseInt(WI[i]);
        }

        ai = ai + VC[sum % 11];
        if (str.length() == 18) {
            return ai.equals(str);
        }

        return true;
    }


    public static boolean isValid(String str) {
        if (str.length() != 15 && str.length() != 18) {
            return false;
        }


        String ai = "";
        if (str.length() == 18) {
            ai = str.substring(0, 17);
        } else if (str.length() == 15) {
            ai = str.substring(0, 6) + "19" + str.substring(6, 15);
        }

        if (!isNumeric(ai)) {
            return false;
        }


        String y = ai.substring(6, 10);
        String m = ai.substring(10, 12);
        String d = ai.substring(12, 14);
        if (!DateUtils.isDate(d + "/" + m + "/" + y)) {
            return false;
        }

        return isVarifyCode(ai, str);
    }


    public static IDCard getExtractor(String str) {
        if (!isValid(str)) {
            return null;
        }


        IDCard idCard = new IDCard();
        if (str.length() == 15) {
            str = str.substring(0, 6) + "19" + str.substring(6, 15);
        }


        idCard.setProvince(str.substring(0, 2));
        String id17 = str.substring(16, 17);
        if (Integer.parseInt(id17) % 2 != 0) {
            idCard.setGender(GenderEnum.M);
        } else {
            idCard.setGender(GenderEnum.F);
        }


        Calendar cal = Calendar.getInstance();
        String birthday = str.substring(6, 14);
        cal.setTime(DateUtils.defaultFormat(birthday));

        idCard.setBirthday(birthday);
        idCard.setYear(cal.get(1));
        idCard.setMonth(cal.get(2) + 1);
        idCard.setDay(cal.get(5));

        return idCard;
    }


    public static class IDCard
            implements Serializable {

        private static final long serialVersionUID = 1L;


        private String province;


        private String city;


        private String region;


        private int year;


        private int month;


        private int day;


        private GenderEnum gender;


        private String birthday;


        public String getProvince() {
            return this.province;
        }


        public void setProvince(String province) {
            this.province = province;
        }


        public String getCity() {
            return this.city;
        }


        public void setCity(String city) {
            this.city = city;
        }


        public String getRegion() {
            return this.region;
        }


        public void setRegion(String region) {
            this.region = region;
        }


        public int getYear() {
            return this.year;
        }


        public void setYear(int year) {
            this.year = year;
        }


        public int getMonth() {
            return this.month;
        }


        public void setMonth(int month) {
            this.month = month;
        }


        public int getDay() {
            return this.day;
        }


        public void setDay(int day) {
            this.day = day;
        }


        public GenderEnum getGender() {
            return this.gender;
        }


        public void setGender(GenderEnum gender) {
            this.gender = gender;
        }


        public String getBirthday() {
            return this.birthday;
        }


        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

    }

}
