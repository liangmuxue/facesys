package com.ss.facesys.web.manage.baseinfo.controller;

public class ResultCode {

    public static final String SUCCESS = "00000000";

    public static final String SUCCESS_MESSAGE = "请求成功";

    public static final String ERROR = "00000001";

    public static final String ERROR_MESSAGE = "未知错误";

    public static final String SERVICE_ERROR = "00000002";

    public static final String SERVICE_ERROR_MESSAGE = "服务暂不可用";

    public static final String UNSUPPORTED_OPENAPI_METHOD = "00000003";

    public static final String UNSUPPORTED_OPENAPI_METHOD_MESSAGE = "未知的方法";

    public static final String REQUEST_LIMIT = "00000004";

    public static final String REQUEST_LIMIT_MESSAGE = "接口调用次数已达到设定的上限";

    public static final String UNAUTHORIZED_ADDRESS = "00000005";

    public static final String UNAUTHORIZED_ADDRESS_MESSAGE = "请求来自未经授权的 IP 地址";

    public static final String NO_PERMISSION_DATA = "00000006";

    public static final String NO_PERMISSION_DATA_MESSAGE = "无权访问该用户数据";

    public static final String NO_PERMISSION_REFERER = "00000007";

    public static final String NO_PERMISSION_REFERER_MESSAGE = "来自该 refer 的请求无访问权限";

    public static final String DATA_TRANSMISSION_TIMEOUT = "00000008";

    public static final String DATA_TRANSMISSION_TIMEOUT_MESSAGE = "数据传输超时";

    public static final String INVALID_PARAMETER = "00000100";

    public static final String INVALID_PARAMETER_MESSAGE = "请求参数无效";

    public static final String INVALID_API_KEY = "00000101";

    public static final String INVALID_API_KEY_MESSAGE = "Api key 无效";

    public static final String INCORRECT_SIGNATURE = "00000101";

    public static final String INCORRECT_SIGNATURE_MESSAGE = "无效签名";

    public static final String MANY_PARAMETERS = "00000105";

    public static final String MANY_PARAMETERS_MESSAGE = "请求参数过多";

    public static final String UNSUPPORTED_METHOD = "00000106";

    public static final String UNSUPPORTED_METHOD_MESSAGE = "未知的签名方法";

    public static final String INVALID_TIMESTAMP_PARAMETER = "00000107";

    public static final String INVALID_TIMESTAMP_PARAMETER_MESSAGE = "Timestamp 参数无效";

    public static final String INVALID_USER_INFO_FIELD = "00000109";

    public static final String INVALID_USER_INFO_FIELD_MESSAGE = "无效的用户资料字段名";

    public static final String ACCESS_TOKEN_INVALID = "00000110";

    public static final String ACCESS_TOKEN_INVALID_MESSAGE = "无效的 access token";

    public static final String ACCESS_TOKEN_EXPIRED = "00000111";

    public static final String ACCESS_TOKEN_EXPIRED_MESSAGE = "Access token 过期";

    public static final String USER_NOT_VISIBLE = "00000111";

    public static final String USER_NOT_VISIBLE_MESSAGE = "用户无效";

    public static final String UNSUPPORTED_PERMISSION = "00000211";

    public static final String UNSUPPORTED_PERMISSION_MESSAGE = "获取未授权字段";

    public static final String UNKNOWN_DATA_STORE_API_ERROR = "00000800";

    public static final String UNKNOWN_DATA_STORE_API_ERROR_MESSAGE = "未知的存储操作错误";

    public static final String NO_SUCH_APPLICATION_EXISTS = "00000900";

    public static final String NO_SUCH_APPLICATION_EXISTS_MESSAGE = "访问应用不存在";

    public static final String STATISTICS_REALHOUSE_FAILED_CODE = "70801001";

    public static final String STATISTICS_POPULACE_FAILED_CODE = "70801002";

    public static final String STATISTICS_REALCOMPANY_FAILED_CODE = "70801003";

    public static final String STATISTICS_CAPTURECOUNT_FAILED_CODE = "70801004";

    public static final String STATISTICS_CAPTUREREGISTER_FAILED_CODE = "70801005";

