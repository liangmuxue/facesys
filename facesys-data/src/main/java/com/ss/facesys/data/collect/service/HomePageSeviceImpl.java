package com.ss.facesys.data.collect.service;

import com.ss.enums.AlarmMessageTypeEnum;
import com.ss.facesys.data.access.mapper.AlarmRecordMapper;
import com.ss.facesys.data.archives.common.model.Victory;
import com.ss.facesys.data.archives.mapper.VictoryMapper;
import com.ss.facesys.data.collect.client.IHomePageService;
import com.ss.facesys.data.collect.common.model.*;
import com.ss.facesys.data.collect.mapper.FacedbFaceMapper;
import com.ss.facesys.data.collect.mapper.HomePageMapper;
import com.ss.facesys.data.collect.mapper.SnapRecordMapper;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.em.FaceOrBodyEnum;
import com.ss.facesys.util.em.ResourceType;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    private HomePageMapper homePageMapper;

    @Override
    public HomePageBase get(HomePageBase homePageBase) {

        FacedbFace facedbFace = new FacedbFace();
        facedbFace.setStatus(1);
        //人像集总数
        int faceTotal = this.facedbFaceMapper.selectCount(facedbFace);
        homePageBase.setFaceTotal(faceTotal);

        Victory victory = new Victory();
        victory.setStatus(1);
        //战果汇总总数
        int victoryTotal = this.victoryMapper.selectCount(victory);
        homePageBase.setVictoryTotal(victoryTotal);

        if (StringUtils.isNotBlank(homePageBase.getSceneIds())) {
            List<String> sceneList = Arrays.asList(homePageBase.getSceneIds().split(","));
            Example example1 = new Example(Camera.class);
            example1.createCriteria().andIn("sceneId", sceneList).andEqualTo("state", 0);
            //接入设备总数
            int deviceCount = this.cameraMapper.selectCountByExample(example1);
            homePageBase.setDeviceTotal(deviceCount);
            //当接入设备总数为0，人脸抓拍总数、重点人员感知总数、布控报警总数也为0
            if (deviceCount == 0) {
                homePageBase.setCaptureTotal(0);
                homePageBase.setFocusOnPeopleTotal(0);
                homePageBase.setAlarmTotal(0);
                return homePageBase;
            }
            //接入全设备
            List<Camera> cameras = this.cameraMapper.selectByExample(example1);
            //接入全设备编号
            List<Integer> ids = cameras.stream().map(Camera::getId).collect(Collectors.toList());
            Example example2 = new Example(SnapRecord.class);
            example2.createCriteria().andEqualTo("deviceType", ResourceType.CAMERA.getValue())
                    .andEqualTo("captureType", FaceOrBodyEnum.FACE.getCode())
                    .andIn("deviceId", ids);
            //人脸抓拍总数
            int captureTotal = this.snapRecordMapper.selectCountByExample(example2);
            homePageBase.setCaptureTotal(captureTotal);
            Example example3 = new Example(AlarmRecord.class);
            Example.Criteria criteria = example3.createCriteria().andEqualTo("deviceType", Enums.AlarmDeviceType.CAMERA.getCode())
                    .andIn("deviceId", ids);
            //布控报警总数
            int alarmTotal = this.alarmRecordMapper.selectCountByExample(example3);
            homePageBase.setAlarmTotal(alarmTotal);
            criteria.andEqualTo("monitorType", AlarmMessageTypeEnum.BLACK.getCode()).andEqualTo("regdbType", "1006");
            //重点人员感知总数
            int focusOnPeopleTotal = this.alarmRecordMapper.selectCountByExample(example3);
            homePageBase.setFocusOnPeopleTotal(focusOnPeopleTotal);
        }
        return homePageBase;
    }

    @Override
    public List<HomePageScene> get(HomePageScene homePageScene) {

        List<HomePageScene> deviceScenes = this.homePageMapper.deviceTotalByScene(homePageScene);
        if (StringUtils.isBlank(homePageScene.getSceneIds())) {
            for (HomePageScene hps: deviceScenes) {
                hps.setDeviceTotal(0);
            }
            return deviceScenes;
        }
        List<HomePageScene> captureScenes = this.homePageMapper.captureTotalByScene(homePageScene);
        Map<Integer, HomePageScene> homePageSceneMap = captureScenes.stream().collect(
                Collectors.toMap( e -> e.getSceneId(),  e -> e));
        for (HomePageScene hps: deviceScenes) {
            hps.setCaptureTotal(homePageSceneMap.get(hps.getSceneId()).getCaptureTotal());
        }
        return deviceScenes;
    }

    @Override
    public HomePageDevice get(HomePageDevice homePageDevice) {
        Example example1 = new Example(SnapRecord.class);
        example1.createCriteria().andEqualTo("deviceType", ResourceType.CAMERA.getValue())
                .andEqualTo("captureType", FaceOrBodyEnum.FACE.getCode())
                .andEqualTo("deviceId", homePageDevice.getDeviceId());
        //人脸抓拍总数
        int captureTotal = this.snapRecordMapper.selectCountByExample(example1);
        homePageDevice.setCaptureTotal(captureTotal);
        Example example3 = new Example(AlarmRecord.class);
        example3.createCriteria().andEqualTo("deviceType", Enums.AlarmDeviceType.CAMERA.getCode())
                .andEqualTo("deviceId", homePageDevice.getDeviceId());
        //布控报警总数
        int alarmTotal = this.alarmRecordMapper.selectCountByExample(example3);
        homePageDevice.setAlarmTotal(alarmTotal);
        return homePageDevice;
    }
}
