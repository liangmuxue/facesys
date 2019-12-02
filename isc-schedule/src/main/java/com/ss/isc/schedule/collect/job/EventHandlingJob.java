package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.access.common.dto.FaceDbDTO;
import com.ss.isc.data.process.common.dto.WarningDTO;
import com.ss.isc.data.process.common.dto.WarningSumDTO;
import com.ss.isc.data.process.mapper.PeopleProcessMapper;
import com.ss.isc.data.push.client.IPushService;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.data.system.common.dto.UserPermission;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.SpringUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.http.BaseFormatJsonUtil;
import com.ss.isc.util.jedis.JedisUtil;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.j7cai.common.util.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EventHandlingJob implements SimpleJob {

    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private PeopleProcessMapper peopleProcessMapper = SpringUtil.getBean(PeopleProcessMapper.class);
    private IOrganizationRegionService oService = SpringUtil.getBean(IOrganizationRegionService.class);
    private IPushService pushService = SpringUtil.getBean(IPushService.class);
    private IAccessService accessService = SpringUtil.getBean(IAccessService.class);


    private Logger logger = LoggerFactory.getLogger(EventHandlingJob.class);


    public void execute(ShardingContext shardingContext) {
        List<UserPermission> permissions = new ArrayList<UserPermission>();
        List<UserPermission> findUserPermission = this.oService.findUserPermission();
        if (findUserPermission != null) {
            permissions = findUserPermission;


            Map<String, WarningDTO> map2 = null;
            Object object1 = this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_ADDPERSON");
            if (null == object1) {


                List<WarningSumDTO> list9 = this.peopleProcessMapper.addUntreatedCount(PropertiesUtil.getAddPersonJudgedDays());
                List<WarningSumDTO> list10 = this.peopleProcessMapper.addDealedCount(PropertiesUtil.getAddPersonJudgedDays());
                map2 = selectProcessCount(list9, list10);
                this.jedisUtil.set("REDIS_KEY_PROCESSTYPE_ADDPERSON", map2);
            } else {

                map2 = (Map) this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_ADDPERSON");
            }


            Map<String, Map<String, Integer>> map10 = ((Map) this.jedisUtil.get("REDIS_KEY_ADDPERSON_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_ADDPERSON_COUNT");


            for (UserPermission userPermission : permissions) {
                String userId = userPermission.getUserId();
                String[] villageCodes = userPermission.getVillageCodes();
                List<String> asList = Arrays.asList(villageCodes);
                Map<String, Integer> addPersonCount = new HashMap<String, Integer>();
                addPersonCount = countSum(map2, addPersonCount, asList);
                map10.put(userId, addPersonCount);
            }
            this.jedisUtil.set("REDIS_KEY_ADDPERSON_COUNT", map10);


            Map<String, WarningDTO> map3 = null;
            Object object2 = this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_LEAVEPERSON");
            if (null == object2) {


                List<WarningSumDTO> list7 = this.peopleProcessMapper.leaveUntreatedCount(PropertiesUtil.getLeaveDays());
                List<WarningSumDTO> list8 = this.peopleProcessMapper.leaveDealedCount(PropertiesUtil.getLeaveDays());
                map3 = selectProcessCount(list7, list8);
                this.jedisUtil.set("REDIS_KEY_PROCESSTYPE_LEAVEPERSON", map3);
            } else {

                map3 = (Map) this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_LEAVEPERSON");
            }


            Map<String, Map<String, Integer>> map11 = ((Map) this.jedisUtil.get("REDIS_KEY_LEAVEPERSON_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_LEAVEPERSON_COUNT");
            for (UserPermission userPermission1 : permissions) {
                String userId = userPermission1.getUserId();
                String[] villageCodes = userPermission1.getVillageCodes();
                List<String> asList = Arrays.asList(villageCodes);
                Map<String, Integer> leavePersonCount = new HashMap<String, Integer>();
                leavePersonCount = countSum(map3, leavePersonCount, asList);
                map11.put(userId, leavePersonCount);
            }
            this.jedisUtil.set("REDIS_KEY_LEAVEPERSON_COUNT", map11);


            Map<String, WarningDTO> map4 = null;
            Object object4 = this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_FREQUENCY");
            if (null == object4) {


                List<WarningSumDTO> list5 = this.peopleProcessMapper.frequencyUntreatedCount(PropertiesUtil.getFrequencyPersonAmount());

                List<WarningSumDTO> list6 = this.peopleProcessMapper.frequencyDealedCount(PropertiesUtil.getFrequencyPersonAmount());
                map4 = selectProcessCount(list5, list6);
                this.jedisUtil.set("REDIS_KEY_PROCESSTYPE_FREQUENCY", map4);
            } else {

                map4 = (Map) this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_FREQUENCY");
            }


            Map<String, Map<String, Integer>> map12 = ((Map) this.jedisUtil.get("REDIS_KEY_FREQUENCY_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_FREQUENCY_COUNT");
            for (UserPermission userPermission2 : permissions) {
                String userId1 = userPermission2.getUserId();
                String[] villageCodes1 = userPermission2.getVillageCodes();
                List<String> asList1 = Arrays.asList(villageCodes1);
                Map<String, Integer> frequencyCount = new HashMap<String, Integer>();
                frequencyCount = countSum(map4, frequencyCount, asList1);
                map12.put(userId1, frequencyCount);
            }
            this.jedisUtil.set("REDIS_KEY_FREQUENCY_COUNT", map12);


            Map<String, WarningDTO> map5 = null;
            Object object5 = this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_OLDMAN");
            if (null == object5) {


                List<WarningSumDTO> list3 = this.peopleProcessMapper.oldmanuntreatedCount(PropertiesUtil.getOldManDays());
                List<WarningSumDTO> list4 = this.peopleProcessMapper.oldmandealedCount(PropertiesUtil.getOldManDays());
                map5 = selectProcessCount(list3, list4);
                this.jedisUtil.set("REDIS_KEY_PROCESSTYPE_OLDMAN", map5);
            } else {

                map5 = (Map) this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_OLDMAN");
            }


            Map<String, Map<String, Integer>> map18 = ((Map) this.jedisUtil.get("REDIS_KEY_OLDMAN_COUNT") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_OLDMAN_COUNT");
            for (UserPermission userPermission3 : permissions) {
                String userId2 = userPermission3.getUserId();
                String[] villageCodes2 = userPermission3.getVillageCodes();
                List<String> asList2 = Arrays.asList(villageCodes2);
                Map<String, Integer> oldManCount = new HashMap<String, Integer>();
                oldManCount = countSum(map5, oldManCount, asList2);
                map18.put(userId2, oldManCount);
            }
            this.jedisUtil.set("REDIS_KEY_OLDMAN_COUNT", map18);


            Map<String, WarningDTO> map6 = null;
            Object object6 = this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_ALARM");
            if (null == object6) {

                JSONObject jsonobj = this.accessService.facedblist();
                if (!StringUtils.checkSuccess(jsonobj)) {
                    this.logger.error("欧神查询人像库失败");
                }

                this.logger.info("facedblist ----------------- 人脸底库列表 result-------------:" + jsonobj);

                List<FaceDbDTO> faceList = BaseFormatJsonUtil.formatList(jsonobj.get("data"), FaceDbDTO.class);
                List<String> addList = new ArrayList<String>();
                if (CollectionUtils.isNotEmpty(faceList)) {
                    for (FaceDbDTO faceDbDTO : faceList) {
                        if (faceDbDTO.getType() != null) {
                            addList.add(faceDbDTO.getId());
                        }
                    }
                }
                String libraryIdsString = StringUtils.join(addList.toArray(), ",");
                List<WarningSumDTO> list3 = this.peopleProcessMapper.alarmUntreatedCount(libraryIdsString);
                List<WarningSumDTO> list4 = this.peopleProcessMapper.alarmdealedCount(libraryIdsString);
                map6 = selectProcessCount(list3, list4);
                this.jedisUtil.set("REDIS_KEY_PROCESSTYPE_ALARM", map6);
            } else {
                map6 = (Map) this.jedisUtil.get("REDIS_KEY_PROCESSTYPE_ALARM");
            }


            Map<String, Map<String, Integer>> map13 = ((Map) this.jedisUtil.get("REDIS_KEY_ALARM") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_ALARM");
            for (UserPermission userPermission4 : permissions) {
                String userId3 = userPermission4.getUserId();
                String[] villageCodes3 = userPermission4.getVillageCodes();
                List<String> asList3 = Arrays.asList(villageCodes3);
                Map<String, Integer> alarmCount = new HashMap<String, Integer>();
                alarmCount = countSum(map6, alarmCount, asList3);
                map13.put(userId3, alarmCount);
            }
            this.jedisUtil.set("REDIS_KEY_ALARM", map13);


            Map<String, WarningDTO> map20 = null;
            Object object7 = this.jedisUtil.get("REDIS_KEY_VEHICLE_RETENTION");
            if (null == object7) {


                String s = "cw_vehicle_retention";
                map20 = selectProcessCount(s);
                this.jedisUtil.set("REDIS_KEY_VEHICLE_RETENTION", map20);
            } else {

                map20 = (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_RETENTION");
            }


            Map<String, Map<String, Integer>> map21 = ((Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERRETENTION") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERRETENTION");
            for (UserPermission userPermission4 : permissions) {
                String userId3 = userPermission4.getUserId();
                String[] villageCodes3 = userPermission4.getVillageCodes();
                List<String> asList3 = Arrays.asList(villageCodes3);
                Map<String, Integer> alarmCount = new HashMap<String, Integer>();
                alarmCount = countSum(map20, alarmCount, asList3);
                map21.put(userId3, alarmCount);
            }
            this.jedisUtil.set("REDIS_KEY_VEHICLE_USERRETENTION", map21);


            Map<String, WarningDTO> map22 = null;
            Object object8 = this.jedisUtil.get("REDIS_KEY_VEHICLE_DISCOVERY");
            if (null == object8) {


                String s1 = "cw_vehicle_discovery";
                map22 = selectProcessCount(s1);
                this.jedisUtil.set("REDIS_KEY_VEHICLE_DISCOVERY", map22);
            } else {

                map22 = (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_DISCOVERY");
            }


            Map<String, Map<String, Integer>> map23 = ((Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERDISCOVERY") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERDISCOVERY");
            for (UserPermission userPermission4 : permissions) {
                String userId3 = userPermission4.getUserId();
                String[] villageCodes3 = userPermission4.getVillageCodes();
                List<String> asList3 = Arrays.asList(villageCodes3);
                Map<String, Integer> alarmCount = new HashMap<String, Integer>();
                alarmCount = countSum(map22, alarmCount, asList3);
                map23.put(userId3, alarmCount);
            }
            this.jedisUtil.set("REDIS_KEY_VEHICLE_USERDISCOVERY", map23);


            Map<String, WarningDTO> map24 = null;
            Object object9 = this.jedisUtil.get("REDIS_KEY_VEHICLE_LEAVE");
            if (null == object9) {


                String s2 = "cw_vehicle_leave";
                map24 = selectProcessCount(s2);
                this.jedisUtil.set("REDIS_KEY_VEHICLE_LEAVE", map24);
            } else {

                map24 = (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_LEAVE");
            }


            Map<String, Map<String, Integer>> map25 = ((Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERLEAVE") == null) ? new HashMap() : (Map) this.jedisUtil.get("REDIS_KEY_VEHICLE_USERLEAVE");
            for (UserPermission userPermission4 : permissions) {
                String userId3 = userPermission4.getUserId();
                String[] villageCodes3 = userPermission4.getVillageCodes();
                List<String> asList3 = Arrays.asList(villageCodes3);
                Map<String, Integer> alarmCount = new HashMap<String, Integer>();
                alarmCount = countSum(map24, alarmCount, asList3);
                map25.put(userId3, alarmCount);
            }
            this.jedisUtil.set("REDIS_KEY_VEHICLE_USERLEAVE", map25);


            Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            map.put("addPersonCount", map10);
            map.put("leavePersonCount", map11);
            map.put("frequencyCount", map12);
            map.put("alarmCount", map13);
            map.put("oldManCount", map18);
            map.put("vehicleLeaveCount", map25);
            map.put("vehicleDsicoveryCount", map23);
            map.put("vehicleRetentionCount", map21);
            map.put("code", "00000000");
            map.put("message", "预警处置实时统计");
            map.put("type", "realTime");
            map.put("typeCode", CommonConstant.SUBSCRIBED_DATATYPE_EVENT);
            String meString = JsonUtils.getFastjsonFromObject(map);
            this.pushService.pushHome(meString);
        } else {
            this.logger.error("EventHandlingJob推送数据为空原因为用户权限为空");
        }
    }


    private Map<String, Integer> countSum(Map<String, WarningDTO> map3, Map<String, Integer> map, List<String> villageCode) {
        int untreated = 0;
        int dealed = 0;
        for (String string : villageCode) {
            if (null != map3) {
                WarningDTO warningDTO = (WarningDTO) map3.get(string);
                if (warningDTO != null) {
                    map.put("untreated", Integer.valueOf(untreated += warningDTO.getUntreated().intValue()));
                    if (null != warningDTO.getDealed()) {
                        map.put("dealed", Integer.valueOf(dealed += warningDTO.getDealed().intValue()));
                        continue;
                    }
                    warningDTO.setDealed(Integer.valueOf(0));
                    map.put("dealed", Integer.valueOf(dealed += warningDTO.getDealed().intValue()));
                }
            }
        }


        if (null != map.get("untreated") && null != map.get("dealed")) {
            map.put("allCount", Integer.valueOf(((Integer) map.get("untreated")).intValue() + ((Integer) map.get("dealed")).intValue()));
        }
        return map;
    }


    private Map<String, WarningDTO> selectProcessCount(List<WarningSumDTO> untreatedList, List<WarningSumDTO> dealedList) {
        Map<String, WarningDTO> map2 = new HashMap<String, WarningDTO>();
        if (null != untreatedList && untreatedList.size() > 0) {
            for (WarningSumDTO warningSumDTO : untreatedList) {
                WarningDTO a = new WarningDTO();
                a.setVillageCode(warningSumDTO.getVillageCode());
                a.setUntreated(warningSumDTO.getNum());
                a.setDealed(Integer.valueOf(0));
                map2.put(a.getVillageCode(), a);
            }
        }
        if (null != dealedList && dealedList.size() > 0) {
            for (WarningSumDTO warningSumDTO : dealedList) {
                if (map2.containsKey(warningSumDTO.getVillageCode())) {
                    WarningDTO warningDTO = (WarningDTO) map2.get(warningSumDTO.getVillageCode());
                    warningDTO.setDealed(warningSumDTO.getNum());
                    map2.put(warningSumDTO.getVillageCode(), warningDTO);
                    continue;
                }
                WarningDTO a = new WarningDTO();
                a.setVillageCode(warningSumDTO.getVillageCode());
                a.setUntreated(Integer.valueOf(0));
                a.setDealed(warningSumDTO.getNum());
                map2.put(a.getVillageCode(), a);
            }
        }

        return map2;
    }

    private Map<String, WarningDTO> selectProcessCount(String s) {
        List<WarningSumDTO> untreatedList = this.peopleProcessMapper.untreatedCount1(s);
        List<WarningSumDTO> dealedList = this.peopleProcessMapper.dealedCount1(s);
        Map<String, WarningDTO> map2 = new HashMap<String, WarningDTO>();
        if (null != untreatedList && untreatedList.size() > 0) {
            for (WarningSumDTO warningSumDTO : untreatedList) {
                WarningDTO a = new WarningDTO();
                a.setVillageCode(warningSumDTO.getVillageCode());
                a.setUntreated(warningSumDTO.getNum());
                map2.put(a.getVillageCode(), a);
            }
        }
        if (null != dealedList && dealedList.size() > 0) {
            for (WarningSumDTO warningSumDTO : dealedList) {
                if (map2.containsKey(warningSumDTO.getVillageCode())) {
                    WarningDTO warningDTO = (WarningDTO) map2.get(warningSumDTO.getVillageCode());
                    warningDTO.setDealed(warningSumDTO.getNum());
                    map2.put(warningSumDTO.getVillageCode(), warningDTO);
                    continue;
                }
                WarningDTO a = new WarningDTO();
                a.setVillageCode(warningSumDTO.getVillageCode());
                a.setUntreated(Integer.valueOf(0));
                a.setDealed(warningSumDTO.getNum());
                map2.put(a.getVillageCode(), a);
            }
        }

        return map2;
    }

}