    public static final String STATISTICS_SNAPTENDENCY_FAILED_CODE = "70801006";

    public static final String STATISTICS_TENDENCY_FAILED_CODE = "70801007";

    public static final String ALLRESOURCE_FAILED_CODE = "70801008";

    public static final String BASIC_RESOURCE_FAILED_CODE = "70801009";

    public static final String ALLDAYRESOURCE_FAILED_CODE = "70801010";

    public static final String DAYRESOURCE_FAILED_CODE = "70801011";

    public static final String ANALYSIS_FAILED_CODE = "70801012";
    /** 查询房屋关联信息详情失败编号 */
    public static final String ARCHIVES_HOUSE_DETAIL_FAILED_CODE = "70802001";
    /** 查询所有小区失败编号 */
    public static final String ARCHIVES_HOUSE_VILLAGE_FAILED_CODE = "70802002";
    /** 查询楼栋失败编号 */
    public static final String ARCHIVES_HOUSE_BUILDING_FAILED_CODE = "70802003";
    /** 查询单元失败编号 */
    public static final String ARCHIVES_HOUSE_UNIT_FAILED_CODE = "70802004";
    /** 查询房间失败编号 */
    public static final String ARCHIVES_HOUSE_HOUSE_FAILED_CODE = "70802005";
    /** 查询车辆列表失败编号 */
    public static final String ARCHIVES_VEHICLE_DETAIL_FAILED_CODE = "70802006";
    /** 查询车辆感知发现失败编号 */
    public static final String ARCHIVES_VEHICLE_DISCOVERY_FAILED_CODE = "70802007";
    /** 查询车辆感知离开失败编号 */
    public static final String ARCHIVES_VEHICLE_LEAVE_FAILED_CODE = "70802008";
    /** 查询车辆滞留信息失败编号 */
    public static final String ARCHIVES_VEHICLE_RETENTION_FAILED_CODE = "70802009";
    /** 查询车辆详细信息失败编号 */
    public static final String ARCHIVES_PERSON_VEHICLE_DETAIL_FAILED_CODE = "70802010";
    /** 查询过车信息失败编号 */
    public static final String ARCHIVES_VEHICLE_LIST_FAILED_CODE = "70802011";

    public static final String ARCHIVES_PERSON_CAPTURE_FAILED_CODE = "70802012";

    public static final String ARCHIVES_PERSON_DETAIL_FAILED_CODE = "70802013";
    /** 查询房屋详情失败编号 */
    public static final String ARCHIVES_PERSON_HOUSE_FAILED_CODE = "70802014";

    public static final String ARCHIVES_PERSON_INFRASTRUCTURE_FAILED_CODE = "70802015";
    /** 房屋居住人员信息查询失败编号 */
    public static final String ARCHIVES_PERSON_HOUSEPEOPLE_FAILED_CODE = "70802016";

    public static final String ARCHIVES_PERSON_WARNING_FAILED_CODE = "70802017";

    public static final String ARCHIVES_PERSON_MAC_FAILED_CODE = "70802018";
    /** 新增车辆信息失败编号 */
    public static final String ARCHIVES_PERSON_ADD_FAILED_CODE = "70802019";
    /** 修改车辆信息失败编号 */
    public static final String ARCHIVES_PERSON_EDIT_FAILED_CODE = "70802020";
    /** 删除车辆信息失败编号 */
    public static final String ARCHIVES_PERSON_DELETE_FAILED_CODE = "70802021";

    public static final String VEHICLETRACK_FAILED_CODE = "70803001";

    public static final String MACTRACK_FAILED_CODE = "70803002";

    public static final String FACETRACK_FAILED_CODE = "70803003";

    public static final String PEOPLEPROCESS_FAILED_CODE = "70804001";

    public static final String PEOPLEPROCESSVIEW_FAILED_CODE = "70804002";

    public static final String REGISTERRECOG_FAILED_CODE = "70804003";

    public static final String SAVERECOGINFO_FAILED_CODE = "70804004";

    public static final String OCEAN_FACEDB_CODE = "70804005";
    /** 疑似新增分页查询失败编号 */
    public static final String ADDPERSON_FAILED_CODE = "70804008";
    /** 疑似离开分页查询失败编号 */
    public static final String LEAVEPERSON_FAILED_CODE = "70804009";

