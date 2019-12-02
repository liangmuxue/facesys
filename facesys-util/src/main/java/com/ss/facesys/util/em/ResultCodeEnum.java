package com.ss.facesys.util.em;


public enum ResultCodeEnum {

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


    SEQUENCE_QUERY_FAIL("79902003", "获取序列编号异常");

    private String key;

    private String value;

    ResultCodeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return this.key;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public String getValue() {
        return this.value;
    }


    public void setValue(String value) {
        this.value = value;
    }
}
