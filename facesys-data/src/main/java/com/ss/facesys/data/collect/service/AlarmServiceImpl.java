package com.ss.facesys.data.collect.service;

import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.baseinfo.common.model.MediaInfo;
import com.ss.facesys.data.baseinfo.mapper.MediaInfoMapper;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IAlarmService;
import com.ss.facesys.data.collect.common.model.AlarmRecord;
import com.ss.facesys.data.collect.common.web.AlarmRecordVO;
import com.ss.facesys.data.collect.common.web.AlarmVO;
import com.ss.facesys.data.collect.mapper.AlarmMapper;
import com.ss.facesys.data.process.common.model.PeopleProcess;
import com.ss.facesys.data.process.common.web.PeopleProcessVO;
import com.ss.facesys.data.process.mapper.PeopleProcessMapper;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.tools.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AlarmServiceImpl
 *
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class AlarmServiceImpl extends BaseServiceImpl implements IAlarmService {

    @Autowired
    private AlarmMapper alarmMapper;
    @Autowired
    private PeopleProcessMapper peopleProcessMapper;
    @Autowired
    private MediaInfoMapper mediaInfoMapper;
    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 查询预警分页列表
     *
     * @param alarm
     * @return
     */
    @Override
    public List<AlarmRecordVO> findListByPage(AlarmVO alarm) {
        PageHelper.startPage(alarm.getCurrentPage(), alarm.getPageSize());
        return this.alarmMapper.findList(alarm);
    }


    @Override
    public List<AlarmRecordVO> findList(AlarmVO alarm) {
        return this.alarmMapper.findList(alarm);
    }


    @Override
    public List<AlarmRecord> queryAnalysisData(Integer cameraType, Integer deviceType, int monitorType, Date begin, Date end) {
        Map<String, Object> parm = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
        parm.put("cameraType", cameraType);
        parm.put("deviceType", deviceType);
        parm.put("monitorType", Integer.valueOf(monitorType));
        parm.put("begin", begin);
        parm.put("end", end);

        return this.alarmMapper.queryAnalysisData(parm);
    }


    @Override
    public AlarmRecord getByCaptureId(String captureId) {
        return this.alarmMapper.getByCaptureId(captureId);
    }

    /**
     * 预警人员处置
     *
     * @param id
     * @param state
     * @return
     */
    @Override
    public boolean alarmProcess(String id, Integer state) {
        return (this.alarmMapper.alarmProcess(id, state) > 0);
    }

    /**
     * 查询预警未处理条数
     *
     * @param alarm
     * @return
     */
    @Override
    public List<Integer> getUntreatedCount(AlarmVO alarm) {
        return alarmMapper.getUntreatedCount(alarm);
    }

    /**
     * 人口预警一键处置
     *
     * @param para
     * @return
     */
    @Override
    public String warningProcessBatch(PeopleProcessVO para) {
        Integer state = para.getState();
        for (String recordId : para.getRecordIds().split(CommonConstant.SPLIT_COMMA)) {
            // 更新预警处理状态
            if (this.alarmMapper.alarmProcess(recordId, state) <= 0) {
                return "一键处置失败：更新预警记录状态时发生错误";
            }
            // 新增处置信息记录
            PeopleProcess peopleProcess = new PeopleProcess();
            BeanUtils.copyProperties(para, peopleProcess);
            peopleProcess.setId(UUIDUtils.getUUID() + System.currentTimeMillis());
            peopleProcess.setProcessTime(new Date());
            peopleProcess.setCreateTime(new Date());
            peopleProcess.setUpdateTime(new Date());
            if (this.peopleProcessMapper.insertSelective(peopleProcess) <= 0) {
                return "一键处置失败：新增处置信息时发生错误";
            }
            // 处理附件信息
            List<MediaInfo> list = para.getMediaInfos();
            if (list != null && list.size() > 0) {
                for (MediaInfo mediaInfo : list) {
                    mediaInfo.setRecordId(peopleProcess.getId());
                    mediaInfo.setOperateUserId(para.getProcessUserId());
                    mediaInfo.setCreateTime(new Date());
                    mediaInfo.setOperateTime(new Date());
                    mediaInfo.setCreateUserId(para.getProcessUserId());
                    if (this.mediaInfoMapper.insertSelective(mediaInfo) <= 0) {
                        return "一键处置失败：新增处置附件信息时发生错误";
                    }
                }
            }
            // 重置redis记录
            this.jedisUtil.del(CacheConstant.REDIS_KEY_PROCESSTYPE_ALARM);
        }
        return "一键处置成功";
    }

    /**
     * 人口预警一键处置有效
     *
     * @param processVO
     * @return
     */
    @Override
    public String handleUntreated(PeopleProcessVO processVO) {
        peopleProcessMapper.insertUntreatedPeople(processVO);
        alarmMapper.updateUntreatedAsValid();
        this.jedisUtil.del(CacheConstant.REDIS_KEY_PROCESSTYPE_ALARM);
        return "人口预警一键处置成功";
    }

}
