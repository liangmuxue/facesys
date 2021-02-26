package com.ss.facesys.data.access.service;

import com.alibaba.fastjson.JSONObject;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.web.TaskReceive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * AccessServiceImpl
 *
 * @author FrancisYs
 * @date 2019/8/21
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class AccessServiceImpl implements IAccessService {

    @Override
    public String login() {
        return OsProxyService.login();
    }

    @Override
    public JSONObject request(String jsonParam, String requestUrl) {
        return OsProxyService.request(jsonParam, requestUrl);
    }

    @Override
    public JSONObject getRecogTrack(String parmJson) {
        return OsProxyService.getRecogTrack(parmJson);
    }

    @Override
    public JSONObject getRecogCameraDb(String parmJson) {
        return OsProxyService.getRecogCameraDb(parmJson);
    }

    @Override
    public JSONObject getRecogCameraBodyDb(String parmJson) {
        return OsProxyService.getRecogCameraBodyDb(parmJson);
    }

    @Override
    public JSONObject getRecogTerminalDb(String parmJson) {
        return OsProxyService.getRecogTerminalDb(parmJson);
    }

    @Override
    public JSONObject getRecordAlarmPages(String parmJson) {
        return OsProxyService.getRecordAlarmPages(parmJson);
    }

    @Override
    public JSONObject getRecogRegisterDb(String parmJson) {
        return OsProxyService.getRecogRegisterDb(parmJson);
    }

    @Override
    public JSONObject getCapturePages(String parmJson) {
        return OsProxyService.getCapturePages(parmJson);
    }

    @Override
    public JSONObject getFaceCapturePages(String parmJson) {
        return OsProxyService.getFaceCapturePages(parmJson);
    }

    @Override
    public JSONObject getCapture(String captureId) {
        return OsProxyService.getCapture(captureId);
    }

    @Override
    public JSONObject captureStatistics(String parmJson) {
        return OsProxyService.captureStatistics(parmJson);
    }

    @Override
    public JSONObject addClusterTask(TaskReceive taskReceive) {
        return OsProxyService.addClusterTask(taskReceive);
    }

    @Override
    public JSONObject getClusterTaskPages(Integer state, String name) {
        return OsProxyService.getClusterTaskPages(state, name);
    }

    @Override
    public JSONObject getClusterResultPages(Integer taskId) {
        return OsProxyService.getClusterResultPages(taskId);
    }

    @Override
    public JSONObject getRecogOne(String faceA, String faceB) {
        return OsProxyService.getRecogOne(faceA, faceB);
    }

    @Override
    public JSONObject getClusterResultDetailPages(String resultId) {
        return OsProxyService.getClusterResultDetailPages(resultId);
    }

    @Override
    public JSONObject getCameraPreview(String parmJson) {
        return OsProxyService.getCameraPreview(parmJson);
    }

    @Override
    public JSONObject addAnalysisTask(String taskName, Float threShold, int times, List<Map<String, Object>> groups) {
        return OsProxyService.addAnalysisTask(taskName, threShold, times, groups);
    }

    @Override
    public JSONObject getAnalysisTaskPages(String taskName, List<String> taskIds, Long bTime, Long eTime, Integer state) {
        return OsProxyService.getAnalysisTaskPages(taskName, taskIds, bTime, eTime, state);
    }

    @Override
    public JSONObject getAnalysisResultPages(String taskId) {
        return OsProxyService.getAnalysisResultPages(taskId);
    }

    @Override
    public JSONObject getAnalysisResultDetailPages(String resultId) {
        return OsProxyService.getAnalysisResultDetailPages(resultId);
    }

    @Override
    public JSONObject getRecogDeviceDb(String paramJson) {
        return OsProxyService.getRecogDeviceDb(paramJson);
    }

    @Override
    public JSONObject getCollectPages(String parmJson) {
        return OsProxyService.getCollectPages(parmJson);
    }

    @Override
    public JSONObject deviceEngineControl(String paramJson) {
        return OsProxyService.deviceEngineControl(paramJson);
    }

    /* ***************************************************** 欧神基础数据类接口--> 人像库相关 ***************************************************** */

    @Override
    public JSONObject facedblist() {
        return OsProxyService.facedblist();
    }

    @Override
    public JSONObject getFacedbDetail(String id) {
        return OsProxyService.getFacedbDetail(id);
    }

    @Override
    public JSONObject facedInsert(String parmJson) {
        return OsProxyService.facedInsert(parmJson);
    }

    @Override
    public JSONObject facedDelete(String parmJson) {
        return OsProxyService.facedDelete(parmJson);
    }

    @Override
    public JSONObject updateFacedb(String paramJson) {
        return OsProxyService.updateFacedb(paramJson);
    }

    @Override
    public JSONObject reFeatureFacedb(String paramJson) {
        return OsProxyService.reFeatureFacedb(paramJson);
    }

    @Override
    public JSONObject facedbEngineControl(String paramJson) {
        return OsProxyService.facedbEngineControl(paramJson);
    }



    /* ***************************************************** 欧神基础数据类接口--> 人像集相关 ***************************************************** */

    @Override
    public JSONObject getFacedbfacePages(String paramJson) {
        return OsProxyService.getFacedbfacePages(paramJson);
    }

    @Override
    public JSONObject facedbfaceGet(String paramJson) {
        return OsProxyService.facedbfaceGet(paramJson);
    }

    @Override
    public JSONObject facedbfaceInsert(String paramJson) {
        return OsProxyService.facedbfaceInsert(paramJson);
    }

    @Override
    public JSONObject facedbfaceDelete(String paramJson) {
        return OsProxyService.facedbfaceDelete(paramJson);
    }

    @Override
    public JSONObject facedbfaceUpdate(String paramJson) {
        return OsProxyService.facedbfaceUpdate(paramJson);
    }

    @Override
    public JSONObject reFeatureFacedbFace(String paramJson) {
        return OsProxyService.reFeatureFacedbFace(paramJson);
    }


    /* ***************************************************** 欧神应用类接口--> 布控相关 ***************************************************** */

    /**
     * 查询布控分页列表
     *
     * @param paramJson
     * @return
     */
    @Override
    public JSONObject getMonitorPages(String paramJson) {
        return OsProxyService.getMonitorPages(paramJson);
    }

    @Override
    public JSONObject getMonitorList(String paramJson) {
        return OsProxyService.getMonitorList(paramJson);
    }

    @Override
    public JSONObject getMonitorDetail(String monitorId) {
        return OsProxyService.getMonitorDetail(monitorId);
    }

    @Override
    public JSONObject insertMonitor(String parmJson) {
        return OsProxyService.insertMonitor(parmJson);
    }

    @Override
    public JSONObject deleteMonitor(String monitorId) {
        return OsProxyService.deleteMonitor(monitorId);
    }

    @Override
    public JSONObject updateMonitor(String paramJson) {
        return OsProxyService.updateMonitor(paramJson);
    }

    @Override
    public JSONObject updateMonitorStatus(String monitorId, int monitorStatus) {
        return OsProxyService.updateMonitorStatus(monitorId, monitorStatus);
    }


    /* ************************************* 人证设备相关接口 ************************************* */

    @Override
    public JSONObject updatePersoncard(String paramJson) {
        return OsProxyService.updatePersoncard(paramJson);
    }

    @Override
    public JSONObject deletePersoncard(String paramJson) {
        return OsProxyService.deletePersoncard(paramJson);
    }

    @Override
    public JSONObject faceDetect(String paramJson) {
        return OsProxyService.faceDetect(paramJson);
    }

}
