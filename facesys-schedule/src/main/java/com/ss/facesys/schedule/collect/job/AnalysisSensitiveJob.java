package com.ss.facesys.schedule.collect.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.j7cai.common.util.UUIDUtil;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.baseinfo.common.model.AnalysisResult;
import com.ss.facesys.data.collect.client.IAnalysisTaskService;
import com.ss.facesys.data.collect.client.IPeopleService;
import com.ss.facesys.data.collect.client.ISensitivePersonCollectService;
import com.ss.facesys.data.collect.common.model.*;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.util.*;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.http.BaseFormatJsonUtil;
import com.ss.facesys.util.thread.SysThreadPool;
import com.ss.tools.FileUtils;
import com.ss.tools.UUIDUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * 敏感通行数据同步定时任务
 * @author 李爽超 chao
 * @create 2019/09/11
 * @email lishuangchao@ss-cas.com
 **/
public class AnalysisSensitiveJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(AnalysisSensitiveJob.class);
    private ICameraService cameraService = SpringUtil.getBean(ICameraService.class);
    private IAccessService accessService = SpringUtil.getBean(IAccessService.class);
    private IAnalysisTaskService analysisTaskService = SpringUtil.getBean(IAnalysisTaskService.class);
    private ISensitivePersonCollectService sensitivePersonCollectService = SpringUtil.getBean(ISensitivePersonCollectService.class);
    private IPeopleService peopleService = SpringUtil.getBean(IPeopleService.class);

    @Override
    public void execute(ShardingContext shardingContext) {

        LOG.info("定时任务AnalysisSensitiveJob已经启动" + DateUtil.getCurrentSqlTimestampString());

        boolean isRun = true;
        while (isRun) {
            isRun = false;
            SensitiveJobThread job = new SensitiveJobThread();
            //开启定时任务
            SysThreadPool.getThread().execute(job);
        }
    }

    /**
     * 敏感通行数据整理
     * @param clusterResult
     * @param detail
     * @param cameraMap
     * @return
     */
    private SensitivePerson sensitivePersonFactory(AnalysisResult clusterResult, SensitivePersonDetail detail, Map<String, String> cameraMap) {
        SensitivePerson person = null;
        String[] camerArr = new String[5];
        if (null != detail) {
            person = new SensitivePerson();
            person.setId(detail.getSensitivePersonId());
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

    /**
     * 敏感通行详情数据整理
     * @param resultId
     * @param sensitivePersonId
     * @param cameraMap
     * @param groupResult
     * @return
     */
    private List<SensitivePersonDetail> sensitivePersonDetialFactory(String resultId, String sensitivePersonId, Map<String, String> cameraMap, JSONObject groupResult) {
        String[] camerArr = new String[5];
        JSONArray detailArr = null;
        List<SensitivePersonDetail> detairList = null;

        if (StringUtils.checkSuccess(groupResult)) {
            detailArr = JsonUtil.getJsonArray(groupResult);
            SensitivePersonDetail apd = null;
            JSONObject obj = null;
            Map<String, String> captureIdMap = null;
            if (!detailArr.isEmpty()) {
                if (null == sensitivePersonId) {
                    sensitivePersonId = UUIDUtil.uuid();
                } else {
                    //查询抓拍照编号
                    List<String> captureIds = this.peopleService.querySensitivePersonDetCaptureIds(sensitivePersonId);
                    if (!StringUtils.isEmpty(captureIds)) {
                        captureIdMap = new HashMap<String, String>();
                        for (String s : captureIds) {
                            captureIdMap.put(s, s);
                        }
                    }
                }
                detairList = new ArrayList<SensitivePersonDetail>();
                String cid = null;
                for (int i = 0; i < detailArr.size(); i++) {
                    obj = detailArr.getJSONObject(i);
                    cid = obj.getString("captureId");
                    if (null == captureIdMap || !captureIdMap.containsKey(cid)) {
                        apd = new SensitivePersonDetail();
                        apd.setId(UUIDUtil.uuid());
                        apd.setSensitivePersonId(sensitivePersonId);
                        apd.setCaptureId(cid);
                        apd.setCaptureTime(obj.getDate("captureTime"));
                        apd.setCaptureUrl(obj.getString("captureUrl"));
                        apd.setCaptureUrlFull(obj.getString("captureUrlFull"));
                        apd.setDeviceId(obj.getString("deviceId"));
                        apd.setLng(obj.getString("lng"));
                        apd.setLat(obj.getString("lat"));
                        apd.setTaskId(obj.getString("refTaskId"));
                        apd.setDetailId(obj.getString("refResultId"));
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
            Collections.sort(detairList, new Comparator<SensitivePersonDetail>() {
                @Override
                public int compare(SensitivePersonDetail d1, SensitivePersonDetail d2) {
                    return d1.getCaptureTime().compareTo(d2.getCaptureTime());
                }
            });
        }

        return detairList;
    }

    public class SensitiveJobThread implements Runnable {
        @Override
        public void run() {
            try {
                Camera ct = null;
                //查找所有摄像头
                List<Camera> cameraList = AnalysisSensitiveJob.this.cameraService.findCameras(ct);
                Map<String, String> cameraMap = new HashMap<String, String>(cameraList.size());

                for (Camera c : cameraList) {
                    cameraMap.put(c.getCameraId(), c
                            .getVillageCode() + "," + ((c.getBuildingNo() == null) ? "" : c.getBuildingNo()) + "," + c
                            .getRegionCode() + "," + c.getCameraType() + "," + c.getCameraName());
                }
                //查找已完成任务
                List<AnalysisTask> finishList = AnalysisSensitiveJob.this.analysisTaskService.queryAnalysisTask(Enums.AnalysisTaskState.FINISH.getCode(), null);

                if (!finishList.isEmpty()) {
                    List<String> taskId = new ArrayList<String>();
                    //敏感通行人员集合
                    List<SensitivePerson> compareList = new ArrayList<SensitivePerson>();
                    //敏感通行汇总集合
                    List<SensitivePersonCollect> collectList = null;
                    List<String> sensitivePersonIds = new ArrayList<String>();
                    //敏感通行详情集合
                    List<SensitivePersonDetail> detairArr = null;
                    Map<String, AnalysisTask> map = new HashMap<String, AnalysisTask>();
                    JSONObject resultStr = null;
                    JSONArray arr = null;
                    //查找未处理敏感通行人员
                    List<SensitivePerson> spList = AnalysisSensitiveJob.this.peopleService.querySensitiveList();

                    for (AnalysisTask at : finishList) {
                        if (!"敏感通行".equals(at.getTaskName())) {
                            continue;
                        }
                        //聚类分析结果列表分页查询
                        resultStr = AnalysisSensitiveJob.this.accessService.getAnalysisResultPages(at.getId());
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

                                        if (cr.getCount() >= PropertiesUtil.getSensitiveTraffic()) {
                                            //聚类分析结果详情列表分页查询
                                            JSONObject groupResult = AnalysisSensitiveJob.this.accessService.getAnalysisResultDetailPages(cr.getResultId());
                                            if (AnalysisSensitiveJob.this.checkRunOnTime(groupResult)) {
                                                boolean isExist = false;
                                                if (!spList.isEmpty()) {
                                                    faceA = FileUtils.getBase64ByUrl(cr.getCaptureUrlFull());
                                                    for (SensitivePerson sp : spList) {
                                                        faceB = FileUtils.getBase64ByUrl(sp.getCapturePath());
                                                        //对比人脸
                                                        recog = AnalysisSensitiveJob.this.accessService.getRecogOne(faceA, faceB);
                                                        if (StringUtils.checkSuccess(recog)) {
                                                            if (PropertiesUtil.getThreShold() < recog.getFloat("data")) {
                                                                isExist = true;
                                                                if (!AnalysisSensitiveJob.this.ignorePeople(sp.getLabel())) {
                                                                    detairArr = AnalysisSensitiveJob.this.sensitivePersonDetialFactory(cr.getResultId(), sp.getId(), cameraMap, groupResult);
                                                                    if (!StringUtils.isEmpty(detairArr)) {
                                                                        SensitivePersonDetail dtTemp = detairArr.get(detairArr.size() - 1);
                                                                        sp.setCapturePath(dtTemp.getCaptureUrlFull());
                                                                        sp.setPanoramaPath(dtTemp.getPanoramaUrlFull());
                                                                        sp.setLastCaptureTime(dtTemp.getCaptureTime());
                                                                        sp.setDetail(detairArr);
                                                                        sp.setFacePitch(dtTemp.getFacePitch());
                                                                        sp.setFaceYaw(dtTemp.getFaceYaw());
                                                                        sp.setFaceRoll(dtTemp.getFaceRoll());
                                                                        sp.setFacex(dtTemp.getFacex());
                                                                        sp.setFacey(dtTemp.getFacey());
                                                                        sp.setFaceWidth(dtTemp.getFaceWidth());
                                                                        sp.setFaceHeight(dtTemp.getFaceHeight());
                                                                        compareList.add(sp);
                                                                        sensitivePersonIds.add(sp.getId());
                                                                    }
                                                                } else {
                                                                    AnalysisSensitiveJob.LOG.info("感知发现 - 用户标签被忽略" + sp.getLabel());
                                                                }
                                                                spList.remove(sp);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if (!isExist) {
                                                        //敏感通行详情数据处理
                                                        detairArr = AnalysisSensitiveJob.this.sensitivePersonDetialFactory(cr.getResultId(), null, cameraMap, groupResult);
                                                        if (!detairArr.isEmpty()) {
                                                            //敏感通行数据处理
                                                            SensitivePerson cap = AnalysisSensitiveJob.this.sensitivePersonFactory(cr, detairArr.get(detairArr.size() - 1), cameraMap);
                                                            cap.setDetail(detairArr);
                                                            compareList.add(cap);
                                                        }
                                                    }
                                                    continue;
                                                }
                                                AnalysisSensitiveJob.LOG.info("感知发现 - 未研判的感知新增数据");
                                                //敏感通行详情数据处理
                                                detairArr = AnalysisSensitiveJob.this.sensitivePersonDetialFactory(cr.getResultId(), null, cameraMap, groupResult);
                                                if (!detairArr.isEmpty()) {
                                                    //敏感通行数据处理
                                                    SensitivePerson cap = AnalysisSensitiveJob.this.sensitivePersonFactory(cr,detairArr.get(detairArr.size() - 1), cameraMap);
                                                    cap.setDetail(detairArr);
                                                    compareList.add(cap);
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    AnalysisSensitiveJob.LOG.error("连续时间判断:", e);
                                }
                            }
                        }
                    }

                    if (!compareList.isEmpty()) {
                        SensitivePersonCollect collect = null;
                        List<SensitivePersonCollect> collectDbList = null;
                        Map<String, SensitivePersonCollect> collectMap = null;
                        if (!sensitivePersonIds.isEmpty()) {
                            collectDbList = AnalysisSensitiveJob.this.sensitivePersonCollectService.queryCollectList(sensitivePersonIds);
                            if (!StringUtils.isEmpty(collectDbList)) {
                                collectMap = new HashMap<String, SensitivePersonCollect>();
                                for (SensitivePersonCollect coll : collectDbList) {
                                    if (!collectMap.containsKey(coll.getSensitivePersonId())) {
                                        collectMap.put(coll.getSensitivePersonId(), coll);
                                    }
                                }
                            }
                        }

                        for (SensitivePerson aps : compareList) {
                            collect = AnalysisSensitiveJob.this.SensitivePersonCollectFactory(aps, collectMap);
                            if (null != collect) {
                                collectList = StringUtils.pickList(collectList, collect);
                            }
                        }
                    }
                    AnalysisSensitiveJob.LOG.info("*****************************************");
                    if (null != collectList && !collectList.isEmpty()) {
                        for (SensitivePersonCollect cc : collectList) {
                            AnalysisSensitiveJob.LOG.info(cc.getDayBegin() + " - " + cc.getDayEnd() + ":" + cc.getAmount());
                        }
                    }
                    AnalysisSensitiveJob.LOG.info("*****************************************");
                    //向表中插入数据
                    AnalysisSensitiveJob.this.analysisTaskService.analysisSensitiveJob(compareList, finishList, collectList);

                } else {
                    AnalysisSensitiveJob.LOG.info("感知发现 - 未发现进行中感知发现的聚类任务");
                }

            } catch (Exception ex) {
                AnalysisSensitiveJob.LOG.error("感知发现聚类 分析异常", ex);
            }
        }

    }

    /**
     * 敏感通行汇总数据整理
     * @param sensitivePerson
     * @param collectMap
     * @return
     */
    private SensitivePersonCollect SensitivePersonCollectFactory(SensitivePerson sensitivePerson, Map<String, SensitivePersonCollect> collectMap) {
        List<SensitivePersonDetail> detairList = sensitivePerson.getDetail();
        if (!StringUtils.isEmpty(detairList)) {
            Collections.sort(detairList, new Comparator<SensitivePersonDetail>() {
                @Override
                public int compare(SensitivePersonDetail d1, SensitivePersonDetail d2) {
                    return d1.getCaptureTime().compareTo(d2.getCaptureTime());
                }
            });
        }

        Date bDate = (detairList.get(0)).getCaptureTime();
        Date eDate = (detairList.get(detairList.size() - 1)).getCaptureTime();

        SensitivePersonCollect collect = null;
        if (null != collectMap && collectMap.containsKey(sensitivePerson.getId())) {
            collect = collectMap.get(sensitivePerson.getId());
            collect.setDayEnd(DateUtil.formatDateDefault(eDate));
            collect.setAmount(detairList.size());
        } else {
            collect = new SensitivePersonCollect();
            collect.setSensitivePersonId(sensitivePerson.getId());
            collect.setCollectId(UUIDUtils.getUUID());
            collect.setDayBegin(DateUtil.formatDateDefault(bDate));
            collect.setDayEnd(DateUtil.formatDateDefault(eDate));
            collect.setAmount(detairList.size());
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
                        //i代表连续出现次数
                        int i = 0;
                        for (Date d : list) {
                            if (null != begin) {
                                if (begin.compareTo(d) != 0) {
                                    continue;
                                }
                                calendar.setTime(begin);
                                if (calendar.getTime().compareTo(d) == 0) {
                                    begin = d;
                                    i++;
                                }
                                continue;
                            }
                            begin = d;
                            i++;
                        }
                        if (i >= PropertiesUtil.getSensitiveTraffic()) {
                            isAdd = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("连续时间判断:", e);
            isAdd = false;
        }
        return isAdd;
    }

}
