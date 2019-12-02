package com.ss.isc.data.statistic.service;

import com.ss.isc.util.DateUtil;

import java.util.Date;

public class test {

    public static void main(String[] args) {
        Date beginDayOfYesterday = DateUtil.getBeginDayOfYesterday();
        Date endDayOfYesterDay = DateUtil.getEndDayOfYesterDay();
        System.out.println(beginDayOfYesterday);
        System.out.println(endDayOfYesterDay);
    }


}
