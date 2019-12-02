package com.ss.facesys.data.process.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.baseinfo.common.model.MediaInfo;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.baseinfo.mapper.MediaInfoMapper;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.process.client.IVehicleProcessService;
import com.ss.facesys.data.process.common.dto.VehicleDTO;
import com.ss.facesys.data.process.common.model.*;
import com.ss.facesys.data.process.common.web.PeopleProcessVO;
import com.ss.facesys.data.process.common.web.VehicleVO;
import com.ss.facesys.data.process.mapper.PeopleProcessMapper;
import com.ss.facesys.data.process.mapper.VehicleDiscoveryDetailMapper;
import com.ss.facesys.data.process.mapper.VehicleProcessMapper;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.jedis.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * VehicleProcessServiceImpl
 *
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class VehicleProcessServiceImpl extends BaseServiceImpl implements IVehicleProcessService {

    private Logger logger = LoggerFactory.getLogger(VehicleProcessServiceImpl.class);

    @Autowired
    private VehicleProcessMapper vehicleMapper;
    @Autowired
    private MediaInfoMapper mediaInfoMapper;
    @Autowired
    private VehicleDiscoveryDetailMapper vehicleDiscoveryDetailMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private PeopleProcessMapper processInfoMapper;

    /**
     * 车辆感知发现分页列表
     *
     * @param vehicle
     * @return
     * @throws ParseException
     */
    @Override
    public List<VehicleDiscovery> discoveryList(VehicleVO vehicle) throws ParseException {
        PageHelper.startPage(vehicle.getCurrentPage(), vehicle.getPageSize());
        // 权限条件
        User user = new User();
        user.setUserId(vehicle.getUserIds());
        String sqlString = dataScopeFilter(user).replace("t1", "d");
        vehicle.setSqlString(sqlString);
        List<VehicleDiscovery> dList = this.vehicleMapper.discoveryList(vehicle);
        // 查询过车记录、总天数
        for (VehicleDiscovery vehicleDiscovery : dList) {
            VehicleDTO dayDTO = new VehicleDTO();
            dayDTO.setBeginTime(vehicleDiscovery.getDayBegin());
            dayDTO.setEndTime(CommonConstant.DATE_PATTERN.format(DateUtil.getEndDayOfSomeDay(CommonConstant.SHORT_DATE_PATTERN.parse(vehicleDiscovery.getDayEnd()))));
            dayDTO.setPlateNumber(vehicleDiscovery.getPlateNumber());
            dayDTO.setVillageCode(vehicleDiscovery.getVillageCode());
            int totalDays = discoveryDays(dayDTO);
            vehicleDiscovery.setTotalDays(totalDays);
            List<VehicleRecord> rList = this.vehicleMapper.recordList(dayDTO);
            vehicleDiscovery.setRecordList(rList);
        }
        return dList;
    }

    @Override
    public VehicleDiscovery discoveryDetail(VehicleDTO dto) {
        VehicleDiscovery vehicleDiscovery = this.vehicleMapper.discoveryDetail(dto);
        if (vehicleDiscovery != null) {

            if (StringUtils.isNotBlank(vehicleDiscovery.getCredentialType())) {
                vehicleDiscovery.setCredentialType(
                        Enums.credentialType.getName(Integer.parseInt(vehicleDiscovery.getCredentialType())));
            }
            if (StringUtils.isNotBlank(vehicleDiscovery.getPlateType())) {
                vehicleDiscovery.setPlateType(
                        Enums.plateType.getName(Integer.parseInt(vehicleDiscovery.getPlateType())));
            }
            if (StringUtils.isNotBlank(vehicleDiscovery.getPlateColor())) {
                vehicleDiscovery.setPlateColor(
                        Enums.plateColor.getName(Integer.parseInt(vehicleDiscovery.getPlateColor())));
            }
            if (StringUtils.isNotBlank(vehicleDiscovery.getCarType())) {
                vehicleDiscovery
                        .setCarType(Enums.carType.getName(Integer.parseInt(vehicleDiscovery.getCarType())));
            }

            if (StringUtils.isNotBlank(vehicleDiscovery.getPlatePic()) && !vehicleDiscovery.getPlatePic()
                    .contains("http")) {
                vehicleDiscovery
                        .setPlatePic(FilePropertiesUtil.getHttpUrl() + vehicleDiscovery.getPlatePic());
            }
        }
        return vehicleDiscovery;
    }


    @Override
    public int discoveryDays(VehicleDTO dayDTO) {
        return this.vehicleMapper.discoveryDays(dayDTO);
    }


    @Override
    public List<VehicleRecord> lastInOutTime(VehicleDTO dto) {
        return this.vehicleMapper.lastInOutTime(dto);
    }

    /**
     * 感知发现处置
     *
     * @param dto
     */
    @Override
    public void discoveryHandle(Vehicle dto) {
        if (CommonConstant.COMMON_2.equals(dto.getStatus())) {
            Vehicle entity = this.vehicleMapper.existVehicle(dto);
            this.vehicleMapper.insertVehicle(dto);
        }
        this.vehicleMapper.updateDiscovery(dto);
        VehicleDiscoveryDetail dtoDetail = new VehicleDiscoveryDetail();
        dtoDetail.setDiscoveryId(dto.getHandleId());
        VehicleDiscoveryDetail discoveryDetail = this.vehicleDiscoveryDetailMapper.selectDetail(dtoDetail);
        if (null != discoveryDetail) {
            try {
                discoveryDetail.setDayEnd(CommonConstant.DATE_PATTERN.format(DateUtil.getEndDayOfSomeDay(CommonConstant.SHORT_DATE_PATTERN.parse(discoveryDetail.getDayEnd()))));
            } catch (ParseException e) {
                this.logger.error(e.toString(), e);
            }
            discoveryDetail.setWithState(NumberConstant.ONE);
            this.vehicleDiscoveryDetailMapper.updateVehicleRecord(discoveryDetail);
        }
    }

    /**
     * 感知离开处置
     *
     * @param dto
     */
    @Override
    public void leaveHandle(Vehicle dto) {
        this.vehicleMapper.updateLeave(dto);
        if (CommonConstant.COMMON_2.equals(dto.getStatus())) {
            dto.setIsLeave(CommonConstant.COMMON_1);
            this.vehicleMapper.updateVehicle(dto);
        }
        VehicleDiscoveryDetail dtoDetail = new VehicleDiscoveryDetail();
        dtoDetail.setLeaveId(dto.getHandleId());
        VehicleDiscoveryDetail recordDTO = this.vehicleDiscoveryDetailMapper.selectVehicleLeaveOne(dtoDetail);
        if (recordDTO != null) {
            recordDTO.setInOutTime(dto.getInOutTime());
            recordDTO.setLeaveState(NumberConstant.ONE);
            this.vehicleDiscoveryDetailMapper.updateVehicleRecord(recordDTO);
        }
    }

    /**
     * 车辆滞留处置
     *
     * @param dto
     */
    @Override
    public void retationHandle(Vehicle dto) {
        this.vehicleMapper.updateRetation(dto);
        VehicleDiscoveryDetail dtoDetail = new VehicleDiscoveryDetail();
        dtoDetail.setRetenId(dto.getHandleId());
        VehicleDiscoveryDetail recordDTO = this.vehicleDiscoveryDetailMapper.selectVehicleRetenOne(dtoDetail);
        if (recordDTO != null) {
            recordDTO.setInOutTime(dto.getInOutTime());
            recordDTO.setRetenState(NumberConstant.ONE);
            this.vehicleDiscoveryDetailMapper.updateVehicleRecord(recordDTO);
        }
    }

    /**
     * 车辆感知离开分页列表
     *
     * @param vehicle
     * @return
     */
    @Override
    public List<VehicleLeave> leavePage(VehicleVO vehicle) {
        PageHelper.startPage(vehicle.getCurrentPage(), vehicle.getPageSize());
        // 权限条件
        User user = new User();
        user.setUserId(vehicle.getUserIds());
        String sqlString = dataScopeFilter(user).replace("t1", "t4");
        vehicle.setSqlString(sqlString);
        List<VehicleLeave> lList = this.vehicleMapper.leaveList(vehicle);
        // 查询附件信息
        for (VehicleLeave vehicleLeave : lList) {
            List<MediaInfo> list = this.mediaInfoMapper.select(new MediaInfo(vehicleLeave.getId(), CommonConstant.COMMON_1));
            for (MediaInfo mInfo : list) {
                mInfo.setDateAttachmentUrl(FilePropertiesUtil.getHttpUrl() + mInfo.getDateAttachmentPath());
            }
            vehicleLeave.setmList(list);
        }
        return lList;
    }

    /**
     * 车辆滞留分页
     *
     * @param vehicle
     * @return
     */
    @Override
    public List<VehicleRetation> retationPage(VehicleVO vehicle) {
        PageHelper.startPage(vehicle.getCurrentPage(), vehicle.getPageSize());
        // 权限条件
        User user = new User();
        user.setUserId(vehicle.getUserIds());
        String sqlString = dataScopeFilter(user).replace("t1", "t4");
        vehicle.setSqlString(sqlString);
        Page page = this.vehicleMapper.retationList(vehicle);
        // 查询附件信息
        for (VehicleRetation vehicleRetation : (Page<VehicleRetation>) page) {
            List<MediaInfo> list = this.mediaInfoMapper.select(new MediaInfo(vehicleRetation.getId(), CommonConstant.COMMON_1));
            for (MediaInfo mInfo : list) {
                mInfo.setDateAttachmentUrl(FilePropertiesUtil.getHttpUrl() + mInfo.getDateAttachmentPath());
            }
            vehicleRetation.setmList(list);
        }
        return page;
    }

    /**
     * 车辆预警一键处置有效
     *
     * @param processVO
     * @return
     */
    @Override
    public String handleUntreated(PeopleProcessVO processVO) {
        processInfoMapper.insertUntreatedVehicle(processVO);
        vehicleMapper.updateUntreatedDiscoveryAsValid();
        vehicleMapper.updateUntreatedLeaveAsValid();
        vehicleMapper.updateUntreatedRetentionAsValid();
        this.logger.info("更新车辆预警处置统计");
        this.jedisUtil.del(CacheConstant.REDIS_KEY_VEHICLE_DISCOVERY);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_VEHICLE_LEAVE);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_VEHICLE_RETENTION);
        return "车辆预警一键处置成功";
    }

}
