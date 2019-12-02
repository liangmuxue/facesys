package com.ss.facesys.data.access.service;

import com.ss.facesys.data.access.client.ISubscribedReceiveService;
import com.ss.facesys.data.access.common.dto.AlarmDTO;
import com.ss.facesys.data.access.common.web.AlarmReceive;
import com.ss.facesys.data.access.common.web.RecordAlarmReceive;
import com.ss.facesys.data.access.common.web.WifiCollectReceive;
import com.ss.facesys.data.access.mapper.AlarmReceiveMapper;
import com.ss.facesys.data.access.mapper.WifiCollectReceiveMapper;
import com.ss.facesys.util.AgeUtil;
import com.ss.facesys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订阅消息接收
 *
 * @author FrancisYs
 * @date 2019/11/11
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class SubscribedReceiveServiceImpl implements ISubscribedReceiveService {

    private static final Logger log = LoggerFactory.getLogger(SubscribedReceiveServiceImpl.class);

    private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    @Autowired
    private AlarmReceiveMapper alarmReceiveMapper;
    @Autowired
    private WifiCollectReceiveMapper wifiCollectReceiveMapper;

    /**
     * 预警数据处理
     * @param alarmReceive
     * @return
     */
    @Override
    public AlarmDTO alarmHandle(AlarmReceive alarmReceive) {
        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmId(alarmReceive.getAlarmId());
        alarmDTO.setMonitorId(alarmReceive.getMonitorId());
        alarmDTO.setAlarmScore(alarmReceive.getAlarmScore());
        alarmDTO.setCaptureId(alarmReceive.getCaptureId());
        alarmDTO.setCaptureTime(alarmReceive.getCaptureTime());
        alarmDTO.setAlarmTime(alarmReceive.getAlarmTime());
        alarmDTO.setDeviceId(alarmReceive.getDeviceId());
        alarmDTO.setDeviceCode(alarmReceive.getDeviceCode());
        alarmDTO.setDeviceName(alarmReceive.getDeviceName());
        alarmDTO.setDeviceAddress(alarmReceive.getDeviceAddress());
        alarmDTO.setCaptureUrlFull(alarmReceive.getCaptureUrlFull());
        List<RecordAlarmReceive> recordAlarmReceives = alarmReceive.getRecordAlarmTopns();
        if (recordAlarmReceives != null && recordAlarmReceives.size() > 0) {
            RecordAlarmReceive recordAlarm = alarmReceive.getRecordAlarmTopns().get(0);
            alarmDTO.setSimScore(recordAlarm.getSimScore());
            alarmDTO.setFacedbId(recordAlarm.getFaceId());
            alarmDTO.setFacedbId(recordAlarm.getFacedbId());
            alarmDTO.setFacedbName(recordAlarm.getFacedbName());
            alarmDTO.setFaceUrlFull(recordAlarm.getFaceUrlFull());
            alarmDTO.setFaceName(recordAlarm.getFaceName());
            String birthday = recordAlarm.getFaceBirthday();
            if (StringUtils.isNotBlank(birthday)) {
                try {
                    Date birthDate = this.format.parse(StringUtils.cleanChar(birthday));
                    alarmDTO.setAge(AgeUtil.getAge(birthDate));
                } catch (Exception e) {
                    log.error("格式化失败" + e, e.getMessage());
                }
            } else if (StringUtils.isNotBlank(alarmReceive.getAge())) {
                alarmDTO.setAge(Integer.parseInt(alarmReceive.getAge()));
            }
            int count = this.alarmReceiveMapper.insertRecordAlarmTopns(recordAlarmReceives);
            if (count > 0) {
                log.info("报警详情信息入库成功");
            } else {
                log.info("报警详情信息入库失败");
            }
        }
        int alarmCount = this.alarmReceiveMapper.insertAlarmReceive(alarmReceive);
        if (alarmCount > 0) {
            log.info("报警信息入库成功");
        } else {
            log.info("报警信息入库失败");
        }
        return alarmDTO;
    }


    @Override
    public boolean batchInsertWifiCollect(List<WifiCollectReceive> wifiReceives) {
        if (wifiReceives != null && wifiReceives.size() > 0) {
            for (WifiCollectReceive wifiCollectReceive : wifiReceives) {
                if (wifiCollectReceive.getCollectTime() == null) {
                    wifiCollectReceive.setCollectTime(new Date());
                }
            }
        }
        int count = this.wifiCollectReceiveMapper.batchInsert(wifiReceives);
        return (count > 0);
    }

}
