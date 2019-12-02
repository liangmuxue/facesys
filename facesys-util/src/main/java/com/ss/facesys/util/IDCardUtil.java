package com.ss.facesys.util;

import java.util.Calendar;
import java.util.regex.Pattern;


public class IDCardUtil {

    public static final int CID_LENGTH = 15;
    public static final int BIRTH_YEAR_LENGTH = 2;
    public static final int IDX_SEX_START = 14;

    public static boolean checkCardId(String cid) {
        String myRegExpIDCardNo = "^\\d{6}(((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}([0-9]|x|X))|(\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}))$";
        return (Pattern.matches(myRegExpIDCardNo, cid) || (cid
                .length() == 17 && Pattern.matches(myRegExpIDCardNo, cid.substring(0, 15))));
    }


    public static int parseAge(String cid) {
        int age = 0;
        if (!checkCardId(cid)) {
            return age;
        }
        int birthYearSpan = 4;

        if (cid.length() == 15) {
            birthYearSpan = 2;
        }

        String year = ((birthYearSpan == 2) ? "19" : "") + cid.substring(6, 6 + birthYearSpan);
        String month = cid.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
        String day = cid.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);


        Calendar certificateCal = Calendar.getInstance();
        Calendar currentTimeCal = Calendar.getInstance();
        certificateCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
        int yearAge = currentTimeCal.get(1) - certificateCal.get(1);
        certificateCal.set(currentTimeCal.get(1), Integer.parseInt(month) - 1, Integer.parseInt(day));
        int monthFloor = currentTimeCal.before(certificateCal) ? 1 : 0;
        return yearAge - monthFloor;
    }


    public static String parseGender(String cid) {
        if (!checkCardId(cid)) {
            return null;
        }
        int idxSexStart = 16;

        if (cid.length() == 15) {
            idxSexStart = 14;
        }

        String idxSexStr = cid.substring(idxSexStart, idxSexStart + 1);
        int idxSex = Integer.parseInt(idxSexStr) % 2;
        return (idxSex == 1) ? "男" : "女";
    }


    public static String parseBirthDay(String cid) {
        if (!checkCardId(cid)) {
            return "";
        }
        int birthYearSpan = 4;

        if (cid.length() == 15) {
            birthYearSpan = 2;
        }

        String year = ((birthYearSpan == 2) ? "19" : "") + cid.substring(6, 6 + birthYearSpan);
        String month = cid.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
        String day = cid.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);
        return year + '-' + month + '-' + day;
    }

}
