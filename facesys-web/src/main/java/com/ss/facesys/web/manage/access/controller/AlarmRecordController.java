package com.ss.facesys.web.manage.access.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.MonitorTask;
import com.ss.facesys.data.access.common.web.MonVO;
import com.ss.facesys.data.access.mapper.AlarmRecordMapper;
import com.ss.facesys.data.access.mapper.MonMapper;
import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.data.baseinfo.mapper.EnumMapper;
import com.ss.facesys.data.collect.common.model.*;
import com.ss.facesys.data.collect.mapper.DevicePersoncardMapper;
import com.ss.facesys.data.collect.mapper.FacedbFaceMapper;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.SfgoHttpConstant;
import com.ss.facesys.util.em.MonitorTypeEnum;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import com.ss.utils.BaseHttpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private CameraMapper cameraMapper;
    @Resource
    private DevicePersoncardMapper devicePersoncardMapper;
    @Resource
    private MonMapper monMapper;
    @Resource
    private FacedbMapper facedbMapper;
    @Resource
    private FacedbFaceMapper facedbFaceMapper;
    @Resource
    private EnumMapper enumMapper;
    @Resource
    private AlarmRecordMapper alarmRecordMapper;

    
    public void dealAlarmEvent(SnapRecord snapRecord){
        MonVO monVO = new MonVO();
        monVO.setEndTime(System.currentTimeMillis());
        monVO.setDeleteFlag(1);
        monVO.setState(1);
        //判断设备类型
        if(snapRecord.getDeviceType() == ResourceType.CAMERA.getValue()){
            monVO.setCameraId(snapRecord.getDeviceId());
        }
        if(snapRecord.getDeviceType() == ResourceType.PERSONCARD.getValue()){
            monVO.setPersoncardDeviceId(snapRecord.getDeviceId());
        }
        //查询设备相关的布控任务信息
        List<MonitorTask> monitorTasks = monMapper.selTasksByCamera(monVO);
        if (CollectionUtils.isNotEmpty(monitorTasks)) {
            //循环布控任务判断布控任务类型
            for (MonitorTask monitorTask : monitorTasks) {
                //查询此任务下人脸库
                List dbList = Arrays.asList(monitorTask.getFacedbIds().split(","));
                Example example = new Example(Facedb.class);
                example.createCriteria().andIn("id", dbList);
                List<Facedb> facedbs = facedbMapper.selectByExample(example);
                if (CollectionUtils.isNotEmpty(facedbs)) {
                    String groupId = null;
                    for (Facedb facedb : facedbs) {
                        //获取库的字符串
                        if (groupId == null) {
                            groupId = facedb.getFacedbId();
                        } else {
                            groupId = "," + groupId;
                        }
                    }
                    //人脸注册库1:N准备数据
                    Map<String, Object> facedbMap = new HashMap<>();
                    facedbMap.put("groupId", groupId);
                    facedbMap.put("img", snapRecord.getCaptureUrl());
                    facedbMap.put("topN", 1);
                    Float[] thresholdScore = new Float[2];
                    thresholdScore[0] = monitorTask.getFaceThreshold() / 100;
                    thresholdScore[1] = 1F;
                    facedbMap.put("thresholdScore", thresholdScore);
                    //人脸注册库1：N
                    String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(facedbMap), PropertiesUtil.getSfgohttp() + SfgoHttpConstant.STATICDB_SEARCH_MULTIPLE, null);
                    JSONObject resObj = JSONObject.parseObject(resultString);
                    if (StringUtils.checkSuccess(resObj)) {
                        //判断任务类型为陌生人报警
                        if (monitorTask.getMonitorType().equals(MonitorTypeEnum.STRANGER.getCode())) {
                            if (!resObj.containsKey("faces")) {
                                //不存在即报警
                                AlarmRecord alarmRecord = new AlarmRecord();
                                alarmRecord.setAlarmId(UUIDUtils.getUUID());
                                alarmRecord.setMonitorId(monitorTask.getMonitorCode());
                                alarmRecord.setMonitorName(monitorTask.getMonitorName());
                                alarmRecord.setMonitorType(monitorTask.getMonitorType());
                                alarmRecord.setMonitorProperty(monitorTask.getMonitorProperty());
                                alarmRecord.setTopn(1);
                                alarmRecord.setAlarmScore(monitorTask.getFaceThreshold() / 100);
                                alarmRecord.setCaptureId(String.valueOf(snapRecord.getId()));
                                alarmRecord.setCaptureTime(snapRecord.getCaptureTime());
                                alarmRecord.setAlarmTime(System.currentTimeMillis());
                                alarmRecord.setDeviceType(snapRecord.getDeviceType());
                                //查询设备相关信息
                                if (alarmRecord.getDeviceType() == ResourceType.CAMERA.getValue()) {
                                    //相机设备
                                    Camera cameraMess = new Camera();
                                    cameraMess.setId(snapRecord.getDeviceId());
                                    Camera cameraMesss = cameraMapper.selectOne(cameraMess);
                                    alarmRecord.setDeviceId(cameraMess.getCameraId());
                                    alarmRecord.setDeviceCode(cameraMesss.getProductCode());
                                    alarmRecord.setDeviceType(ResourceType.CAMERA.getValue());
                                    alarmRecord.setDeviceName(cameraMesss.getCameraName());
                                    //设备地址暂存
                                    alarmRecord.setDeviceAddress(cameraMesss.getInstallAdd());
                                    alarmRecord.setLng(String.valueOf(cameraMesss.getLon()));
                                    alarmRecord.setLat(String.valueOf(cameraMesss.getLat()));
                                    alarmRecord.setPanoramaId(String.valueOf(snapRecord.getId()));
                                    alarmRecord.setCaptureUrlFull(FilePropertiesUtil.getHttpUrl() + snapRecord.getCaptureUrl());
                                    if (StringUtils.isNotBlank(snapRecord.getPanoramaUrl())) {
                                        alarmRecord.setPanoramaUrlFull(FilePropertiesUtil.getHttpUrl() + snapRecord.getPanoramaUrl());
                                    } else {
                                        alarmRecord.setPanoramaUrlFull(null);
                                    }
                                }
                                if (alarmRecord.getDeviceType() == ResourceType.PERSONCARD.getValue()) {
                                    //人证设备
                                    DevicePersoncard devicePersoncard = new DevicePersoncard();
                                    devicePersoncard.setId(snapRecord.getDeviceId());
                                    DevicePersoncard deviceMess = devicePersoncardMapper.selectOne(devicePersoncard);
                                    alarmRecord.setDeviceId(deviceMess.getDeviceId());
                                    alarmRecord.setDeviceCode(deviceMess.getDeviceCode());
                                    alarmRecord.setDeviceType(ResourceType.PERSONCARD.getValue());
                                    alarmRecord.setDeviceName(deviceMess.getDeviceName());
                                    alarmRecord.setLng(String.valueOf(deviceMess.getLon()));
                                    alarmRecord.setLat(String.valueOf(deviceMess.getLat()));
                                }
                                //获取特征值
                                alarmRecord.setAge(snapRecord.getAge());
                                alarmRecord.setGender(snapRecord.getGender());
                                alarmRecord.setMaskState(snapRecord.getMask());
                                alarmRecord.setGlassesState(snapRecord.getGlasses());
                                //获取备注
                                alarmRecord.setRemark(monitorTask.getRemark());
                                //插入陌生人报警记录
                                alarmRecordMapper.insertStrangerRecord(alarmRecord);
                                logger.info("人脸照下陌生人报警记录插入成功");
                            }
                        }
                    }
                    //判断任务类型为黑名单报警
                    else if (monitorTask.getMonitorType().equals(MonitorTypeEnum.BLACK.getCode())) {
                        if (resObj.containsKey("faces")) {
                            //存在即报警
                            JSONArray faceData = (JSONArray) resObj.get("faces");
                            if (faceData != null) {
                                JSONObject jsonObject = faceData.getJSONObject(0);
                                AlarmRecord alarmRecord = new AlarmRecord();
                                alarmRecord.setAlarmId(UUIDUtils.getUUID());
                                alarmRecord.setMonitorId(monitorTask.getMonitorCode());
                                alarmRecord.setMonitorName(monitorTask.getMonitorName());
                                alarmRecord.setMonitorType(monitorTask.getMonitorType());
                                alarmRecord.setMonitorProperty(monitorTask.getMonitorProperty());
                                alarmRecord.setTopn(1);
                                alarmRecord.setAlarmScore(monitorTask.getFaceThreshold() / 100);
                                alarmRecord.setCaptureId(String.valueOf(snapRecord.getId()));
                                alarmRecord.setCaptureTime(snapRecord.getCaptureTime());
                                alarmRecord.setAlarmTime(System.currentTimeMillis());
                                //查询设备相关信息
                                if (alarmRecord.getDeviceType() == ResourceType.CAMERA.getValue()) {
                                    //相机设备
                                    Camera cameraMess = new Camera();
                                    cameraMess.setId(snapRecord.getDeviceId());
                                    Camera cameraMesss = cameraMapper.selectOne(cameraMess);
                                    alarmRecord.setDeviceId(cameraMess.getCameraId());
                                    alarmRecord.setDeviceCode(cameraMesss.getProductCode());
                                    alarmRecord.setDeviceType(ResourceType.CAMERA.getValue());
                                    alarmRecord.setDeviceName(cameraMesss.getCameraName());
                                    //设备地址暂存
                                    alarmRecord.setDeviceAddress(cameraMesss.getInstallAdd());
                                    alarmRecord.setLng(String.valueOf(cameraMesss.getLon()));
                                    alarmRecord.setLat(String.valueOf(cameraMesss.getLat()));
                                    alarmRecord.setPanoramaId(String.valueOf(snapRecord.getId()));
                                    alarmRecord.setCaptureUrlFull(FilePropertiesUtil.getHttpUrl() + snapRecord.getCaptureUrl());
                                    if (StringUtils.isNotBlank(snapRecord.getPanoramaUrl())) {
                                        alarmRecord.setPanoramaUrlFull(FilePropertiesUtil.getHttpUrl() + snapRecord.getPanoramaUrl());
                                    } else {
                                        alarmRecord.setPanoramaUrlFull(null);
                                    }
                                }
                                if (alarmRecord.getDeviceType() == ResourceType.PERSONCARD.getValue()) {
                                    //人证设备
                                    DevicePersoncard devicePersoncard = new DevicePersoncard();
                                    devicePersoncard.setId(snapRecord.getDeviceId());
                                    DevicePersoncard deviceMess = devicePersoncardMapper.selectOne(devicePersoncard);
                                    alarmRecord.setDeviceId(deviceMess.getDeviceId());
                                    alarmRecord.setDeviceCode(deviceMess.getDeviceCode());
                                    alarmRecord.setDeviceType(ResourceType.PERSONCARD.getValue());
                                    alarmRecord.setDeviceName(deviceMess.getDeviceName());
                                    alarmRecord.setLng(String.valueOf(deviceMess.getLon()));
                                    alarmRecord.setLat(String.valueOf(deviceMess.getLat()));
                                }
                                //获取特征值
                                alarmRecord.setMaskState(snapRecord.getMask());
                                alarmRecord.setGlassesState(snapRecord.getGlasses());
                                //获取备注
                                alarmRecord.setRemark(monitorTask.getRemark());
                                //查询人员信息
                                if (jsonObject.containsKey("userId")) {
                                    FacedbFace facedbFace = new FacedbFace();
                                    facedbFace.setFaceId(jsonObject.getString("userId"));
                                    FacedbFace facedbFaceMess = facedbFaceMapper.selectOne(facedbFace);
                                    alarmRecord.setBirthday(facedbFaceMess.getBirthday());
                                    //获取民族名
                                    if (facedbFaceMess.getNation() != null) {
                                        BaseEnums baseEnums = new BaseEnums();
                                        baseEnums.setEnumType("NATION");
                                        baseEnums.setEnumValue(facedbFaceMess.getNation());
                                        BaseEnums baseEnumss = enumMapper.selectOne(baseEnums);
                                        alarmRecord.setCardNation(baseEnumss.getEnumName());
                                    } else {
                                        alarmRecord.setCardNation(null);
                                    }
                                    alarmRecord.setCardId(facedbFaceMess.getCardId());
                                    alarmRecord.setName(facedbFaceMess.getName());
                                    alarmRecord.setCardGender(facedbFaceMess.getGender());
                                    alarmRecord.setGender(facedbFaceMess.getGender());
                                    //获取年龄
                                    if(StringUtils.isNotBlank(facedbFaceMess.getBirthday())) {
                                        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                                        Date bithday = null;
                                        try {
                                            bithday = format.parse(facedbFaceMess.getBirthday());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        alarmRecord.setAge(DateUtils.getAgeByBirth(bithday));
                                    }else{
                                        alarmRecord.setAge(null);
                                    }
                                    alarmRecord.setRegisterUrl(FilePropertiesUtil.getHttpUrl() + facedbFaceMess.getFacePath());
                                    alarmRecord.setRegId(facedbFaceMess.getFaceId());
                                    //获取库信息
                                    if (jsonObject.containsKey("groupId")) {
                                        alarmRecord.setRegdbId(jsonObject.getString("groupId"));
                                        //查询库名
                                        Facedb facedb = new Facedb();
                                        facedb.setFacedbId(jsonObject.getString("groupId"));
                                        Facedb facedbMess = facedbMapper.selectOne(facedb);
                                        alarmRecord.setRegdbName(facedbMess.getName());
                                    }
                                }
                                //获取对比值
                                if (jsonObject.containsKey("score")) {
                                    alarmRecord.setScore(jsonObject.getFloat("score") / 100);
                                }
                                //插入黑名单报警记录
                                alarmRecordMapper.insertBlackListRecord(alarmRecord);
                                logger.info("人脸照下黑名单报警记录插入成功");
                            }
                        }
                    }
                }
            }
        }
    }
}