    public static final String FREQUENCYPERSON_FAILED_CODE = "70804010";

    public static final String SPECIALPERSON_FAILED_CODE = "70804011";

    public static final String CAPTURE_DETAILS_CODE = "70804012";

    public static final String FREQUENCYPERSON_TOP_FAILED_CODE = "70804013";

    public static final String TOP_DETAILS_CODE = "70804014";

    public static final String NO_ALARM_CODE = "70804015";

    public static final String POPULATION_STATISTICS_CODE = "70804016";

    public static final String VEHICLE_PROCESS_DISCOVERY_PAGE_CODE = "70804017";

    public static final String VEHICLE_PROCESS_DISCOVERY_DETAIL_CODE = "70804018";

    public static final String VEHICLE_PROCESS_HANDLE_CODE = "70804019";

    public static final String VEHICLE_PROCESS_LEAVE_PAGE_CODE = "70804020";

    public static final String VEHICLE_PROCESS_RETATION_PAGE_CODE = "70804021";
    /** 夜间高频人员分页查询失败编号 */
    public static final String FREQUENCYNIGHT_FAILED_CODE = "70804022";
    /** 长时间逗留人员分页查询失败编号 */
    public static final String LONGTIMESTAY_FAILED_CODE = "70804023";
    /** 敏感通行人员分页查询失败编号 */
    public static final String SENSITIVETRAFFIC_FAILED_CODE = "70804024";
    /** 查询实有房屋失败编号 */
    public static final String GETHOUSE_FAILED_CODE = "70805001";
    /** 查询实有单位失败编号 */
    public static final String GETCOMPANY_FAILED_CODE = "70805002";

    public static final String FINDALLCAMERAS_FAILED_CODE = "70805003";

    public static final String FINDALLWIFIS_FAILED_CODE = "70805004";

    public static final String FINDALLVEHICLETOLLGATES_FAILED_CODE = "70805005";

    public static final String FINDALLVILLAGEENTRANCES_FAILED_CODE = "70805006";

    public static final String PAGE_FAILED_CODE = "70805007";

    public static final String OPENDOOR_FAILED_CODE = "70805008";

    public static final String FINDLIST_FAILED_CODE = "70805009";

    public static final String WIFIPAGE_FAILED_CODE = "70805010";

    public static final String COLLECT_PEOPLE_FAILED_CODE = "70805011";

    public static final String NO_AUTHORITY_CODE = "70806001";

    public static final String NO_AUTHORITY_MESSAGE = "您无权访问此功能，请联系管理员！";

    public static final String REGIONINSERT_FAILED_CODE = "70806002";

    public static final String REGIONDELETE_BUILDING_CODE = "70806003";

    public static final String REGIONUPDATE_FAILED_CODE = "70806004";

    public static final String FINDALLREGIONS_FAILED_CODE = "70806005";

    public static final String VILLAGEINSERT_FAILED_CODE = "70806006";

    public static final String VILLAGEDELETE_FAILED_CODE = "70806007";

    public static final String VILLAGEUPDATE_FAILED_CODE = "70806008";

    public static final String FINDALLVILLAGES_FAILED_CODE = "70806009";

    public static final String NO_BUILDING_PAGE_CODE = "70806010";

    public static final String NO_BUILDING_ADD_CODE = "70806011";

    public static final String NO_BUILDING_DETAIL_CODE = "70806012";

    public static final String NO_BUILDING_UPDATE_CODE = "70806013";

    public static final String NO_BUILDING_DELETE_CODE = "70806014";

    public static final String NO_BUILDING_LIST_CODE = "70806015";

    public static final String NO_UNIT_PAGE_CODE = "70806016";

    public static final String NO_UNIT_ADD_CODE = "70806017";

    public static final String NO_UNIT_DETAIL_CODE = "70806018";

    public static final String NO_UNIT_UPDATE_CODE = "70806019";

    public static final String NO_UNIT_DELETE_CODE = "70806020";

    public static final String NO_UNIT_LIST_CODE = "70806021";

    public static final String RESOURCE_HOUSE_PAGE_FAILED_CODE = "70806022";

