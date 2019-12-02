package com.ss.isc.data.collect.service;

import com.ss.isc.data.archives.common.model.Vehicle;
import com.ss.isc.data.archives.mapper.ArchivesVehicleMapper;
import com.ss.isc.data.collect.client.IVehicleService;
import com.ss.isc.data.collect.common.dto.CaptureSumDTO;
import com.ss.isc.data.collect.common.dto.VehicleDTO;
import com.ss.isc.data.collect.common.dto.VehicleStatisticsDTO;
import com.ss.isc.data.collect.common.model.Camera;
import com.ss.isc.data.collect.common.model.VehicleRecord;
import com.ss.isc.data.collect.common.web.VehicleQueryVO;
import com.ss.isc.data.collect.common.web.VehicleVO;
import com.ss.isc.data.collect.mapper.VehicleMapper;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CacheConstant;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.constant.NumberConstant;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.util.nasstorage.config.properties.NasstorageProperties;
import com.ss.util.nasstorage.file.NasstorageFile;
import com.ss.util.nasstorage.util.NasstorageUtil;
import com.github.pagehelper.PageHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * VehicleServiceImpl
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class VehicleServiceImpl implements IVehicleService {

    @Resource
    private IOrganizationRegionService organizationRegionService;
    @Autowired
    private VehicleMapper vehicleMapper;
    @Resource
    public JedisUtil jedisUtil;
    @Autowired
    private NasstorageProperties nasstorageProperties;

    @Override
    public List<VehicleDTO> findList(VehicleVO vehicleVO) {
        return this.vehicleMapper.findList(vehicleVO);
    }


    @Override
    public List<CaptureSumDTO> findSixCount() {
        return this.vehicleMapper.findSixCount();
    }


    @Override
    public List<CaptureSumDTO> findVehicleList() {
        return this.vehicleMapper.findVehicleList();
    }


    @Override
    public Map<String, Integer> findVehicleStatistics(String villageCode) {
        Map<String, Integer> map = new HashMap<String, Integer>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

        int registeredVehicle = 0;

        int perceptionVehicle = 0;

        int perceptualLeave = 0;

        int perceptualDiscovery = 0;


        Map<String, VehicleStatisticsDTO> vsMap = (Map) this.jedisUtil.get("VEHICLE_TODAY_STATISTICS");
        if (vsMap == null) {
            vsMap = new HashMap<String, VehicleStatisticsDTO>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
        }

        Date beginTime = DateUtil.getDayBegin();

        Integer enterNumber = this.vehicleMapper.findVehicleStatistics(villageCode, beginTime, NumberConstant.ONE);
        Integer outNumber = this.vehicleMapper.findVehicleStatistics(villageCode, beginTime, NumberConstant.TWO);
        vsMap.put(villageCode, new VehicleStatisticsDTO(enterNumber, outNumber));

        this.jedisUtil.set("VEHICLE_TODAY_STATISTICS", vsMap);

        registeredVehicle = this.vehicleMapper.findRegisterVehicle(villageCode);
        perceptualLeave = this.vehicleMapper.findPerceptualLeave(villageCode).intValue();
        perceptualDiscovery = this.vehicleMapper.findPerceptualDiscovery(villageCode).intValue();
        perceptionVehicle = perceptualDiscovery + perceptualLeave;

        map.put("registeredVehicle", Integer.valueOf(registeredVehicle));
        map.put("perceptionVehicle", Integer.valueOf(perceptionVehicle));
        map.put("perceptualLeave", Integer.valueOf(perceptualLeave));
        map.put("perceptualDiscovery", Integer.valueOf(perceptualDiscovery));
        map.put("enterVehicle", enterNumber);
        map.put("outVehicle", outNumber);

        return map;
    }


    @Override
    public VehicleStatisticsDTO findTodayRecordStatistics(String villageCode, boolean enterAdd, boolean outAdd) {
        VehicleStatisticsDTO vsDto = new VehicleStatisticsDTO();

        Map<String, VehicleStatisticsDTO> vsMap = (Map) this.jedisUtil.get("VEHICLE_TODAY_STATISTICS");
        if (vsMap == null) {
            vsMap = new HashMap<String, VehicleStatisticsDTO>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
        }
        if (vsMap.containsKey(villageCode)) {
            VehicleStatisticsDTO vs = (VehicleStatisticsDTO) vsMap.get(villageCode);
            if (enterAdd) {
                vsDto.setEnterNumber(Integer.valueOf(vs.getEnterNumber().intValue() + 1));
            } else {

                vsDto.setEnterNumber(vs.getEnterNumber());
            }
            if (outAdd) {
                vsDto.setOutNumber(Integer.valueOf(vs.getOutNumber().intValue() + 1));
            } else {

                vsDto.setOutNumber(vs.getOutNumber());
            }

        } else {

            if (enterAdd) {
                vsDto.setEnterNumber(NumberConstant.ONE);
            } else {

                vsDto.setEnterNumber(NumberConstant.ZERO);
            }
            if (outAdd) {
                vsDto.setOutNumber(NumberConstant.ONE);
            } else {

                vsDto.setOutNumber(NumberConstant.ZERO);
            }
        }

        vsMap.put(villageCode, vsDto);
        this.jedisUtil.set("VEHICLE_TODAY_STATISTICS", vsMap);
        return vsDto;
    }

    /**
     * 查询过车记录列表
     * @param queryVO
     * @return
     */
    @Override
    public List<VehicleDTO> findCarList(VehicleQueryVO queryVO) {
        PageHelper.startPage(queryVO.getCurrentPage(), queryVO.getPageSize());
        return this.vehicleMapper.findCarList(queryVO);
    }


    @Override
    public List<CaptureSumDTO> getTenCountCar(Map map6) {
        return this.vehicleMapper.getTenCountCar(map6);
    }

    /**
     * 查询小区名称
     * @param villageCode
     * @return
     */
    @Override
    public String selectVillageName(String villageCode) {
        return this.vehicleMapper.selectVillageName(villageCode);
    }

    /**
     * 新增过车信息
     * @param list
     * @return
     */
    @Override
    public String batchInsertRecord(List<VehicleRecord> list) {
        int count = this.vehicleMapper.batchInsertRecord(list);
        return (count > 0) ? "success" : "failed";
    }


    @Override
    public VehicleRecord saveVehicleRecordImage(VehicleRecord vehicleRecord) {
        NasstorageUtil nasstorageUtil = new NasstorageUtil();
        NasstorageFile nasstorageFile = null;

        nasstorageFile = nasstorageUtil.nasstorageVehicle(this.nasstorageProperties, NumberConstant.ONE, vehicleRecord
                        .getTollgateID(), vehicleRecord.getPlateNoPicImg(), null,
                Long.valueOf(vehicleRecord.getInOutTime().getTime()));
        vehicleRecord.setPlateNoPicUrl(FilePropertiesUtil.getNginxImgUrl() + nasstorageFile.getStorageRelativePath());

        if (StringUtils.isNotBlank(vehicleRecord.getPlatePicImg())) {
            nasstorageFile = nasstorageUtil.nasstorageVehicle(this.nasstorageProperties, NumberConstant.TWO, vehicleRecord
                            .getTollgateID(), vehicleRecord.getPlatePicImg(), null,
                    Long.valueOf(vehicleRecord.getInOutTime().getTime()));
            vehicleRecord.setPlatePicUrl(FilePropertiesUtil.getNginxImgUrl() + nasstorageFile.getStorageRelativePath());
        }
        return vehicleRecord;
    }

}
