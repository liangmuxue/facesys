package com.ss.facesys.util.constant;

import java.text.SimpleDateFormat;

/**
 * CommonConstant
 *
 * @author FrancisYs
 * @date 2019/9/3
 * @email yaoshuai@ss-cas.com
 */
public class CommonConstant {

    public static final String FLOW_RECORD_SEQUENCE = "seq_flowrecord";
    public static final String HH_MM_SS_START = "000000";
    public static final String HH_MM_SS_END = "235959";
    public static String NGINX_IMAGE_PATH = "";


    public static final String HTTP_PREFIX = "http://";


    public static final String COLON = ":";


    public static final String SUFFIX = "jpg";


    public static final Short FEATURE_STATUS_SUCCESS = (short) 0;


    public static final Short FEATURE_STATUS_FAIL = (short) 1;


    public static final Integer OFFLINE_VIDEO_ID_INITVALUE = 10000;


    public static final String OFFLINE_VIDEO_ID_KEY = "OFFLINE_VIDEO_ID";


    public static final String MONITOR_KEY = "MONITOR_%s_%s";


    public static final String UYGUR_KEY = "UYGUR_%s";


    public static final char CACHE_SEPARATOR = '_';


    public static final String MESSAGE_DATE_PATTERN = "YYYY-MM-dd HH:mm:ss";


    public static final SimpleDateFormat DATE_PATTERN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static final SimpleDateFormat SHORT_DATE_PATTERN = new SimpleDateFormat("yyyy-MM-dd");


    public static String REGISTRY_TOPN = "1000";


    public static String CACHE_EXPIRE = "600";


    public static String IMAGE_TYPE = "jpeg,jpg,png,bmp";


    public static String CAMERA_STREAM_SERVER_URL = "rtsp://192.168.10.32:554/";


    public static Integer IMAGE_MAX_SIZE = 2;


    public static final int FORM_LIST_MAX_SIZE = 500;


    public static String ADMIN_USER_ID = "1000";


    public static String POLICE_CODE = "";


    public static final Integer HASHMAP_INITIALCAPACITY = 16;


    public static final Integer IMGTYPE_URL = 1;


    public static final Integer IMGTYPE_BASE64 = 2;


    public static final Integer IMGTYPE_FULLURL = 3;


    public static final String SS_STATUS_CODE_SUCCESS = "00000000";


    public static final String CW_STATUS_CODE_CODE = "0";


    public static final String NO_AUTHORITY_CODE = "70000000";


    public static final String STATUS_NORMAL = "1";


    public static final String STATUS_DISABLE = "0";


    public static final String STATUS_DELETE = "-1";


    public static final Integer COMMON_NEGATIVE_4 = -4;
    public static final Integer COMMON_NEGATIVE_1 = -1;
    public static final Integer COMMON_0 = 0;
    public static final Integer COMMON_1 = 1;
    public static final Integer COMMON_2 = 2;
    public static final Integer COMMON_3 = 3;
    public static final Integer COMMON_4 = 4;
    public static final Integer COMMON_5 = 5;
    public static final Integer COMMON_6 = 6;
    public static final Integer COMMON_7 = 7;
    public static final Integer COMMON_8 = 8;


    public static final Integer SUBSCRIBED_DATATYPE_CAPTURE = 1;


    public static final Integer SUBSCRIBED_DATATYPE_BIG_EYES = 5;


    public static final Integer SUBSCRIBED_DATATYPE_ALARM = 15;


    public static final Integer SUBSCRIBED_DATATYPE_WIFI = 21;


    public static final Integer MAX_PAGE_NUMBER = 500;


    public static final Integer SUBSCRIBED_DATATYPE_SUPERHOME = 2;


    public static final Integer SUBSCRIBED_DATATYPE_REALTIME = 7;


    public static final Integer SUBSCRIBED_DATATYPE_EVENT = 8;

