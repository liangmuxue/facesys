package com.ss.facesys.data.access.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ss.facesys.data.access.common.web.TaskReceive;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.constant.HttpConstant;
import com.ss.facesys.util.http.BaseHttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OsProxyService
 *
 * @author FrancisYs
 * @date 2019/8/21
 * @email yaoshuai@ss-cas.com
 */
@Service
public class OsProxyService {

    public static final Log LOG = LogFactory.getLog(BaseHttpUtil.class);

    /**
     * 应用鉴权：获取接口请求token
     *
     * @return
     */
    public static String login() {
        return BaseHttpUtil.httpPost(null, PropertiesUtil.getOshttp() + HttpConstant.TOKEN, null);
    }

    /**
     * 人员轨迹检索
     *
     * @param parmJson
     * @return
     */
    public static JSONObject getRecogTrack(String parmJson) {
        //String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.RECOG_CAMERADB, null);
        // Francis[2019-08-02]: requestUrl参数调整 HttpConstant.RECOG_CAMERADB --> HttpConstant.RECOG_TRACK
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.RECOG_TRACK, null);
        return JSONObject.parseObject(resultString);
    }




    public static JSONObject getRecogTerminalDb(String parmJson) {
        // Francis[2019-08-02]: 欧神接口文档未见此接口
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.RECOG_TERMINALDB, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 分页查询报警中心(黑名单,陌生人,少数民族黑名单,少数民族陌生人)
     *
     * @param parmJson
     * @return
     */
    public static JSONObject getRecordAlarmPages(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.RECORDALARM_PAGES, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 分页查询像机抓拍数据
     *
     * @param parmJson
     * @return
     */
    public static JSONObject getCapturePages(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.CAMERA_CAPTURE_PAGE, null);
        return JSONObject.parseObject(resultString);
    }

    public static JSONObject getFaceCapturePages(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.CAPTURESTATISTICS_COUNT, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 获取抓拍记录详细信息
     *
     * @param captureId 采集编号
     * @return
     */
    public static JSONObject getCapture(String captureId) {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("captureId", captureId);
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getOshttp() + HttpConstant.CAMERA_CAPTURE_GET, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 抓拍统计
     *
     * @param parmJson
     * @return
     */
    public static JSONObject captureStatistics(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.CAPTURESTATISTICS_COUNT, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 新增聚类任务
     *
     * @param taskReceive
     * @return
     */
    public static JSONObject addClusterTask(TaskReceive taskReceive) {
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(taskReceive), PropertiesUtil.getOshttp() + HttpConstant.CLUSTER_TASK_ADD, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 分页查询聚类分析任务
     *
     * @param state 聚类状态(1:进行中,2:失败,4:完成)
     * @param name  聚类名称
     * @return
     */
    public static JSONObject getClusterTaskPages(Integer state, String name) {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("name", name);
        if (null != state) {
            parm.put("state", state);
        }
        parm.put("currentPage", 1);
        parm.put("pageSize", 10);
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getOshttp() + HttpConstant.CLUSTER_TASK_PAGE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 分页查询聚类分析任务分组结果信息
     *
     * @param taskId 聚类任务ID
     * @return
     */
    public static JSONObject getClusterResultPages(Integer taskId) {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("taskId", taskId);
        parm.put("currentPage", 1);
        parm.put("pageSize", 10);
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getOshttp() + HttpConstant.CLUSTER_TASK_RESULT_PAGE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 分页查询聚类分析任务分组结果详细人脸信息
     *
     * @param resultId 分组结果ID
     * @return
     */
    public static JSONObject getClusterResultDetailPages(String resultId) {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("resultId", resultId);
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getOshttp() + HttpConstant.CLUSTER_RESULT_PAGESDETAIL, null);
        return JSONObject.parseObject(resultString);
    }


    public static JSONObject addAnalysisTask(String taskName, Float threShold, int times, List<Map<String, Object>> groups) {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("times", times);
        if (null != taskName) {
            parm.put("taskName", taskName);
        }
        if (null != threShold) {
            parm.put("threShold", threShold);
        }
        if (null != groups) {
            parm.put("groups", groups);
        }
        // Francis[2019-08-02]: 欧神接口文档未见此接口
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getOshttp() + HttpConstant.ANALYSIS_TASK_ADD, null);
        LOG.error("创建聚类任务:" + JSON.toJSONString(parm));
        return JSONObject.parseObject(resultString);
    }


    public static JSONObject getAnalysisTaskPages(String taskName, List<String> taskIds, Long bTime, Long eTime, Integer state) {
        Map<String, Object> parm = new HashMap<String, Object>();
        if (null != taskName) {
            parm.put("taskName", taskName);
        }
        if (null != bTime) {
            parm.put("createdBeginTime", bTime);
        }
        if (null != eTime) {
            parm.put("createdEndTime", eTime);
        }
        if (null != state) {
            parm.put("state", state);
        }
        if (!taskIds.isEmpty()) {
            parm.put("taskIds", taskIds);
            parm.put("pageSize", taskIds.size());
        } else {
            parm.put("pageSize", 10);
        }
        parm.put("currentPage", 1);
        // Francis[2019-08-02]: 欧神接口文档未见此接口
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getOshttp() + HttpConstant.ANALYSIS_TASK_PAGE, null);
        return JSONObject.parseObject(resultString);
    }


    public static JSONObject getAnalysisResultPages(String taskId) {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("taskId", taskId);
        parm.put("currentPage", 1);
        parm.put("pageSize", 100);
        // Francis[2019-08-02]: 欧神接口文档未见此接口
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getOshttp() + HttpConstant.ANALYSIS_TASK_RESULT_PAGE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 像机预览
     *
     * @param parmJson
     * @return
     */
    public static JSONObject getCameraPreview(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.CAMERA_PREVIEW, null);
        return JSONObject.parseObject(resultString);
    }


    public static JSONObject getAnalysisResultDetailPages(String resultId) {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("resultId", resultId);
        parm.put("pageSize", 100);
        // Francis[2019-08-02]: 欧神接口文档未见此接口
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getOshttp() + HttpConstant.ANALYSIS_RESULT_PAGESDETAIL, null);
        return JSONObject.parseObject(resultString);
    }


    public static JSONObject getRecogDeviceDb(String paramJson) {
        // Francis[2019-08-02]: 欧神接口文档未见此接口
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.RECOG_DEVICEDB, null);
        return JSONObject.parseObject(resultString);
    }


    public static JSONObject getCollectPages(String parmJson) {
        // Francis[2019-08-02]: 欧神接口文档未见此接口
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.COLLECT_PAGES, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 设备绑定引擎关系
     *
     * @param paramJson
     * @return
     */
    public static JSONObject deviceEngineControl(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.DEVICE_ENGINE_BIND_CONTROL, null);
        return JSONObject.parseObject(resultString);
    }

    /* ***************************************************** 基础数据类接口--> 人像库相关 ***************************************************** */

    /**
     * 查询人像库列表数据
     *
     * @return
     */
    public static JSONObject facedblist() {
        String resultString = BaseHttpUtil.httpPost(null, PropertiesUtil.getOshttp() + HttpConstant.FACEDB_PAGE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 查询人像库详细信息
     *
     * @param id 人像库id
     * @return
     */
    public static JSONObject getFacedbDetail(String id) {
        Map<String, Object> paramJson = new HashMap<String, Object>();
        paramJson.put("id", id);
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(paramJson), PropertiesUtil.getOshttp() + HttpConstant.FACEDB_GET, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 新增人像库信息
     *
     * @param parmJson
     * @return
     */
    public static JSONObject facedInsert(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDB_INSERT, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 删除人像库信息
     *
     * @param parmJson
     * @return
     */
    public static JSONObject facedDelete(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDB_DELETE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 编辑人像库信息
     *
     * @param paramJson
     * @return
     */
    public static JSONObject updateFacedb(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDB_UPDATE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 人像库重提特征
     *
     * @param paramJson
     * @return
     */
    public static JSONObject reFeatureFacedb(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDB_REFEATURE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 人像库绑定引擎关系
     *
     * @param paramJson
     * @return
     */
    public static JSONObject facedbEngineControl(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDB_ENGINE_BIND_CONTROL, null);
        return JSONObject.parseObject(resultString);
    }


    /* ***************************************************** 基础数据类接口--> 人像集相关 ***************************************************** */

    /**
     * 分页查询人像集数据
     *
     * @param paramJson
     * @return
     */
    public static JSONObject getFacedbfacePages(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDBFACE_PAGE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 查询人像集详细信息
     *
     * @param paramJson
     * @return
     */
    public static JSONObject facedbfaceGet(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDBFACE_GET, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 新增人像集信息
     *
     * @param parmJson
     * @return
     */
    public static JSONObject facedbfaceInsert(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDBFACE_INSERT, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 删除人像集信息
     *
     * @param paramJson
     * @return
     */
    public static JSONObject facedbfaceDelete(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDBFACE_DELETE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 编辑人像集信息
     *
     * @param paramJson
     * @return
     */
    public static JSONObject facedbfaceUpdate(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDBFACE_UPDATE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 人像集重提特征
     *
     * @param paramJson
     * @return
     */
    public static JSONObject reFeatureFacedbFace(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.FACEDBFACE_REFEATURE, null);
        return JSONObject.parseObject(resultString);
    }


    /* ***************************************************** 应用类接口--> 布控相关 ***************************************************** */

    /**
     * 查询布控分页列表
     *
     * @param paramJson
     * @return
     */
    public static JSONObject getMonitorPages(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.MONITOR_PAGES, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 查询布控列表(不关联底库、设备)
     *
     * @param paramJson
     * @return
     */
    public static JSONObject getMonitorList(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.MONITOR_LIST, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 查询布控详细信息
     *
     * @param monitorId 布控编号
     * @return
     */
    public static JSONObject getMonitorDetail(String monitorId) {
        Map<String, Object> paramJson = new HashMap<String, Object>();
        paramJson.put("monitorId", monitorId);
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(paramJson), PropertiesUtil.getOshttp() + HttpConstant.MONITOR_GET, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 新增布控
     *
     * @param parmJson
     * @return
     */
    public static JSONObject insertMonitor(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.MONITOR_ADD, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 删除布控
     *
     * @param monitorId 布控编号
     * @return
     */
    public static JSONObject deleteMonitor(String monitorId) {
        Map<String, Object> paramJson = new HashMap<String, Object>();
        paramJson.put("monitorId", monitorId);
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(paramJson), PropertiesUtil.getOshttp() + HttpConstant.MONITOR_DELETE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 编辑布控
     *
     * @param paramJson
     * @return
     */
    public static JSONObject updateMonitor(String paramJson) {
        String resultString = BaseHttpUtil.httpPost(paramJson, PropertiesUtil.getOshttp() + HttpConstant.MONITOR_EDIT, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 修改布控状态
     *
     * @param monitorId     布控编号
     * @param monitorStatus 布控状态：1-启用，2-停用
     * @return
     */
    public static JSONObject updateMonitorStatus(String monitorId, int monitorStatus) {
        Map<String, Object> paramJson = new HashMap<String, Object>();
        paramJson.put("monitorId", monitorId);
        paramJson.put("monitorStatus", monitorStatus);
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(paramJson), PropertiesUtil.getOshttp() + HttpConstant.MONITOR_STATUS_EDIT, null);
        return JSONObject.parseObject(resultString);
    }


    /**
     * 人脸比对功能（1:1）
     *
     * @param faceA 图片A的base64，图片大小要求小于3M
     * @param faceB 图片B的base64，图片大小要求小于3M
     * @return
     */
    public static JSONObject getRecogOne(String faceA, String faceB) {
        Map<String, Object> parm = new HashMap<>(2);
        parm.put("faceA", faceA);
        parm.put("faceB", faceB);
        String resultString = BaseHttpUtil.httpPost(JSON.toJSONString(parm), PropertiesUtil.getOshttp() + HttpConstant.RECOG_ONE_VS_ONE, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 1:N 抓拍库
     *
     * @param parmJson
     * @return
     */
    public static JSONObject getRecogCameraDb(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.RECOG_CAMERADB, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 1:N 注册库
     *
     * @param parmJson
     * @return
     */
    public static JSONObject getRecogRegisterDb(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.RECOG_REGISTERDB, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 编辑人证设备
     *
     * @param parmJson
     * @return
     */
    public static JSONObject updatePersoncard(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.DEVICE_PERSONCARD_EDIT, null);
        return JSONObject.parseObject(resultString);
    }

    /**
     * 删除人证设备
     *
     * @param parmJson
     * @return
     */
    public static JSONObject deletePersoncard(String parmJson) {
        String resultString = BaseHttpUtil.httpPost(parmJson, PropertiesUtil.getOshttp() + HttpConstant.DEVICE_PERSONCARD_DELETE, null);
        return JSONObject.parseObject(resultString);
    }

}
