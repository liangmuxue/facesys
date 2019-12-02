package com.ss.facesys.data.statistic.service;

import com.ss.facesys.util.DateUtil;

import java.util.Date;

public class test {

    public static void main(String[] args) {
        Date beginDayOfYesterday = DateUtil.getBeginDayOfYesterday();
        Date endDayOfYesterDay = DateUtil.getEndDayOfYesterDay();
        System.out.println(beginDayOfYesterday);
        System.out.println(endDayOfYesterDay);
    }


}