    /**
     * 处置类型：人员疑似新增处置
     */
    public static final Integer PROCESSTYPE_ADDPERSON = 1;
    /**
     * 处置类型：人员疑似离开处置
     */
    public static final Integer PROCESSTYPE_LEAVEPERSON = 2;
    /**
     * 处置类型：高频陌生人处置
     */
    public static final Integer PROCESSTYPE_FREQUENCY = 3;
    /**
     * 处置类型：高龄老人处置
     */
    public static final Integer PROCESSTYPE_OLDMAN = 4;
    /**
     * 处置类型：人员布控报警处置
     */
    public static final Integer PROCESSTYPE_ALARM = 5;
    /**
     * 处置类型：车辆感知发现处置
     */
    public static final Integer PROCESSTYPE_VEHICLE_DISCOVERY = 6;
    /**
     * 处置类型：车辆感知离开处置
     */
    public static final Integer PROCESSTYPE_VEHICLE_LEAVE = 7;
    /**
     * 处置类型：车辆滞留处置
     */
    public static final Integer PROCESSTYPE_VEHICLE_RETENTION = 8;
    /**
     * 处置类型：夜间高频处置
     */
    public static final Integer PROCESSTYPE_FREQUENCYNIGHT= 9;
    /**
     * 处置类型：敏感通行处置
     */
    public static final Integer PROCESSTYPE_SENSITIVETRAFFIC= 10;
    /**
     * 处置类型：长时间逗留处置
     */
    public static final Integer PROCESSTYPE_LONGTIMESTAY= 11;

    /**
     * 成功英文描述
     */
    public static final String SUCCESS_EN_CODE = "success";
    /**
     * 失败英文描述
     */
    public static final String ERROR_EN_CODE = "failed";
    /**
     * 欧神失败英文描述
     */
    public static final String ERROR_EN_OCEAN_CODE = "osFailed";

    /**
     * 未收藏/取消收藏
     */
    public static final int STATUS_CANCEL_COLLECT = 0;
    /**
     * 收藏状态/收藏操作
     */
    public static final int STATUS_COLLECT = 1;

    /**
     * 分隔符：英文逗号字符串
     */
    public static final String SPLIT_COMMA = ",";

    /**
     * 人像库用途：通用
     */
    public static final int FACEDB_MODEL_GENERAL = 1;
    /**
     * 人像库用途：通用+人口管理分析
     */
    public static final int FACEDB_MODEL_SPECIAL = 2;

    /**
     * 删除标识：未删除
     */
    public static final int DELETE_FLAG_EXIST = 0;
    /**
     * 删除标识：已删除
     */
    public static final int DELETE_FLAG_DELETE = 1;

    /**
     * 欧神证件类型：身份证
     */
    public static final String OCEAN_CARD_TYPE_ID = "111";

    /**
     * 布控时间类型：永久
     */
    public static final int MONITOR_TIME_TYPE_PERMANENT = 0;
    /**
     * 布控时间类型：一天
     */
    public static final int MONITOR_TIME_TYPE_ONE_DAY = 1;
    /**
     * 布控时间类型：三天
     */
    public static final int MONITOR_TIME_TYPE_THREE_DAY = 3;
    /**
     * 布控时间类型：一周
     */
    public static final int MONITOR_TIME_TYPE_WEEK = 7;
    /**
     * 布控时间类型：一个月
     */
    public static final int MONITOR_TIME_TYPE_MONTH = 30;
    /**
     * 布控时间类型：自定义
     */
    public static final int MONITOR_TIME_TYPE_CUSTOM = 19;

    /**
     * 布控类型：黑名单报警
     */
    public static final int MONITOR_TYPE_BLACK_LIST = 1;
    /**
     * 布控类型：陌生人报警
     */
    public static final int MONITOR_TYPE_STRANGE = 2;
    /**
     * 布控类型：聚集报警
     */
    public static final int MONITOR_TYPE_BLACK_GATHER = 3;

    /**
     * 参数校验：是否通过主键
     */
    public static final String PARAM_VALID_KEY = "valid";
    /**
     * 参数校验：返回信息主键
     */
    public static final String PARAM_MSG_KEY = "msg";

    /**
     * 欧神设备类型：像机设备
     */
    public static final short OCEAN_DEVICE_TYPE_USUAL = 1;
    /**
     * 欧神设备类型：人证设备
     */
    public static final short OCEAN_DEVICE_TYPE_CERD = 2;
    /**
     * 欧神设备类型：离线视频
     */
    public static final short OCEAN_DEVICE_TYPE_VIDEO = 3;
    /**
     * 欧神设备类型：终端设备
     */
    public static final short OCEAN_DEVICE_TYPE_TERMINAL = 4;

}
