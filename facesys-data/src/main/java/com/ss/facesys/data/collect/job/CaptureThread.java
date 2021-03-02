package com.ss.facesys.data.collect.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import com.ss.facesys.data.access.service.AlarmRecordServiceImpl;
import com.ss.facesys.data.collect.common.dto.Transfer;
import com.ss.facesys.data.collect.common.model.DevicePersoncard;
import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.facesys.data.collect.mapper.DevicePersoncardMapper;
import com.ss.facesys.data.collect.mapper.SnapRecordMapper;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.SpringUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.SfgoHttpConstant;
import com.ss.facesys.util.em.AgeTypeEnum;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.facesys.util.netty.MyWebSocket;
import com.ss.spider.system.user.mapper.UserResourceMapper;
import com.ss.spider.system.user.model.UserResource;
import com.ss.tools.FileUtils;
import com.ss.util.nasstorage.file.FileUtil;
import com.ss.utils.BaseHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * com.ss.thread
 *
 * @author 李爽超 chao
 * @create 2020/11/26
 * @email lishuangchao@ss-cas.com
 **/
public class CaptureThread implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SnapRecordMapper snapRecordMapper = SpringUtil.getBean(SnapRecordMapper.class);
    private CameraMapper cameraMapper = SpringUtil.getBean(CameraMapper.class);
    private DevicePersoncardMapper devicePersoncardMapper = SpringUtil.getBean(DevicePersoncardMapper.class);
    private UserResourceMapper userResourceMapper = SpringUtil.getBean(UserResourceMapper.class);
    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private AlarmRecordServiceImpl alarmRecordService = SpringUtil.getBean(AlarmRecordServiceImpl.class);

    private Integer deviceType;

    private String deviceNo;

    private Map<Integer, String> captureMap;

    private final static int FACE_TYPE = 1;
    private final static int BODY_TYPE = 2;
    private final static int FULL_TYPE = 3;

    private final static String TODAY_CAPTURE_TOTAL = "FACESYS_TODAY_CAPTURE_TOTAL";
    private final static String THIS_MONTH_CAPTURE_TOTAL = "FACESYS_THIS_MONTH_CAPTURE_TOTAL";

    public CaptureThread(Integer deviceType, String deviceNo, Map<Integer, String> captureMap) {
        this.deviceType = deviceType;
        this.deviceNo = deviceNo;
        this.captureMap = captureMap;
    }

    @Override
    public void run() {
        try {
            Camera camera = null;
            DevicePersoncard devicePersoncard = null;
            if (1 == deviceType) {
                Camera c = new Camera();
                c.setCameraId(deviceNo);
                c.setState(0);
                camera = this.cameraMapper.selectOne(c);
                if (camera == null) {
                    return;
                }
            } else if (2 == deviceType) {
                DevicePersoncard dp = new DevicePersoncard();
                dp.setDeviceCode(deviceNo);
                dp.setStatus(1);
                devicePersoncard = this.devicePersoncardMapper.selectOne(dp);
                if (devicePersoncard == null) {
                    return;
                }
            } else {
                return;
            }
            //人脸照base64
            String faceBase64 = captureMap.get(FACE_TYPE);
            //人体照base64
            String bodyBase64 = captureMap.get(BODY_TYPE);;
            //全景照base64
            String fullBase64 = captureMap.get(FULL_TYPE);;
            //人脸照路径
            String faceUrl = null;
            //人体照路径
            String bodyUrl = null;
            //全景照路径
            String fullUrl = null;
            if (faceBase64 != null) {
                faceUrl = "/capture/" + System.currentTimeMillis() + "_face.jpg";
                String faceImgPath = PropertiesUtil.getCaptureUrl() + faceUrl;
                FileUtil.saveBase64ToFile(faceBase64, faceImgPath);
            }
            if (bodyBase64 != null) {
                bodyUrl = "/capture/" + System.currentTimeMillis() + "_body.jpg";
                String bodyImgPath = PropertiesUtil.getCaptureUrl() + bodyUrl;
                FileUtil.saveBase64ToFile(bodyBase64, bodyImgPath);
            }
            if (fullBase64 != null) {
                fullUrl = "/capture/" + System.currentTimeMillis() + "_full.jpg";
                String fullImgPath = PropertiesUtil.getCaptureUrl() + fullUrl;
                FileUtil.saveBase64ToFile(fullBase64, fullImgPath);
            }
            if (faceBase64 != null && bodyBase64 == null && fullBase64 != null) {
                //只存在人脸照和全景照时，通过sfgo获取人体照
                JSONObject bodyFeature = new JSONObject();
                bodyFeature.put("faceImg", faceBase64);
                bodyFeature.put("img", fullBase64);
                String bodyFeatureResult = BaseHttpUtil.httpPost(JSON.toJSONString(bodyFeature), PropertiesUtil.getSfgohttp() + SfgoHttpConstant.REID_FEATURE, null);
                this.logger.info("获取人体特征值" + bodyFeatureResult);
                JSONObject bodyFeatureJson = JSONObject.parseObject(bodyFeatureResult);
                if (StringUtils.checkSuccess(bodyFeatureJson)) {
                    JSONArray bodyImgs = (JSONArray) bodyFeatureJson.get("bodyImg");
                    if (bodyImgs != null && bodyImgs.size() > 0) {
                        bodyBase64 = bodyImgs.getString(0);
                        bodyUrl = "/" + System.currentTimeMillis() + "_body.jpg";
                        String bodyImgPath = PropertiesUtil.getCaptureUrl() + bodyUrl;
                        FileUtil.saveBase64ToFile(bodyBase64, bodyImgPath);
                    }
                }
            } else if (faceBase64 == null && bodyBase64 != null) {
                //存在人体照，不存在人脸照时，通过sfgo获取人脸照
                JSONObject bodyFeature = new JSONObject();
                bodyFeature.put("img", bodyBase64);
                String bodyFeatureResult = BaseHttpUtil.httpPost(JSON.toJSONString(bodyFeature), PropertiesUtil.getSfgohttp() + SfgoHttpConstant.REID_FEATURE, null);
                this.logger.info("获取人体特征值" + bodyFeatureResult);
                JSONObject bodyFeatureJson = JSONObject.parseObject(bodyFeatureResult);
                if (StringUtils.checkSuccess(bodyFeatureJson)) {
                    JSONArray faceImgs = (JSONArray) bodyFeatureJson.get("faceImg");
                    if (faceImgs != null && faceImgs.size() > 0) {
                        faceBase64 = faceImgs.getString(0);
                        faceUrl = "/" + System.currentTimeMillis() + "_face.jpg";
                        String faceImgPath = PropertiesUtil.getCaptureUrl() + faceUrl;
                        FileUtil.saveBase64ToFile(faceBase64, faceImgPath);
                    }
                }
            }
            //眼镜
            Integer glasses = null;
            //墨镜
            Integer sunglasses = null;
            //口罩
            Integer mask = null;
            //少数民族
            Integer minority = null;
            //性别
            Integer gender = null;
            //年龄
            Integer age = null;
            //年龄类别
            Integer ageType = null;
            if (faceBase64 != null) {
                JSONObject faceFeature = new JSONObject();
                faceFeature.put("img", faceBase64);
                String faceFeatureResult = BaseHttpUtil.httpPost(JSON.toJSONString(faceFeature), PropertiesUtil.getSfgohttp() + SfgoHttpConstant.FACE_FEATURE, null);
                this.logger.info("获取人脸特征值" + faceFeatureResult);
                JSONObject faceFeatureJson = JSONObject.parseObject(faceFeatureResult);
                if (StringUtils.checkSuccess(faceFeatureJson)) {
                    //性别
                    if (faceFeatureJson.containsKey("gender")) {
                        gender = faceFeatureJson.getInteger("gender");
                    }
                    //年龄
                    if (faceFeatureJson.containsKey("age")) {
                        age = faceFeatureJson.getInteger("age");
                        String[] ageA = AgeTypeEnum.AGEa.getCode().split("-");
                        String[] ageB = AgeTypeEnum.AGEb.getCode().split("-");
                        String[] ageC = AgeTypeEnum.AGEc.getCode().split("-");
                        String[] ageD = AgeTypeEnum.AGEd.getCode().split("-");
                        String[] ageE = AgeTypeEnum.AGEe.getCode().split("-");
                        if (age > Integer.parseInt(ageA[0]) && age < Integer.parseInt(ageA[1])) {
                            ageType = 1;
                        } else if (age > Integer.parseInt(ageB[0]) && age < Integer.parseInt(ageB[1])) {
                            ageType = 2;
                        } else if (age > Integer.parseInt(ageC[0]) && age < Integer.parseInt(ageC[1])) {
                            ageType = 3;
                        } else if (age > Integer.parseInt(ageD[0]) && age < Integer.parseInt(ageD[1])) {
                            ageType = 4;
                        } else if (age > Integer.parseInt(ageE[0]) && age < Integer.parseInt(ageE[1])) {
                            ageType = 5;
                        }
                    }
                    //眼镜
                    if (faceFeatureJson.containsKey("glass")) {
                        glasses = faceFeatureJson.getInteger("glass");
                    }
                    //口罩
                    if (faceFeatureJson.containsKey("mask")) {
                        mask = faceFeatureJson.getInteger("mask");
                    }
                }
            }
            Long time = System.currentTimeMillis();
            List<SnapRecord> snapRecords = new ArrayList<>();
            if (faceUrl != null) {
                SnapRecord snapRecord = new SnapRecord();
                snapRecord.setCaptureUrl(faceUrl);
                snapRecord.setPanoramaUrl(fullUrl);
                snapRecord.setCaptureType(FACE_TYPE);
                snapRecord.setCaptureTime(time);
                if (1 == deviceType) {
                    snapRecord.setDeviceId(camera.getId());
                    snapRecord.setDeviceName(camera.getCameraName());
                } else if (2 == deviceType) {
                    snapRecord.setDeviceId(devicePersoncard.getId());
                    snapRecord.setDeviceName(devicePersoncard.getDeviceName());
                }
                snapRecord.setDeviceType(deviceType);
                snapRecord.setGlasses(glasses);
                snapRecord.setSunglasses(sunglasses);
                snapRecord.setMask(mask);
                snapRecord.setMinority(minority);
                snapRecord.setGender(gender);
                snapRecord.setAge(age);
                snapRecord.setAgeType(ageType);
                snapRecords.add(snapRecord);
            }
            if (bodyUrl != null) {
                SnapRecord snapRecord = new SnapRecord();
                snapRecord.setCaptureUrl(bodyUrl);
                snapRecord.setPanoramaUrl(fullUrl);
                snapRecord.setCaptureType(BODY_TYPE);
                snapRecord.setCaptureTime(time);
                if (1 == deviceType) {
                    snapRecord.setDeviceId(camera.getId());
                    snapRecord.setDeviceName(camera.getCameraName());
                } else if (2 == deviceType) {
                    snapRecord.setDeviceId(devicePersoncard.getId());
                    snapRecord.setDeviceName(devicePersoncard.getDeviceName());
                }
                snapRecord.setDeviceType(deviceType);
                snapRecord.setGlasses(glasses);
                snapRecord.setSunglasses(sunglasses);
                snapRecord.setMask(mask);
                snapRecord.setMinority(minority);
                snapRecord.setGender(gender);
                snapRecord.setAge(age);
                snapRecord.setAgeType(ageType);
                snapRecords.add(snapRecord);
            }
            if (snapRecords.size() > 0) {
                this.snapRecordMapper.insertList(snapRecords);
            }
            for (SnapRecord sr: snapRecords) {
                if (FACE_TYPE == sr.getCaptureType()) {
                    //人脸类型入sfgo
                    String deviceId = null;
                    if (1 == deviceType) {
                        deviceId = camera.getCameraId();
                    } else if (2 == deviceType) {
                        deviceId = devicePersoncard.getDeviceId();
                    }
                    addSfgoCapture(sr.getId(), faceBase64, deviceId, sr.getCaptureType(), time);
                    transferData(sr);
                    alarmRecordService.dealAlarmEvent(sr);
                } else if (BODY_TYPE == sr.getCaptureType()) {
                    //人体类型入sfgo
                    String deviceId = null;
                    if (1 == deviceType) {
                        deviceId = camera.getCameraId();
                    } else if (2 == deviceType) {
                        deviceId = devicePersoncard.getDeviceId();
                    }
                    addSfgoCapture(sr.getId(), bodyBase64, deviceId, sr.getCaptureType(), time);
                }
            }
        } catch (Exception e) {
            logger.info("抓拍照入库处理异常：" + e, e.toString());
        }
    }

    /**
     * 抓拍照入sfgo
     * @param captureId
     * @param base64
     * @param deviceId
     * @param captureType
     * @param time
     */
    private void addSfgoCapture(Integer captureId, String base64, String deviceId, Integer captureType, Long time) {
        if (FACE_TYPE == captureType) {
            JSONObject parm = new JSONObject();
            parm.put("groupId", deviceId);
            parm.put("userId", captureId);
            parm.put("img", base64);
            parm.put("datetime", time / 1000);
            String result = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getSfgohttp() + SfgoHttpConstant.FACE_ADD, null);
            this.logger.info("添加人脸抓拍照返回信息：" + result);
        } else if (BODY_TYPE == captureType) {
            JSONObject parm = new JSONObject();
            parm.put("groupId", deviceId);
            parm.put("userId", captureId);
            parm.put("img", base64);
            parm.put("datetime", time / 1000);
            String result = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getSfgohttp() + SfgoHttpConstant.REID_ADD, null);
            this.logger.info("添加人体抓拍照返回信息：" + result);
        }
    }

    private void transferData (SnapRecord snapRecord) {
        Transfer transfer = new Transfer();
        transfer.setId(snapRecord.getId());
        transfer.setType("capture");
        transfer.setDeviceName(snapRecord.getDeviceName());
        transfer.setCaptureTime(snapRecord.getCaptureTime());
        transfer.setCaptureUrl(FilePropertiesUtil.getHttpUrl() + snapRecord.getCaptureUrl());
        transfer.setPanoramaUrl(FilePropertiesUtil.getHttpUrl() + snapRecord.getPanoramaUrl());
        int newTodayTotal = 0;
        if (this.jedisUtil.get(TODAY_CAPTURE_TOTAL) == null){
            this.jedisUtil.set(TODAY_CAPTURE_TOTAL, 1);
            newTodayTotal = 1;
        } else {
            int oldTodayTotal = (int)this.jedisUtil.get(TODAY_CAPTURE_TOTAL);
            this.jedisUtil.set(TODAY_CAPTURE_TOTAL, oldTodayTotal + 1);
            newTodayTotal = oldTodayTotal + 1;
        }
        transfer.setTodayCaptureTotal(newTodayTotal);

        int newThisMonthTotal = 0;
        if (this.jedisUtil.get(THIS_MONTH_CAPTURE_TOTAL) == null){
            this.jedisUtil.set(THIS_MONTH_CAPTURE_TOTAL, 1);
            newThisMonthTotal = 1;
        } else {
            int oldThieMonthTotal = (int)this.jedisUtil.get(THIS_MONTH_CAPTURE_TOTAL);
            this.jedisUtil.set(THIS_MONTH_CAPTURE_TOTAL, oldThieMonthTotal + 1);
            newThisMonthTotal = oldThieMonthTotal + 1;
        }

        transfer.setThisMonthCaptureTotal(newThisMonthTotal);
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
                if (!allResourceIds.contains(snapRecord.getDeviceId())) {
                    continue;
                }
                MyWebSocket.userIdMap.get(key).sendText(JsonUtils.getFastjsonFromObject(transfer));
            }
        }
    }
}
