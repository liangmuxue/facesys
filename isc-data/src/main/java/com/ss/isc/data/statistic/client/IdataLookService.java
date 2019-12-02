package com.ss.isc.data.statistic.client;

/**
 * IBaseService
 *
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
public interface IdataLookService {
    //本月
    void selthisMouthPeopel(int mouthNum);
    //本周
    void selThisWeekPeopel(int weekNum);
    //日数据
    void selThisThirtyDayPeopel(int dayNum);

    /***********实有房屋******************/
    //本月
    void selthisMouthHouse(int mouthNum);
    //本周
    void selThisWeekHouse(int weekNum);
    //日数据
    void selThisThirtyDayHouse(int dayNum);

    /*************实有单位**************/
    //本月
    void selthisMouthCompany(int mouthNum);
    //本周
    void selThisWeekCompany(int weekNum);
    //日数据
    void selThisThirtyDayCompany(int dayNum);

    /*************感知增量  过车统计**************/
    //本月
    void selthisMouthPercept(int mouthNum);
    //本周
    void selThisWeekPercept(int weekNum);
    //日数据
    void selThisThirtyDayPercept(int dayNum);

    /*************感知增量   人脸统计**************/
    void selThisThirtyDayFace(int dayNum);
}
