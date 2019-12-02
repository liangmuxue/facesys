package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.archives.client.IArchiveVehicleService;
import com.ss.isc.data.archives.client.IVehicleRetentionService;
import com.ss.isc.data.archives.common.model.VehicleRecord;
import com.ss.isc.data.archives.common.model.VehicleRetation;
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


public class VehicleRetationJob implements SimpleJob {

    private IVillageService villageService = SpringUtil.getBean(IVillageService.class);
    private IArchiveVehicleService archiveVehicleService = SpringUtil.getBean(IArchiveVehicleService.class);
    private IVehicleRetentionService vehicleRetentionService = SpringUtil.getBean(IVehicleRetentionService.class);
    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);


    public void execute(ShardingContext shardingContext) {
        Village dto = new Village();
        dto.setState(CommonConstant.COMMON_0);
        List<Village> vList = this.villageService.select(dto);
        for (Village village : vList) {


            VehicleRetationThread vehicleRetationThread = new VehicleRetationThread(village.getVillageCode());
            SysThreadPool.getThread().execute(vehicleRetationThread);
        }
    }


    public class VehicleRetationThread
            implements Runnable {

        public final Logger LOGGER;
        private String villageCode;

        public VehicleRetationThread(String villageCode) {
            this.LOGGER = LoggerFactory.getLogger(VehicleRetationThread.class);


            this.villageCode = villageCode;
        }


        public void run() {
            this.LOGGER.debug("=====================VehicleRetationThread Thread start=========================");
            Map<String, String> map = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            map.put("villageCode", this.villageCode);

            map.put("beginTime", CommonConstant.DATE_PATTERN.format(DateUtil.getBeginDayOfSomeday(PropertiesUtil.getVehicleRetationDays())));
            this.LOGGER.debug("=======================days===========================" + String.valueOf(PropertiesUtil.getVehicleRetationDays()));
            this.LOGGER.debug("=======================villageCode===========================" + this.villageCode);
            List<VehicleRetation> firstStatusList = new ArrayList<VehicleRetation>();


            List<VehicleRecord> rList = VehicleRetationJob.this.archiveVehicleService.getRetationList(map);
            List<VehicleRecord> addList = new ArrayList<VehicleRecord>();
            addList.addAll(rList);

            List<VehicleRetation> lList = VehicleRetationJob.this.archiveVehicleService.getRetation(new VehicleRetation(this.villageCode, CommonConstant.COMMON_1));


            for (int i = 0; i < rList.size(); i++) {
                for (int j = 0; j < lList.size(); j++) {
                    if (((VehicleRecord) rList.get(i)).getPlateNumber().equals(((VehicleRetation) lList.get(j)).getPlateNumber()) && CommonConstant.COMMON_0.equals(((VehicleRetation) lList.get(j)).getFlag())) {

                        firstStatusList.add(lList.get(j));


                        addList.remove(rList.get(i));
                    }
                }
            }


            for (VehicleRetation vehicleRetation : firstStatusList) {
                String detailId = VehicleRetationJob.this.vehicleRetentionService.getRetentionDetailId(vehicleRetation);
                vehicleRetation.setDetailId(detailId);
                vehicleRetation.setDayEnd(DateUtil.formatDateDefault(DateUtils.addDays(new Date(), CommonConstant.COMMON_NEGATIVE_1.intValue())));
                vehicleRetation.setDays(Integer.valueOf((int) DateUtil.getDaySub(DateUtil.formatDate(vehicleRetation.getInOutTime()), CommonConstant.DATE_PATTERN.format(new Date())) - 1));
            }

            if (CollectionUtils.isNotEmpty(firstStatusList)) {
                VehicleRetationJob.this.vehicleRetentionService.updateRetentionFirstMaster(firstStatusList);
                VehicleRetationJob.this.vehicleRetentionService.updateRetentionFirstSlave(firstStatusList);
            }


            for (VehicleRecord vehicleRecord : addList) {
                vehicleRecord.setThirdId(UUIDUtils.getUUID());

                vehicleRecord.setDayBegin(DateUtil.formatDateDefault(DateUtils.addDays(vehicleRecord.getInOutTime(), 1)));
                vehicleRecord.setDayEnd(DateUtil.formatDateDefault(DateUtils.addDays(new Date(), CommonConstant.COMMON_NEGATIVE_1.intValue())));
                vehicleRecord.setDays(Integer.valueOf((int) DateUtil.getDaySub(DateUtil.formatDate(vehicleRecord.getInOutTime()), CommonConstant.DATE_PATTERN.format(new Date())) - 1));
            }

            if (CollectionUtils.isNotEmpty(addList)) {
                VehicleRetationJob.this.vehicleRetentionService.insertsRetention(addList);
                VehicleRetationJob.this.vehicleRetentionService.insertsRetentionRecordSlave(addList);
            }


            VehicleRetationJob.this.jedisUtil.del(new String[]{"REDIS_KEY_VEHICLE_RETENTION"});
        }

    }

}
