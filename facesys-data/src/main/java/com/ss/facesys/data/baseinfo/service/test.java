package com.ss.facesys.data.baseinfo.service;

import com.ss.tools.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        String date1 = String.valueOf(DateUtils.getPastTimeStamp("1"));
        SimpleDateFormat simpleDateFormat =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date dt1;
        try {
            dt1 = simpleDateFormat .parse(date1);
            long ts1 = dt1.getTime();
            System.out.println(date1+"时间戳" + ts1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static String timeStampToTime(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        return res;
    }

}
