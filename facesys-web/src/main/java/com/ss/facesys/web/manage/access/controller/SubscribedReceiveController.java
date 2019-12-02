package com.ss.facesys.web.manage.access.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.client.ISubscribedReceiveService;
import com.ss.facesys.data.access.common.dto.AlarmDTO;
import com.ss.facesys.data.access.common.web.*;
import com.ss.facesys.data.archives.client.IPersonService;
import com.ss.facesys.data.collect.client.*;
import com.ss.facesys.data.collect.common.dto.CaptureSumDTO;
import com.ss.facesys.data.collect.common.dto.RegisterStatisticsDTO;
import com.ss.facesys.data.collect.common.dto.VehicleStatisticsDTO;
import com.ss.facesys.data.collect.common.model.VehicleRecord;
import com.ss.facesys.data.collect.common.web.VehiclereReceiveVO;
import com.ss.facesys.data.push.client.IPushService;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.client.IVehicleTollgateService;
import com.ss.facesys.data.resource.client.IVillageService;
import com.ss.facesys.data.resource.client.IWifiService;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.model.Tollgate;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.facesys.util.thread.SysThreadPool;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 订阅接收
 *
 * @author FrancisYs
 * @date 2019/11/11
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/subscribed"})
public class SubscribedReceiveController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(SubscribedReceiveController.class);
    @Resource
    private ISubscribedReceiveService subscribedReceiveService;
    @Resource
    private IPushService pushService;
    @Resource
    private ICaptureService captureService;
    @Resource
    private IVillageService villageService;
    @Resource
    private IOrganizationRegionService oService;
    @Resource
    public JedisUtil jedisUtil;
    @Resource
    private IPersonService personService;
    @Resource
    private ICameraService cameraService;
    @Resource
    private IAccessService accessService;
    @Resource
    private IPeopleService peopleService;
    @Resource
    private IAnalysisTaskService analysisTaskService;
    @Resource
    private IFrequencyPersonService frequencyPersonService;
    @Resource
    private IAlarmService alarmService;
    @Resource
    private IAddPersonCollectService addPersonCollectService;
    @Resource
    private IWifiService wifiService;
    @Resource
    private IVehicleService vehicleService;
    @Resource
    private IVehicleTollgateService vehicleTollgateService;

    /**
     * 接收预警信息并推送前台
     * @param request
     * @param response
     * @param alarmReceive
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = {"/alarm"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> receiveAlarm(HttpServletRequest request, HttpServletResponse response, @RequestBody AlarmReceive alarmReceive, BindingResult bindingResult) {
        ResponseEntity<JSONObject> resp = createSuccResponse();
        try {
            log.info("布控告警订阅" + JsonUtils.getFastjsonFromObject(alarmReceive));
            Camera camera = this.cameraService.findDevice(alarmReceive.getDeviceId());
            log.info("布控告警相机信息" + JsonUtils.getFastjsonFromObject(camera));
            if (camera != null) {
                AlarmThread alarmThread = new AlarmThread(alarmReceive, camera);
                SysThreadPool.getThread().execute(alarmThread);
            }
            resp.setMessage("报警数据推送成功");
            return resp;
        } catch (Exception e) {
            resp = createFailResponse();
            log.error("报警推送错误", e);
            return resp;
        }
    }


    @RequestMapping(value = {"/capture"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> receiveCapture(HttpServletRequest request, HttpServletResponse response, @RequestBody CameraReceive cameraReceive, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONObject> resp = createSuccResponse();
        log.info("抓拍数据订阅成功" + JsonUtils.getFastjsonFromObject(cameraReceive));
        Camera camera = this.cameraService.findDevice(cameraReceive.getDeviceId());
        log.info("抓拍机信息" + JsonUtils.getFastjsonFromObject(camera));
        if (camera != null) {

            cameraReceive.setDataType(CommonConstant.SUBSCRIBED_DATATYPE_CAPTURE);
            CaptureThread captureThread = new CaptureThread(cameraReceive, camera);
            SysThreadPool.getThread().execute(captureThread);
        }
        resp.setMessage("抓拍数据推送成功");
        return resp;
    }


    @RequestMapping(value = {"/doorflow"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> doorflow(HttpServletRequest request, HttpServletResponse response, @RequestBody Object object, BindingResult bindingResult) throws Exception {
        log.info("**************************开门订阅********************************************");

        String para = JsonUtils.getFastjsonFromObject(object);
        log.info("开门数据订阅成功" + para);
        ResponseEntity<JSONObject> resp = createSuccResponse();
        TerminalFlow terminalFlow = (TerminalFlow) JsonUtils.getObjectTFromFastJson(para, TerminalFlow.class);
        terminalFlow.setDataType(CommonConstant.SUBSCRIBED_DATATYPE_BIG_EYES);

        String camreaId = terminalFlow.getDeviceId();
        Camera camera = this.cameraService.findDevice(camreaId);
        log.info("抓拍机信息" + JsonUtils.getFastjsonFromObject(camera));
        if (camera != null) {
            if (StringUtils.isNotBlank(terminalFlow.getCardId())) {
                DoorflowThread doorflowThread = new DoorflowThread(terminalFlow, camera);
                SysThreadPool.getThread().execute(doorflowThread);
            }

            CameraReceive cameraReceive = new CameraReceive();
            BeanUtils.copyProperties(terminalFlow, cameraReceive);
            cameraReceive.setDataType(CommonConstant.SUBSCRIBED_DATATYPE_CAPTURE);
            CaptureThread captureThread = new CaptureThread(cameraReceive, camera);
            SysThreadPool.getThread().execute(captureThread);
        }
        resp.setMessage("开门数据推送成功");
        return resp;
    }


    @RequestMapping(value = {"/wifiCollect"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> wifiCollect(HttpServletRequest request, HttpServletResponse response, @RequestBody WifiCollectReceiveVO wifiReceiveVO, BindingResult bindingResult) throws Exception {
        log.info("**************************wifi采集数据订阅****************************************");

        String para = JsonUtils.getFastjsonFromObject(wifiReceiveVO);
        log.info("wifi采集数据订阅成功" + para);
        ResponseEntity<JSONObject> resp = createSuccResponse();

        List<WifiCollectReceive> list = wifiReceiveVO.getWifiReceives();
        WifiCollectThread wifiCollectThread = new WifiCollectThread(list);
        SysThreadPool.getThread().execute(wifiCollectThread);

        resp.setMessage("WIFI数据推送成功");
        return resp;
    }


    @RequestMapping(value = {"/vehicleRecord"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> vehicleRecord(HttpServletRequest request, HttpServletResponse response, @RequestBody VehiclereReceiveVO vehicleRecord, BindingResult bindingResult) throws Exception {
        log.info("**************************过车数据********************************************");


        log.info("过车数据订阅成功");
        ResponseEntity<JSONObject> resp = createSuccResponse();
        List<VehicleRecord> list = vehicleRecord.getVehicleRecords();
        if (list != null && list.size() > 0) {
            VehicleRecordThread doorflowThread = new VehicleRecordThread(list);
            SysThreadPool.getThread().execute(doorflowThread);
        }
        resp.setMessage("过车数据推送成功");
        return resp;
    }


    class VehicleRecordThread
            implements Runnable {

        private List<VehicleRecord> list;


        public VehicleRecordThread(List<VehicleRecord> list) {
            this.list = list;
        }


        public void run() {
            if (this.list.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
                List<VehicleRecord> vehicleRecordList = new ArrayList<VehicleRecord>();

                for (VehicleRecord vehicleRecord : this.list) {
                    Tollgate tollgate = SubscribedReceiveController.this.vehicleTollgateService.getTollgateInfo(vehicleRecord.getTollgateID());
                    SubscribedReceiveController.this.logger.info("车辆卡口信息" + JsonUtils.getFastjsonFromObject(tollgate));
                    if (tollgate != null) {
                        vehicleRecord.setVillageCode(tollgate.getVillageCode());
                        vehicleRecord.setLon(tollgate.getLon());
                        vehicleRecord.setLat(tollgate.getLat());
                        if (vehicleRecord.getInOutTime() == null) {
                            vehicleRecord.setInOutTime(new Date());
                        }

                        if (StringUtils.isNotBlank(vehicleRecord.getPlateNoPicImg())) {
                            vehicleRecord = SubscribedReceiveController.this.vehicleService.saveVehicleRecordImage(vehicleRecord);
                        }
                        vehicleRecord.setPlatePicImg(null);
                        vehicleRecord.setPlateNoPicImg(null);
                        vehicleRecordList.clear();
                        vehicleRecordList.add(vehicleRecord);
                        boolean enterAdd = false;
                        boolean outAdd = false;
                        if (vehicleRecord.getInOutType() != null) {
                            enterAdd = CommonConstant.COMMON_1.equals(vehicleRecord.getInOutType());
                            outAdd = CommonConstant.COMMON_2.equals(vehicleRecord.getInOutType());
                        }

                        VehicleStatisticsDTO vs = SubscribedReceiveController.this.vehicleService.findTodayRecordStatistics(vehicleRecord.getVillageCode(), enterAdd, outAdd);
                        map.put("code", "00000000");
                        map.put("message", "车辆通行记录");
                        map.put("data", vehicleRecordList);
                        map.put("type", "vehicle");
                        map.put("typeCode", NumberConstant.THREE);
                        map.put("enterVehicle", vs.getEnterNumber());
                        map.put("outVehicle", vs.getOutNumber());
                        map.put("villageCode", vehicleRecord.getVillageCode());
                        map.put("userIds", SubscribedReceiveController.this.oService.findPermissionByVillageCode(vehicleRecord.getVillageCode()));
                        String meString = JsonUtils.getFastjsonFromObject(map);
                        SubscribedReceiveController.this.pushService.pushHome(meString);
                        log.info("过车数据推送" + meString);
                    }
                }


                String message = SubscribedReceiveController.this.vehicleService.batchInsertRecord(this.list);
                log.info("车辆信息入库" + message);
            }
        }

    }

    /**
     * 预警线程
     */
    class AlarmThread implements Runnable {

        private AlarmReceive alarmReceive;
        private Camera camera;

        public AlarmThread(AlarmReceive alarmReceive, Camera camera) {
            this.alarmReceive = alarmReceive;
            this.camera = camera;
        }

        @Override
        public void run() {
            // 报警数据入库
            AlarmDTO alarmDTO = SubscribedReceiveController.this.subscribedReceiveService.alarmHandle(this.alarmReceive);
            SubscribedReceiveController.this.jedisUtil.del("REDIS_KEY_PROCESSTYPE_ALARM");
            int monitorType = Integer.parseInt(this.alarmReceive.getMonitorType());
            if (Enums.AlarmMonitorType.STRANGER.getCode() != monitorType) {
                String villageCode = this.camera.getVillageCode();
                List<AlarmDTO> list = new ArrayList<>();
                alarmDTO.setVillageCode(villageCode);
                alarmDTO.setDeviceName(this.camera.getCameraName());
                alarmDTO.setDeviceAddress(this.camera.getCameraName());
                list.add(alarmDTO);
                Map<String, Object> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
                map.put("code", "00000000");
                map.put("message", "告警数据推送成功");
                map.put("data", list);
                map.put("type", "alarm");
                map.put("typeCode", CommonConstant.SUBSCRIBED_DATATYPE_ALARM);
                map.put("villageCode", villageCode);
                map.put("userIds", SubscribedReceiveController.this.oService.findPermissionByVillageCode(villageCode));
                String meString = JsonUtils.getFastjsonFromObject(map);
                log.info("布控告警推送数据" + meString);
                // 报警数据推送前台
                SubscribedReceiveController.this.pushService.pushHome(meString);
                SubscribedReceiveController.this.jedisUtil.del("REDIS_KEY_PROCESSTYPE_ALARM");
            }
        }

    }


    class CaptureThread implements Runnable {

        private CameraReceive cameraReceive;

        private Camera camera;

        private boolean exit;

        public CaptureThread(CameraReceive cameraReceive, Camera camera) {
            this.exit = false;

            this.cameraReceive = cameraReceive;
            this.camera = camera;
        }


        public void run() {
            long beginTime = System.currentTimeMillis();
            log.info("抓拍线程类启动开始 :" + DateUtil.getCurrentDayTime());

            String villageCode = this.camera.getVillageCode();
            this.cameraReceive.setDeviceName(this.camera.getCameraName());
            if (StringUtils.isNotBlank(this.camera.getInstallAdd())) {
                this.cameraReceive.setDeviceAddress(this.camera.getInstallAdd());
            } else {

                this.cameraReceive.setDeviceAddress(this.camera.getCameraName());
            }
            this.cameraReceive.setLat(this.camera.getLat().toString());
            this.cameraReceive.setLng(this.camera.getLon().toString());
            this.cameraReceive.setVillageCode(villageCode);
            List<CameraReceive> list = new ArrayList<CameraReceive>();
            list.add(this.cameraReceive);


            int thisVillageCaptureCount = 0;


            List<CaptureSumDTO> captureSumDTOs = SubscribedReceiveController.this.captureService.findVillageCaptureTotal();
            boolean ishaveThisVillage = true;
            for (CaptureSumDTO captureSumDTO : captureSumDTOs) {
                if (captureSumDTO.getVillageCode().equals(villageCode)) {
                    thisVillageCaptureCount = captureSumDTO.getNum().intValue() + 1;
                    captureSumDTO.setNum(Integer.valueOf(thisVillageCaptureCount));
                    ishaveThisVillage = false;
                    break;
                }
            }
            if (ishaveThisVillage) {
                captureSumDTOs.add(new CaptureSumDTO(NumberConstant.ONE, villageCode, null));
            }
            SubscribedReceiveController.this.jedisUtil.set("VILLAGES_CAPTURETOTAL", captureSumDTOs);


            int captureTodayRegister = 0;
            int captureTodayUnregister = 0;


            List<RegisterStatisticsDTO> rs = (SubscribedReceiveController.this.jedisUtil.get("CAPTURE_REGISTER_STATISTICS") == null) ? SubscribedReceiveController.this.captureService.registerStatistics() : (List) SubscribedReceiveController.this.jedisUtil.get("CAPTURE_REGISTER_STATISTICS");

            boolean isAdd = false;
            boolean isHave = true;

            if (StringUtils.isBlank(this.cameraReceive.getCardId())) {
                String facedbId = SubscribedReceiveController.this.villageService.getVillageFacedbId(villageCode);
                if (StringUtils.isNotBlank(facedbId)) {
                    log.info("1：N 注册库检索开始");
                    Map<String, Object> paramap = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

                    String captureUrlFull = this.cameraReceive.getCaptureUrlFull().replace("http://", "");
                    paramap.put("img", captureUrlFull
                            .substring(captureUrlFull.indexOf("/", 1), captureUrlFull.length()));
                    paramap.put("imgType", CommonConstant.IMGTYPE_URL);
                    paramap.put("topN", NumberConstant.ONE);
                    paramap.put("thresholdMin", Float.valueOf(PropertiesUtil.getThreShold()));
                    paramap.put("facedbIds", new String[]{facedbId});
                    String parmJson = JsonUtils.getFastjsonFromObject(paramap);
                    log.info("注册库检索参数" + parmJson);
                    JSONObject jsonObject = SubscribedReceiveController.this.accessService.getRecogRegisterDb(parmJson);
                    log.info("注册库检索返回结果" + jsonObject.toString());
                    if (StringUtils.checkSuccess(jsonObject)) {
                        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                        isAdd = (jsonArray.size() > 0);
                    }
                }
            } else {

                isAdd = true;
            }
            for (RegisterStatisticsDTO re : rs) {
                if (villageCode.equals(re.getVillageCode())) {
                    if (isAdd) {
                        re.setRegisterNumber(Integer.valueOf(re.getRegisterNumber().intValue() + 1));
                    } else {

                        re.setUnRegisterNumbe(Integer.valueOf(re.getUnRegisterNumbe() + 1));
                    }
                    captureTodayRegister = re.getRegisterNumber().intValue();
                    captureTodayUnregister = re.getUnRegisterNumbe();
                    isHave = false;
                    break;
                }
            }
            if (isHave) {
                captureTodayRegister = isAdd ? 1 : 0;
                captureTodayUnregister = isAdd ? 0 : 1;
                rs.add(new RegisterStatisticsDTO(villageCode, Integer.valueOf(captureTodayUnregister), Integer.valueOf(captureTodayRegister)));
            }
            SubscribedReceiveController.this.jedisUtil.set("CAPTURE_REGISTER_STATISTICS", rs);

            Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            map.put("code", "00000000");
            map.put("message", "抓拍数据");
            map.put("data", list);
            map.put("type", "capture");
            map.put("typeCode", this.cameraReceive.getDataType());
            map.put("villageCode", villageCode);
            map.put("captureTotal", Integer.valueOf(thisVillageCaptureCount));
            map.put("captureTodayRegister", Integer.valueOf(captureTodayRegister));
            map.put("captureTodayUnregister", Integer.valueOf(captureTodayUnregister));
            map.put("userIds", SubscribedReceiveController.this.oService.findPermissionByVillageCode(villageCode));

            String webScoketSendMessage = JsonUtils.getFastjsonFromObject(map);
            log.info("抓拍推送数据" + webScoketSendMessage);
            SubscribedReceiveController.this.pushService.pushHome(webScoketSendMessage);


            Map<String, Integer> map8 = (SubscribedReceiveController.this.jedisUtil.get("KEY_CAPTURE_REALTIME") == null) ? new HashMap() : (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_CAPTURE_REALTIME");


            if (StringUtils.isNotBlank(villageCode) && map8.containsKey(villageCode)) {
                map8.put(villageCode, Integer.valueOf(((Integer) map8.get(villageCode)).intValue() + 1));
            } else if (StringUtils.isNotBlank(villageCode)) {
                map8.put(villageCode, Integer.valueOf(1));
            }
            SubscribedReceiveController.this.jedisUtil.set("KEY_CAPTURE_REALTIME", map8);


            Map<String, Integer> map9 = (SubscribedReceiveController.this.jedisUtil.get("KEY_CAPTURE_TODAY") == null) ? new HashMap() : (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_CAPTURE_TODAY");

            if (StringUtils.isNotBlank(villageCode) && map9.containsKey(villageCode)) {
                map9.put(villageCode, Integer.valueOf(((Integer) map9.get(villageCode)).intValue() + 1));
            } else if (StringUtils.isNotBlank(villageCode)) {
                map9.put(villageCode, Integer.valueOf(1));
            }
            SubscribedReceiveController.this.jedisUtil.set("KEY_CAPTURE_TODAY", map9);


            Map<String, Integer> map11 = null;
            Object object1 = SubscribedReceiveController.this.jedisUtil.get("KEY_CAPTURE_SEVEN");
            if (null == object1) {
                map11 = new HashMap<String, Integer>();

                List<CaptureSumDTO> list2 = SubscribedReceiveController.this.captureService.getCaptureSum();
                for (CaptureSumDTO captureSumDTO : list2) {
                    map11.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
                }
                if (StringUtils.isNotBlank(villageCode) && map11.containsKey(villageCode)) {
                    map11.put(villageCode, Integer.valueOf(((Integer) map11.get(villageCode)).intValue() + 1));
                } else if (StringUtils.isNotBlank(villageCode)) {
                    map11.put(villageCode, Integer.valueOf(1));
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_CAPTURE_SEVEN", map11);
            } else {

                map11 = (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_CAPTURE_SEVEN");
                if (StringUtils.isNotBlank(villageCode) && map11.containsKey(villageCode)) {
                    map11.put(villageCode, Integer.valueOf(((Integer) map11.get(villageCode)).intValue() + 1));
                } else if (StringUtils.isNotBlank(villageCode)) {
                    map11.put(villageCode, Integer.valueOf(1));
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_CAPTURE_SEVEN", map11);
            }


            Map<String, Integer> map12 = null;
            Object object2 = SubscribedReceiveController.this.jedisUtil.get("KEY_CAPTURE_ALLDAY");
            if (null == object2) {
                map12 = new HashMap<String, Integer>();

                List<CaptureSumDTO> list2 = SubscribedReceiveController.this.captureService.getCaptureAllSum();
                for (CaptureSumDTO captureSumDTO : list2) {
                    map12.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
                }
                if (StringUtils.isNotBlank(villageCode) && map12.containsKey(villageCode)) {
                    map12.put(villageCode, Integer.valueOf(((Integer) map12.get(villageCode)).intValue() + 1));
                } else if (StringUtils.isNotBlank(villageCode)) {
                    map12.put(villageCode, Integer.valueOf(1));
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_CAPTURE_ALLDAY", map12);
            } else {

                map12 = (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_CAPTURE_ALLDAY");
                if (StringUtils.isNotBlank(villageCode) && map12.containsKey(villageCode)) {
                    map12.put(villageCode, Integer.valueOf(((Integer) map12.get(villageCode)).intValue() + 1));
                } else if (StringUtils.isNotBlank(villageCode)) {
                    map12.put(villageCode, Integer.valueOf(1));
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_CAPTURE_ALLDAY", map12);
            }
            log.info("抓拍线程类结束，耗时" + (System.currentTimeMillis() - beginTime) + "毫秒");
        }

    }


    class DoorflowThread
            implements Runnable {

        private TerminalFlow terminalFlow;


        private Camera camera;


        public DoorflowThread(TerminalFlow terminalFlow, Camera camera) {
            this.terminalFlow = terminalFlow;
            this.camera = camera;
        }


        public void run() {
            long beginTime = System.currentTimeMillis();
            log.info("开门线程类启动开始" + DateUtil.getCurrentDayTime());

            String villageCode = this.camera.getVillageCode();
            this.terminalFlow.setDeviceName(this.camera.getCameraName());
            if (StringUtils.isBlank(this.camera.getInstallAdd())) {
                this.camera.setInstallAdd(this.camera.getCameraName());
            }
            this.terminalFlow.setDeviceAddress(this.camera.getInstallAdd());
            this.terminalFlow.setLat(this.camera.getLat().toString());
            this.terminalFlow.setLng(this.camera.getLon().toString());

            List<TerminalFlow> list = new ArrayList<TerminalFlow>();
            list.add(this.terminalFlow);
            Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            map.put("code", "00000000");
            map.put("message", "开门数据");
            map.put("data", list);
            map.put("type", "doorflow");
            map.put("typeCode", this.terminalFlow.getDataType());
            map.put("villageCode", villageCode);
            map.put("userIds", SubscribedReceiveController.this.oService.findPermissionByVillageCode(villageCode));

            String webScoketMessage = JsonUtils.getFastjsonFromObject(map);
            SubscribedReceiveController.this.pushService.pushHome(webScoketMessage);
            log.info("开门推送数据" + webScoketMessage);


            Map<String, Integer> map8 = (SubscribedReceiveController.this.jedisUtil.get("KEY_DOORFLOW_REALTIME") == null) ? new HashMap() : (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_DOORFLOW_REALTIME");


            if (StringUtils.isNotBlank(villageCode) && map8.containsKey(villageCode)) {
                map8.put(villageCode, Integer.valueOf(((Integer) map8.get(villageCode)).intValue() + 1));
            } else if (StringUtils.isNotBlank(villageCode)) {
                map8.put(villageCode, Integer.valueOf(1));
            }
            SubscribedReceiveController.this.jedisUtil.set("KEY_DOORFLOW_REALTIME", map8);


            Map<String, Integer> map9 = (SubscribedReceiveController.this.jedisUtil.get("KEY_DOORFLOW_TODAY") == null) ? new HashMap() : (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_DOORFLOW_TODAY");

            if (StringUtils.isNotBlank(villageCode) && map9.containsKey(villageCode)) {
                map9.put(villageCode, Integer.valueOf(((Integer) map9.get(villageCode)).intValue() + 1));
            } else if (StringUtils.isNotBlank(villageCode)) {
                map9.put(villageCode, Integer.valueOf(1));
            }
            SubscribedReceiveController.this.jedisUtil.set("KEY_DOORFLOW_TODAY", map9);

            log.info("开门线程类结束，耗时" + (System.currentTimeMillis() - beginTime) + "毫秒");
        }

    }


    class WifiCollectThread
            implements Runnable {

        private List<WifiCollectReceive> wifiReceives;


        public WifiCollectThread(List<WifiCollectReceive> wifiReceives) {
            this.wifiReceives = wifiReceives;
        }


        public void run() {
            Map<String, Integer> map8 = (SubscribedReceiveController.this.jedisUtil.get("KEY_WIFI_REALTIME") == null) ? new HashMap() : (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_WIFI_REALTIME");

            Map<String, Integer> map9 = (SubscribedReceiveController.this.jedisUtil.get("KEY_WIFI_TODAY") == null) ? new HashMap() : (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_WIFI_TODAY");


            Map<String, Integer> map11 = null;
            Object object1 = SubscribedReceiveController.this.jedisUtil.get("KEY_WIFI_SEVEN");
            if (null == object1) {
                map11 = new HashMap<String, Integer>();

                List<CaptureSumDTO> list2 = SubscribedReceiveController.this.wifiService.getWifiSum();
                for (CaptureSumDTO captureSumDTO : list2) {
                    map11.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_WIFI_SEVEN", map11);
            } else {

                map11 = (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_WIFI_SEVEN");
            }


            Map<String, Integer> map12 = null;
            Object object2 = SubscribedReceiveController.this.jedisUtil.get("KEY_WIFI_ALLDAY");
            if (null == object2) {
                map12 = new HashMap<String, Integer>();

                List<CaptureSumDTO> list2 = SubscribedReceiveController.this.wifiService.getWifiAllSum();
                for (CaptureSumDTO captureSumDTO : list2) {
                    map12.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_WIFI_ALLDAY", map12);
            } else {

                map12 = (Map) SubscribedReceiveController.this.jedisUtil.get("KEY_WIFI_ALLDAY");
            }


            int index = 0;
            for (WifiCollectReceive wifiReceive : this.wifiReceives) {
                if (wifiReceive.getCollectTime() == null) {
                    wifiReceive.setCollectTime(new Date());
                }
                String villageCode = wifiReceive.getVillageCode();
                Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
                map.put("code", "00000000");
                map.put("message", "MAC数据");
                map.put("data", this.wifiReceives.subList(index, index + 1));
                map.put("type", "wifiCollect");
                map.put("typeCode", CommonConstant.SUBSCRIBED_DATATYPE_WIFI);
                map.put("villageCode", wifiReceive.getVillageCode());
                map.put("userIds", SubscribedReceiveController.this.oService.findPermissionByVillageCode(villageCode));

                String webScoketMessage = JsonUtils.getFastjsonFromObject(map);
                SubscribedReceiveController.this.pushService.pushHome(webScoketMessage);
                index++;
                log.info("webScoket推送数据" + webScoketMessage);


                if (StringUtils.isNotBlank(villageCode) && map8.containsKey(villageCode)) {
                    map8.put(villageCode, Integer.valueOf(((Integer) map8.get(villageCode)).intValue() + 1));
                } else if (StringUtils.isNotBlank(villageCode)) {
                    map8.put(villageCode, Integer.valueOf(1));
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_WIFI_REALTIME", map8);


                if (StringUtils.isNotBlank(villageCode) && map9.containsKey(villageCode)) {
                    map9.put(villageCode, Integer.valueOf(((Integer) map9.get(villageCode)).intValue() + 1));
                } else if (StringUtils.isNotBlank(villageCode)) {
                    map9.put(villageCode, Integer.valueOf(1));
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_WIFI_TODAY", map9);


                if (StringUtils.isNotBlank(villageCode) && map11.containsKey(villageCode)) {
                    map11.put(villageCode, Integer.valueOf(((Integer) map11.get(villageCode)).intValue() + 1));
                } else if (StringUtils.isNotBlank(villageCode)) {
                    map11.put(villageCode, Integer.valueOf(1));
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_WIFI_SEVEN", map11);


                if (StringUtils.isNotBlank(villageCode) && map12.containsKey(villageCode)) {
                    map12.put(villageCode, Integer.valueOf(((Integer) map12.get(villageCode)).intValue() + 1));
                } else if (StringUtils.isNotBlank(villageCode)) {
                    map12.put(villageCode, Integer.valueOf(1));
                }
                SubscribedReceiveController.this.jedisUtil.set("KEY_WIFI_ALLDAY", map12);
            }

            boolean insertFlag = SubscribedReceiveController.this.subscribedReceiveService.batchInsertWifiCollect(this.wifiReceives);
            log.info("WIFI数据入库" + insertFlag);
        }

    }

}
