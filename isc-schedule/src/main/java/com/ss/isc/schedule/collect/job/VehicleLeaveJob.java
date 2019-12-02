package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.archives.client.IArchiveVehicleService;
import com.ss.isc.data.archives.common.dto.VehicleDTO;
import com.ss.isc.data.archives.common.model.VehicleLeave;
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


public class VehicleLeaveJob implements SimpleJob {

    private IVillageService villageService = SpringUtil.getBean(IVillageService.class);
    private IArchiveVehicleService archiveVehicleService = SpringUtil.getBean(IArchiveVehicleService.class);
    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);


    public void execute(ShardingContext shardingContext) {
        Village dto = new Village();
        dto.setState(CommonConstant.COMMON_0);
        List<Village> vList = this.villageService.select(dto);
        for (Village village : vList) {


            VehicleLeaveThread vehicleLeaveThread = new VehicleLeaveThread(village.getVillageCode());
            SysThreadPool.getThread().execute(vehicleLeaveThread);
        }
    }


    public class VehicleLeaveThread
            implements Runnable {

        public final Logger LOGGER;


        private String villageCode;


        public VehicleLeaveThread(String villageCode) {
            this.LOGGER = LoggerFactory.getLogger(VehicleLeaveThread.class);


            this.villageCode = villageCode;
        }


        public void run() {
            this.LOGGER.debug("=====================VehicleLeaveThread Thread start=========================");
            Map<String, String> map = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            map.put("villageCode", this.villageCode);

            map.put("beginTime", CommonConstant.DATE_PATTERN.format(DateUtil.getBeginDayOfSomeday(PropertiesUtil.getVehicleLeaveDays())));
            this.LOGGER.debug("=======================days===========================" + String.valueOf(PropertiesUtil.getVehicleLeaveDays()));
            this.LOGGER.debug("=======================villageCode===========================" + this.villageCode);
            List<VehicleLeave> firstStatusList = new ArrayList<VehicleLeave>();


            List<VehicleRecord> rList = VehicleLeaveJob.this.archiveVehicleService.getLeaveList(map);
            List<VehicleRecord> addList = new ArrayList<VehicleRecord>();
            addList.addAll(rList);

            List<VehicleLeave> lList = VehicleLeaveJob.this.archiveVehicleService.getLeave(new VehicleLeave(this.villageCode, CommonConstant.COMMON_1));


            for (int i = 0; i < rList.size(); i++) {
                for (int j = 0; j < lList.size(); j++) {
                    if (((VehicleRecord) rList.get(i)).getPlateNumber().equals(((VehicleLeave) lList.get(j)).getPlateNumber())) {

                        firstStatusList.add(lList.get(j));


                        addList.remove(rList.get(i));
                    }
                }
            }


            for (VehicleLeave vehicleLeave : firstStatusList) {
                String detailId = VehicleLeaveJob.this.archiveVehicleService.getLeaveDetailId(vehicleLeave);
                vehicleLeave.setDetailId(detailId);
                vehicleLeave.setDayEnd(DateUtil.formatDateDefault(DateUtils.addDays(new Date(), CommonConstant.COMMON_NEGATIVE_1.intValue())));
                vehicleLeave.setDays(Integer.valueOf((int) DateUtil.getDaySub(DateUtil.formatDate(vehicleLeave.getInOutTime()), CommonConstant.DATE_PATTERN.format(new Date())) - 1));
            }

            if (CollectionUtils.isNotEmpty(firstStatusList)) {
                VehicleLeaveJob.this.archiveVehicleService.updateLeaveFirstMaster(firstStatusList);
                VehicleLeaveJob.this.archiveVehicleService.updateLeaveFirstSlave(firstStatusList);
            }


            for (VehicleRecord vehicleRecord : addList) {
                vehicleRecord.setThirdId(UUIDUtils.getUUID());

                vehicleRecord.setDayBegin(DateUtil.formatDateDefault(DateUtils.addDays(vehicleRecord.getInOutTime(), 1)));
                vehicleRecord.setDayEnd(DateUtil.formatDateDefault(DateUtils.addDays(new Date(), CommonConstant.COMMON_NEGATIVE_1.intValue())));
                vehicleRecord.setDays(Integer.valueOf((int) DateUtil.getDaySub(DateUtil.formatDate(vehicleRecord.getInOutTime()), CommonConstant.DATE_PATTERN.format(new Date())) - 1));
            }
            if (CollectionUtils.isNotEmpty(addList)) {
                VehicleLeaveJob.this.archiveVehicleService.insertsLeave(addList);
                VehicleLeaveJob.this.archiveVehicleService.insertsLeaveRecordSlave(addList);
            }


            VehicleDTO dto = new VehicleDTO();
            dto.setInType(DateUtil.getBeginDayOfYesterday());
            dto.setOutType(DateUtil.getEndDayOfYesterDay());
            dto.setVillageCode(this.villageCode);
            List<VehicleRecord> plateNumberList = VehicleLeaveJob.this.archiveVehicleService.recordYesToday(dto);

            List<VehicleRecord> lastList = new ArrayList<VehicleRecord>();
            for (VehicleRecord vehicleRecord : plateNumberList) {
                dto.setPlateNumber(vehicleRecord.getPlateNumber());
                VehicleRecord record = VehicleLeaveJob.this.archiveVehicleService.getVehicleRecord(dto);
                lastList.add(record);
            }

            if (CollectionUtils.isNotEmpty(lastList)) {
                VehicleLeaveJob.this.archiveVehicleService.updateLeaveMaster(lastList);
            }


            VehicleLeaveJob.this.jedisUtil.del(new String[]{"REDIS_KEY_VEHICLE_LEAVE"});
        }

    }

}
