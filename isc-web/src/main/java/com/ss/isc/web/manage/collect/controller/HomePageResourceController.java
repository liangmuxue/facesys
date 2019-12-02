package com.ss.isc.web.manage.collect.controller;

import com.ss.isc.data.archives.common.model.TimeCount;
import com.ss.isc.data.baseinfo.common.model.User;
import com.ss.isc.data.collect.client.IStatisticsService;
import com.ss.isc.data.collect.client.IVehicleService;
import com.ss.isc.data.collect.common.dto.CaptureSumDTO;
import com.ss.isc.data.resource.client.ICommunityResourceService;
import com.ss.isc.data.resource.client.IHomePageResourceService;
import com.ss.isc.data.resource.common.model.*;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.data.system.common.dto.UserPermission;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * HomePageResourceController 首页（数据看板）相关请求
 * @author FrancisYs
 * @date 2019/8/21
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/collect"})
public class HomePageResourceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(HomePageResourceController.class);

    @Autowired
    private IHomePageResourceService homePageResourceService;
    @Resource
    private IStatisticsService statisticsService;
    @Resource
    private ICommunityResourceService communityResourceService;
    @Resource
    private JedisUtil jedisUtil;
    @Resource
    private IOrganizationRegionService organizationRegionService;
    @Resource
    private IVehicleService vehicleService;

    /**
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/basicResourceCount"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> resourceCount(@RequestBody StParam stParam, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 参数校验
//            if (StringUtils.isBlank(stParam.getUserIds())) {
//                resp = createFailResponse();
//                resp.setCode(ResultCode.INVALID_PARAMETER);
//                resp.setMessage(ResultCode.INVALID_PARAMETER_MESSAGE);
//                return resp;
//            }
            resp.setData(this.homePageResourceService.selAllOneBidThirty(stParam.getVillageCodes(),stParam.getPeriodType()));
        } catch (Exception e) {
            this.logger.error("查找首页一标三实失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.BASIC_RESOURCE_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/perceptionCount"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> perceptionCount(@RequestBody StParam stParam, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 参数校验
//            if (StringUtils.isBlank(stParam.getUserIds())) {
//                resp = createFailResponse();
//                resp.setCode(ResultCode.INVALID_PARAMETER);
//                resp.setMessage(ResultCode.INVALID_PARAMETER_MESSAGE);
//                return resp;
//            }
            resp.setData(this.homePageResourceService.selPercept(stParam.getVillageCodes(),stParam.getPeriodType()));
        } catch (Exception e) {
            this.logger.error("查找首页一标三实失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.BASIC_RESOURCE_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * TODO 未实现首页---感知增量占比数据接口
     * @param map
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/perceptionRatio"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> perceptionRatio(@RequestBody Map<Object, Object> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        int initCapacity = CommonConstant.HASHMAP_INITIALCAPACITY;
        try {
            Map<String, Object> resutltMap = new HashMap<>(initCapacity);
            resp.setData(resutltMap);
        } catch (Exception e) {
            this.logger.error("首页感知增量统计初始化数据失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("");
            // ResultCode.ANALYSIS_FAILED_CODE
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * TODO 未实现首页---实时预警数据接口
     * @param map
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/currentWarning"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> currentWarning(@RequestBody Map<Object, Object> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        int initCapacity = CommonConstant.HASHMAP_INITIALCAPACITY;
        try {
            Map<String, Object> resutltMap = new HashMap<>(initCapacity);
            resp.setData(resutltMap);
        } catch (Exception e) {
            this.logger.error("首页感知增量统计初始化数据失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("");
            // ResultCode.ANALYSIS_FAILED_CODE
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * TODO 未实现首页---感知统计排行数据接口
     * @param map
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/perceptionRanking"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> perceptionRanking(@RequestBody Map<Object, Object> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        int initCapacity = CommonConstant.HASHMAP_INITIALCAPACITY;
        try {
            Map<String, Object> resutltMap = new HashMap<>(initCapacity);
            resp.setData(resutltMap);
        } catch (Exception e) {
            this.logger.error("首页感知增量统计初始化数据失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("");
            // ResultCode.ANALYSIS_FAILED_CODE
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * TODO 未实现首页---智能分析统计接口
     * @param map
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/analysis"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> analysis(@RequestBody Map<Object, Object> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        int initCapacity = CommonConstant.HASHMAP_INITIALCAPACITY;
        try {
            Map<String, Object> resutltMap = new HashMap<>(initCapacity);
            Map<String, Map<String, Integer>> map10 = (this.jedisUtil.get("REDIS_KEY_ADDPERSON_COUNT") == null) ? new HashMap(initCapacity) : (Map) this.jedisUtil.get("REDIS_KEY_ADDPERSON_COUNT");
            Map<String, Map<String, Integer>> map11 = (this.jedisUtil.get("REDIS_KEY_LEAVEPERSON_COUNT") == null) ? new HashMap(initCapacity) : (Map) this.jedisUtil.get("REDIS_KEY_LEAVEPERSON_COUNT");
            Map<String, Map<String, Integer>> map12 = (this.jedisUtil.get("REDIS_KEY_FREQUENCY_COUNT") == null) ? new HashMap(initCapacity) : (Map) this.jedisUtil.get("REDIS_KEY_FREQUENCY_COUNT");
            Map<String, Map<String, Integer>> map13 = (this.jedisUtil.get("REDIS_KEY_ALARM") == null) ? new HashMap(initCapacity) : (Map) this.jedisUtil.get("REDIS_KEY_ALARM");
            Map<String, Map<String, Integer>> map18 = (this.jedisUtil.get("REDIS_KEY_OLDMAN_COUNT") == null) ? new HashMap(initCapacity) : (Map) this.jedisUtil.get("REDIS_KEY_OLDMAN_COUNT");
            Map<String, Map<String, Integer>> map25 = (this.jedisUtil.get("REDIS_KEY_VEHICLE_USERLEAVE") == null) ? new HashMap(initCapacity) : (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERLEAVE");
            Map<String, Map<String, Integer>> map23 = (this.jedisUtil.get("REDIS_KEY_VEHICLE_USERDISCOVERY") == null) ? new HashMap(initCapacity) : (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERDISCOVERY");
            Map<String, Map<String, Integer>> map21 = (this.jedisUtil.get("REDIS_KEY_VEHICLE_USERRETENTION") == null) ? new HashMap(initCapacity) : (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERRETENTION");

            resutltMap.put("addPersonCount", map10);
            resutltMap.put("leavePersonCount", map11);
            resutltMap.put("frequencyCount", map12);
            resutltMap.put("alarmCount", map13);
            resutltMap.put("oldManCount", map18);
            resutltMap.put("vehicleLeaveCount", map25);
            resutltMap.put("vehicleDsicoveryCount", map23);
            resutltMap.put("vehicleRetentionCount", map21);
            resp.setData(resutltMap);
        } catch (Exception e) {
            this.logger.error("首页智能分析初始化数据失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.ANALYSIS_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    @RequestMapping(value = {"/allresource"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> mapresource(HttpServletRequest request, HttpServletResponse response, @RequestBody Village village, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);


        try {
            String villageCode = village.getVillageCode();

            List<Village> villages = this.communityResourceService.findVillages(village);

            List<VillageEntrance> villageEntrances = this.communityResourceService.findVillageEntrance(village);

            List<Building> buildings = this.communityResourceService.findBuildings(village);

            Camera camera = new Camera();
            if (null != villageCode) {
                camera.setVillageCode(villageCode);
            }
            camera.setUserIds(village.getUserIds());
            List<Camera> cameras = this.communityResourceService.findCameras(camera);

            List<Company> companys = this.homePageResourceService.findCompany(village);

            List<Wifi> wifis = this.homePageResourceService.findWifis(village);

            List<Tollgate> tollgates = this.homePageResourceService.findTollgates(village);

            List<AccessDevice> accessDevices = this.homePageResourceService.findAccessDevice(village);

            Map<String, Object> resutltMap = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

            resutltMap.put("villages", villages);
            resutltMap.put("villageEntrances", villageEntrances);
            resutltMap.put("buildings", buildings);
            resutltMap.put("cameras", cameras);
            resutltMap.put("companys", companys);
            resutltMap.put("wifis", wifis);
            resutltMap.put("tollgates", tollgates);
            resutltMap.put("accessDevices", accessDevices);

            resp.setData(resutltMap);
        } catch (Exception e) {
            this.logger.error("首页地图资源撒点失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70801008");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/searchRegion"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Region>> searchRegion(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Region>> resp = validite(bindingResult);


        try {
            String regionName = (String) map.get("regionName");


            List<Region> list = this.homePageResourceService.findRegion(regionName);

            resp.setData(list);
        } catch (Exception e) {
            this.logger.error("查找行政区划失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/searchNextRegion"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Region>> searchNextRegion(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Region>> resp = validite(bindingResult);


        try {
            String regionCode = (String) map.get("regionCode");


            List<Region> list = this.homePageResourceService.searchNextRegion(regionCode);

            resp.setData(list);
        } catch (Exception e) {
            this.logger.error("查找行政区划失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/getVillageInformation"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Village>> getVillageInformation(HttpServletRequest request, HttpServletResponse response, @RequestBody Village village, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Village>> resp = validite(bindingResult);


        try {
            List<Village> list = this.homePageResourceService.getVillageInformation(village);

            resp.setData(list);
        } catch (Exception e) {
            this.logger.error("查找小区信息失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/getAllDayResource"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> getSuperHomePage(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<Object, Object> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);


        try {
            Map<String, Integer> map15 = ((Map) this.jedisUtil.get("REDIS_KEY_SUPERHOME") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_SUPERHOME");


            Map<String, CaptureSumDTO> map16 = ((Map) this.jedisUtil.get("KEY_VEHICLE_SEVENRECORD") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_VEHICLE_SEVENRECORD");


            Map<String, CaptureSumDTO> map17 = ((Map) this.jedisUtil.get("KEY_CAPTURE_SEVEN_SEVENRECORD") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_CAPTURE_SEVEN_SEVENRECORD");


            Map<String, CaptureSumDTO> map20 = ((Map) this.jedisUtil.get("KEY_CAPTURE_WIFI_SEVENRECORD") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_CAPTURE_WIFI_SEVENRECORD");


            Map<String, CaptureSumDTO> map88 = ((Map) this.jedisUtil.get("REDIS_KEY_SUPERHOME_PARTSEVEN") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_SUPERHOME_PARTSEVEN");

            Map<String, Object> resutltMap = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            resutltMap.put("code", "00000000");
            resutltMap.put("allCount", map15);
            resutltMap.put("CarCategory", map16);
            resutltMap.put("PeopleCategory", map17);
            resutltMap.put("WifiCategory", map20);
            resutltMap.put("SevenDay", map88);
            resp.setData(resutltMap);
        } catch (Exception e) {
            this.logger.error("初始化人车开门7天以及所有天数记录缓存失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70801010");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/getDayResource"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> getDayResource(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<Object, Object> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);

        try {
            Map<String, Object> resutltMap = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());


            Map<String, List<TimeCount>> map13 = ((Map) this.jedisUtil.get("KEY_VEHICLE_REALTIME_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_VEHICLE_REALTIME_COUNT");


            Map<String, Integer> map14 = ((Map) this.jedisUtil.get("KEY_VEHICLE_TODAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_VEHICLE_TODAY");


            Map<String, List<TimeCount>> map11 = ((Map) this.jedisUtil.get("KEY_CAPTURE_REALTIME_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_CAPTURE_REALTIME_COUNT");


            Map<String, Integer> map22 = ((Map) this.jedisUtil.get("REDIS_KEY_CAPTURE_PEOPLETODAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_CAPTURE_PEOPLETODAY");


            Map<String, Integer> map23 = ((Map) this.jedisUtil.get("KEY_DOORFLOW_TODAYCOUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_DOORFLOW_TODAYCOUNT");


            Map<String, List<TimeCount>> map12 = ((Map) this.jedisUtil.get("KEY_DOORFLOW_REALTIME_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_DOORFLOW_REALTIME_COUNT");


            Map<String, List<TimeCount>> map31 = ((Map) this.jedisUtil.get("KEY_WIFI_REALTIME_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_WIFI_REALTIME_COUNT");


            Map<String, Integer> map34 = ((Map) this.jedisUtil.get("KEY_WIFI_PEOPLETODAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_WIFI_PEOPLETODAY");

            resutltMap.put("carTen", map13);
            resutltMap.put("carTodayCount", map14);
            resutltMap.put("peopleTen", map11);
            resutltMap.put("peopleTodayCount", map22);
            resutltMap.put("doorTodayCount", map23);
            resutltMap.put("doorTen", map12);
            resutltMap.put("wifiToday", map31);
            resutltMap.put("wifiTodayCount", map34);
            resp.setData(resutltMap);
        } catch (Exception e) {
            this.logger.error("初始化人车开门当天记录缓存失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70801011");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/getWarningEvents"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> getWarningEvents(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<Object, Object> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);

        try {
            Map<String, Object> resutltMap = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());


            Map<String, Map<String, Integer>> map10 = ((Map) this.jedisUtil.get("REDIS_KEY_ADDPERSON_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_ADDPERSON_COUNT");


            Map<String, Map<String, Integer>> map11 = ((Map) this.jedisUtil.get("REDIS_KEY_LEAVEPERSON_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_LEAVEPERSON_COUNT");


            Map<String, Map<String, Integer>> map12 = ((Map) this.jedisUtil.get("REDIS_KEY_FREQUENCY_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_FREQUENCY_COUNT");


            Map<String, Map<String, Integer>> map13 = ((Map) this.jedisUtil.get("REDIS_KEY_ALARM") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_ALARM");


            Map<String, Map<String, Integer>> map18 = ((Map) this.jedisUtil.get("REDIS_KEY_OLDMAN_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_OLDMAN_COUNT");


            Map<String, Map<String, Integer>> map25 = ((Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERLEAVE") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERLEAVE");


            Map<String, Map<String, Integer>> map23 = ((Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERDISCOVERY") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERDISCOVERY");


            Map<String, Map<String, Integer>> map21 = ((Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERRETENTION") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERRETENTION");

            resutltMap.put("addPersonCount", map10);
            resutltMap.put("leavePersonCount", map11);
            resutltMap.put("frequencyCount", map12);
            resutltMap.put("alarmCount", map13);
            resutltMap.put("oldManCount", map18);
            resutltMap.put("vehicleLeaveCount", map25);
            resutltMap.put("vehicleDsicoveryCount", map23);
            resutltMap.put("vehicleRetentionCount", map21);
            resp.setData(resutltMap);
        } catch (Exception e) {
            this.logger.error("首页预警处置初始化数据失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70801012");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 获取当前用户权限小区
     * @param map
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/getCurrentUserIds"}, method = {RequestMethod.POST})
    public ResponseEntity<List<CaptureSumDTO>> getCurrentUserIds(@RequestBody Map<String, String> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<CaptureSumDTO>> resp = validite(bindingResult);
        try {
            String userId;
            if (!map.containsKey("userIds") || StringUtils.isBlank(userId = map.get("userIds"))) {
                resp = createFailResponse();
                resp.setMessage("请求失败：参数userIds不能为空！");
                return resp;
            }
            // 查询用户权限
            UserPermission userPermission = this.organizationRegionService.findCurrentPersion(userId);
            String[] villageCodes = userPermission.getVillageCodes();
            List<CaptureSumDTO> villages = new ArrayList<>();
            for (String villageCode : villageCodes) {
                String villageName = this.vehicleService.selectVillageName(villageCode);
                CaptureSumDTO c = new CaptureSumDTO();
                c.setVillageName(villageName);
                c.setVillageCode(villageCode);
                villages.add(c);
            }
            resp.setData(villages);
        } catch (Exception e) {
            this.logger.error("获取当前登入用户的小区权限失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

}