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
    ONETOONE_VPLAT_RSP_ERROR("800011002", "汇聚平台返回数据异常");

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
