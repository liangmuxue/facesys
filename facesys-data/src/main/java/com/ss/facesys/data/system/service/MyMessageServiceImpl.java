package com.ss.facesys.data.system.service;

import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.access.common.dto.MonitorTask;
import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.data.baseinfo.mapper.EnumMapper;
import com.ss.facesys.data.system.client.IMyMessageService;
import com.ss.facesys.data.system.common.dto.MyMessageQuery;
import com.ss.facesys.data.system.common.model.AlarmMessage;
import com.ss.facesys.data.system.common.model.SystemMessage;
import com.ss.facesys.data.system.mapper.AlarmMessageServiceMapper;
import com.ss.facesys.data.system.mapper.MonitorTaskMapper;
import com.ss.facesys.data.system.mapper.SystemMessageServiceMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyMessageServiceImpl implements IMyMessageService {

    @Resource
    private SystemMessageServiceMapper systemMessageServiceMapper;
    @Resource
    private AlarmMessageServiceMapper alarmMessageServiceMapper;
    @Resource
    private MonitorTaskMapper monitorTaskMapper;
    @Resource
    private EnumMapper enumMapper;

    @Override
    public List<SystemMessage> systemPage(MyMessageQuery myMessageQuery) {
        Example example = new Example(SystemMessage.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(myMessageQuery.getKeyword())) {
            criteria.andEqualTo("content", myMessageQuery.getKeyword());
        }
        if (myMessageQuery.getStartTime()!=null && !"".equals(myMessageQuery.getStartTime()) &&
                myMessageQuery.getEndTime()!=null && !"".equals(myMessageQuery.getEndTime())) {
            criteria.andBetween("createTime", myMessageQuery.getStartTime(),myMessageQuery.getEndTime());
        }
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(myMessageQuery.getCurrentPage(), myMessageQuery.getPageSize());
        List<SystemMessage> myMessages = this.systemMessageServiceMapper.selectByExample(example);
        return myMessages;
    }

    @Override
    public List<AlarmMessage> alarmPage(MyMessageQuery myMessageQuery) {
        Example example = new Example(AlarmMessage.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(myMessageQuery.getKeyword())) {
            criteria.andEqualTo("content", myMessageQuery.getKeyword());
        }
        if (myMessageQuery.getAlarmType() != null && !"".equals(myMessageQuery.getAlarmType())) {
            criteria.andEqualTo("alarmType", myMessageQuery.getKeyword());
        }
        if (myMessageQuery.getAlarmGrade() != null && !"".equals(myMessageQuery.getAlarmGrade())) {
            criteria.andEqualTo("alarmGrade", myMessageQuery.getKeyword());
        }
        if (myMessageQuery.getStartTime()!=null && !"".equals(myMessageQuery.getStartTime()) &&
                myMessageQuery.getEndTime()!=null && !"".equals(myMessageQuery.getEndTime())) {
            criteria.andBetween("createTime", myMessageQuery.getStartTime(),myMessageQuery.getEndTime());
        }
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(myMessageQuery.getCurrentPage(), myMessageQuery.getPageSize());
        List<AlarmMessage> myMessages = this.alarmMessageServiceMapper.selectByExample(example);
        if(myMessages.isEmpty()){
            return new ArrayList<>();
        }
        for(AlarmMessage a :myMessages){
            Example example1 = new Example(MonitorTask.class);
            example1.createCriteria().andEqualTo("id", a.getMonitorId());
            List<MonitorTask> monitorTasks = this.monitorTaskMapper.selectByExample(example1);
            if(!monitorTasks.isEmpty()){
                a.setMonitorName(monitorTasks.get(0).getMonitorName());
            }
            Example example2 = new Example(BaseEnums.class);
            example2.createCriteria().andEqualTo("enumType", "alarm_type").andEqualTo("enumValue", a.getAlarmType());
            List<BaseEnums> baseEnums = this.enumMapper.selectByExample(example2);
            if(!baseEnums.isEmpty()){
                a.setAlarmTypeName(baseEnums.get(0).getEnumName());
            }
            Example example3 = new Example(BaseEnums.class);
            example3.createCriteria().andEqualTo("enumType", "alarm_grade").andEqualTo("enumValue", a.getAlarmGrade());
            List<BaseEnums> baseEnums1 = this.enumMapper.selectByExample(example3);
            if(!baseEnums1.isEmpty()){
                a.setAlarmGradeName(baseEnums.get(0).getEnumName());
            }
        }
        return myMessages;
    }

    /**
     * 删除系统消息
     * @param id
     * @return
     */
    @Override
    public int delSystemMes(Integer id) {
        Example example = new Example(SystemMessage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        return this.systemMessageServiceMapper.deleteByExample(example);
    }

    /**
     * 删除报警消息
     * @param id
     * @return
     */
    @Override
    public int delAlarmMes(Integer id) {
        Example example = new Example(AlarmMessage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        return this.alarmMessageServiceMapper.deleteByExample(example);
    }
}