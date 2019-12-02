package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.archives.client.IArchiveVehicleService;
import com.ss.isc.data.archives.client.IVehicleDiscoveryService;
import com.ss.isc.data.archives.common.model.VehicleDiscovery;
import com.ss.isc.data.archives.common.model.VehicleRecord;
import com.ss.isc.data.resource.client.IVillageService;
import com.ss.isc.data.resource.common.model.Village;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.SpringUtil;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.isc.util.thread.SysThreadPool;
import com.ss.tools.UUIDUtils;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VehicleDiscoveryJob implements SimpleJob {

    public void execute(ShardingContext shardingContext) {
        Village dto = new Village();
        dto.setState(CommonConstant.COMMON_0);
        List<Village> vList = this.villageService.select(dto);
        for (Village village : vList) {


            VehicleDiscoveryThread vehicleDiscoveryThread = new VehicleDiscoveryThread(village.getVillageCode());
            SysThreadPool.getThread().execute(vehicleDiscoveryThread);
        }
    }


    private IVillageService villageService = SpringUtil.getBean(IVillageService.class);
    private IArchiveVehicleService archiveVehicleService = SpringUtil.getBean(IArchiveVehicleService.class);
    private IVehicleDiscoveryService discoveryService = SpringUtil.getBean(IVehicleDiscoveryService.class);
    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);

    public class VehicleDiscoveryThread
            implements Runnable {

        public final Logger logger;
        private String villageCode;

        public VehicleDiscoveryThread(String villageCode) {
            this.logger = LoggerFactory.getLogger(VehicleDiscoveryThread.class);


            this.villageCode = villageCode;
        }


        public void run() {
            this.logger.debug("=====================VehicleDiscoveryThread Thread start=========================");
            this.logger.debug("=======================length===========================" + String.valueOf(PropertiesUtil.getVehicleDiscoveryLength()));
            this.logger.debug("=======================villageCode===========================" + this.villageCode);
            int days = PropertiesUtil.getVehicleDiscoveryDays();
            Map<String, String> map = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            map.put("villageCode", this.villageCode);
            map.put("beginTime", CommonConstant.DATE_PATTERN.format(DateUtil.getBeginDayOfSomeday(PropertiesUtil.getVehicleDiscoveryLength())));
            map.put("endTime", CommonConstant.DATE_PATTERN.format(DateUtil.getEndDayOfSomeDay(CommonConstant.COMMON_1.intValue())));
            List<VehicleDiscovery> firstStatusList = new ArrayList<VehicleDiscovery>();
            List<VehicleDiscovery> thirdStatusList = new ArrayList<VehicleDiscovery>();
            List<VehicleRecord> add1List = new ArrayList<VehicleRecord>();

            List<VehicleRecord> addList = new ArrayList<VehicleRecord>();

            //感知发现查询
            List<VehicleDiscovery> lList = VehicleDiscoveryJob.this.archiveVehicleService.getDiscovery(new VehicleDiscovery(this.villageCode, CommonConstant.COMMON_1));
            List<VehicleDiscovery> dList = new ArrayList<VehicleDiscovery>();
            dList.addAll(lList);

            List<VehicleRecord> vList = VehicleDiscoveryJob.this.archiveVehicleService.unregisterList(map);

            for (VehicleRecord vehicleRecord : vList) {
                map.put("plateNumber", vehicleRecord.getPlateNumber());

                List<VehicleRecord> daysList = VehicleDiscoveryJob.this.archiveVehicleService.unregisterDays(map);
                if (daysList.size() >= days) {
                    VehicleRecord record = (VehicleRecord) daysList.get(daysList.size() - 1);
                    record.setDayBegin(DateUtil.formatDateDefault(((VehicleRecord) daysList.get(0)).getInOutTime()));
                    record.setDayEnd(DateUtil.formatDateDefault(((VehicleRecord) daysList.get(daysList.size() - 1)).getInOutTime()));
                    addList.add(record);
                }
            }
            add1List.addAll(addList);

            for (int i = 0; i < addList.size(); i++) {
                for (int j = 0; j < lList.size(); j++) {
                    if (((VehicleRecord) addList.get(i)).getPlateNumber().equals(((VehicleDiscovery) lList.get(j)).getPlateNumber()) && CommonConstant.COMMON_0.equals(((VehicleDiscovery) lList.get(j)).getFlag())) {

                        add1List.remove(addList.get(i));
                        dList.remove(lList.get(j));

                        VehicleDiscovery dto = new VehicleDiscovery();
                        dto.setDayEnd(((VehicleRecord) addList.get(i)).getDayEnd());
                        dto.setInOutTime(((VehicleRecord) addList.get(i)).getInOutTime());
                        dto.setPlatePicUrl(((VehicleRecord) addList.get(i)).getPlatePicUrl());
                        dto.setPlateNoPicUrl(((VehicleRecord) addList.get(i)).getPlateNoPicUrl());
                        dto.setId(((VehicleDiscovery) lList.get(j)).getId());
                        firstStatusList.add(dto);
                    } else if (((VehicleRecord) addList.get(i)).getPlateNumber().equals(((VehicleDiscovery) lList.get(j)).getPlateNumber()) && CommonConstant.COMMON_1.equals(((VehicleDiscovery) lList.get(j)).getFlag())) {

                        add1List.remove(addList.get(i));
                        dList.remove(lList.get(j));

                        VehicleDiscovery dto = new VehicleDiscovery();
                        dto.setDayBegin(((VehicleRecord) addList.get(i)).getDayBegin());
                        dto.setDayEnd(((VehicleRecord) addList.get(i)).getDayEnd());
                        dto.setInOutTime(((VehicleRecord) addList.get(i)).getInOutTime());
                        dto.setPlatePicUrl(((VehicleRecord) addList.get(i)).getPlatePicUrl());
                        dto.setPlateNoPicUrl(((VehicleRecord) addList.get(i)).getPlateNoPicUrl());
                        dto.setId(((VehicleDiscovery) lList.get(j)).getId());
                        thirdStatusList.add(dto);
                    }
                }
            }


            for (VehicleDiscovery vehicleDiscovery : firstStatusList) {
                String detailId = VehicleDiscoveryJob.this.discoveryService.getDiscoveryDetailId(vehicleDiscovery);
                vehicleDiscovery.setDetailId(detailId);
            }

            if (CollectionUtils.isNotEmpty(firstStatusList)) {
                VehicleDiscoveryJob.this.discoveryService.updateDiscoveryFirstMaster(firstStatusList);
                VehicleDiscoveryJob.this.discoveryService.updateDiscoveryFirstSlave(firstStatusList);
            }


            for (VehicleDiscovery vehicleDiscovery : thirdStatusList) {
                vehicleDiscovery.setDayBegin(DateUtil.formatDateDefault(DateUtils.addDays(new Date(), CommonConstant.COMMON_NEGATIVE_4.intValue())));
                vehicleDiscovery.setDayEnd(DateUtil.formatDateDefault(DateUtils.addDays(new Date(), CommonConstant.COMMON_NEGATIVE_1.intValue())));
                vehicleDiscovery.setDays(Integer.valueOf(PropertiesUtil.getVehicleDiscoveryDays()));
            }
            if (CollectionUtils.isNotEmpty(thirdStatusList)) {
                VehicleDiscoveryJob.this.discoveryService.updateDiscoveryThirdMaster(thirdStatusList);
                VehicleDiscoveryJob.this.discoveryService.insertsDiscoveryThirdSlave(thirdStatusList);
            }


            for (VehicleRecord vehicleRecord : add1List) {
                vehicleRecord.setThirdId(UUIDUtils.getUUID());
                vehicleRecord.setDays(Integer.valueOf(PropertiesUtil.getVehicleDiscoveryDays()));
            }
            if (CollectionUtils.isNotEmpty(add1List)) {
                VehicleDiscoveryJob.this.discoveryService.insertsDiscovery(add1List);
                VehicleDiscoveryJob.this.discoveryService.insertsDiscoveryRecordSlave(add1List);
            }

            if (CollectionUtils.isNotEmpty(dList)) {
                VehicleDiscoveryJob.this.discoveryService.updateDiscoveryByFlag(dList);
            }


            VehicleDiscoveryJob.this.jedisUtil.del(new String[]{"REDIS_KEY_VEHICLE_DISCOVERY"});
        }

    }

}
