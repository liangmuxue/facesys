package com.ss.facesys.data.access.mapper;

import com.ss.facesys.data.access.common.dto.MonitorTask;
import com.ss.facesys.data.access.common.web.MonVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MonMapper extends SsMapper<MonitorTask> {

    List<MonitorTask> selExistMonTask(MonVO para);
    List<MonitorTask> selMonitorTask(MonVO para);
    String selcameraNames(@Param("cameraIds") List cameraIds);
    String selPersoncardDeviceNames(@Param("personcardDeviceIds") List personcardDeviceIds);
    String selFacedbNames(@Param("facedbIds") List facedbIds);
    MonitorTask selMonitorDetail(MonVO para);
    MonitorTask selMonUserName(MonVO para);
    Integer updateMontask(MonVO para);

    List<MonitorTask> selTasksByCamera(MonVO para);

}
