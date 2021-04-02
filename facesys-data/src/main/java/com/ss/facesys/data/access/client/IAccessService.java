package com.ss.facesys.data.access.client;

import com.alibaba.fastjson.JSONObject;
import com.ss.facesys.data.access.common.web.TaskReceive;

import java.util.List;
import java.util.Map;

/**
 * IAccessService
 *
 * @author FrancisYs
 * @date 2019/8/21
 * @email yaoshuai@ss-cas.com
 */
public interface IAccessService {

    /**
     * 应用鉴权：获取接口请求token
     *
     * @return
     */
    String login();

    /**
     * 通用请求
     *
     * @param jsonParam
     * @param requestUrl
     * @return
     */
    JSONObject request(String jsonParam, String requestUrl);

    /**
     * 人员轨迹检索
     *
     * @param paramString
     * @return
     */
    JSONObject getRecogTrack(String paramString);

    /**
     * 检索抓拍库数据
     *
     * @param paramString
     * @return
     */
    JSONObject getRecogCameraDb(String paramString);

    JSONObject getRecogCameraBodyDb(String paramString);

    JSONObject getRecogTerminalDb(String paramString);

    /**
     * 分页查询报警中心(黑名单,陌生人,少数民族黑名单,少数民族陌生人)
     *
     * @param paramString
     * @return
     */
    JSONObject getRecordAlarmPages(String paramString);

    /**
     * 注册库人脸检索
     *
     * @param paramString
     * @return
     */
    JSONObject getRecogRegisterDb(String paramString);

    /**
     * 分页查询像机抓拍数据
     *
     * @param paramString
     * @return
     */
    JSONObject getCapturePages(String paramString);

    //人脸抓拍
    JSONObject getFaceCapturePages(String paramString);


    /**
     * 获取抓拍记录详细信息
     *
     * @param paramString
     * @return
     */
    JSONObject getCapture(String paramString);

    /**
     * 抓拍统计
     *
     * @param paramString
     * @return
     */
    JSONObject captureStatistics(String paramString);

    /**
     * 新增聚类任务
     *
     * @param paramTaskReceive
     * @return
     */
    JSONObject addClusterTask(TaskReceive paramTaskReceive);

    /**
     * 分页查询聚类分析任务
     *
     * @param paramInteger 聚类状态(1:进行中,2:失败,4:完成)
     * @param paramString  聚类名称
     * @return
     */
    JSONObject getClusterTaskPages(Integer paramInteger, String paramString);

    /**
     * 分页查询聚类分析任务分组结果信息
     *
     * @param paramInteger 聚类任务ID
     * @return
     */
    JSONObject getClusterResultPages(Integer paramInteger);

    /**
     * 人脸比对功能（1:1）
     *
     * @param paramString1 图片A的base64，图片大小要求小于3M
     * @param paramString2 图片B的base64，图片大小要求小于3M
     * @return
     */
    JSONObject getRecogOne(String paramString1, String paramString2);

    /**
     * 分页查询聚类分析任务分组结果详细人脸信息
     *
     * @param paramString 分组结果ID
     * @return
     */
    JSONObject getClusterResultDetailPages(String paramString);

    /**
     * 像机预览
     *
     * @param paramString
     * @return
     */
    JSONObject getCameraPreview(String paramString);

    JSONObject addAnalysisTask(String paramString, Float paramFloat, int paramInt, List<Map<String, Object>> paramList);

    JSONObject getAnalysisTaskPages(String paramString, List<String> paramList, Long paramLong1, Long paramLong2, Integer paramInteger);

    JSONObject getAnalysisResultPages(String paramString);

    JSONObject getAnalysisResultDetailPages(String paramString);

    JSONObject getRecogDeviceDb(String paramString);

    JSONObject getCollectPages(String paramString);

    /**
     * 设备绑定引擎关系
     *
     * @param paramJson
     * @return
     */
    JSONObject deviceEngineControl(String paramJson);

    /* ***************************************************** 欧神基础数据类接口--> 人像库相关 ***************************************************** */

    /**
     * 查询人像库列表数据
     *
     * @return
     */
    JSONObject facedblist();

    /**
     * 查询人像库详细信息
     *
     * @param id
     * @return
     */
    JSONObject getFacedbDetail(String id);

    /**
     * 新增人像库信息
     *
     * @param paramString
     * @return
     */
    JSONObject facedInsert(String paramString);

    /**
     * 删除人像库信息
     *
     * @param paramString
     * @return
     */
    JSONObject facedDelete(String paramString);

    /**
     * 编辑人像库信息
     *
     * @param paramJson
     * @return
     */
    JSONObject updateFacedb(String paramJson);

    /**
     * 人像库重提特征
     *
     * @param paramJson
     * @return
     */
    JSONObject reFeatureFacedb(String paramJson);

    /**
     * 人像库绑定引擎关系
     *
     * @param paramJson
     * @return
     */
    JSONObject facedbEngineControl(String paramJson);


    /* ***************************************************** 欧神基础数据类接口--> 人像集相关 ***************************************************** */

    /**
     * 分页查询人像集数据
     *
     * @param paramJson
     * @return
     */
    JSONObject getFacedbfacePages(String paramJson);

    /**
     * 查询人像集详细信息
     *
     * @param paramString
     * @return
     */
    JSONObject facedbfaceGet(String paramString);

    /**
     * 新增人像集信息
     *
     * @param paramString
     * @return
     */
    JSONObject facedbfaceInsert(String paramString);

    /**
     * 校验图片类型
     * @param paramString
     * @return
     */
    JSONObject checkPic(String paramString);

    /**
     * 删除人像集信息
     *
     * @param paramString
     * @return
     */
    JSONObject facedbfaceDelete(String paramString);

    /**
     * 编辑人像集信息
     *
     * @param paramString
     * @return
     */
    JSONObject facedbfaceUpdate(String paramString);

    /**
     * 人像集重提特征
     *
     * @param paramJson
     * @return
     */
    JSONObject reFeatureFacedbFace(String paramJson);


    /* ***************************************************** 欧神应用类接口--> 布控相关 ***************************************************** */

    /**
     * 查询布控分页列表
     *
     * @param paramJson
     * @return
     */
    JSONObject getMonitorPages(String paramJson);

    /**
     * 查询布控列表(不关联底库、设备)
     *
     * @param paramJson
     * @return
     */
    JSONObject getMonitorList(String paramJson);

    /**
     * 查询布控详细信息
     *
     * @param monitorId 布控编号
     * @return
     */
    JSONObject getMonitorDetail(String monitorId);

    /**
     * 新增布控
     *
     * @param parmJson
     * @return
     */
    JSONObject insertMonitor(String parmJson);

    /**
     * 删除布控
     *
     * @param monitorId
     * @return
     */
    JSONObject deleteMonitor(String monitorId);

    /**
     * 编辑布控
     *
     * @param paramJson
     * @return
     */
    JSONObject updateMonitor(String paramJson);

    /**
     * 修改布控状态
     *
     * @param monitorId     布控编号
     * @param monitorStatus 布控状态：1-启用，2-停用
     * @return
     */
    JSONObject updateMonitorStatus(String monitorId, int monitorStatus);


    /* ************************************* 人证设备相关接口 ************************************* */

    /**
     * 修改人证设备
     *
     * @param paramJson
     * @return
     */
    JSONObject updatePersoncard(String paramJson);

    /**
     * 删除人证设备
     *
     * @param paramJson
     * @return
     */
    JSONObject deletePersoncard(String paramJson);

    /**
     * 人脸检测
     *
     * @param paramJson
     * @return
     */
    JSONObject faceDetect(String paramJson);

}
