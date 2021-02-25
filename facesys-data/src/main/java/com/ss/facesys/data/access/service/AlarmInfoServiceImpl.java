package com.ss.facesys.data.access.service;

import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.access.common.dto.AlarmInfo;
import com.ss.facesys.data.access.common.web.AlarmInfoVO;
import com.ss.facesys.data.access.mapper.AlarmInfoMapper;
import com.ss.facesys.data.collect.common.web.AlarmVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AlarmInfoServiceImpl {

    private Logger logger = LoggerFactory.getLogger(AlarmInfoServiceImpl.class);

    @Resource
    private AlarmInfoMapper alarmInfoMapper;

    public List<AlarmInfo> selectAlarmInfo(AlarmInfoVO para){
        Example example = new Example(AlarmInfo.class);
        example.createCriteria().andEqualTo("deleteFlag",1);
        PageHelper.startPage(para.getCurrentPage(),para.getPageSize());
        List<AlarmInfo> alarmInfos = alarmInfoMapper.selectByExample(example);
        return alarmInfos;
    }

    public String updateAlarmInfo(AlarmInfoVO para){
        AlarmInfo alarmInfo = new AlarmInfo();
        BeanUtils.copyProperties(para,alarmInfo);
        alarmInfoMapper.updateByPrimaryKeySelective(alarmInfo);
        return "SUCCESS";
    }
}
