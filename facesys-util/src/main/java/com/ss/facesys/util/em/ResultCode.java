package com.ss.facesys.util.em;

import com.ss.tools.IResultCode;

/**
 * ResultCode
 *
 * @author FrancisYs
 * @date 2019/12/4
 * @email yaoshuai@ss-cas.com
 */
public enum ResultCode implements IResultCode {

    SUCCESS("00000000", "成功"),

    RECORD_FLOW_QUERY_PLATFORM_NULL("79901001", "查询流水记录，请求系统编号为空"),
    RECORD_FLOW_QUERY_TIME_NULL("79901002", "查询流水记录，创建时间为空"),
    RECORD_FLOW_QUERY_FAIL("79901003", "查询流水记录异常"),
    RECORD_FLOW_ADD_PLATFORM_NULL("79901004", "新增流水记录，请求系统编号为空"),
    RECORD_FLOW_ADD_TIME_NULL("79901005", "新增流水记录，创建时间为空"),
    RECORD_FLOW_ADD_FAIL("79901006", "新增流水记录异常"),
    RECORD_FLOW_GET_PLATFORM_NULL("79901007", "获取流水记录，请求系统编号为空"),
    RECORD_FLOW_GET_TIME_NULL("79901008", "获取流水记录，创建时间为空"),
    RECORD_FLOW_GET_ID_NULL("79901009", "获取流水记录，记录ID为空"),
    RECORD_FLOW_GET_FAIL("79901010", "获取流水记录异常"),
    RECORD_FLOW_SHARDING_TABLE_FAIL("79901011", "分表策略错误,表不存在"),

    SEQUENCE_NAME_NULL("79902001", "获取序列编号,序列名称为空"),
    SEQUENCE_NAME_NOT_EXIST("79902002", "获取序列编号,序列名称不存在"),
    SEQUENCE_QUERY_FAIL("79902003", "获取序列编号异常"),

    ONETOONE_IMAGE_TYPE_ERROR("800011001", "图片上传格式配置错误"),
    ONETOONE_VPLAT_RSP_ERROR("800011002", "汇聚平台返回数据异常"),

    FACEDB_NOTEXIST("800061000", "人像库不存在"),
    FACEDB_FACEDBNAME_EXIST("800061001", "人像库名称已存在"),
    FACEDB_DELETEFAIL_MONITOR("800061002", "人像库已布控,不能删除"),
    FACEDB_VPLAT_FAIL("800061003", "汇聚平台人像库操作异常"),
    FACEDB_FACESYS_INSERT_FAIL("800061004", "人像系统新增人像库数据失败：facedbMapper.insertSelective异常"),
    FACEDB_FACESYS_UPDATE_FAIL("800061005", "人像系统更新人像库数据失败：facedbMapper.updateByPrimaryKeySelective异常"),
    FACEDB_FACESYS_DELETE_FAIL("800061006", "人像系统逻辑删除人像库数据失败：facedbMapper.updateByPrimaryKeySelective异常"),
    FACEDB_ENGINE_BIND_REF_NOT_EXIST("800061007", "人像库与引擎绑定关系不存在"),

    DEVICE_NOTEXIST("800064000", "设备不存在"),

    IMG_TO_BASE64_FAIL("800079001", "图片转换base64发生异常，请检查图片内容或图片路径"),

    FACEDBFACE_CARDID_EXIST("800062001", "证件类型+证件号存在重复的人像集"),
    FACEDBFACE_DELETEFAIL_MONITOR("800062002", "人像集已布控,不能删除"),
    FACEDBFACE_VPLAT_FAIL("800062003", "汇聚平台人像集操作异常"),
    FACEDBFACE_FACESYS_INSERT_FAIL("800062004", "人像系统新增人像集数据失败：facedbFaceMapper.insertSelective异常"),
    FACEDBFACE_FACESYS_UPDATE_FAIL("800062005", "人像系统更新人像集数据失败：facedbFaceMapper.updateByPrimaryKeySelective异常"),
    FACEDBFACE_FACESYS_DELETE_FAIL("800062006", "人像系统逻辑删除人像集数据失败：facedbFaceMapper.updateByPrimaryKeySelective异常"),
    FACEDBFACE_FACESYS_BAT_DELETE_FAIL("800062007", "人像系统批量逻辑删除人像集数据失败：facedbFaceMapper.updateByExampleSelective异常"),

    FACEDB_ENGINE_BIND_NULLTARGET("800074001", "人像库关联引擎绑定关系失败：已选人像库中不存在可进行指定操作（绑定/取消绑定）的目标数据"),
    FACEDB_ENGINE_BIND_FAIL("800074002", "人像库关联引擎绑定关系失败：新增关联关系数据失败"),
    FACEDB_ENGINE_CANCEL_BIND_FAIL("800074003", "人像库关联引擎绑定关系失败：删除关联关系数据失败"),
    FACEDB_VPLAT_ENGINE_CONTROL_FAIL("800074004", "汇聚平台人像库绑定引擎关系操作异常"),

    DEVICE_ENGINE_BIND_NULLTARGET("800074201", "设备关联引擎绑定关系失败：已选设备中不存在可进行指定操作（绑定/取消绑定）的目标数据"),
    DEVICE_ENGINE_BIND_FAIL("800074202", "设备关联引擎绑定关系失败：新增关联关系数据失败"),
    DEVICE_ENGINE_CANCEL_BIND_FAIL("800074203", "设备关联引擎绑定关系失败：删除关联关系数据失败"),
    DEVICE_VPLAT_ENGINE_CONTROL_FAIL("800074204", "汇聚平台设备绑定引擎关系操作异常"),

    RECOG_CAPTURE_VPLAT_FAIL("800015001", "人像检索1:N抓拍库汇聚平台操作异常"),
    RECOG_FACEDB_VPLAT_FAIL("800015002", "人像检索1:N注册库汇聚平台操作异常"),

    ;

    private String code;
    private String desc;

    ResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
