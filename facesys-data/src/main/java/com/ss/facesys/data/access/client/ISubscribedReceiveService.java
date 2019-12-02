package com.ss.facesys.data.access.client;

import com.ss.facesys.data.access.common.dto.AlarmDTO;
import com.ss.facesys.data.access.common.web.AlarmReceive;
import com.ss.facesys.data.access.common.web.WifiCollectReceive;

import java.util.List;

/**
 * 订阅消息接收
 *
 * @author FrancisYs
 * @date 2019/11/11
 * @email yaoshuai@ss-cas.com
 */
public interface ISubscribedReceiveService {

    /**
     * 预警数据处理
     * @param paramAlarmReceive
     * @return
     */
    AlarmDTO alarmHandle(AlarmReceive paramAlarmReceive);

    boolean batchInsertWifiCollect(List<WifiCollectReceive> paramList);

}
