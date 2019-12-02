package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.model.AlarmVehicle;
import com.ss.mapper.CWMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 过车告警
* @author chao
* @create 2019/10/31
* @email lishuangchao@ss-cas.com
**/
@Repository
@Mapper
public interface AlarmVehicleMapper extends CWMapper<AlarmVehicle> {
    /**
     * 新增过车告警信息
     * @param paramList
     * @return
     */
    int insertAlarmVehicle(List<AlarmVehicle> paramList);

}
