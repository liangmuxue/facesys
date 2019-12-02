package com.ss.isc.data.process.service;

import com.ss.isc.data.baseinfo.common.model.BaseEnums;
import com.ss.isc.data.baseinfo.common.model.MediaInfo;
import com.ss.isc.data.baseinfo.mapper.MediaInfoMapper;
import com.ss.isc.data.process.client.IPeopleProcessService;
import com.ss.isc.data.process.common.model.AddPersonRecog;
import com.ss.isc.data.process.common.model.PeopleProcess;
import com.ss.isc.data.process.common.model.Vehicle;
import com.ss.isc.data.process.common.web.PeopleProcessVO;
import com.ss.isc.data.process.mapper.AddPersonRecogMapper;
import com.ss.isc.data.process.mapper.PeopleProcessMapper;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CacheConstant;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.em.Enums;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.tools.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * PeopleProcessServiceImpl
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class PeopleProcessServiceImpl implements IPeopleProcessService {

    private Logger logger = LoggerFactory.getLogger(PeopleProcessServiceImpl.class);


    @Autowired
    private PeopleProcessMapper peopleProcessMapper;


    @Autowired
    private MediaInfoMapper mediaInfoMapper;


    @Autowired
    private AddPersonRecogMapper addPersonRecogMapper;


    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 人员处置
     * @param processVO
     * @return
     */
    @Override
    public String peopleProcess(PeopleProcessVO processVO) {
        boolean flag;
        PeopleProcess peopleProcess = new PeopleProcess();
        BeanUtils.copyProperties(processVO, peopleProcess);

        peopleProcess.setId(UUIDUtils.getUUID() + System.currentTimeMillis());
        peopleProcess.setProcessTime(new Date());
        peopleProcess.setCreateTime(new Date());
        peopleProcess.setUpdateTime(new Date());
        if (processVO.getLabelValue() != null) {
            peopleProcess.setLabel(StringUtils.arrToString(processVO.getLabelValue()));
        }
        flag = (this.peopleProcessMapper.insertSelective(peopleProcess) > 0);
        if (!flag) {
            return "failed";
        }
        // 非人口新增预警的处置需要处理附件信息
        if (processVO.getProcessType() > CommonConstant.PROCESSTYPE_ADDPERSON) {
            List<MediaInfo> list = processVO.getMediaInfos();
            if (list != null && list.size() > 0) {
                for (MediaInfo mediaInfo : list) {
                    mediaInfo.setRecordId(peopleProcess.getId());
                    mediaInfo.setOperateUserId(processVO.getProcessUserId());
                    mediaInfo.setCreateTime(new Date());
                    mediaInfo.setOperateTime(new Date());
                    mediaInfo.setCreateUserId(processVO.getProcessUserId());
                    flag = (this.mediaInfoMapper.insertSelective(mediaInfo) > 0);
                }
            }
        }
        String villageCode = processVO.getVillageCode();
        Integer processType = processVO.getProcessType();
        Date recordTime = processVO.getRecordTime();
        if (processType != null) {
            updatePoliceStatistics(villageCode, processType, recordTime);
        }
        return flag ? "success" : "failed";
    }

    /**
     * 查询预警处置记录（此处实际包含了人员预警和车辆预警）
     * @param peopleProcess
     * @return
     */
    @Override
    public PeopleProcess selectPeopleProcess(PeopleProcess peopleProcess) {
        PeopleProcess process = this.peopleProcessMapper.selectPeopleProcess(peopleProcess);
        if (process != null) {
            List<BaseEnums> labels = process.getLabels();
            if (StringUtils.isNotBlank(process.getLabel())) {
                String[] arr = process.getLabel().split(",");
                for (String s : arr) {
                    int code = Integer.parseInt(s);
                    labels.add(new BaseEnums(Enums.FrequencyLabel.getName(code), code));
                }
            }
        }
        return process;
    }

    /**
     * 更新redis处置相关数据
     * @param villageCode
     * @param processType
     * @param recordTime
     */
    private void updatePoliceStatistics(String villageCode, Integer processType, Date recordTime) {
        if (CommonConstant.PROCESSTYPE_ADDPERSON.equals(processType)) {
            this.logger.info("更新疑似新增处置统计");
            this.jedisUtil.del(CacheConstant.REDIS_KEY_PROCESSTYPE_ADDPERSON);
        } else if (CommonConstant.PROCESSTYPE_LEAVEPERSON.equals(processType)) {
            this.logger.info("更新疑似离开处置统计");
            this.jedisUtil.del(CacheConstant.REDIS_KEY_PROCESSTYPE_LEAVEPERSON);
        } else if (CommonConstant.PROCESSTYPE_FREQUENCY.equals(processType)) {
            this.logger.info("更新高频陌生人处置统计");
            this.jedisUtil.del(CacheConstant.REDIS_KEY_PROCESSTYPE_FREQUENCY);
        } else if (CommonConstant.PROCESSTYPE_OLDMAN.equals(processType)) {
            this.logger.info("更新疑似老人处置统计");
            this.jedisUtil.del(CacheConstant.REDIS_KEY_PROCESSTYPE_OLDMAN);
        } else if (CommonConstant.PROCESSTYPE_ALARM.equals(processType)) {
            this.logger.info("更新预警人员处置统计");
            this.jedisUtil.del(CacheConstant.REDIS_KEY_PROCESSTYPE_ALARM);
        } else if (CommonConstant.PROCESSTYPE_VEHICLE_DISCOVERY.equals(processType)) {
            this.logger.info("更新车辆感知发现处置统计");
            this.jedisUtil.del(CacheConstant.REDIS_KEY_VEHICLE_DISCOVERY);
        } else if (CommonConstant.PROCESSTYPE_VEHICLE_LEAVE.equals(processType)) {
            this.logger.info("更新车辆感知离开处置统计");
            this.jedisUtil.del(CacheConstant.REDIS_KEY_VEHICLE_LEAVE);
        } else if (CommonConstant.PROCESSTYPE_VEHICLE_RETENTION.equals(processType)) {
            this.logger.info("更新车辆滞留处置统计");
            this.jedisUtil.del(CacheConstant.REDIS_KEY_VEHICLE_RETENTION);
        }
    }

    /**
     * 车辆处置
     * @param dto
     * @return
     */
    @Override
    public boolean vehicleProcess(Vehicle dto) {
        dto.setProcessId(UUIDUtils.getUUID() + System.currentTimeMillis());
        boolean flag;
        flag = (this.peopleProcessMapper.addVehicleProcess(dto) > 0);
        if (!flag) {
            return false;
        }
        if (CommonConstant.PROCESSTYPE_VEHICLE_LEAVE.equals(dto.getProcessType()) || CommonConstant.PROCESSTYPE_VEHICLE_RETENTION.equals(dto.getProcessType())) {
            List<MediaInfo> list = dto.getMediaInfos();
            if (CollectionUtils.isNotEmpty(list)) {
                for (MediaInfo mediaInfo : list) {
                    mediaInfo.setRecordId(String.valueOf(dto.getHandleId()));
                    mediaInfo.setOperateUserId(dto.getProcessUserId());
                    mediaInfo.setCreateTime(new Date());
                    mediaInfo.setOperateTime(new Date());
                    mediaInfo.setCreateUserId(dto.getProcessUserId());
                    flag = (this.mediaInfoMapper.insertSelective(mediaInfo) > 0);
                }
            }
        }
        String villageCode = dto.getVillageCode();
        Integer processType = dto.getProcessType();
        Date recordTime = new Date();
        if (processType != null) {
            updatePoliceStatistics(villageCode, processType, recordTime);
        }
        return flag;
    }


    @Override
    public AddPersonRecog selectAddPersonRecogMapper(String recordId) {
        AddPersonRecog addPersonRecog = new AddPersonRecog();
        addPersonRecog.setRecordId(recordId);
        return (AddPersonRecog) this.addPersonRecogMapper.selectOne(addPersonRecog);
    }


    @Override
    public String saveRecogInfo(AddPersonRecog addPersonRecog) {
        int count = 0;

        AddPersonRecog ag = new AddPersonRecog();
        addPersonRecog.setRecordId(addPersonRecog.getRecordId());
        AddPersonRecog entity = (AddPersonRecog) this.addPersonRecogMapper.selectOne(ag);
        if (entity == null) {
            count = this.addPersonRecogMapper.insertSelective(addPersonRecog);
        } else {

            addPersonRecog.setId(entity.getId());
            count = this.addPersonRecogMapper.updateByPrimaryKey(addPersonRecog);
        }
        return (count > 0) ? "success" : "failed";
    }

}
