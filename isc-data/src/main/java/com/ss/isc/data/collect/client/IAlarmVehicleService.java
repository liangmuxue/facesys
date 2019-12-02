package com.ss.isc.data.collect.client;

import com.ss.isc.data.collect.common.model.AlarmVehicle;

import java.util.List;

/**
* 过车告警
* @author chao
* @create 2019/10/31
* @email lishuangchao@ss-cas.com
**/
public interface IAlarmVehicleService {

    /**
     * 新增过车信息
     * @param paramList
     * @return
     */
    String insertAlarmVehicle(List<AlarmVehicle> paramList);

}
