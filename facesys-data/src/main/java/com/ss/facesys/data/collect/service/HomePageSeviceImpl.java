package com.ss.facesys.data.collect.service;

import com.ss.enums.AlarmMessageTypeEnum;
import com.ss.facesys.data.access.mapper.AlarmRecordMapper;
import com.ss.facesys.data.archives.mapper.VictoryMapper;
import com.ss.facesys.data.collect.client.IHomePageService;
import com.ss.facesys.data.collect.common.model.AlarmRecord;
import com.ss.facesys.data.collect.common.model.FacedbFace;
import com.ss.facesys.data.collect.common.model.HomePageBase;
import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.facesys.data.collect.mapper.FacedbFaceMapper;
import com.ss.facesys.data.collect.mapper.SnapRecordMapper;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.em.FaceOrBodyEnum;
import com.ss.facesys.util.em.ResourceType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * com.ss.facesys.data.collect.service
 *
 * @author 李爽超 chao
 * @create 2021/03/02
 * @email lishuangchao@ss-cas.com
 **/
@Service
public class HomePageSeviceImpl implements IHomePageService {

    @Resource
    private FacedbFaceMapper facedbFaceMapper;
    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private SnapRecordMapper snapRecordMapper;
    @Resource
    private AlarmRecordMapper alarmRecordMapper;
    @Resource
    private VictoryMapper victoryMapper;

    @Override
    public HomePageBase get(HomePageBase homePageBase) {
        FacedbFace facedbFace = new FacedbFace();
        facedbFace.setStatus(1);
        int faceTotal = this.facedbFaceMapper.selectCount(facedbFace);
        homePageBase.setFaceTotal(faceTotal);
        Camera camera = new Camera();
        camera.setState(0);
        int deviceCount = this.cameraMapper.selectCount(camera);
        homePageBase.setDeviceTotal(deviceCount);
        SnapRecord snapRecord = new SnapRecord();
        snapRecord.setDeviceType(ResourceType.CAMERA.getValue());
        snapRecord.setCaptureType(FaceOrBodyEnum.FACE.getCode());
        int captureTotal = this.snapRecordMapper.selectCount(snapRecord);
        homePageBase.setCaptureTotal(captureTotal);
        AlarmRecord alarmRecord = new AlarmRecord();
        alarmRecord.setMonitorType(AlarmMessageTypeEnum.BLACK.getCode());
        alarmRecord.setDeviceType(Enums.AlarmDeviceType.PERSONCARD.getCode());
        this.alarmRecordMapper.selectCount(alarmRecord);
        return null;
    }
}