    public static final String RESOURCE_HOUSE_INFO_FAILED_CODE = "70806023";
    /** 新增房屋失败编号 */
    public static final String RESOURCE_HOUSE_ADD_FAILED_CODE = "70806024";
    /** 修改房屋失败编号 */
    public static final String RESOURCE_HOUSE_EDIT_FAILED_CODE = "70806025";
    /** 删除房屋失败编号 */
    public static final String RESOURCE_HOUSE_DELETE_FAILED_CODE = "70806026";
    /** 查询人房关系待添加人员列表失败编号 */
    public static final String RESOURCE_HOUSE_PEOPLELIST_FAILED_CODE = "70806027";
    /** 编辑人房关系失败编号 */
    public static final String RESOURCE_HOUSE_PEOPLERELATION_FAILED_CODE = "70806028";

    public static final String RESOURCE_HOUSE_IMPORT_FAILED_CODE = "70806029";

    public static final String NO_VILLAGEENTRANCE_PAGE_CODE = "70806030";

    public static final String NO_VILLAGEENTRANCE_ADD_CODE = "70806031";

    public static final String NO_VILLAGEENTRANCE_DETAIL_CODE = "70816031";

    public static final String NO_VILLAGEENTRANCE_UPDATE_CODE = "70806032";

    public static final String NO_VILLAGEENTRANCE_DELETE_CODE = "70806033";

    public static final String NO_VILLAGEENTRANCE_REF_CODE = "70806034";

    public static final String NO_VILLAGEENTRANCE_REFLIST_CODE = "70806035";

    public static final String CAMERAINSERT_FAILED_CODE = "70806036";

    public static final String CAMERADELETE_FAILED_CODE = "70806037";

    public static final String CAMERAUPDATE_FAILED_CODE = "70806038";

    public static final String FINDCAMERAS_FAILED_CODE = "70806039";

    public static final String IMPORTCAMERA_FAILED_CODE = "70806040";

    public static final String WIFIINSERT_FAILED_CODE = "70806041";

    public static final String WIFIDELETE_FAILED_CODE = "70806042";

    public static final String WIFIUPDATE_FAILED_CODE = "70806043";

    public static final String FINDWIFIS_FAILED_CODE = "70806044";

    public static final String IMPORTWIFI_FAILED_CODE = "70806045";

    public static final String NO_VEHICLETOLLGATE_PAGE_CODE = "70806046";

    public static final String NO_VEHICLETOLLGATE_ADD_CODE = "70806047";

    public static final String NO_VEHICLETOLLGATE_DETAIL_CODE = "70816047";

    public static final String NO_VEHICLETOLLGATE_UPDATE_CODE = "70806048";

    public static final String NO_VEHICLETOLLGATE_DELETE_CODE = "70806049";

    public static final String NO_VEHICLETOLLGATE_IMPORT_CODE = "70806050";

    public static final String RESOURCE_PEOPLE_PAGE_FAILED_CODE = "70806051";

    public static final String RESOURCE_PEOPLE_INFO_FAILED_CODE = "70806052";

    public static final String RESOURCE_PEOPLE_ADD_FAILED_CODE = "70806053";

    public static final String RESOURCE_PEOPLE_EDIT_FAILED_CODE = "70806054";

    public static final String RESOURCE_PEOPLE_DELETE_FAILED_CODE = "70806055";

    public static final String RESOURCE_PEOPLE_IMPORT_FAILED_CODE = "70806056";

    public static final String RESOURCE_COMPANY_PAGE_FAILED_CODE = "70806057";
    /** 实有单位从业人员分页查询失败编号 */
    public static final String RESOURCE_COMPANY_PEOPLE_PAGE_FAILED_CODE = "70806058";
    /** 实有单位详情信息查询失败编号 */
    public static final String RESOURCE_COMPANY_INFO_FAILED_CODE = "70806059";
    /** 新增实有单位失败编号 */
    public static final String RESOURCE_COMPANY_ADD_FAILED_CODE = "70806060";
    /** 实有单位修改失败编号 */
    public static final String RESOURCE_COMPANY_EDIT_FAILED_CODE = "70806061";
    /** 实有单位删除失败编号 */
    public static final String RESOURCE_COMPANY_DELETE_FAILED_CODE = "70806062";

