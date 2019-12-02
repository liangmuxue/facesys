package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.archives.common.model.TimeCount;
import com.ss.isc.data.collect.client.IVehicleService;
import com.ss.isc.data.collect.common.dto.CaptureSumDTO;
import com.ss.isc.data.process.mapper.PeopleProcessMapper;
import com.ss.isc.data.push.client.IPushService;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.data.system.common.dto.UserPermission;
import com.ss.isc.util.SpringUtil;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.jedis.JedisUtil;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.j7cai.common.util.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePageRealTimeJob implements SimpleJob {

    private static final Logger log = LoggerFactory.getLogger(HomePageRealTimeJob.class);


    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private IPushService pushService = SpringUtil.getBean(IPushService.class);
    private IVehicleService vehicleService = SpringUtil.getBean(IVehicleService.class);
    private PeopleProcessMapper peopleProcessMapper = SpringUtil.getBean(PeopleProcessMapper.class);
    private IOrganizationRegionService oService = SpringUtil.getBean(IOrganizationRegionService.class);


    public void execute(ShardingContext shardingContext) {
        long currentTimeMillis = System.currentTimeMillis();


        List<UserPermission> permissions = new ArrayList<UserPermission>();
        List<UserPermission> findUserPermission = this.oService.findUserPermission();
        if (findUserPermission != null) {
            permissions = findUserPermission;


            Map<String, List<TimeCount>> captureTen = captureTen(currentTimeMillis, permissions);


            Map<String, Integer> captureTenCount = captureTenCount(permissions);


            Map<String, List<TimeCount>> openDoorTen = openDoorTen(currentTimeMillis, permissions);


            Map<String, List<TimeCount>> wifiTen = wifiTen(currentTimeMillis, permissions);


            Map<String, Integer> openDoorToday = openDoorToday(permissions);


            Map<String, Integer> wifiToday = wifiToday(permissions);


            Map<String, String> map20 = new HashMap<String, String>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startDate = sdf.format(Long.valueOf(currentTimeMillis));
            long ab = currentTimeMillis - 10000L;
            String endDate = sdf.format(Long.valueOf(ab));
            map20.put("startDate", startDate);
            map20.put("endDate", endDate);
            List<CaptureSumDTO> list = this.vehicleService.getTenCountCar(map20);


            Map<String, Integer> map3 = new HashMap<String, Integer>();
            for (CaptureSumDTO captureSumDTO : list) {
                if (map3.containsKey(captureSumDTO.getVillageCode())) {
                    map3.put(captureSumDTO.getVillageCode(),
                            Integer.valueOf(((Integer) map3.get(captureSumDTO.getVillageCode())).intValue() + captureSumDTO.getNum().intValue()));
                    continue;
                }
                map3.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
            }


            Map<String, List<TimeCount>> carTen = carTen(currentTimeMillis, permissions, map3);


            Map<String, Integer> carToday = carToday(permissions, map3);


            carSeven(list);


            carAll(list);


            Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

            map.put("carTen", carTen);
            map.put("carTodayCount", carToday);
            map.put("peopleTen", captureTen);
            map.put("peopleTodayCount", captureTenCount);
            map.put("doorTodayCount", openDoorToday);
            map.put("doorTen", openDoorTen);
            map.put("wifiToday", wifiTen);
            map.put("wifiTodayCount", wifiToday);
            map.put("code", "00000000");
            map.put("message", "实时统计页面");
            map.put("type", "realTime");
            map.put("typeCode", CommonConstant.SUBSCRIBED_DATATYPE_REALTIME);
            String meString = JsonUtils.getFastjsonFromObject(map);
            this.pushService.pushHome(meString);
        } else {
            log.error("HomePageRealTimeJob推送数据为空原因为用户权限为空");
        }
    }


    private void carAll(List<CaptureSumDTO> list) {
        Map<String, Integer> map21 = null;
        Object object2 = this.jedisUtil.get("KEY_VEHICLE_ALLDAY");
        if (null == object2) {
            map21 = new HashMap<String, Integer>();

            List<CaptureSumDTO> list1 = this.vehicleService.findVehicleList();

            for (CaptureSumDTO captureSumDTO : list1) {
                map21.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
            }
        } else {
            map21 = (Map) this.jedisUtil.get("KEY_VEHICLE_ALLDAY");
            for (CaptureSumDTO captureSumDTO : list) {
                if (map21.containsKey(captureSumDTO.getVillageCode())) {
                    map21.put(captureSumDTO.getVillageCode(),
                            Integer.valueOf(((Integer) map21.get(captureSumDTO.getVillageCode())).intValue() + captureSumDTO.getNum().intValue()));
                    continue;
                }
                map21.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
            }
        }

        this.jedisUtil.set("KEY_VEHICLE_ALLDAY", map21);
    }


    private void carSeven(List<CaptureSumDTO> list) {
        Map<String, Integer> map5 = null;
        Object object1 = this.jedisUtil.get("KEY_VEHICLE_SEVEN");
        if (null == object1) {
            map5 = new HashMap<String, Integer>();

            List<CaptureSumDTO> list1 = this.vehicleService.findSixCount();

            for (CaptureSumDTO captureSumDTO : list1) {
                map5.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
            }
        } else {
            map5 = (Map) this.jedisUtil.get("KEY_VEHICLE_SEVEN");
            for (CaptureSumDTO captureSumDTO : list) {
                if (map5.containsKey(captureSumDTO.getVillageCode())) {
                    map5.put(captureSumDTO.getVillageCode(),
                            Integer.valueOf(((Integer) map5.get(captureSumDTO.getVillageCode())).intValue() + captureSumDTO.getNum().intValue()));
                    continue;
                }
                map5.put(captureSumDTO.getVillageCode(), captureSumDTO.getNum());
            }
        }

        this.jedisUtil.set("KEY_VEHICLE_SEVEN", map5);
    }


    private Map<String, Integer> carToday(List<UserPermission> permissions, Map<String, Integer> map3) {
        Map<String, Integer> map14 = ((Map) this.jedisUtil.get("KEY_VEHICLE_TODAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_VEHICLE_TODAY");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            Integer carCount = (Integer) map14.get(userId);
            if (null == carCount) {
                carCount = Integer.valueOf(0);
            }
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map3.containsKey(s)) {
                    count += ((Integer) map3.get(s)).intValue();
                }
            }
            carCount = Integer.valueOf(carCount.intValue() + count);
            map14.put(userId, carCount);
            this.jedisUtil.set("KEY_VEHICLE_TODAY", map14);
        }
        return map14;
    }


    private Map<String, List<TimeCount>> carTen(long currentTimeMillis, List<UserPermission> permissions, Map<String, Integer> map3) {
        Map<String, List<TimeCount>> map13 = ((Map) this.jedisUtil.get("KEY_VEHICLE_REALTIME_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_VEHICLE_REALTIME_COUNT");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            List<TimeCount> carList = (List) map13.get(userId);
            if (null == carList) {
                carList = new ArrayList<TimeCount>();
            }
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map3.containsKey(s)) {
                    count += ((Integer) map3.get(s)).intValue();
                }
            }
            carList.add(new TimeCount(currentTimeMillis, Integer.valueOf(count)));

            if (carList.size() > 30) {
                carList.subList(0, carList.size() - 30).clear();
            }
            sortCollections(carList);
            map13.put(userId, carList);
            this.jedisUtil.set("KEY_VEHICLE_REALTIME_COUNT", map13);
        }
        return map13;
    }


    private Map<String, Integer> wifiToday(List<UserPermission> permissions) {
        Map<String, Integer> map33 = (this.jedisUtil.get("KEY_WIFI_TODAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_WIFI_TODAY");


        Map<String, Integer> map34 = ((Map) this.jedisUtil.get("KEY_WIFI_PEOPLETODAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_WIFI_PEOPLETODAY");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map33.containsKey(s)) {
                    count += ((Integer) map33.get(s)).intValue();
                }
                map34.put(userId, Integer.valueOf(count));
                this.jedisUtil.set("KEY_WIFI_PEOPLETODAY", map34);
            }
        }
        return map34;
    }


    private Map<String, Integer> openDoorToday(List<UserPermission> permissions) {
        Map<String, Integer> map9 = (this.jedisUtil.get("KEY_DOORFLOW_TODAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_DOORFLOW_TODAY");


        Map<String, Integer> map23 = ((Map) this.jedisUtil.get("KEY_DOORFLOW_TODAYCOUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_DOORFLOW_TODAYCOUNT");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map9.containsKey(s)) {
                    count += ((Integer) map9.get(s)).intValue();
                }
                map23.put(userId, Integer.valueOf(count));
                this.jedisUtil.set("KEY_DOORFLOW_TODAYCOUNT", map23);
            }
        }
        return map23;
    }


    private Map<String, List<TimeCount>> wifiTen(long currentTimeMillis, List<UserPermission> permissions) {
        Map<String, Integer> map30 = (this.jedisUtil.get("KEY_WIFI_REALTIME") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_WIFI_REALTIME");


        Map<String, List<TimeCount>> map31 = ((Map) this.jedisUtil.get("KEY_WIFI_REALTIME_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_WIFI_REALTIME_COUNT");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            List<TimeCount> wifiList = (List) map31.get(userId);
            if (null == wifiList) {
                wifiList = new ArrayList<TimeCount>();
            }
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map30.containsKey(s)) {
                    count += ((Integer) map30.get(s)).intValue();
                }
            }
            wifiList.add(new TimeCount(currentTimeMillis, Integer.valueOf(count)));

            if (wifiList.size() > 30) {
                wifiList.subList(0, wifiList.size() - 30).clear();
            }

            sortCollections(wifiList);
            map31.put(userId, wifiList);
            this.jedisUtil.set("KEY_WIFI_REALTIME_COUNT", map31);
        }
        this.jedisUtil.del(new String[]{"KEY_WIFI_REALTIME"});
        return map31;
    }


    private Map<String, List<TimeCount>> openDoorTen(long currentTimeMillis, List<UserPermission> permissions) {
        Map<String, Integer> map7 = (this.jedisUtil.get("KEY_DOORFLOW_REALTIME") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_DOORFLOW_REALTIME");


        Map<String, List<TimeCount>> map12 = ((Map) this.jedisUtil.get("KEY_DOORFLOW_REALTIME_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_DOORFLOW_REALTIME_COUNT");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            List<TimeCount> doorList = (List) map12.get(userId);
            if (null == doorList) {
                doorList = new ArrayList<TimeCount>();
            }
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map7.containsKey(s)) {
                    count += ((Integer) map7.get(s)).intValue();
                }
            }
            doorList.add(new TimeCount(currentTimeMillis, Integer.valueOf(count)));

            if (doorList.size() > 30) {
                doorList.subList(0, doorList.size() - 30).clear();
            }

            sortCollections(doorList);
            map12.put(userId, doorList);
            this.jedisUtil.set("KEY_DOORFLOW_REALTIME_COUNT", map12);
        }
        this.jedisUtil.del(new String[]{"KEY_DOORFLOW_REALTIME"});
        return map12;
    }


    private Map<String, Integer> captureTenCount(List<UserPermission> permissions) {
        Map<String, Integer> map8 = (this.jedisUtil.get("KEY_CAPTURE_TODAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_CAPTURE_TODAY");


        Map<String, Integer> map22 = ((Map) this.jedisUtil.get("REDIS_KEY_CAPTURE_PEOPLETODAY") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_CAPTURE_PEOPLETODAY");

        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map8.containsKey(s)) {
                    count += ((Integer) map8.get(s)).intValue();
                }
                map22.put(userId, Integer.valueOf(count));
                this.jedisUtil.set("REDIS_KEY_CAPTURE_PEOPLETODAY", map22);
            }
        }
        return map22;
    }


    private Map<String, List<TimeCount>> captureTen(long currentTimeMillis, List<UserPermission> permissions) {
        Map<String, Integer> map6 = (this.jedisUtil.get("KEY_CAPTURE_REALTIME") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_CAPTURE_REALTIME");


        Map<String, List<TimeCount>> map11 = ((Map) this.jedisUtil.get("KEY_CAPTURE_REALTIME_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("KEY_CAPTURE_REALTIME_COUNT");


        for (UserPermission userPermission : permissions) {
            int count = 0;
            String userId = userPermission.getUserId();
            List<TimeCount> peopleList1 = (List) map11.get(userId);
            if (null == peopleList1) {
                peopleList1 = new ArrayList<TimeCount>();
            }
            String[] villageCodes = userPermission.getVillageCodes();
            for (String s : villageCodes) {
                if (map6.containsKey(s)) {
                    count += ((Integer) map6.get(s)).intValue();
                }
            }
            peopleList1.add(new TimeCount(currentTimeMillis, Integer.valueOf(count)));


            if (peopleList1.size() > 30) {
                peopleList1.subList(0, peopleList1.size() - 30).clear();
            }

            sortCollections(peopleList1);
            map11.put(userId, peopleList1);
            this.jedisUtil.set("KEY_CAPTURE_REALTIME_COUNT", map11);
        }
        this.jedisUtil.del(new String[]{"KEY_CAPTURE_REALTIME"});
        return map11;
    }


    private void sortCollections(List<TimeCount> list) {
        Collections.sort(list, new Comparator<TimeCount>() {
            public int compare(TimeCount arg0, TimeCount arg1) {
                long a0 = arg0.getTime();
                long a1 = arg1.getTime();
                if (a1 < a0)
                    return 1;
                if (a1 == a0) {
                    return 0;
                }
                return -1;
            }
        });
    }

}
