package com.ss.isc.data.collect.service;

import com.ss.isc.data.collect.client.IAlarmVehicleService;
import com.ss.isc.data.collect.common.model.AlarmVehicle;
import com.ss.isc.data.collect.mapper.AlarmVehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
* 过车告警
* @author chao
* @create 2019/10/31
* @email lishuangchao@ss-cas.com
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class AlarmVehicleServiceImpl implements IAlarmVehicleService {

    @Autowired
    private AlarmVehicleMapper alarmVehicleMapper;

    /**
     * 新增过车告警信息
     * @param paramList
     * @return
     */
    @Override
    public String insertAlarmVehicle(List<AlarmVehicle> paramList) {
        int count = this.alarmVehicleMapper.insertAlarmVehicle(paramList);
        return (count > 0) ? "success" : "failed";
    }
}
