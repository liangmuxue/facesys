package com.ss.facesys.data.access.service;

import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.common.dto.MonUserRef;
import com.ss.facesys.data.access.common.dto.MonitorTask;
import com.ss.facesys.data.access.common.web.MonVO;
import com.ss.facesys.data.access.mapper.MonMapper;
import com.ss.facesys.data.access.mapper.MonUserRefMapper;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.data.collect.common.model.FacedbFace;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.MonitorModeEnum;
import com.ss.facesys.util.em.MonitorStateEnum;
import com.ss.tools.FileUtils;
import com.ss.tools.IDUtils;
import com.ss.tools.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MonServiceImpl
 * @author zhangao
 * @date 2021/2/24
 * @email zhangao@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class MonServiceImpl extends BaseServiceImpl {

    private Logger logger = LoggerFactory.getLogger(MonServiceImpl.class);

    @Resource
    private MonMapper monMapper;
    @Resource
    private FacedbMapper facedbMapper;
    @Resource
    private MonUserRefMapper monUserRefMapper;

    /**
     * 新增布控任务
     *
     * @param para
     * @return
     */
    public String insertMonitor(MonVO para) throws ServiceException {
        MonitorTask monitorTask = new MonitorTask();
        BeanUtils.copyProperties(para,monitorTask);
        monitorTask.setMonitorCode(IDUtils.createMonitorID(PropertiesUtil.getMonitorCode()));
        monitorTask.setCreateTime(System.currentTimeMillis());
        monitorTask.setCreateUserId(para.getUserId());
        //插入布控任务表
        monMapper.insertSelective(monitorTask);
        //插入任务用户关联表
        List<MonUserRef> monUserRefs = new ArrayList<>();
        List<String> userIdList = Arrays.asList(para.getPoliceUserIds().split(","));
        for (String userId : userIdList) {
            MonUserRef monUserRef = new MonUserRef();
            monUserRef.setMonitorId(monitorTask.getId());
            monUserRef.setUserId(userId);
            monUserRefs.add(monUserRef);
        }
        monUserRefMapper.insertList(monUserRefs);
        //修改人像库布控状态
        Example example = new Example(Facedb.class);
        List facedbIdList = Arrays.asList(para.getFacedbIds().split(","));
        example.createCriteria().andIn("id",facedbIdList);
        Facedb facedb = new Facedb();
        facedb.setMonitorState(MonitorStateEnum.YES.getCode());
        facedbMapper.updateByExampleSelective(facedb,example);
        return "SUCCESS";
    }

    /**
     * 修改布控任务
     *
     * @param para
     * @return
     */
    public String updateMonitor(MonVO para) throws ServiceException{
        MonitorTask monitorTask = new MonitorTask();
        BeanUtils.copyProperties(para,monitorTask);
        monMapper.updateByPrimaryKeySelective(monitorTask);
        //删除原任务用户关联表
        monUserRefMapper.deleteByPrimaryKey(para.getId());
        return null;
    }
}
