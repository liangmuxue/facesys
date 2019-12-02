package com.ss.isc.web.manage.archives.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.collect.client.IAlarmService;
import com.ss.isc.data.collect.common.dto.AlarmDTO;
import com.ss.isc.data.collect.common.web.AlarmRecordVO;
import com.ss.isc.data.collect.common.web.AlarmVO;
import com.ss.isc.data.resource.client.ICommunityResourceService;
import com.ss.isc.data.resource.common.model.Camera;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/archives/personalarm"})
public class PersonAlarmController extends BaseController {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");


    private Logger logger = LoggerFactory.getLogger(PersonAlarmController.class);


    @Resource
    private IAlarmService alarmService;


    @Resource
    private ICommunityResourceService communityResourceService;


    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "布控告警列表", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<AlarmRecordVO>> list(HttpServletRequest request, @RequestBody AlarmVO alarm, BindingResult bindingResult) throws Exception {
        try {
            alarm.setMonitorType(1);

            alarm.setEndTime(new Date());


            List<Camera> cList = new ArrayList<Camera>();
            Camera camera = new Camera();
            camera.setVillageCode(alarm.getVillageCode());
            cList = this.communityResourceService.findCameras(camera);
            List<String> cameravoList = new ArrayList<String>();
            for (Camera cameraVO : cList) {
                cameravoList.add(cameraVO.getCameraId());
            }
            alarm.setCameraIds(cameravoList);
            List<AlarmRecordVO> alarmList = this.alarmService.findList(alarm);

            ResponseEntity<List<AlarmRecordVO>> resp = validite(bindingResult);
            resp.setData(alarmList);
            return resp;
        } catch (Exception e) {

            this.logger.error(e.toString(), e);
            throw e;
        }
    }

}
