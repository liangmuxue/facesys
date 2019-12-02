package com.ss.facesys.schedule.collect.job;

import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.archives.client.IPersonService;
import com.ss.facesys.data.archives.common.model.People;
import com.ss.facesys.data.collect.client.IPeopleService;
import com.ss.facesys.data.collect.common.model.CaptureInfo;
import com.ss.facesys.data.collect.common.model.LeavePerson;
import com.ss.facesys.data.collect.common.model.LeavePersonDetail;
import com.ss.facesys.data.collect.common.model.SpecialPerson;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.client.IVillageService;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.util.AgeUtil;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.SpringUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.http.BaseFormatJsonUtil;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.facesys.util.thread.SysThreadPool;
import com.ss.tools.UUIDUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.j7cai.common.util.UUIDUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* 疑似离开定时任务
* @author chao
* @create 2019/10/22
* @email lishuangchao@ss-cas.com
**/
public class PeopleDiscoveryLeave implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(PeopleDiscoveryLeave.class);
    public JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private ICameraService cameraService = SpringUtil.getBean(ICameraService.class);
    private IAccessService accessService = SpringUtil.getBean(IAccessService.class);
    private IVillageService villageService = SpringUtil.getBean(IVillageService.class);
    private IPeopleService peopleService = SpringUtil.getBean(IPeopleService.class);
    private IPersonService personService = SpringUtil.getBean(IPersonService.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        LOG.info("定时任务PeopleDiscoveryLeave已经启动" + DateUtil.getCurrentSqlTimestampString());
        String key = "CAPTURE_DEVICEIDS_LEAVE";
        boolean isRun = true;
        while (isRun) {
            if (!this.jedisUtil.hasKey(key)) {
                isRun = false;
                LeaveJobThread job = new LeaveJobThread();
                SysThreadPool.getThread().execute(job);
                continue;
            }
            try {
                Thread.sleep(PropertiesUtil.getJobSleepTime());
            } catch (InterruptedException e) {
                LOG.error("感知离开 分析异常", e);
            }
        }
    }

    private LeavePerson leavePersonFactory(People people, List<Map<String, Object>> deviceIds, Village village) {
        LeavePerson levae = null;
        levae = new LeavePerson();
        levae.setId(UUIDUtils.getUUID());
        levae.setState(Enums.LeaveState.STATE_1.getCode());
        levae.setLeaveDays(PropertiesUtil.getLeaveDays());
        levae.setPeopleId(people.getPeopleId());
        levae.setVillageId(String.valueOf(village.getVillageCode()));
        levae.setAddress(village.getAddress());
        long[] setime = DateUtil.getStartAndEnd((PropertiesUtil.getLeaveDays() + 1) * -1);
        setime[1] = setime[1] - (PropertiesUtil.getLeaveDays() * 86400000);
        JSONObject captureJson = getRecogCameraDb(setime, deviceIds, people.getIdCardPic());
        CaptureInfo info = getLastCapture(captureJson);
        if (null != info) {
            levae = fillCaptureInfo(levae, info);
            List<LeavePersonDetail> detail = leavePersonDetailFactory(info, levae.getId(), people.getVillageCode());
            levae.setDetail(detail);
        } else {
            LOG.error("感知离开 - 未找到住户" + people.getPeopleName() + "抓拍记录");
        }
        return levae;
    }

    private List<LeavePersonDetail> leavePersonDetailFactory(CaptureInfo info, String leaveId, String villageId) {
        List<LeavePersonDetail> list = null;
        if (null != info) {
            LeavePersonDetail detail = null;
            list = new ArrayList<LeavePersonDetail>();
            detail = new LeavePersonDetail();
            detail.setCaptureId(info.getCaptureId());
            detail.setCapturePath(info.getCaptureUrlFull());
            detail.setPanoramaPath(info.getPanoramaUrlFull());
            detail.setPanoramaId(info.getPanoramaId());
            detail.setDeviceId(info.getDeviceId());
            detail.setDeviceName(info.getDeviceName());
            detail.setDeviceAddress(info.getDeviceAddress());
            detail.setLat(info.getLat());
            detail.setLng(info.getLng());
            detail.setRecogScore(info.getRecogScore());
            detail.setCaptureTime(info.getCaptureTime());
            detail.setVillageId(villageId);
            detail.setLeaveId(leaveId);
            detail.setFacePitch(String.valueOf(info.getPitch()));
            detail.setFaceYaw(String.valueOf(info.getYaw()));
            detail.setFaceRoll(String.valueOf(info.getRoll()));
            detail.setFacex(String.valueOf(info.getX()));
            detail.setFacey(String.valueOf(info.getY()));
            detail.setFaceWidth(String.valueOf(info.getWidth()));
            detail.setFaceHeight(String.valueOf(info.getHeight()));
            list.add(detail);
        }
        return list;
    }

    private JSONObject getRecogCameraDb(long[] setime, List<Map<String, Object>> deviceIds, String facePic) {
        String ts = "{\"code\":\"00000000\",\"data\":[],\"message\":\"请求处理成功\",\"error\":[]}";
        if (!StringUtils.isEmpty(deviceIds)) {
            if (StringUtils.isNotBlank(facePic) && !facePic.contains("http")) {
                //取得单位图片绝对路径
                facePic = FilePropertiesUtil.getHttpUrl() + facePic;
            }
            Map<String, Object> map = new HashMap<String, Object>(6);
            map.put("devices", deviceIds);
            map.put("captureTimeB", setime[0]);
            map.put("captureTimeE", setime[1]);
            map.put("topN", 1);
            map.put("threshold", PropertiesUtil.getThreShold());
            map.put("imgType", CommonConstant.IMGTYPE_FULLURL);
            map.put("img", facePic);
            LOG.info("感知离开 - 查询参数： " + JSONObject.toJSONString(map));
            JSONObject resultStr = this.accessService.getRecogDeviceDb(JSONObject.toJSONString(map));
            if ("20030000".equals(resultStr.getString("code"))) {
                LOG.error("感知离开 - 1:N 查询返回： " + resultStr);
                LOG.error("感知离开 - 1:N 查询参数： " + JSONObject.toJSONString(map));
                return JSONObject.parseObject(ts);
            }
            return resultStr;
        }

        return JSONObject.parseObject(ts);
    }


    private SpecialPerson specialPersonFactory(Map<String, SpecialPerson> spMap, People people, LeavePerson leave) {
        SpecialPerson lps = null;
        if (null != spMap && spMap.containsKey(people.getPeopleId())) {
            lps = (SpecialPerson) spMap.get(people.getPeopleId());

        } else if (!StringUtils.isEmpty(people.getBirthDate())) {
            if (AgeUtil.isOld(people.getBirthDate(), PropertiesUtil.getOldMan())) {
                LOG.info("高龄老人判断 - " + people.getPeopleName());
                lps = new SpecialPerson();
                lps.setId(UUIDUtil.uuid());
                lps.setPeopleId(people.getPeopleId());
                lps.setRecordId(leave.getId());
                lps.setSpecialType(Enums.SpecialType.OLD.getCode());
                if (null != leave.getDetail() && !leave.getDetail().isEmpty()) {
                    lps.setLeaveTime(((LeavePersonDetail) leave.getDetail().get(0)).getCaptureTime());
                }
                lps.setDays(PropertiesUtil.getLeaveDays());
                lps.setState(Enums.LeaveState.STATE_1.getCode());
            }
        } else {
            LOG.error("感知离开 - 高龄老人判断," + people.getPeopleName() + " 的生日字段为空，忽略住户");
        }

        return lps;
    }

    public class LeaveJobThread implements Runnable {

        @Override
        public void run() {
            PeopleDiscoveryLeave.LOG.info("感知离开分析开始 ： LeaveJobThread");
            long startTime = System.currentTimeMillis();
            String key = CacheConstant.REDIS_KEY_DEVICEIDS_LEAVE;
            PeopleDiscoveryLeave.this.jedisUtil.del(key);
            if (!PeopleDiscoveryLeave.this.jedisUtil.hasKey(key)) {
                PeopleDiscoveryLeave.LOG.info("感知离开分析开始");
                PeopleDiscoveryLeave.this.jedisUtil.set(key, DateUtil.getYesterdayCurrentDay(), 43200L);
                try {
                    Camera ct = new Camera();
                    //查询设备信息
                    List<Camera> cameraList = PeopleDiscoveryLeave.this.cameraService.findCameras(ct);
                    Map<String, String> cameraMap = new HashMap<String, String>(cameraList.size());
                    for (int i = 0; i < cameraList.size(); i++) {
                        cameraMap.put(((Camera) cameraList.get(i)).getCameraId(), ((Camera) cameraList.get(i)).getVillageCode() + "," + ((Camera) cameraList.get(i)).getVillageName());
                    }
                    Village village = new Village();
                    //查询小区信息
                    List<Village> villages = PeopleDiscoveryLeave.this.villageService.findList(village);
                    for (Village v : villages) {
                        List<Map<String, Object>> deviceIds = null;
                        Map<String, Object> mapDev = null;
                        for (int i = 0; i < cameraList.size(); i++) {
                            if (null != ((Camera) cameraList.get(i)).getVillageCode() && v.getVillageCode().equals(((Camera) cameraList.get(i)).getVillageCode())) {
                                if (null == deviceIds) {
                                    deviceIds = new ArrayList<Map<String, Object>>();
                                }
                                mapDev = new HashMap<String, Object>();
                                mapDev.put("deviceId", ((Camera) cameraList.get(i)).getCameraId());
                                if (((Camera) cameraList.get(i)).getCameraType() == Enums.cameraType.USUAL.getCode()) {
                                    mapDev.put("deviceType", 6);
                                } else {
                                    mapDev.put("deviceType", 4);
                                }
                                deviceIds.add(mapDev);
                            }
                        }

                        PeopleDiscoveryLeave.LOG.info("感知离开 - 小区：" + v.getVillageCode());
                        //查询对应小区所有未离开的人
                        List<People> peopleList = PeopleDiscoveryLeave.this.personService.getPeopleDiscoveryLeave(v.getVillageCode());

                        if (!StringUtils.isEmpty(peopleList)) {
                            PeopleDiscoveryLeave.LOG.info("感知离开 - 住户：" + peopleList.size());
                            //查询对应小区疑似离开且未处理的人
                            List<LeavePerson> lpList = PeopleDiscoveryLeave.this.peopleService.queryLeaveListByState(v.getVillageCode(), Enums.LeaveState.STATE_1.getCode());
                            Map<String, LeavePerson> leaveMap = null;
                            if (!lpList.isEmpty()) {
                                PeopleDiscoveryLeave.LOG.info("查询无效和未处理的感知离开数据量：" + lpList.size());
                                leaveMap = new HashMap<String, LeavePerson>();
                                for (LeavePerson sp : lpList) {
                                    if (!leaveMap.containsKey(sp.getPeopleId())) {
                                        leaveMap.put(sp.getPeopleId(), sp);
                                        continue;
                                    }
                                    PeopleDiscoveryLeave.LOG.error("感知离开 - 疑似离开数据异常：存在重复的未处置的感知离开数据,peopleId: " + sp.getPeopleId());
                                }
                            } else {
                                PeopleDiscoveryLeave.LOG.info("查询无效和未处理的感知离开数据量：0");
                            }
                            PeopleDiscoveryLeave.LOG.info("查询高龄数据");
                            //查询高龄老人
                            List<SpecialPerson> spList = PeopleDiscoveryLeave.this.peopleService.querySpecialListByState(null, Enums.SpecialType.OLD.getCode());
                            Map<String, SpecialPerson> spMap = null;
                            if (!spList.isEmpty()) {
                                PeopleDiscoveryLeave.LOG.info("高龄老人数量：" + spList.size());
                                spMap = new HashMap<String, SpecialPerson>();
                                for (SpecialPerson sp : spList) {
                                    if (!spMap.containsKey(sp.getPeopleId())) {
                                        spMap.put(sp.getPeopleId(), sp);
                                        continue;
                                    }
                                    PeopleDiscoveryLeave.LOG.error("感知离开 - 高龄老人数据异常：存在重复的未处置的高龄老人数据,peopleId: " + sp.getPeopleId());
                                }
                            } else {
                                PeopleDiscoveryLeave.LOG.info("高龄老人数据：0");
                            }

                            JSONObject resultStr = null;
                            JSONArray arr = null;
                            LeavePerson leave = null;
                            List<LeavePerson> compareList = new ArrayList<LeavePerson>();
                            List<SpecialPerson> compareSpecialList = new ArrayList<SpecialPerson>();
                            List<String> delLeaveIdList = new ArrayList<String>();
                            SpecialPerson special = null;

                            for (People p : peopleList) {
                                long[] setime = DateUtil.getStartAndEnd(-1);
                                //获取抓拍数据
                                resultStr = PeopleDiscoveryLeave.this.getRecogCameraDb(setime, deviceIds, p.getIdCardPic());
                                if (null != resultStr && StringUtils.checkSuccess(resultStr)) {
                                    arr = resultStr.getJSONArray("data");
                                    if (null == arr || arr.size() == 0) {
                                        setime = DateUtil.getStartAndEnd(PropertiesUtil.getLeaveDays() * -1);
                                        setime[1] = setime[1] - 86400000L;
                                        //获取抓拍数据
                                        resultStr = PeopleDiscoveryLeave.this.getRecogCameraDb(setime, deviceIds, p.getIdCardPic());
                                    }
                                }

                                if (null != resultStr && StringUtils.checkSuccess(resultStr)) {
                                    arr = resultStr.getJSONArray("data");
                                    if (null != arr && arr.size() == 0) {
                                        PeopleDiscoveryLeave.LOG.info("没有抓拍数据");
                                        if (null != leaveMap && leaveMap.containsKey(p.getPeopleId())) {
                                            LeavePerson lp = (LeavePerson) leaveMap.get(p.getPeopleId());
                                            int days = PeopleDiscoveryLeave.this.getDays(lp.getCreatedTime()) + PropertiesUtil.getLeaveDays();
                                            lp.setLeaveDays(days);
                                            compareList.add(lp);
                                            if (null != spMap && spMap.containsKey(lp.getPeopleId())) {
                                                SpecialPerson sPer = (SpecialPerson) spMap.get(lp.getPeopleId());
                                                if (sPer.getState() == Enums.LeaveState.STATE_2.getCode()) {
                                                    special = null;
                                                } else {
                                                    days = PeopleDiscoveryLeave.this.getDays(lp.getCreatedTime()) + PropertiesUtil.getLeaveDays();
                                                    special = (SpecialPerson) spMap.get(lp.getPeopleId());
                                                    special.setDays(days);
                                                    compareSpecialList.add(special);
                                                }
                                            } else {
                                                special = PeopleDiscoveryLeave.this.specialPersonFactory(spMap, p, lp);
                                                if (null != special) {
                                                    compareSpecialList.add(special);
                                                }
                                            }
                                            lpList.remove(lp);
                                            continue;
                                        }
                                        leave = PeopleDiscoveryLeave.this.leavePersonFactory(p, deviceIds, v);
                                        compareList.add(leave);
                                        special = PeopleDiscoveryLeave.this.specialPersonFactory(spMap, p, leave);
                                        if (null != special) {
                                            compareSpecialList.add(special);
                                        }
                                        continue;
                                    }
                                    PeopleDiscoveryLeave.LOG.info("已经抓拍数据");
                                    delLeaveIdList.add(p.getPeopleId());
                                }
                            }
                            PeopleDiscoveryLeave.LOG.info("批量新增or更新发现感知信息");
                            PeopleDiscoveryLeave.LOG.info("高龄老人判断 - " + compareSpecialList.size());

                            //向表中插入数据
                            PeopleDiscoveryLeave.this.peopleService.batchCompareLeavePerson(compareList, compareSpecialList, delLeaveIdList);
                            PeopleDiscoveryLeave.this.jedisUtil.del("REDIS_KEY_PROCESSTYPE_LEAVEPERSON");
                        }
                        PeopleDiscoveryLeave.LOG.info("感知离开分析结束");
                    }
                } catch (Exception e) {
                    PeopleDiscoveryLeave.LOG.error("感知离开 - 任务异常： " + e);
                } finally {
                    PeopleDiscoveryLeave.this.jedisUtil.del(key);
                }
            } else {

                PeopleDiscoveryLeave.LOG.debug("感知离开 - 任务未结束");
            }
            PeopleDiscoveryLeave.LOG.debug("程序运行时间： " + (System.currentTimeMillis() - startTime) + "ms");
        }

    }

    private int getDays(Date createDate) {
        Calendar c = Calendar.getInstance();
        c.add(5, -1);
        Date eday = c.getTime();
        c.setTime(createDate);
        Date bday = c.getTime();

        return (int) DateUtil.getDaySub(DateUtil.formatDateDefault(bday) + " 00:00:00", DateUtil.formatDateDefault(eday) + " 00:00:00");
    }

    private CaptureInfo getLastCapture(JSONObject captureJson) {
        CaptureInfo info = null;
        if (null != captureJson && StringUtils.checkSuccess(captureJson)) {
            JSONArray arr = captureJson.getJSONArray("data");
            Date maxDate = null;
            if (arr.size() > 0) {
                for (int i = 0; i < arr.size(); i++) {
                    if (null == maxDate) {
                        maxDate = arr.getJSONObject(i).getDate("captureTime");
                        info = (CaptureInfo) BaseFormatJsonUtil.formatEntity(arr.getJSONObject(i), CaptureInfo.class);
                    } else if (arr.getJSONObject(i).getDate("captureTime").compareTo(maxDate) > 0) {
                        maxDate = arr.getJSONObject(i).getDate("captureTime");
                        info = (CaptureInfo) BaseFormatJsonUtil.formatEntity(arr.getJSONObject(i), CaptureInfo.class);
                    }
                }
            }
        }
        return info;
    }

    private LeavePerson fillCaptureInfo(LeavePerson leave, CaptureInfo info) {
        if (null != info) {
            leave.setFacePitch(String.valueOf(info.getPitch()));
            leave.setFaceYaw(String.valueOf(info.getYaw()));
            leave.setFaceRoll(String.valueOf(info.getRoll()));
            leave.setFacex(String.valueOf(info.getX()));
            leave.setFacey(String.valueOf(info.getY()));
            leave.setFaceWidth(String.valueOf(info.getWidth()));
            leave.setFaceHeight(String.valueOf(info.getHeight()));
            leave.setCapturePath(info.getCaptureUrlFull());
            leave.setPanoramaPath(info.getPanoramaUrlFull());
            leave.setLastCaptureTime(info.getCaptureTime());
        }
        return leave;
    }

}
