package com.ss.facesys.data.system.client;

import com.ss.facesys.data.system.common.dto.MyMessageQuery;
import com.ss.facesys.data.system.common.model.AlarmMessage;
import com.ss.facesys.data.system.common.model.SystemMessage;

import java.util.List;

public interface IMyMessageService {

    List<SystemMessage> systemPage(MyMessageQuery myMessageQuery);
    List<AlarmMessage> alarmPage(MyMessageQuery myMessageQuery);
    int delSystemMes (Integer id);
    int delAlarmMes (Integer id);

}
