package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.AlarmVehicle;
import com.ss.mapper.SsMapper;
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
public interface AlarmVehicleMapper extends SsMapper<AlarmVehicle> {
    /**
     * 新增过车告警信息
     * @param paramList
     * @return
     */
    int insertAlarmVehicle(List<AlarmVehicle> paramList);

}
