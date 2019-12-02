package com.ss.facesys.schedule.collect.job;

import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.collect.client.IPeopleService;
import com.ss.facesys.data.collect.common.model.AddPersonDetail;
import com.ss.facesys.data.collect.common.model.AddPersonWith;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.client.IVillageService;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.JsonUtil;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.SpringUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.facesys.util.thread.SysThreadPool;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeopleWithJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(PeopleWithJob.class);
    private Logger logger = LoggerFactory.getLogger(PeopleWithJob.class);
    public JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private ICameraService cameraService = SpringUtil.getBean(ICameraService.class);
    private IAccessService accessService = SpringUtil.getBean(IAccessService.class);
    private IPeopleService peopleService = SpringUtil.getBean(IPeopleService.class);
    private IVillageService villageService = SpringUtil.getBean(IVillageService.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        LOG.info("定时任务PeopleWithJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        String key = CacheConstant.REDIS_KEY_DEVICEIDS_ADD;

        boolean isRun = true;
        while (isRun) {
            if (!this.jedisUtil.hasKey(key)) {
                isRun = false;
                WithJobThread job = new WithJobThread();
                SysThreadPool.getThread().execute(job);
                continue;
            }
            try {
                Thread.sleep(PropertiesUtil.getJobSleepTime());
            } catch (InterruptedException e) {
                this.logger.error(e.toString(), e);
            }
        }
    }

    private AddPersonWith addWithFactory(AddPersonDetail det, Map<String, Camera> cameraMap, JSONObject capture, JSONObject recog) {
        AddPersonWith apw = new AddPersonWith();
        apw.setCardId(recog.getString("cardId"));
        apw.setCardType(recog.getString("cardType"));
        apw.setName(recog.getString("name"));
        apw.setFacePath(recog.getString("facePath"));
        apw.setCardPath(recog.getString("cardPath"));
        apw.setFacePathFull(recog.getString("facePathFull"));
        apw.setCardPathFull(recog.getString("cardPathFull"));
        apw.setThreshold(recog.getFloatValue("threshold"));
        apw.setRecogScore(recog.getFloatValue("recogScore"));
        apw.setAddress(recog.getString("address"));
        apw.setKeyState(recog.getIntValue("keyState"));
        apw.setSuspectState(recog.getIntValue("suspectState"));
        apw.setAddPersonDetId(det.getId());
        apw.setCaptureId(capture.getString("captureId"));
        apw.setCaptureUrl(capture.getString("captureUrl"));
        apw.setCaptureUrlFull(capture.getString("captureUrlFull"));
        apw.setPanoramaUrl(capture.getString("panoramaUrl"));
        apw.setPanoramaUrlFull(capture.getString("panoramaUrlFull"));
        apw.setCaptureTime(capture.getDate("captureTime"));
        apw.setDeviceId(capture.getString("deviceId"));
        apw.setLng(capture.getString("lng"));
        apw.setLat(capture.getString("lat"));
        if (cameraMap.containsKey(apw.getDeviceId())) {
            apw.setVillageId(((Camera) cameraMap.get(apw.getDeviceId())).getVillageCode());
        }
        return apw;
    }

    private JSONObject getRegisterDb(String imgUrl, String facedbId) {
        JSONObject parm = new JSONObject();
        parm.put("img", imgUrl);
        parm.put("imgType", CommonConstant.IMGTYPE_URL);
        parm.put("topN", 3);
        parm.put("facedbIds", new String[]{facedbId});
        parm.put("thresholdMin", PropertiesUtil.getThreShold());
        return this.accessService.getRecogRegisterDb(parm.toJSONString());
    }

    private JSONObject getCapturePages(Date bTime, Date eTime, String deviceId, Map<String, Camera> cameraMap) {
        JSONObject parm = new JSONObject();
        parm.clear();
        parm.put("captureTimeB", bTime);
        parm.put("captureTimeE", eTime);
        parm.put("deviceIds", new String[]{deviceId});
        JSONObject result = null;
        if (cameraMap.containsKey(deviceId)) {
            if (Enums.cameraType.DOOR.getCode() == ((Camera) cameraMap.get(deviceId)).getCameraType()) {
                result = this.accessService.getCollectPages(parm.toJSONString());
            } else if (Enums.cameraType.USUAL.getCode() == ((Camera) cameraMap.get(deviceId)).getCameraType()) {
                result = this.accessService.getCapturePages(parm.toJSONString());
            }
        }
        return result;
    }

    public class WithJobThread implements Runnable {

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            long st = 0L;
            String key = "CAPTURE_DEVICEIDS_WITH";
            PeopleWithJob.this.jedisUtil.del(key);
            if (!PeopleWithJob.this.jedisUtil.hasKey(key)) {
                PeopleWithJob.LOG.info("同行分析开始");
                PeopleWithJob.this.jedisUtil.set(key, DateUtil.getYesterdayCurrentDay(), 43200L);
                try {
                    List<String> detIds = new ArrayList<String>();
                    Camera ct = null;
                    //查询相机
                    List<Camera> cameraList = PeopleWithJob.this.cameraService.findCameras(ct);
                    Map<String, Camera> cameraMap = new HashMap<String, Camera>(cameraList.size());
                    String[] deviceIds = new String[cameraList.size()];
                    for (int i = 0; i < cameraList.size(); i++) {
                        deviceIds[i] = ((Camera) cameraList.get(i)).getCameraId();
                        cameraMap.put(((Camera) cameraList.get(i)).getCameraId(), cameraList.get(i));
                    }
                    //查询疑似新增人员信息
                    List<AddPersonDetail> detList = PeopleWithJob.this.peopleService.queryDayAddDetailList(Enums.SysState.STATE_1.getCode(), PropertiesUtil.getAddPersonJudgedDays());
                    PeopleWithJob.LOG.info("同行分析-感知新增明细数量：" + detList.size());
                    if (!detList.isEmpty()) {
                        List<AddPersonWith> withList = null;
                        JSONObject capturePageStr = null;
                        JSONArray captureArr = null;
                        JSONObject captureObj = null;
                        JSONObject recogStr = null;
                        JSONArray recogArr = null;
                        JSONObject recogObj = null;
                        for (AddPersonDetail det : detList) {
                            detIds.add(det.getId());
                            Date[] time = DateUtil.getStartAndEnd(det.getCaptureTime(), PropertiesUtil.getWithSecond());
                            //取得时间段内流水抓拍记录
                            capturePageStr = PeopleWithJob.this.getCapturePages(time[0], time[1], det.getDeviceId(), cameraMap);
                            if(capturePageStr == null){
                                continue;
                            }
                            captureArr = JsonUtil.getJsonArray(capturePageStr);
                            if (null != captureArr) {
                                for (int i = 0; i < captureArr.size(); i++) {
                                    captureObj = captureArr.getJSONObject(i);
                                    if (!det.getCaptureId().equals(captureObj.getString("captureId"))) {
                                        String villageCode = PeopleWithJob.this.villageService.getVillageFacedbId(((Camera) cameraMap.get(det.getDeviceId())).getVillageCode());
                                        PeopleWithJob.LOG.info("通行分析--查询注册库 villageCode:" + villageCode);
                                        if (!StringUtils.isEmpty(villageCode)) {
                                            recogStr = PeopleWithJob.this.getRegisterDb(captureObj.getString("captureUrl"), villageCode);
                                            PeopleWithJob.LOG.info("同行分析 -注册库查询返回结果：" + recogStr.toJSONString());
                                            if (StringUtils.checkSuccess(recogStr)) {
                                                recogArr = JsonUtil.getJsonArray(recogStr);
                                                if (null != recogArr && recogArr.size() > 0) {
                                                    recogObj = recogArr.getJSONObject(0);
                                                    if (!StringUtils.isEmpty(recogObj.getString("cardId"))) {
                                                        if (withList == null) {
                                                            withList = new ArrayList<AddPersonWith>();
                                                        }
                                                        withList.add(PeopleWithJob.this.addWithFactory(det, cameraMap, captureObj, recogObj));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                continue;
                            }
                            PeopleWithJob.LOG.info("通行分析 - 分页查询 " + det.getDeviceId() + "像机抓拍数据,结果为空");
                        }

                        if (!StringUtils.isEmpty(withList)) {
                            PeopleWithJob.LOG.info("同行分析 -同行数量：" + withList.size());
                            PeopleWithJob.this.peopleService.batchInsertAddPersonWith(withList, detIds);
                        } else {
                            PeopleWithJob.LOG.info("同行分析 -同行数量：0");
                        }
                        PeopleWithJob.LOG.info("同行分析结束");
                    } else {
                        PeopleWithJob.LOG.debug("通行分析 - 状态为感知发现明细信息为空");
                    }
                    PeopleWithJob.LOG.debug("通行分析 - 结果耗时： " + (System.currentTimeMillis() - st) + "ms");
                } catch (Exception e) {
                    PeopleWithJob.LOG.error("通行分析 - 任务异常： " + e);
                } finally {
                    PeopleWithJob.this.jedisUtil.del(key);
                }
            } else {

                PeopleWithJob.LOG.debug("通行分析 - 任务未结束");
            }
            PeopleWithJob.LOG.debug("程序运行时间： " + (System.currentTimeMillis() - startTime) + "ms");
        }

    }

}
