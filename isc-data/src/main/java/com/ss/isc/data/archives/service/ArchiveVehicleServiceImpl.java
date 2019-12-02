package com.ss.isc.data.archives.service;

import com.ss.isc.data.archives.client.IArchiveVehicleService;
import com.ss.isc.data.archives.common.dto.VehicleDTO;
import com.ss.isc.data.archives.common.model.VehicleDiscovery;
import com.ss.isc.data.archives.common.model.VehicleLeave;
import com.ss.isc.data.archives.common.model.VehicleRecord;
import com.ss.isc.data.archives.common.model.VehicleRetation;
import com.ss.isc.data.archives.mapper.ArchivesVehicleMapper;
import com.ss.isc.data.archives.mapper.ArchivesVehicleRecordMapper;
import com.ss.isc.data.baseinfo.common.model.User;
import com.ss.isc.data.baseinfo.service.BaseServiceImpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class ArchiveVehicleServiceImpl extends BaseServiceImpl implements IArchiveVehicleService {
    @Autowired
    private ArchivesVehicleRecordMapper vehicleRecordMapper;
    @Autowired
    private ArchivesVehicleMapper vehicleMapper;

    public List<VehicleRecord> unregisterList(Map<String, String> map) {
        return this.vehicleRecordMapper.unregisterList(map);
    }

    public List<VehicleRecord> unregisterDays(Map<String, String> map) {
        return this.vehicleRecordMapper.unregisterDays(map);
    }

    public void addOneDay() {
        this.vehicleRecordMapper.addOneDay();
    }

    /**
     * 车辆感知出现查询
     * @param dto
     * @return
     */
    @Override
    public List<VehicleDiscovery> getDiscovery(VehicleDiscovery dto) {
        if (StringUtils.isNotBlank(dto.getUserIds())) {
            User user = new User();
            user.setUserId(dto.getUserIds());
            String sqlString = dataScopeFilter(user);
            dto.setSqlString(sqlString);
        }
        //查询并返回车辆信息
        return this.vehicleMapper.getDiscovery(dto);
    }

    /**
     * 感知离开查询
     * @param dto
     * @return
     */
    @Override
    public List<VehicleLeave> getLeave(VehicleLeave dto) {
        if (StringUtils.isNotBlank(dto.getUserIds())) {
            User user = new User();
            user.setUserId(dto.getUserIds());
            String sqlString = dataScopeFilter(user);
            dto.setSqlString(sqlString);
        }
        //查询并返回车辆信息
        return this.vehicleMapper.getLeave(dto);
    }

    /**
     * 车辆滞留查询
     * @param dto
     * @return
     */
    @Override
    public List<VehicleRetation> getRetation(VehicleRetation dto) {
        if (StringUtils.isNotBlank(dto.getUserIds())) {
            User user = new User();
            user.setUserId(dto.getUserIds());
            String sqlString = dataScopeFilter(user);
            dto.setSqlString(sqlString);
        }
        //查询并返回车辆信息
        return this.vehicleMapper.getRetation(dto);
    }

    public List<VehicleRecord> getLeaveList(Map<String, String> map) {
        return this.vehicleRecordMapper.getLeaveList(map);
    }

    public void leaveAddOneDay() {
        this.vehicleRecordMapper.leaveAddOneDay();
    }

    public void insertsLeave(List<VehicleRecord> insertList) {
        this.vehicleRecordMapper.insertsLeave(insertList);
    }

    public List<VehicleRecord> getRetationList(Map<String, String> map) {
        return this.vehicleRecordMapper.getRetationList(map);
    }

    public void retationAddOneDay() {
        this.vehicleRecordMapper.retationAddOneDay();
    }

    public void insertsRetation(List<VehicleRecord> insertList) {
        this.vehicleRecordMapper.insertsRetation(insertList);
    }

    public VehicleDiscovery discoveryExist(VehicleRecord vehicleRecord) {
        return this.vehicleRecordMapper.discoveryExist(vehicleRecord);
    }

    public void updateLeaveFirstMaster(List<VehicleLeave> firstStatusList) {
        this.vehicleRecordMapper.updateLeaveFirstMaster(firstStatusList);
    }

    public void updateLeaveFirstSlave(List<VehicleLeave> firstStatusList) {
        this.vehicleRecordMapper.updateLeaveFirstSlave(firstStatusList);
    }

    public void updateLeaveThirdMaster(List<VehicleLeave> thirdStatusList) {
        this.vehicleRecordMapper.updateLeaveThirdMaster(thirdStatusList);
    }

    public void insertsLeaveThirdSlave(List<VehicleLeave> thirdStatusList) {
        this.vehicleRecordMapper.insertsLeaveThirdSlave(thirdStatusList);
    }

    public void insertsLeaveRecordSlave(List<VehicleRecord> rList) {
        this.vehicleRecordMapper.insertsLeaveRecordSlave(rList);
    }

    public void updateLeaveMaster(List<VehicleRecord> lList) {
        this.vehicleRecordMapper.updateLeaveMaster(lList);
    }

    public String getLeaveDetailId(VehicleLeave vehicleLeave) {
        return this.vehicleRecordMapper.getLeaveDetailId(vehicleLeave);
    }

    public List<VehicleRecord> recordYesToday(VehicleDTO dto) {
        return this.vehicleRecordMapper.recordYesToday(dto);
    }

    public VehicleRecord getVehicleRecord(VehicleDTO dto) {
        return this.vehicleRecordMapper.getVehicleRecord(dto);
    }

    public void addRecordList(List<VehicleRecord> recordList) {
        this.vehicleRecordMapper.insertList(recordList);
    }
}
