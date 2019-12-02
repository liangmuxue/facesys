package com.ss.isc.data.statistic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface StHomeDataMapper {
    //日统计
    int selDayPeopel(@Param("currencDate") String currencDate,@Param("previousData") String previousData);
    //月统计
    int selMouthPeopel(@Param("currencDate") String currencDate,@Param("previousData") String previousData);
    //周统计
    int selWeekPeopel(@Param("weekNum") int weekNum);

    /**********************实有房屋*******************/
    //日统计
    int selDayHouse(@Param("currencDate") String currencDate,@Param("previousData") String previousData);
    //月统计
    int selMouthHouse(@Param("currencDate") String currencDate,@Param("previousData") String previousData);
    //周统计
    int selWeekHouse(@Param("weekNum") int weekNum);

    /**********************实有单位*******************/
    //日统计
    int selDayCompany(@Param("currencDate") String currencDate,@Param("previousData") String previousData);
    //月统计
    int selMouthCompany(@Param("currencDate") String currencDate,@Param("previousData") String previousData);
    //周统计
    int selWeekCompany(@Param("weekNum") int weekNum);

    /**********************感知增量*******************/
    //日统计
    int selDayPercept(@Param("currencDate") String currencDate);
    //月统计
    int selMouthPercept(@Param("currencDate") String currencDate);
    //周统计
    int selWeekPercept(@Param("weekNum") int weekNum);

}