    public static final String RESOURCE_COMPANY_IMPORT_FAILED_CODE = "70806063";

    public static final String RESOURCE_COMPANY_PEOPLE_IMPORT_FAILED_CODE = "70806064";

    public static final String RESOURCE_PEOPLE_COLLECT_FAILED_CODE = "70806065";
    /** 查询人车关系待添加车辆列表失败编号 */
    public static final String RESOURCE_VEHICLE_PEOPLELIST_FAILED_CODE = "70806066";
    /** 编辑人车关系失败编号 */
    public static final String RESOURCE_VEHICLE_PEOPLERELATION_FAILED_CODE = "70806067";

    /** 人像库列表查询失败编号 */
    public static final String FACEDB_LIST_FAILED_CODE = "800067001";
    /** 人像库分页列表查询失败编号 */
    public static final String FACEDB_PAGE_FAILED_CODE = "800067002";
    /** 人像库明细查询失败编号 */
    public static final String FACEDB_DETAIL_FAILED_CODE = "800067003";
    /** 新增人像库失败编号 */
    public static final String FACEDB_ADD_FAILED_CODE = "800067004";
    /** 删除人像库失败编号 */
    public static final String FACEDB_DELETE_FAILED_CODE = "800067005";
    /** 修改人像库失败编号 */
    public static final String FACEDB_EDIT_FAILED_CODE = "800067006";
    /** 重提人像库特征失败编号 */
    public static final String FACEDB_REFEATURE_FAILED_CODE = "800067007";

    /** 人像集分页列表查询失败编号 */
    public static final String FACEDBFACE_PAGE_FAILED_CODE = "70807006";
    /** 人像集明细查询失败编号 */
    public static final String FACEDBFACE_DETAIL_FAILED_CODE = "70807007";
    /** 新增人像集失败编号 */
    public static final String FACEDBFACE_ADD_FAILED_CODE = "70807008";
    /** 删除人像集失败编号 */
    public static final String FACEDBFACE_DELETE_FAILED_CODE = "70807009";
    /** 修改人像集失败编号 */
    public static final String FACEDBFACE_EDIT_FAILED_CODE = "708070010";

    /** 布控列表查询失败编号 */
    public static final String MONITOR_LIST_FAILED_CODE = "70807011";
    /** 布控明细查询失败编号 */
    public static final String MONITOR_DETAIL_FAILED_CODE = "70807012";
    /** 新增布控失败编号 */
    public static final String MONITOR_ADD_FAILED_CODE = "70807013";
    /** 删除布控失败编号 */
    public static final String MONITOR_DELETE_FAILED_CODE = "70807014";
    /** 修改布控失败编号 */
    public static final String MONITOR_EDIT_FAILED_CODE = "708070015";
    /** 修改布控启动暂停状态失败编号 */
    public static final String MONITOR_STATUS_EDIT_FAILED_CODE = "708070016";
    /** 布控分页列表查询失败编号 */
    public static final String MONITOR_PAGE_FAILED_CODE = "70807017";

    /** 重点人员分页列表查询失败编号 */
    public static final String FACEDB_PEOPLE_PAGE_FAILED_CODE = "70807018";
    /** 新增重点人员失败编号 */
    public static final String FACEDB_PEOPLE_ADD_FAILED_CODE = "70807019";
    /** 移除重点人员失败编号 */
    public static final String FACEDB_PEOPLE_DELETE_FAILED_CODE = "70807020";

    /** 报警配置信息查询失败编号 */
    public static final String ALARMINFO_SELECT_FAILED_CODE = "70807021";
    /** 报警配置信息修改失败编号 */
    public static final String ALARMINFO_EDIT_FAILED_CODE = "70807022";
    /** 报警上传文件失败编号 */
    public static final String ALARMINFO_UPLOAD_FAILED_CODE = "70807023";

    /** 查询报警信息失败编号 */
    public static final String ALARMRECORD_FAILED_CODE = "70807024";
    /** 修改报警信息失败编号 */
    public static final String ALARMRECORD_EDIT_FAILED_CODE = "70807025";

}
