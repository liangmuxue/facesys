package com.ss.facesys.data.access.service;

import com.github.pagehelper.PageHelper;
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
import org.apache.commons.collections.CollectionUtils;
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
        //查询布控任务原数据
        MonitorTask oldMonitorTask = monMapper.selectByPrimaryKey(para.getId());
        //修改布控任务表
        MonitorTask monitorTask = new MonitorTask();
        BeanUtils.copyProperties(para,monitorTask);
        monitorTask.setUpdateUserId(para.getUserId());
        monitorTask.setUpdateTime(System.currentTimeMillis());
        monMapper.updateByPrimaryKeySelective(monitorTask);
        //删除原任务用户关联表
        Example example = new Example(MonUserRef.class);
        example.createCriteria().andEqualTo("monitorId",para.getId());
        monUserRefMapper.deleteByExample(example);
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
        //修改原人像库布控状态
        updMonitorState(para.getId(),oldMonitorTask.getFacedbIds());
        //修改新添加人像库布控状态
        Example examp = new Example(Facedb.class);
        List facedbIdList = Arrays.asList(para.getFacedbIds().split(","));
        examp.createCriteria().andIn("id",facedbIdList);
        Facedb facedb = new Facedb();
        facedb.setMonitorState(MonitorStateEnum.YES.getCode());
        facedbMapper.updateByExampleSelective(facedb,examp);
        return "SUCCESS";
    }

    /**
     * 启用/停用布控任务
     *
     * @param para
     * @return
     */
    public String updateMonitorStatus(MonVO para){
        //修改本地布控任务状态
        MonitorTask monitorTask = new MonitorTask();
        BeanUtils.copyProperties(para,monitorTask);
        monitorTask.setUpdateUserId(para.getUserId());
        monitorTask.setUpdateTime(System.currentTimeMillis());
        monMapper.updateByPrimaryKeySelective(monitorTask);
        return "SUCCESS";
    }

    /**
     * 删除布控任务
     *
     * @param para
     * @return
     */
    public String deleteMonitor(MonVO para) throws ServiceException{
        //查询布控任务原数据
        MonitorTask oldMonitorTask = monMapper.selectByPrimaryKey(para.getId());
        //逻辑删除本地布控任务
        MonitorTask monitorTask = new MonitorTask();
        monitorTask.setId(para.getId());
        monitorTask.setDeleteFlag(0);
        monitorTask.setDeleteUserId(para.getUserId());
        monitorTask.setDeleteTime(System.currentTimeMillis());
        monMapper.updateByPrimaryKeySelective(monitorTask);
        //删除关联表
        Example example = new Example(MonUserRef.class);
        example.createCriteria().andEqualTo("monitorId",para.getId());
        monUserRefMapper.deleteByExample(example);
        //修改布控状态
        updMonitorState(para.getId(),oldMonitorTask.getFacedbIds());
        return "SUCCESS";
    }

    /**
     * 查询布控任务
     *
     * @param para
     * @return
     */
    public List<MonitorTask> selectMonitor(MonVO para){
        //查询布控任务
        para.setNowTime(System.currentTimeMillis());
        if(StringUtils.isNotBlank(para.getCameraIds())) {
            para.setCameraIdList(Arrays.asList(para.getCameraIds().split(",")));
        }
        if(StringUtils.isNotBlank(para.getFacedbIds())) {
            para.setFacedbIdList(Arrays.asList(para.getFacedbIds().split(",")));
        }
        PageHelper.startPage(para.getCurrentPage(), para.getPageSize());
        List<MonitorTask> monitorTasks = monMapper.selMonitorTask(para);
        for (MonitorTask monitorTask : monitorTasks) {
            List<String> cameraIds = Arrays.asList(monitorTask.getCameraIds().split(","));
            String cameraIdsName = monMapper.selcameraNames(cameraIds);
            monitorTask.setCameraIdsName(cameraIdsName);
            List<String> facedbIds = Arrays.asList(monitorTask.getFacedbIds().split(","));
            String facedbIdsName = monMapper.selFacedbNames(facedbIds);
            monitorTask.setFacedbIdsName(facedbIdsName);
        }
        return monitorTasks;
    }

    /**
     * 查询布控任务详情
     *
     * @param para
     * @return+
     */
    public MonitorTask selectMonitorDetail(MonVO para){
        MonitorTask monitorTask = monMapper.selMonitorDetail(para);
        //查询设备名
        List<String> cameraIds = Arrays.asList(monitorTask.getCameraIds().split(","));
        String cameraIdsName = monMapper.selcameraNames(cameraIds);
        monitorTask.setCameraIdsName(cameraIdsName);
        //查询人像库名
        List<String> facedbIds = Arrays.asList(monitorTask.getFacedbIds().split(","));
        String facedbIdsName = monMapper.selFacedbNames(facedbIds);
        monitorTask.setFacedbIdsName(facedbIdsName);
        //查询处警人名
        String policeUserIdsName = monMapper.selMonUserName(para);
        monitorTask.setPoliceUserIdsName(policeUserIdsName);
        return monitorTask;
    }

    /**
     * 修改人像库表中的布控状态
     *
     * @param monitorId 布控任务主键
     * @param facedbIds 库id(逗号分隔)
     * @return
     */
    public void updMonitorState (Integer monitorId,String facedbIds){
        List<String> ids = Arrays.asList(facedbIds.split(","));
        for (String id : ids) {
            //判断此库下是否还存在其他布控任务
            MonVO monVO = new MonVO();
            monVO.setDeleteFlag(1);
            monVO.setId(monitorId);
            monVO.setFacedbId(id);
            List<MonitorTask> monitorTasks = monMapper.selExistMonTask(monVO);
            if(CollectionUtils.isEmpty(monitorTasks)){
                //修改人像库状态
                Facedb facedb = new Facedb();
                facedb.setId(Integer.valueOf(id));
                facedb.setMonitorState(MonitorStateEnum.NO.getCode());
                facedbMapper.updateByPrimaryKeySelective(facedb);
            }
        }
    }
}
