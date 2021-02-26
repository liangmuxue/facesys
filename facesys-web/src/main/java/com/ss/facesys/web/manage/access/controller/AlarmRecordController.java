package com.ss.facesys.web.manage.access.controller;

import com.ss.facesys.data.collect.common.model.SnapRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报警配置信息
 * @author zhangao
 * @date 2021/2/2
 * @email zhangao@ss-cas.com
 */
@RestController
@RequestMapping({"/alarmRecord"})
public class AlarmRecordController {

    private Logger logger = LoggerFactory.getLogger(AlarmInfoController.class);

    public void dealAlarmEvent(SnapRecord snapRecord){
        
    }
}
