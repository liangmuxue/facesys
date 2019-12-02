package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.collect.client.ICaptureService;
import com.ss.isc.data.collect.client.IVehicleService;
import com.ss.isc.data.collect.common.dto.CaptureSumDTO;
import com.ss.isc.data.push.client.IPushService;
import com.ss.isc.data.resource.client.IWifiService;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.data.system.common.dto.UserPermission;
import com.ss.isc.util.SpringUtil;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.jedis.JedisUtil;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.j7cai.common.util.JsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePagePushJob implements SimpleJob {

    private static final Logger log = LoggerFactory.getLogger(HomePagePushJob.class);


    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private IPushService pushService = SpringUtil.getBean(IPushService.class);
    private IVehicleService vehicleService = SpringUtil.getBean(IVehicleService.class);
    private IOrganizationRegionService oService = SpringUtil.getBean(IOrganizationRegionService.class);
    private ICaptureService captureService = SpringUtil.getBean(ICaptureService.class);
    private IWifiService wifiService = SpringUtil.getBean(IWifiService.class);


    public void execute(ShardingContext shardingContext) {
        List<UserPermission> permissions = new ArrayList<UserPermission>();
        List<UserPermission> findUserPermission = this.oService.findUserPermission();
        if (findUserPermission != null) {
            permissions = findUserPermission;


            Map<String, Integer> map1 = ((Map) this.jedisUtil.get("KEY_VEHICLE_ALLDAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_VEHICLE_ALLDAY");

            Map<String, Integer> map2 = null;
            Object object30 = this.jedisUtil.get("KEY_CAPTURE_ALLDAY");
            if (null == object30) {
                map2 = new HashMap<String, Integer>();

                List<CaptureSumDTO> list2 = this.captureService.getCaptureAllSum();
                for (CaptureSumDTO captureSumDTO : list2) {
                    map2.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
                }
                this.jedisUtil.set("KEY_CAPTURE_ALLDAY", map2);
            } else {
                map2 = (Map) this.jedisUtil.get("KEY_CAPTURE_ALLDAY");
            }


            Map<String, Integer> map40 = ((Map) this.jedisUtil.get("KEY_WIFI_ALLDAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_WIFI_ALLDAY");


            Map<String, Integer> map3 = null;
            Object object1 = this.jedisUtil.get("KEY_CAPTURE_SEVEN");
            if (null == object1) {
                map3 = new HashMap<String, Integer>();

                List<CaptureSumDTO> list2 = this.captureService.getCaptureSum();
                for (CaptureSumDTO captureSumDTO : list2) {
                    map3.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
                }
                this.jedisUtil.set("KEY_CAPTURE_SEVEN", map3);
            } else {
                map3 = (Map) this.jedisUtil.get("KEY_CAPTURE_SEVEN");
            }


            Map<String, Integer> map4 = ((Map) this.jedisUtil.get("KEY_VEHICLE_SEVEN") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_VEHICLE_SEVEN");


            Map<String, Integer> map30 = null;
            Object object10 = this.jedisUtil.get("KEY_WIFI_SEVEN");
            if (null == object10) {
                map30 = new HashMap<String, Integer>();

                List<CaptureSumDTO> list2 = this.wifiService.getWifiSum();
                for (CaptureSumDTO captureSumDTO : list2) {
                    map30.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
                }
                this.jedisUtil.set("KEY_WIFI_SEVEN", map30);
            } else {
                map30 = (Map) this.jedisUtil.get("KEY_WIFI_SEVEN");
            }


            Map<String, Map<String, CaptureSumDTO>> SevenDay = SevenDay(permissions, map3, map4, map30);


            Map<String, CaptureSumDTO> carSeven = carSeven(permissions, map4);


            Map<String, CaptureSumDTO> captureSeven = captureSeven(permissions, map3);


            Map<String, CaptureSumDTO> wifiSeven = wifiSeven(permissions, map30);


            Map<String, Integer> allCount = allCount(permissions, map1, map2, map40);

            Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            map.put("code", "00000000");
            map.put("message", "人车7天总天数数据");
            map.put("type", "superHome");
            map.put("typeCode", CommonConstant.SUBSCRIBED_DATATYPE_SUPERHOME);
            map.put("allCount", allCount);
            map.put("CarCategory", carSeven);
            map.put("PeopleCategory", captureSeven);
            map.put("WifiCategory", wifiSeven);
            map.put("SevenDay", SevenDay);

            String meString = JsonUtils.getFastjsonFromObject(map);
            this.pushService.pushHome(meString);
        } else {
            log.error("HomePagePushJob推送数据为空原因为用户权限为空");
        }
    }


    private Map<String, Integer> allCount(List<UserPermission> permissions, Map<String, Integer> map1, Map<String, Integer> map2, Map<String, Integer> map40) {
        Map<String, Integer> map15 = ((Map) this.jedisUtil.get("REDIS_KEY_SUPERHOME") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_SUPERHOME");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map1.containsKey(s) &&
                        null != map1.get(s)) {
                    count += ((Integer) map1.get(s)).intValue();
                }

                if (map2.containsKey(s) &&
                        null != map2.get(s)) {
                    count += ((Integer) map2.get(s)).intValue();
                }

                if (map40.containsKey(s) &&
                        null != map40.get(s)) {
                    count += ((Integer) map40.get(s)).intValue();
                }

                map15.put(userId, Integer.valueOf(count));
                this.jedisUtil.set("REDIS_KEY_SUPERHOME", map15);
            }
        }
        return map15;
    }


    private Map<String, Map<String, CaptureSumDTO>> SevenDay(List<UserPermission> permissions, Map<String, Integer> map3, Map<String, Integer> map4, Map<String, Integer> map30) {
        Map<String, Map<String, CaptureSumDTO>> map18 = ((Map) this.jedisUtil.get("REDIS_KEY_SUPERHOME_SEVEN") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_SUPERHOME_SEVEN");
        Map<String, CaptureSumDTO> map89 = new HashMap<String, CaptureSumDTO>();
        Map<String, Map<String, CaptureSumDTO>> map88 = new HashMap<String, Map<String, CaptureSumDTO>>();

        for (UserPermission userPermission : permissions) {
            String userId = userPermission.getUserId();
            List<CaptureSumDTO> list = new ArrayList<CaptureSumDTO>();
            String[] villageCodes = userPermission.getVillageCodes();
            Map<String, CaptureSumDTO> map = (Map) map18.get(userId);
            if (null == map) {
                map = new HashMap<String, CaptureSumDTO>();
            }
            for (String s : villageCodes) {
                int count = 0;
                String villageName = this.vehicleService.selectVillageName(s);
                if (map3.containsKey(s) &&
                        null != map3.get(s)) {
                    count += ((Integer) map3.get(s)).intValue();
                }

                if (map4.containsKey(s) &&
                        null != map4.get(s)) {
                    count += ((Integer) map4.get(s)).intValue();
                }

                if (map30.containsKey(s) &&
                        null != map30.get(s)) {
                    count += ((Integer) map30.get(s)).intValue();
                }

                CaptureSumDTO c = new CaptureSumDTO();
                c.setNum(Integer.valueOf(count));
                c.setVillageCode(s);
                if (null != villageName) {
                    c.setVillageName(villageName);
                }
                map.put(s, c);


                list.add(c);
                map18.put(userId, map);
                this.jedisUtil.set("REDIS_KEY_SUPERHOME_SEVEN", map18);
            }

            Collections.sort(list, new Comparator<CaptureSumDTO>() {
                public int compare(CaptureSumDTO arg0, CaptureSumDTO arg1) {
                    int num = arg0.getNum().intValue();
                    int num2 = arg1.getNum().intValue();
                    if (num < num2)
                        return 1;
                    if (num == num2) {
                        return 0;
                    }
                    return -1;
                }
            });

            if (list.size() > 5) {
                list = list.subList(0, 5);
            }
            map89 = new LinkedHashMap<String, CaptureSumDTO>();
            for (CaptureSumDTO captureSumDTO : list) {
                map89.put(captureSumDTO.getVillageCode(), captureSumDTO);
            }
            map88.put(userId, map89);
        }
        this.jedisUtil.set("REDIS_KEY_SUPERHOME_PARTSEVEN", map88);
        return map88;
    }


    private Map<String, CaptureSumDTO> wifiSeven(List<UserPermission> permissions, Map<String, Integer> map30) {
        Map<String, CaptureSumDTO> map20 = ((Map) this.jedisUtil.get("KEY_CAPTURE_WIFI_SEVENRECORD") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_CAPTURE_WIFI_SEVENRECORD");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map30.containsKey(s) &&
                        null != map30.get(s)) {
                    count += ((Integer) map30.get(s)).intValue();
                }
            }

            CaptureSumDTO c = new CaptureSumDTO();
            c.setNum(Integer.valueOf(count));
            map20.put(userId, c);
            this.jedisUtil.set("KEY_CAPTURE_WIFI_SEVENRECORD", map20);
        }
        return map20;
    }


    private Map<String, CaptureSumDTO> captureSeven(List<UserPermission> permissions, Map<String, Integer> map3) {
        Map<String, CaptureSumDTO> map17 = ((Map) this.jedisUtil.get("KEY_CAPTURE_SEVEN_SEVENRECORD") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_CAPTURE_SEVEN_SEVENRECORD");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map3.containsKey(s) &&
                        null != map3.get(s)) {
                    count += ((Integer) map3.get(s)).intValue();
                }
            }

            CaptureSumDTO c = new CaptureSumDTO();
            c.setNum(Integer.valueOf(count));
            map17.put(userId, c);
            this.jedisUtil.set("KEY_CAPTURE_SEVEN_SEVENRECORD", map17);
        }
        return map17;
    }


    private Map<String, CaptureSumDTO> carSeven(List<UserPermission> permissions, Map<String, Integer> map4) {
        Map<String, CaptureSumDTO> map16 = ((Map) this.jedisUtil.get("KEY_VEHICLE_SEVENRECORD") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_VEHICLE_SEVENRECORD");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map4.containsKey(s) &&
                        null != map4.get(s)) {
                    count += ((Integer) map4.get(s)).intValue();
                }
            }

            CaptureSumDTO c = new CaptureSumDTO();
            c.setNum(Integer.valueOf(count));
            map16.put(userId, c);
            this.jedisUtil.set("KEY_VEHICLE_SEVENRECORD", map16);
        }
        return map16;
    }

}
