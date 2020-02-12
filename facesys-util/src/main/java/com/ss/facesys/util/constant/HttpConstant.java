package com.ss.facesys.util.constant;

/**
 * 汇聚平台接口路由
 *
 * @author FrancisYs
 * @date 2019/12/20
 * @email yaoshuai@ss-cas.com
 */
public class HttpConstant {

    public static String TOKEN = "/auth/token";
    public static String RECORDALARM_PAGES = "/api/recordAlarm/pages";
    public static String RECOG_TRACK = "/api/recog/track";
    public static String RECOG_DEVICEDB = "/api/recog/deviceDb";
    public static String RECOG_TERMINALDB = "/api/recog/terminalDb";
    public static String CAMERA_CAPTURE_PAGE = "/api/camera/capture/pages";
    public static String CAMERA_CAPTURE_GET = "/api/camera/capture/get";
    public static String COLLECT_PAGES = "/api/terminal/collect/pages";
    public static String CAPTURESTATISTICS_COUNT = "/api/captureStatistics/count";
    public static String CAMERA_ADD = "/api/device/camera/add";
    public static String CAMERA_DELETE = "/api/device/camera/delete";
    public static String CAMERA_EDIT = "/api/device/camera/edit";
    public static String CAMERA_PAGE = "/api/device/camera/pages";
    public static String CAMERA_GET = "/api/device/camera/get";
    public static String CAMERA_LIST = "/api/device/camera/list";
    public static String CLUSTER_TASK_ADD = "/api/cluster/task/insert";
    public static String ANALYSIS_TASK_ADD = "/api/analysis/cluster/addClusterByIds";
    public static String CLUSTER_TASK_PAGE = "/api/cluster/task/pages";
    public static String ANALYSIS_TASK_PAGE = "/api/analysis/cluster/idClusterPages";
    public static String CLUSTER_TASK_RESULT_PAGE = "/api/cluster/result/pages";
    public static String ANALYSIS_TASK_RESULT_PAGE = "/api/analysis/cluster/pageResults";
    public static String CLUSTER_RESULT_PAGESDETAIL = "/api/cluster/result/pagesDetail";
    public static String ANALYSIS_RESULT_PAGESDETAIL = "/api/analysis/cluster/pageResultDetails";
    public static String CAMERA_PREVIEW = "/api/device/camera/preview";
    public static String TERMINAL_REG_DEVICE = "/terminal/regDevice";
    public static String TERMINAL_UPLOAD_RECORD = "/terminal/uploadRecord";
    public static String ENGINE_BIND = "/api/device/engine/bind";
    public static String CW_SUBSCRIBED_VEHICLERECORD = "/subscribed/vehicleRecord";
    public static String VMS_LOGIN = "/api/user/login";
    public static String VMS_DEVICE = "/api/device";
    public static String SWAGGER_LIST = "/api/foreign/getList";

    /**
     * 设备绑定引擎
     */
    public static String DEVICE_ENGINE_BIND_CONTROL = "/api/device/camera/engine/bind/control";

    /* ************************************* 欧神基础数据类接口--> 人像库相关 ************************************* */

    /**
     * 查询人像库列表数据
     */
    public static String FACEDB_PAGE = "/api/facedb/list";
    /**
     * 查询人像库详细信息
     */
    public static String FACEDB_GET = "/api/facedb/get";
    /**
     * 新增人像库信息
     */
    public static String FACEDB_INSERT = "/api/facedb/insert";
    /**
     * 删除人像库信息
     */
    public static String FACEDB_DELETE = "/api/facedb/delete";
    /**
     * 编辑人像库信息
     */
    public static String FACEDB_UPDATE = "/api/facedb/update";
    /**
     * 人像库重提特征
     */
    public static String FACEDB_REFEATURE = "/api/facedb/reFeature";
    /**
     * 人像库绑定引擎关系
     */
    public static String FACEDB_ENGINE_BIND_CONTROL = "/api/facedb/engine/bind/control";


    /* ************************************* 欧神基础数据类接口--> 人像集相关 ************************************* */

    /**
     * 分页查询人像集数据
     */
    public static String FACEDBFACE_PAGE = "/api/facedbface/pages";
    /**
     * 查询人像集详细信息
     */
    public static String FACEDBFACE_GET = "/api/facedbface/get";
    /**
     * 新增人像集信息
     */
    public static String FACEDBFACE_INSERT = "/api/facedbface/insert";
    /**
     * 删除人像集信息
     */
    public static String FACEDBFACE_DELETE = "/api/facedbface/delete";
    /**
     * 编辑人像集信息
     */
    public static String FACEDBFACE_UPDATE = "/api/facedbface/update";
    /**
     * 人像集重提特征
     */
    public static String FACEDBFACE_REFEATURE = "/api/facedbface/featureExtraction";


    /* ************************************* 欧神应用类接口--> 布控相关 ************************************* */

    /**
     * 分页查询布控列表
     */
    public static String MONITOR_PAGES = "/api/monitor/pages";
    /**
     * 查询布控列表(不关联底库、设备)
     */
    public static String MONITOR_LIST = "/api/monitor/list";
    /**
     * 查询布控列表（关联设备、底库）
     */
    public static String MONITOR_LIST_ASSOCIATE = "/api/monitor/list/associate";
    /**
     * 查询布控详细信息
     */
    public static String MONITOR_GET = "/api/monitor/get";
    /**
     * 新增布控
     */
    public static String MONITOR_ADD = "/api/monitor/add";
    /**
     * 删除布控
     */
    public static String MONITOR_DELETE = "/api/monitor/delete";
    /**
     * 编辑布控
     */
    public static String MONITOR_EDIT = "/api/monitor/edit";
    /**
     * 批量删除布控
     */
    public static String MONITOR_BATCH_DELETE = "/api/monitor/batchDelete";
    /**
     * 修改布控状态（启用、停用）
     */
    public static String MONITOR_STATUS_EDIT = "/api/monitor/status/edit";
    /**
     * 批量修改布控状态（启用、停用）
     */
    public static String MONITOR_STATUS_BATCH_EDIT = "/api/monitor/status/batchEdit";


    /* ************************************* 人脸识别相关接口 ************************************* */

    /**
     * 1:1
     */
    public static String RECOG_ONE_VS_ONE = "/api/recog/1vs1";
    /**
     * 1:N 抓拍库
     */
    public static String RECOG_CAMERADB = "/api/recog/cameraDb";
    /**
     * 1:N 注册库
     */
    public static String RECOG_REGISTERDB = "/api/recog/registerDb";
    /**
     * 1:N 人证库
     */
    public static String RECOG_PERSONCARDDB = "/api/recog/personcardDb";
    /**
     * 1:N 离线视频
     */
    public static String RECOG_VIDEODB = "/api/recog/videoDb";


    /* ************************************* 人证设备相关接口 ************************************* */

    /**
     * 编辑人证设备
     */
    public static String DEVICE_PERSONCARD_EDIT = "/api/device/personcard/edit";
    /**
     * 删除人证设备
     */
    public static String DEVICE_PERSONCARD_DELETE = "/api/device/personcard/delete";

}
