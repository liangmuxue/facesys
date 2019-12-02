package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.baseinfo.common.model.AnalysisResult;
import com.ss.isc.data.collect.client.IAddPersonCollectService;
import com.ss.isc.data.collect.client.IAlarmService;
import com.ss.isc.data.collect.client.IAnalysisTaskService;
import com.ss.isc.data.collect.client.IPeopleService;
import com.ss.isc.data.collect.common.model.AddPerson;
import com.ss.isc.data.collect.common.model.AddPersonCollect;
import com.ss.isc.data.collect.common.model.AddPersonDetail;
import com.ss.isc.data.collect.common.model.AlarmRecord;
import com.ss.isc.data.collect.common.model.AnalysisTask;
import com.ss.isc.data.collect.common.model.FrequencyRecord;
import com.ss.isc.data.resource.client.ICameraService;
import com.ss.isc.data.resource.common.model.Camera;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.JsonUtil;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.SpringUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CacheConstant;
import com.ss.isc.util.em.Enums;
import com.ss.isc.util.http.BaseFormatJsonUtil;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.isc.util.thread.SysThreadPool;
import com.ss.tools.FileUtils;
import com.ss.tools.UUIDUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.j7cai.common.util.UUIDUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* 疑似新增数据同步定时任务
* @author chao
* @create 2019/9/10
* @email lishuangchao@ss-cas.com
**/
public class AnalysisAddJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(AnalysisAddJob.class);
    public JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private ICameraService cameraService = SpringUtil.getBean(ICameraService.class);
    private IAccessService accessService = SpringUtil.getBean(IAccessService.class);
    private IAnalysisTaskService analysisTaskService = SpringUtil.getBean(IAnalysisTaskService.class);
    private IAddPersonCollectService addPersonCollectService = SpringUtil.getBean(IAddPersonCollectService.class);
    private IAlarmService alarmService = SpringUtil.getBean(IAlarmService.class);
    private IPeopleService peopleService = SpringUtil.getBean(IPeopleService.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        LOG.info("定时任务AnalysisAddJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        String key1 = CacheConstant.TASK_KEY + DateUtil.getYesterdayCurrentDay();
        String key2 = CacheConstant.REDIS_KEY_DEVICEIDS_ADD;

        boolean isRun = true;
        while (isRun) {
            if (/*this.jedisUtil.hasKey(key1) && */!this.jedisUtil.hasKey(key2)) {
                this.jedisUtil.del(key1);
                isRun = false;
                AddJobThread job = new AddJobThread();
                //开启定时任务
                SysThreadPool.getThread().execute(job);
                continue;
            }
            try {
                Thread.sleep(PropertiesUtil.getJobSleepTime());
            } catch (InterruptedException e) {
                LOG.error("感知发现分析异常:", e);
            }
        }
    }

    private AddPerson addPersonFactory(AnalysisResult clusterResult, AddPersonDetail detail, Map<String, String> cameraMap) {
        AddPerson person = null;
        String[] camerArr = new String[5];
        if (null != detail) {
            person = new AddPerson();
            person.setId(detail.getAddPersonId());
            if (cameraMap.containsKey(detail.getDeviceId())) {
                camerArr = ((String) cameraMap.get(detail.getDeviceId())).split(",");
                person.setVillageId(camerArr[0]);
                person.setDoorCode(camerArr[1]);
                person.setDoorName(camerArr[1]);
                person.setRegionCode(camerArr[2]);
            } else {
                LOG.error("感知发现聚类 - 未找到设备id为：" + detail.getDeviceId() + "的设备，请确认欧神设备id和本地设备id是否一致");
            }
            person.setCapturePath(detail.getCaptureUrlFull());
            person.setPanoramaPath(detail.getPanoramaUrlFull());
            person.setLastCaptureTime(detail.getCaptureTime());
            person.setDeviceNo(detail.getDeviceId());
            person.setState(Enums.SysState.STATE_1.getCode());
            person.setKeyState(Enums.SysState.STATE_0.getCode());
            person.setExcludeFlag(Enums.SysState.STATE_0.getCode());
            person.setFacePitch(detail.getFacePitch());
            person.setFaceYaw(detail.getFaceYaw());
            person.setFaceRoll(detail.getFaceRoll());
            person.setFacex(detail.getFacex());
            person.setFacey(detail.getFacey());
            person.setFaceWidth(detail.getFaceWidth());
            person.setFaceHeight(detail.getFaceHeight());
        }
        return person;
    }

    private List<AddPersonDetail> addPersonDetialFactory(String resultId, String addPersonId, Map<String, String> cameraMap, JSONObject groupResult) {
        String[] camerArr = new String[5];
        JSONArray detailArr = null;
        List<AddPersonDetail> detairList = null;

        if (StringUtils.checkSuccess(groupResult)) {
            detailArr = JsonUtil.getJsonArray(groupResult);
            AddPersonDetail apd = null;
            JSONObject obj = null;
            Map<String, String> captureIdMap = null;
            if (!detailArr.isEmpty()) {
                if (null == addPersonId) {
                    addPersonId = UUIDUtil.uuid();
                } else {
                    //查询抓拍照编号
                    List<String> captureIds = this.peopleService.queryAddPersonDetCaptureIds(addPersonId);
                    if (!StringUtils.isEmpty(captureIds)) {
                        captureIdMap = new HashMap<String, String>();
                        for (String s : captureIds) {
                            captureIdMap.put(s, s);
                        }
                    }
                }

                detairList = new ArrayList<AddPersonDetail>();
                AlarmRecord info = null;
                String cid = null;
                for (int i = 0; i < detailArr.size(); i++) {
                    obj = detailArr.getJSONObject(i);
                    cid = obj.getString("captureId");
                    if (null == captureIdMap || !captureIdMap.containsKey(cid)) {
                        //查询布控报警数据
                        info = getCaptureInfo(cid);
                        apd = new AddPersonDetail();
                        apd.setId(UUIDUtil.uuid());
                        apd.setAddPersonId(addPersonId);
                        apd.setCaptureId(cid);
                        apd.setCaptureTime(obj.getDate("captureTime"));
                        apd.setCaptureUrl(obj.getString("captureUrl"));
                        apd.setCaptureUrlFull(obj.getString("captureUrlFull"));
                        apd.setDeviceId(obj.getString("deviceId"));
                        apd.setLng(obj.getString("lng"));
                        apd.setLat(obj.getString("lat"));
                        apd.setTaskId(obj.getString("refTaskId"));
                        apd.setDetailId(obj.getString("refResultId"));
                        if (null != info) {
                            apd.setFacePitch(info.getPitch());
                            apd.setFaceYaw(info.getYaw());
                            apd.setFaceRoll(info.getRoll());
                            apd.setFacex(info.getX());
                            apd.setFacey(info.getY());
                            apd.setFaceWidth(info.getWidth());
                            apd.setFaceHeight(info.getHeight());
                            apd.setPanoramaUrlFull(info.getPanoramaUrlFull());
                        }
                        if (cameraMap.containsKey(apd.getDeviceId())) {
                            camerArr = ((String) cameraMap.get(apd.getDeviceId())).split(",");
                            apd.setDeviceType(camerArr[3]);
                            apd.setDeviceName(camerArr[4]);
                        }
                        detairList.add(apd);
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(detairList)) {
            Collections.sort(detairList, new Comparator<AddPersonDetail>() {
                @Override
                public int compare(AddPersonDetail d1, AddPersonDetail d2) {
                    return d1.getCaptureTime().compareTo(d2.getCaptureTime());
                }
            });
        }

        return detairList;
    }

    private FrequencyRecord frequencyRecordFactory(String addPersonId, int amount, Date date, FrequencyRecord record) {
        if (null != record) {
            record.setAmount(amount);
            record.setBeginTime(date);
            record.setEndTime(date);
        } else {
            record = new FrequencyRecord();
            record.setAddPersonId(addPersonId);
            record.setAmount(amount);
            record.setBeginTime(date);
            record.setEndTime(date);
        }
        return record;
    }

    private AlarmRecord getCaptureInfo(String captureId) {
        AlarmRecord info = null;
        if (!StringUtils.isEmpty(captureId)) {
            info = this.alarmService.getByCaptureId(captureId);
        }
        return info;
    }

    public class AddJobThread implements Runnable {

        @Override
        public void run() {
            String key = CacheConstant.REDIS_KEY_DEVICEIDS_ADD;

            if (!AnalysisAddJob.this.jedisUtil.hasKey(key)) {
                AnalysisAddJob.this.jedisUtil.set(key, DateUtil.getYesterdayCurrentDay(), 43200L);
                try {
                    Camera ct = null;
                    //查找所有摄像头
                    List<Camera> cameraList = AnalysisAddJob.this.cameraService.findCameras(ct);
                    Map<String, String> cameraMap = new HashMap<String, String>(cameraList.size());

                    for (Camera c : cameraList) {
                        cameraMap.put(c.getCameraId(), c
                                .getVillageCode() + "," + ((c.getBuildingNo() == null) ? "" : c.getBuildingNo()) + "," + c
                                .getRegionCode() + "," + c.getCameraType() + "," + c.getCameraName());
                    }
                    //查找已完成任务
                    List<AnalysisTask> finishList = AnalysisAddJob.this.analysisTaskService.queryAnalysisTask(Enums.AnalysisTaskState.FINISH.getCode(), null);

                    if (!finishList.isEmpty()) {
                        List<String> taskId = new ArrayList<String>();
                        //疑似新增人员集合
                        List<AddPerson> compareList = new ArrayList<AddPerson>();
                        //疑似新增汇总集合
                        List<AddPersonCollect> collectList = null;
                        List<String> addPersonIds = new ArrayList<String>();
                        //高频陌生人集合
                        List<FrequencyRecord> frequencyRecordList = new ArrayList<FrequencyRecord>();
                        //疑似新增详情集合
                        List<AddPersonDetail> detairArr = null;
                        Map<String, AnalysisTask> map = new HashMap<String, AnalysisTask>();
                        JSONObject resultStr = null;
                        JSONArray arr = null;
                        //查找未处理疑似新增人员
                        List<AddPerson> apList = AnalysisAddJob.this.peopleService.queryDayAddList();

                        for (AnalysisTask at : finishList) {
                            if (!"疑似新进".equals(at.getTaskName())){
                                continue;
                            }
                            //at.setState(Enums.AnalysisTaskState.OVER.getCode());
                            taskId.add(at.getId());
                            map.put(at.getId(), at);
                            //聚类分析结果列表分页查询
                            resultStr = AnalysisAddJob.this.accessService.getAnalysisResultPages(at.getId());
                            if (StringUtils.checkSuccess(resultStr)) {
                                arr = JsonUtil.getJsonArray(resultStr);
                                if (arr.size() > 0) {
                                    String faceA = null;
                                    String faceB = null;
                                    JSONObject recog = null;
                                    //聚类分析结果集合
                                    List<AnalysisResult> crList = BaseFormatJsonUtil.formatList(arr, AnalysisResult.class);
                                    try {
                                        for (AnalysisResult cr : crList) {

                                            if (cr.getCount() >= PropertiesUtil.getAddPersonJudgedDays()) {
                                                //聚类分析结果详情列表分页查询
                                                JSONObject groupResult = AnalysisAddJob.this.accessService.getAnalysisResultDetailPages(cr.getResultId());
                                                if (AnalysisAddJob.this.checkRunOnTime(groupResult)) {
                                                    boolean isExist = false;
                                                    if (!apList.isEmpty()) {
                                                        faceA = FileUtils.getBase64ByUrl(cr.getCaptureUrlFull());
                                                        for (AddPerson ap : apList) {
                                                            faceB = FileUtils.getBase64ByUrl(ap.getCapturePath());
                                                            //对比人脸
                                                            recog = AnalysisAddJob.this.accessService.getRecogOne(faceA, faceB);
                                                            if (StringUtils.checkSuccess(recog)) {
                                                                if (PropertiesUtil.getThreShold() < recog.getFloat("data")) {
                                                                    isExist = true;
                                                                    if (!AnalysisAddJob.this.ignorePeople(ap.getLabel())) {
                                                                        detairArr = AnalysisAddJob.this.addPersonDetialFactory(cr.getResultId(), ap
                                                                                .getId(), cameraMap, groupResult);
                                                                        if (!StringUtils.isEmpty(detairArr)) {
                                                                            AddPersonDetail dtTemp = (AddPersonDetail) detairArr.get(detairArr.size() - 1);
                                                                            ap.setCapturePath(dtTemp.getCaptureUrlFull());
                                                                            ap.setPanoramaPath(dtTemp.getPanoramaUrlFull());
                                                                            ap.setLastCaptureTime(dtTemp.getCaptureTime());
                                                                            ap.setDetail(detairArr);
                                                                            ap.setFacePitch(dtTemp.getFacePitch());
                                                                            ap.setFaceYaw(dtTemp.getFaceYaw());
                                                                            ap.setFaceRoll(dtTemp.getFaceRoll());
                                                                            ap.setFacex(dtTemp.getFacex());
                                                                            ap.setFacey(dtTemp.getFacey());
                                                                            ap.setFaceWidth(dtTemp.getFaceWidth());
                                                                            ap.setFaceHeight(dtTemp.getFaceHeight());
                                                                            compareList.add(ap);
                                                                            addPersonIds.add(ap.getId());
                                                                            if (cr.getCount().intValue() >= PropertiesUtil.getFrequencyPersonAmount()) {

                                                                                List<JSONObject> resultArr = AnalysisAddJob.this.seachByTodayCluster(groupResult);
                                                                                if (resultArr.size() >= PropertiesUtil.getFrequencyPersonAmount()) {

                                                                                    FrequencyRecord record = AnalysisAddJob.this.analysisTaskService.getFrequencyRecordById(dtTemp
                                                                                            .getAddPersonId());
                                                                                    record = AnalysisAddJob.this.frequencyRecordFactory(dtTemp
                                                                                            .getAddPersonId(), resultArr
                                                                                            .size(), dtTemp
                                                                                            .getCaptureTime(), record);

                                                                                    frequencyRecordList.add(record);
                                                                                }
                                                                            }
                                                                        }
                                                                    } else {
                                                                        AnalysisAddJob.LOG.info("感知发现 - 用户标签被忽略" + ap.getLabel());
                                                                    }
                                                                    apList.remove(ap);
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        if (!isExist) {
                                                            //疑似新增详情处理
                                                            detairArr = AnalysisAddJob.this.addPersonDetialFactory(cr.getResultId(), null, cameraMap, groupResult);
                                                            if (!detairArr.isEmpty()) {
                                                                AddPerson cap = AnalysisAddJob.this.addPersonFactory(cr, (AddPersonDetail) detairArr.get(detairArr.size() - 1), cameraMap);
                                                                cap.setDetail(detairArr);
                                                                compareList.add(cap);
                                                                if (cr.getCount().intValue() >= PropertiesUtil.getFrequencyPersonAmount()) {
                                                                    List<JSONObject> resultArr = AnalysisAddJob.this.seachByTodayCluster(groupResult);
                                                                    if (resultArr.size() >= PropertiesUtil.getFrequencyPersonAmount()) {

                                                                        FrequencyRecord record = AnalysisAddJob.this.analysisTaskService.getFrequencyRecordById(((AddPersonDetail) detairArr
                                                                                .get(0)).getAddPersonId());
                                                                        record = AnalysisAddJob.this.frequencyRecordFactory(cap.getId(), resultArr.size(), cap
                                                                                .getLastCaptureTime(), null);
                                                                        frequencyRecordList.add(record);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        continue;
                                                    }
                                                    AnalysisAddJob.LOG.info("感知发现 - 未研判的感知新增数据");
                                                    //疑似新增详情处理
                                                    detairArr = AnalysisAddJob.this.addPersonDetialFactory(cr.getResultId(), null, cameraMap, groupResult);
                                                    if (!detairArr.isEmpty()) {
                                                        AddPerson cap = AnalysisAddJob.this.addPersonFactory(cr, (AddPersonDetail) detairArr.get(detairArr.size() - 1), cameraMap);
                                                        cap.setDetail(detairArr);
                                                        compareList.add(cap);
                                                        if (detairArr.size() >= PropertiesUtil.getFrequencyPersonAmount()) {
                                                            List<JSONObject> resultArr = AnalysisAddJob.this.seachByTodayCluster(groupResult);
                                                            if (resultArr.size() >= PropertiesUtil.getFrequencyPersonAmount()) {
                                                                FrequencyRecord record = AnalysisAddJob.this.frequencyRecordFactory(cap.getId(), resultArr.size(), cap
                                                                        .getLastCaptureTime(), null);
                                                                frequencyRecordList.add(record);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        AnalysisAddJob.LOG.error("连续时间判断:", e);
                                    }
                                }
                            }
                        }

                        if (!compareList.isEmpty()) {
                            AddPersonCollect collect = null;
                            List<AddPersonCollect> collectDbList = null;
                            Map<String, AddPersonCollect> collectMap = null;
                            if (!addPersonIds.isEmpty()) {

                                collectDbList = AnalysisAddJob.this.addPersonCollectService.queryCollectList(addPersonIds);
                                if (!StringUtils.isEmpty(collectDbList)) {

                                    collectMap = new HashMap<String, AddPersonCollect>();
                                    for (AddPersonCollect coll : collectDbList) {
                                        if (!collectMap.containsKey(coll.getAddPersonId())) {
                                            collectMap.put(coll.getAddPersonId(), coll);
                                        }
                                    }
                                }
                            }

                            for (AddPerson aps : compareList) {
                                collect = AnalysisAddJob.this.addPersonCollectFactory(aps, collectMap);
                                if (null != collect) {
                                    collectList = StringUtils.pickList(collectList, collect);
                                }
                            }

                            AnalysisAddJob.this.jedisUtil.del("REDIS_KEY_PROCESSTYPE_ADDPERSON");
                        }
                        AnalysisAddJob.LOG.info("*****************************************");
                        if (null != collectList && !collectList.isEmpty()) {
                            for (AddPersonCollect cc : collectList) {
                                AnalysisAddJob.LOG.info(cc.getDayBegin() + " - " + cc.getDayEnd() + ":" + cc.getDays());
                            }
                        }
                        AnalysisAddJob.LOG.info("*****************************************");
                        //向表中插入数据
                        AnalysisAddJob.this.analysisTaskService.analysisAddJob(compareList, finishList, collectList, frequencyRecordList);

                    } else {
                        AnalysisAddJob.LOG.info("感知发现 - 未发现进行中感知发现的聚类任务");
                    }

                } catch (Exception ex) {
                    AnalysisAddJob.LOG.error("感知发现聚类 分析异常", ex);
                } finally {

                    AnalysisAddJob.this.jedisUtil.del(key);
                }
            } else {

                AnalysisAddJob.LOG.error("感知发现聚类 - 聚类任务" + AnalysisAddJob.this.jedisUtil.get(key) + "尚未结束");
            }
        }

    }

    /**
     * 疑似新增汇总数据整理
     * @param addPerson
     * @param collectMap
     * @return
     */
    private AddPersonCollect addPersonCollectFactory(AddPerson addPerson, Map<String, AddPersonCollect> collectMap) {
        List<AddPersonDetail> detairList = addPerson.getDetail();
        if (!StringUtils.isEmpty(detairList)) {
            Collections.sort(detairList, new Comparator<AddPersonDetail>() {
                @Override
                public int compare(AddPersonDetail d1, AddPersonDetail d2) {
                    return d1.getCaptureTime().compareTo(d2.getCaptureTime());
                }
            });
        }

        Date bDate = ((AddPersonDetail) detairList.get(0)).getCaptureTime();
        Date eDate = ((AddPersonDetail) detairList.get(detairList.size() - 1)).getCaptureTime();

        AddPersonCollect collect = null;
        int days = 0;
        if (null != collectMap && collectMap.containsKey(addPerson.getId())) {
            collect = (AddPersonCollect) collectMap.get(addPerson.getId());
            days = (int) DateUtil.getDaySub(collect.getDayBegin() + " 00:00:00", DateUtil.formatDateDefault(eDate) + " 00:00:00");
            collect.setDayEnd(DateUtil.formatDateDefault(eDate));
            collect.setDays(days + 1);
        } else {
            collect = new AddPersonCollect();
            collect.setAddPersonId(addPerson.getId());
            collect.setCollectId(UUIDUtils.getUUID());
            collect.setDayBegin(DateUtil.formatDateDefault(bDate));
            collect.setDayEnd(DateUtil.formatDateDefault(eDate));
            days = (int) DateUtil.getDaySub(collect.getDayBegin() + " 00:00:00", DateUtil.formatDateDefault(eDate) + " 00:00:00");
            collect.setDays(days + 1);
        }
        return collect;
    }

    /**
     * 判断是否忽略标签
     * @param label
     * @return
     */
    private boolean ignorePeople(String label) {
        boolean isPass = false;
        if (!StringUtils.isEmpty(label)) {
            String[] setting = PropertiesUtil.getPeopleLabel().split(",");
            String[] labels = label.split(",");
            for (String st : setting) {
                for (String lb : labels) {
                    if (st.equals(lb)) {
                        return true;
                    }
                }
            }
        }

        return isPass;
    }

    private List<JSONObject> seachByTodayCluster(JSONObject groupResult) {
        JSONArray detailArr = JsonUtil.getJsonArray(groupResult);
        List<JSONObject> resultArr = new ArrayList<JSONObject>();
        JSONObject obj = null;
        if (detailArr.size() >= PropertiesUtil.getFrequencyPersonAmount()) {
            for (int i = 0; i < detailArr.size(); i++) {
                obj = detailArr.getJSONObject(i);
                if (obj.containsKey("captureTime") && null != obj.getDate("captureTime")) {
                    Date dt = obj.getDate("captureTime");
                    if (dt.compareTo(DateUtil.getBeginDayOfYesterday()) >= 0 &&
                            DateUtil.getEndDayOfYesterDay().compareTo(dt) >= 0) {
                        resultArr.add(obj);
                    }
                }
            }
        }
        return resultArr;
    }

    /**
     * 判断新增人员是否连续出现
     * @param groupResult
     * @return
     */
    private boolean checkRunOnTime(JSONObject groupResult) {
        boolean isAdd = false;
        try {
            if (StringUtils.checkSuccess(groupResult)) {

                JSONArray detailArr = null;
                detailArr = JsonUtil.getJsonArray(groupResult);
                JSONObject obj = null;
                List<Date> list = new ArrayList<Date>();
                if (!detailArr.isEmpty()) {
                    for (int i = 0; i < detailArr.size(); i++) {
                        obj = detailArr.getJSONObject(i);
                        if (obj.containsKey("captureTime") && null != obj.getDate("captureTime")) {
                            //存储时间日期 年/月/日
                            list.add(DateUtil.formatDate(DateUtil.formatDateDefault(obj.getDate("captureTime"))));
                        }
                    }

                    if (!list.isEmpty()) {

                        Collections.sort(list, new Comparator<Date>() {
                            @Override
                            public int compare(Date d1, Date d2) {
                                return d1.compareTo(d2);
                            }
                        });
                        Calendar calendar = Calendar.getInstance();
                        Date begin = null;
                        //i代表连续出现天数
                        int i = 0;
                        for (Date d : list) {
                            if (null != begin) {
                                if (begin.compareTo(d) == 0) {
                                    continue;
                                }
                                calendar.setTime(begin);
                                //天数加1
                                calendar.add(5, 1);
                                if (calendar.getTime().compareTo(d) == 0) {
                                    begin = d;
                                    i++;
                                }
                                continue;
                            }
                            begin = d;
                            i++;
                        }
                        if (i == PropertiesUtil.getAddPersonJudgedDays()) {
                            isAdd = true;
                        }
                    }
                }
            }

            if (!isAdd) {
                List<JSONObject> resultArr = seachByTodayCluster(groupResult);
                //判断是否高频
                if (resultArr.size() >= PropertiesUtil.getFrequencyPersonAmount()) {
                    isAdd = true;
                }
            }

        } catch (Exception e) {
            LOG.error("连续时间判断:", e);
            isAdd = false;
        }
        return isAdd;
    }

}
