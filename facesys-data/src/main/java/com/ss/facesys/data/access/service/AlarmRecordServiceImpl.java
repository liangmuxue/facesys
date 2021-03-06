package com.ss.facesys.data.access.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.j7cai.common.util.JsonUtils;
import com.ss.facesys.data.access.common.dto.MonUserRef;
import com.ss.facesys.data.access.common.dto.MonitorTask;
import com.ss.facesys.data.access.common.web.AlarmRecordsVO;
import com.ss.facesys.data.access.common.web.MonVO;
import com.ss.facesys.data.access.mapper.AlarmRecordMapper;
import com.ss.facesys.data.access.mapper.MonMapper;
import com.ss.facesys.data.access.mapper.MonUserRefMapper;
import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.data.baseinfo.mapper.EnumMapper;
import com.ss.facesys.data.collect.common.dto.Transfer;
import com.ss.facesys.data.collect.common.model.*;
import com.ss.facesys.data.collect.mapper.DevicePersoncardMapper;
import com.ss.facesys.data.collect.mapper.FacedbFaceMapper;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.constant.SfgoHttpConstant;
import com.ss.facesys.util.em.*;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.facesys.util.netty.MyWebSocket;
import com.ss.spider.system.user.mapper.UserResourceMapper;
import com.ss.spider.system.user.model.UserResource;
import com.ss.tools.DateUtils;
import com.ss.tools.FileUtils;
import com.ss.utils.BaseHttpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlarmRecordServiceImpl {

    private Logger logger = LoggerFactory.getLogger(AlarmRecordServiceImpl.class);

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
    @Resource
    private MonUserRefMapper monUserRefMapper;
    @Resource
    private JedisUtil jedisUtil;
    @Resource
    private UserResourceMapper userResourceMapper;

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
                            groupId = groupId + "," + facedb.getFacedbId();
                        }
                    }
                    //人脸注册库1:N准备数据
                    Map<String, Object> facedbMap = new HashMap<>();
                    facedbMap.put("groupId", groupId);
                    String base64ByUrl = null;
                    try {
                        base64ByUrl = FileUtils.getBase64ByUrl(FilePropertiesUtil.getHttpUrl() + snapRecord.getCaptureUrl());
                        //base64ByUrl = FileUtils.getImageStr("F:\\home\\program\\ss_facesys_resource\\media" + snapRecord.getCaptureUrl().replace("/capture/","\\capture\\"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    facedbMap.put("img",base64ByUrl);
                    facedbMap.put("topN", PropertiesUtil.getTopN());
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
                                faceStrangerAlarm(monitorTask,snapRecord);
                            }
                            if(resObj.containsKey("faces")){
                                //sfgo比对成功，校验业务层是否删除
                                JSONArray faceData = (JSONArray) resObj.get("faces");
                                if (faceData != null) {
                                    //循环取值
                                    List<String> userList = new ArrayList<>();
                                    for (int i = 0; i < faceData.size(); i++) {
                                        JSONObject json = faceData.getJSONObject(i);
                                        userList.add(json.getString("userId"));
                                    }
                                    Example example1 = new Example(FacedbFace.class);
                                    example1.createCriteria().andEqualTo("status",1).andIn("faceId",userList);
                                    List<FacedbFace> facedbFaces = facedbFaceMapper.selectByExample(example1);
                                    if(CollectionUtils.isEmpty(facedbFaces)){
                                        //业务层无此人，陌生人报警
                                        faceStrangerAlarm(monitorTask,snapRecord);
                                    }
                                }
                            }
                        }
                        //判断任务类型为黑名单报警
                        else if (monitorTask.getMonitorType().equals(MonitorTypeEnum.BLACK.getCode())) {
                            if (resObj.containsKey("faces")) {
                                //存在即报警
                                JSONArray faceData = (JSONArray) resObj.get("faces");
                                if (faceData != null) {
                                    //循环取值
                                    for (int i = 0; i < faceData.size(); i++) {
                                        JSONObject jsonObject = faceData.getJSONObject(i);
                                        FacedbFace facedbFace = new FacedbFace();
                                        facedbFace.setStatus(1);
                                        facedbFace.setFaceId(jsonObject.getString("userId"));
                                        FacedbFace facedbFace1 = facedbFaceMapper.selectOne(facedbFace);
                                        if(facedbFace1 != null){
                                            //业务层未删除此人时报警
                                            faceBlackAlarm(monitorTask,snapRecord,jsonObject);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 人脸陌生人报警
     * @param monitorTask
     * @param snapRecord
     */
    private void faceStrangerAlarm(MonitorTask monitorTask, SnapRecord snapRecord){
        AlarmRecord alarmRecord = new AlarmRecord();
        //报警配置信息
        alarmRecord.setAlarmId(monitorTask.getAlarmId());
        alarmRecord.setAlarmName(monitorTask.getAlarmName());
        alarmRecord.setTipFlag(monitorTask.getTipFlag());
        alarmRecord.setVoiceFlag(monitorTask.getVoiceFlag());
        alarmRecord.setColorCode(monitorTask.getColorCode());
        alarmRecord.setVoiceUrl(monitorTask.getVoiceUrl());
        //布控信息
        alarmRecord.setMonitorUserName(monitorTask.getMonitorUserName());
        alarmRecord.setMonitorId(monitorTask.getId());
        alarmRecord.setMonitorCode(monitorTask.getMonitorCode());
        alarmRecord.setMonitorName(monitorTask.getMonitorName());
        alarmRecord.setMonitorType(monitorTask.getMonitorType());
        alarmRecord.setMonitorProperty(monitorTask.getMonitorProperty());
        alarmRecord.setTopn(PropertiesUtil.getTopN());
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
            alarmRecord.setDeviceId(cameraMesss.getId());
            alarmRecord.setDeviceNo(cameraMesss.getCameraId());
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
            alarmRecord.setDeviceId(deviceMess.getId());
            alarmRecord.setDeviceNo(deviceMess.getDeviceId());
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
        alarmRecord.setSunGlassesState(snapRecord.getSunglasses());
        alarmRecord.setMinority(snapRecord.getMinority());
        //获取备注
        alarmRecord.setRemark(monitorTask.getRemark());
        //插入陌生人报警记录
        alarmRecord.setCreateTime(System.currentTimeMillis());
        alarmRecordMapper.insertStrangerRecord(alarmRecord);
        logger.info("人脸照下陌生人报警记录插入成功");
        transferData(alarmRecord);
    }

    /**
     * 人脸黑名单报警
     * @param monitorTask
     * @param snapRecord
     * @param jsonObject
     */
    private void faceBlackAlarm(MonitorTask monitorTask, SnapRecord snapRecord,JSONObject jsonObject ){
        AlarmRecord alarmRecord = new AlarmRecord();
        //报警配置信息
        alarmRecord.setAlarmId(monitorTask.getAlarmId());
        alarmRecord.setAlarmName(monitorTask.getAlarmName());
        alarmRecord.setTipFlag(monitorTask.getTipFlag());
        alarmRecord.setVoiceFlag(monitorTask.getVoiceFlag());
        alarmRecord.setColorCode(monitorTask.getColorCode());
        alarmRecord.setVoiceUrl(monitorTask.getVoiceUrl());
        //布控信息
        alarmRecord.setMonitorUserName(monitorTask.getMonitorUserName());
        alarmRecord.setMonitorId(monitorTask.getId());
        alarmRecord.setMonitorCode(monitorTask.getMonitorCode());
        alarmRecord.setMonitorName(monitorTask.getMonitorName());
        alarmRecord.setMonitorType(monitorTask.getMonitorType());
        alarmRecord.setMonitorProperty(monitorTask.getMonitorProperty());
        alarmRecord.setTopn(PropertiesUtil.getTopN());
        alarmRecord.setAlarmScore(monitorTask.getFaceThreshold() / 100);
        alarmRecord.setCaptureId(String.valueOf(snapRecord.getId()));
        alarmRecord.setCaptureTime(snapRecord.getCaptureTime());
        alarmRecord.setAlarmTime(System.currentTimeMillis());
        //查询设备相关信息
        if (snapRecord.getDeviceType().equals(ResourceType.CAMERA.getValue())) {
            //相机设备
            Camera cameraMess = new Camera();
            cameraMess.setId(snapRecord.getDeviceId());
            Camera cameraMesss = cameraMapper.selectOne(cameraMess);
            alarmRecord.setDeviceId(cameraMesss.getId());
            alarmRecord.setDeviceNo(cameraMesss.getCameraId());
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
        if (snapRecord.getDeviceType().equals(ResourceType.PERSONCARD.getValue())) {
            //人证设备
            DevicePersoncard devicePersoncard = new DevicePersoncard();
            devicePersoncard.setId(snapRecord.getDeviceId());
            DevicePersoncard deviceMess = devicePersoncardMapper.selectOne(devicePersoncard);
            alarmRecord.setDeviceId(deviceMess.getId());
            alarmRecord.setDeviceNo(deviceMess.getDeviceId());
            alarmRecord.setDeviceCode(deviceMess.getDeviceCode());
            alarmRecord.setDeviceType(ResourceType.PERSONCARD.getValue());
            alarmRecord.setDeviceName(deviceMess.getDeviceName());
            alarmRecord.setLng(String.valueOf(deviceMess.getLon()));
            alarmRecord.setLat(String.valueOf(deviceMess.getLat()));
        }
        //获取特征值
        alarmRecord.setMaskState(snapRecord.getMask());
        alarmRecord.setGlassesState(snapRecord.getGlasses());
        alarmRecord.setSunGlassesState(snapRecord.getSunglasses());
        alarmRecord.setMinority(snapRecord.getMinority());
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
            if (StringUtils.isNotBlank(facedbFaceMess.getBirthday())) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                Date bithday = null;
                try {
                    bithday = format.parse(facedbFaceMess.getBirthday());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int age = DateUtils.getAgeByBirth(bithday);
                String[] ageA = AgeTypeRangeEnum.AGEa.getCode().split("-");
                String[] ageB = AgeTypeRangeEnum.AGEb.getCode().split("-");
                String[] ageC = AgeTypeRangeEnum.AGEc.getCode().split("-");
                String[] ageD = AgeTypeRangeEnum.AGEd.getCode().split("-");
                String[] ageE = AgeTypeRangeEnum.AGEe.getCode().split("-");
                if (age > Integer.parseInt(ageA[0]) && age < Integer.parseInt(ageA[1])) {
                    alarmRecord.setAgeGroupName(AgeTypeRangeEnum.AGEa.getName());
                    alarmRecord.setAgeGroup(AgeTypeEnum.CHILDREN.getCode());
                } else if (age > Integer.parseInt(ageB[0]) && age < Integer.parseInt(ageB[1])) {
                    alarmRecord.setAgeGroupName(AgeTypeRangeEnum.AGEb.getName());
                    alarmRecord.setAgeGroup(AgeTypeEnum.JUVENILE.getCode());
                } else if (age > Integer.parseInt(ageC[0]) && age < Integer.parseInt(ageC[1])) {
                    alarmRecord.setAgeGroupName(AgeTypeRangeEnum.AGEc.getName());
                    alarmRecord.setAgeGroup(AgeTypeEnum.YOUTH.getCode());
                } else if (age > Integer.parseInt(ageD[0]) && age < Integer.parseInt(ageD[1])) {
                    alarmRecord.setAgeGroupName(AgeTypeRangeEnum.AGEd.getName());
                    alarmRecord.setAgeGroup(AgeTypeEnum.MIDDLE.getCode());
                } else if (age > Integer.parseInt(ageE[0]) && age < Integer.parseInt(ageE[1])) {
                    alarmRecord.setAgeGroupName(AgeTypeRangeEnum.AGEe.getName());
                    alarmRecord.setAgeGroup(AgeTypeEnum.OLD.getCode());
                }
                alarmRecord.setAge(age);
            } else {
                alarmRecord.setAge(null);
            }
            alarmRecord.setRegisterUrl(FilePropertiesUtil.getHttpUrl() + facedbFaceMess.getFacePath());
            alarmRecord.setRegId(facedbFaceMess.getFaceId());
            //获取库信息
            if (jsonObject.containsKey("groupId")) {
                alarmRecord.setRegdb(jsonObject.getString("groupId"));
                //查询库名
                Facedb facedb = new Facedb();
                facedb.setFacedbId(jsonObject.getString("groupId"));
                Facedb facedbMess = facedbMapper.selectOne(facedb);
                alarmRecord.setRegdbName(facedbMess.getName());
                alarmRecord.setRegdbId(facedbMess.getId());
                alarmRecord.setRegdbType(facedbMess.getType());
            }
        }
        //获取对比值
        if (jsonObject.containsKey("score")) {
            alarmRecord.setScore(jsonObject.getFloat("score") / 100);
        }
        //插入黑名单报警记录
        alarmRecord.setCreateTime(System.currentTimeMillis());
        alarmRecordMapper.insertBlackListRecord(alarmRecord);
        logger.info("人脸照下黑名单报警记录插入成功");
        transferData(alarmRecord);
    }

    public List<AlarmRecord> selBlackRecord(AlarmRecordsVO para){
        //处警人可查看报警信息
        MonUserRef monUserRef = new MonUserRef();
        monUserRef.setUserId(para.getUserId());
        List<MonUserRef> monUserRefList = monUserRefMapper.select(monUserRef);
        if(CollectionUtils.isNotEmpty(monUserRefList)) {
            List<Integer> monIdList = new ArrayList();
            for (MonUserRef userRef : monUserRefList) {
                monIdList.add(userRef.getMonitorId());
            }
            Example example = new Example(AlarmRecord.class);
            example.createCriteria().andIn("monitorId", monIdList).andEqualTo("monitorType", MonitorTypeEnum.BLACK.getCode());
            //姓名
            if (StringUtils.isNotBlank(para.getName())) {
                example.and().andLike("name",'%' + para.getName() + "%");
            }
            //证件号
            if (StringUtils.isNotBlank(para.getCardId())) {
                example.and().andLike("cardId", '%' + para.getCardId() + "%");
            }
            //人像库
            if (StringUtils.isNotBlank(para.getFacedbIds())) {
                List facedbList = Arrays.asList(para.getFacedbIds().split(","));
                example.and().andIn("regdbId", facedbList);
            }
            //设备条件
            if (StringUtils.isNotBlank(para.getCameraIds()) && StringUtils.isNotBlank(para.getPersoncardDeviceIds())) {
                List cameraIdList = Arrays.asList(para.getCameraIds().split(","));
                List personcardDebiceIdList = Arrays.asList(para.getPersoncardDeviceIds().split(","));
                example.and().andEqualTo("deviceType", ResourceType.CAMERA.getValue())
                        .andIn("deviceId", cameraIdList)
                        .orEqualTo("deviceType", ResourceType.PERSONCARD.getValue())
                        .andIn("deviceId", personcardDebiceIdList);

            } else {
                if (StringUtils.isNotBlank(para.getCameraIds())) {
                    List cameraIdList = Arrays.asList(para.getCameraIds().split(","));
                    example.and().andEqualTo("deviceType", ResourceType.CAMERA.getValue())
                            .andIn("deviceId", cameraIdList);
                }
                if (StringUtils.isNotBlank(para.getPersoncardDeviceIds())) {
                    List personcardDebiceIdList = Arrays.asList(para.getPersoncardDeviceIds().split(","));
                    example.and().andEqualTo("deviceType", ResourceType.PERSONCARD.getValue())
                            .andIn("deviceId", personcardDebiceIdList);
                }
            }
            if (para.getMonitorId() != null) {
                example.and().andEqualTo("monitorId", para.getMonitorId());
            }
            if (para.getAlarmId() != null) {
                example.and().andEqualTo("alarmId", para.getAlarmId());
            }
            if (para.getState() != null) {
                example.and().andEqualTo("state", para.getState());
            }
            if (para.getStartTime() != null  && !"".equals(para.getEndTime())) {
                example.and().andBetween("alarmTime", para.getStartTime(), para.getEndTime());
            }
            if (StringUtils.isNotBlank(para.getMonitorUserName())) {
                example.and().andLike("monitorUserName",'%' + para.getMonitorUserName() + '%');
            }
            if (StringUtils.isNotBlank(para.getRemark())) {
                example.and().andLike("remark",'%' + para.getRemark() + '%');
            }
            example.orderBy("createTime").desc();
            PageHelper.startPage(para.getCurrentPage(), para.getPageSize());
            List<AlarmRecord> alarmRecords = alarmRecordMapper.selectByExample(example);
            return alarmRecords;
        }else{
            return null;
        }
    }

    public List<AlarmRecord> selStrangerRecord(AlarmRecordsVO para){
        //处警人可查看报警信息
        MonUserRef monUserRef = new MonUserRef();
        monUserRef.setUserId(para.getUserId());
        List<MonUserRef> monUserRefList = monUserRefMapper.select(monUserRef);
        if(CollectionUtils.isNotEmpty(monUserRefList)) {
            List<Integer> monIdList = new ArrayList();
            for (MonUserRef userRef : monUserRefList) {
                monIdList.add(userRef.getMonitorId());
            }
            Example example = new Example(AlarmRecord.class);
            example.createCriteria().andIn("monitorId", monIdList).andEqualTo("monitorType", MonitorTypeEnum.STRANGER.getCode());
            //布控任务
            if (para.getMonitorId() != null) {
                example.and().andEqualTo("monitorId", para.getMonitorId());
            }
            //设备条件
            if (StringUtils.isNotBlank(para.getCameraIds()) && StringUtils.isNotBlank(para.getPersoncardDeviceIds())) {
                List cameraIdList = Arrays.asList(para.getCameraIds().split(","));
                List personcardDebiceIdList = Arrays.asList(para.getPersoncardDeviceIds().split(","));
                example.and().andEqualTo("deviceType", ResourceType.CAMERA.getValue())
                        .andIn("deviceId", cameraIdList)
                        .orEqualTo("deviceType", ResourceType.PERSONCARD.getValue())
                        .andIn("deviceId", personcardDebiceIdList);

            } else {
                if (StringUtils.isNotBlank(para.getCameraIds())) {
                    List cameraIdList = Arrays.asList(para.getCameraIds().split(","));
                    example.and().andEqualTo("deviceType", ResourceType.CAMERA.getValue())
                            .andIn("deviceId", cameraIdList);
                }
                if (StringUtils.isNotBlank(para.getPersoncardDeviceIds())) {
                    List personcardDebiceIdList = Arrays.asList(para.getPersoncardDeviceIds().split(","));
                    example.and().andEqualTo("deviceType", ResourceType.PERSONCARD.getValue())
                            .andIn("deviceId", personcardDebiceIdList);
                }
            }
            //报警等级
            if (para.getAlarmId() != null) {
                example.and().andEqualTo("alarmId", para.getAlarmId());
            }
            //报警时间
            if (para.getStartTime() != null  && !"".equals(para.getEndTime())) {
                example.and().andBetween("alarmTime", para.getStartTime(), para.getEndTime());
            }
            //状态
            if (para.getState() != null) {
                example.and().andEqualTo("state", para.getState());
            }
            example.orderBy("createTime").desc();
            PageHelper.startPage(para.getCurrentPage(), para.getPageSize());
            List<AlarmRecord> alarmRecords = alarmRecordMapper.selectByExample(example);
            return alarmRecords;
        }else{
            return null;
        }
    }

    public List<AlarmRecord> selInconformityRecord(AlarmRecordsVO para){
        //处警人可查看报警信息
        MonUserRef monUserRef = new MonUserRef();
        monUserRef.setUserId(para.getUserId());
        List<MonUserRef> monUserRefList = monUserRefMapper.select(monUserRef);
        if(CollectionUtils.isNotEmpty(monUserRefList)) {
            List<Integer> monIdList = new ArrayList();
            for (MonUserRef userRef : monUserRefList) {
                monIdList.add(userRef.getMonitorId());
            }
            Example example = new Example(AlarmRecord.class);
            example.createCriteria().andIn("monitorId", monIdList).andEqualTo("monitorType", MonitorTypeEnum.INCONFORMITY.getCode());
            //姓名
            if (StringUtils.isNotBlank(para.getName())) {
                example.and().andLike("name", '%' + para.getName() + "%");
            }
            //证件号
            if (StringUtils.isNotBlank(para.getCardId())) {
                example.and().andLike("cardId", '%' + para.getCardId() + "%");
            }
            //人证设备
            if (StringUtils.isNotBlank(para.getPersoncardDeviceIds())) {
                List personcardDebiceIdList = Arrays.asList(para.getPersoncardDeviceIds().split(","));
                example.and().andEqualTo("deviceType", ResourceType.PERSONCARD.getValue())
                        .andIn("deviceId", personcardDebiceIdList);
            }
            //报警时间
            if (para.getStartTime() != null && !"".equals(para.getEndTime())) {
                example.and().andBetween("alarmTime", para.getStartTime(), para.getEndTime());
            }
            //状态
            if (para.getState() != null) {
                example.and().andEqualTo("state", para.getState());
            }
            example.orderBy("createTime").desc();
            PageHelper.startPage(para.getCurrentPage(), para.getPageSize());
            List<AlarmRecord> alarmRecords = alarmRecordMapper.selectByExample(example);
            return alarmRecords;
        }else{
            return null;
        }
    }

    public String updateState(AlarmRecordsVO para){
        AlarmRecord alarmRecord = new AlarmRecord();
        alarmRecord.setState(para.getState());
        alarmRecord.setId(para.getId());
        int i = alarmRecordMapper.updateByPrimaryKeySelective(alarmRecord);
        if(i > 0){
            return "SUCCESS";
        }else{
            return "FAILED";
        }
    }

    private void transferData (AlarmRecord alarmRecord) {
        Transfer transfer = new Transfer();
        transfer.setId(alarmRecord.getId());
        transfer.setType("alarm");
        transfer.setDeviceName(alarmRecord.getDeviceName());
        transfer.setCaptureTime(alarmRecord.getCaptureTime());
        transfer.setCaptureUrl(alarmRecord.getCaptureUrlFull());
        transfer.setPanoramaUrl(alarmRecord.getPanoramaUrlFull());
        transfer.setFaceUrl(alarmRecord.getRegisterUrl());
        transfer.setRecogScore(alarmRecord.getScore());
        transfer.setPeopleName(alarmRecord.getName());
        if (alarmRecord.getGender() != null) {
            transfer.setGenderName(Enums.Gender.getName(alarmRecord.getGender()));
        }
        if (alarmRecord.getAgeGroup() != null) {
            transfer.setAgeTypeName(AgeTypeEnum.getMessage(alarmRecord.getAgeGroup()));
        }
        transfer.setFacedbName(alarmRecord.getRegdbName());
        transfer.setNationName(alarmRecord.getCardNation());
        transfer.setCardNo(alarmRecord.getCardId());
        transfer.setSunglasses(alarmRecord.getSunGlassesState());
        transfer.setGlasses(alarmRecord.getGlassesState());
        transfer.setMask(alarmRecord.getMaskState());
        transfer.setMinority(alarmRecord.getMinority());
        transfer.setAlarmName(alarmRecord.getAlarmName());
        transfer.setColorCode(alarmRecord.getColorCode());
        if (alarmRecord.getVoiceFlag() == 1) {
            transfer.setVoiceUrl(alarmRecord.getVoiceUrl());
        }
        int newTodayTotal = 0;
        if (this.jedisUtil.get(CacheConstant.TODAY_ALARM_TOTAL) == null){
            this.jedisUtil.set(CacheConstant.TODAY_ALARM_TOTAL, 1);
            newTodayTotal = 1;
        } else {
            int oldTodayTotal = (int)this.jedisUtil.get(CacheConstant.TODAY_ALARM_TOTAL);
            this.jedisUtil.set(CacheConstant.TODAY_ALARM_TOTAL, oldTodayTotal + 1);
            newTodayTotal = oldTodayTotal + 1;
        }
        transfer.setTodayAlarmTotal(newTodayTotal);
        if (alarmRecord.getTipFlag() == 0) {
            return;
        }
        for (String key : MyWebSocket.userIdMap.keySet()) {
            if (MyWebSocket.userIdMap.get(key) != null) {
                String[] strings = key.split("_");
                String userId = strings[1];
                UserResource userResource = new UserResource();
                userResource.setUserId(userId);
                userResource.setType(ResourceType.CAMERA.getValue());
                List<UserResource> allResources = userResourceMapper.select(userResource);
                if (allResources == null || allResources.size() == 0) {
                    continue;
                }
                List<Integer> allResourceIds = allResources.stream().map(UserResource::getResourceId).collect(Collectors.toList());
                if (!allResourceIds.contains(alarmRecord.getDeviceId())) {
                    continue;
                }
                String deviceIds = String.valueOf(this.jedisUtil.hget(CacheConstant.SELECTED_DEVICE, key));
                if (StringUtils.isBlank(deviceIds) || !deviceIds.contains(alarmRecord.getDeviceId().toString())) {
                    continue;
                }
                MyWebSocket.userIdMap.get(key).sendText(JsonUtils.getFastjsonFromObject(transfer));
            }
        }
    }

}
