package com.ss.facesys.data.access.common.dto;


import com.alibaba.fastjson.JSONArray;

/**
 * MonitorDTO
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
public class MonitorDTO {

    /** 布控编号 */
    private String monitorId;
    /** 报警 topn */
    private Integer topn;
    /** 报警阈值 0~1 */
    private Float alarmThreshold;
    /** 布控名称 */
    private String monitorName;
    /** 布控状态 [1-启用,2-停用] */
    private Integer monitorStatus;
    /** 布控时间类型 [0-永久,1-一天,3-三天,7-一周,30-一个月,19-自定义] */
    private Integer monitorTimeType;
    /** 布控结束时间 13位时间戳 */
    private Long monitorTimeE;
    /** 布控类型 [1-黑名单报警,2-陌生人报警,3-聚集报警] */
    private Integer monitorType;
    /** 布控属性 [0-不限,1-少数民族] */
    private Integer monitorProperty;
    /** 用户ID */
    private String userId;
    /** 备注信息 */
    private String remark;
    /** 布控报警的聚集数量限制 */
    private Integer monitorNum;
    /** 聚集分 */
    private Integer monitorMinute;
    /** 布控扩展字段1 */
    private String monExtField1;
    /** 布控扩展字段2 */
    private String monExtField2;
    /** 布控扩展字段3 */
    private String monExtField3;
    /** 底库列表(聚集布控时不传)，JSON 数组对象[,,...] */
    private JSONArray facegroupId;
    /** JSON数组对象字符串，用于传参 */
    private JSONArray devices;

    /** 布控开始时间 13位时间戳 */
    private Long monitorTimeB;


    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    public Integer getTopn() {
        return topn;
    }

    public void setTopn(Integer topn) {
        this.topn = topn;
    }

    public Float getAlarmThreshold() {
        return alarmThreshold;
    }

    public void setAlarmThreshold(Float alarmThreshold) {
        this.alarmThreshold = alarmThreshold;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public Integer getMonitorStatus() {
        return monitorStatus;
    }

    public void setMonitorStatus(Integer monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    public Integer getMonitorTimeType() {
        return monitorTimeType;
    }

    public void setMonitorTimeType(Integer monitorTimeType) {
        this.monitorTimeType = monitorTimeType;
    }

    public Long getMonitorTimeE() {
        return monitorTimeE;
    }

    public void setMonitorTimeE(Long monitorTimeE) {
        this.monitorTimeE = monitorTimeE;
    }

    public Integer getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(Integer monitorType) {
        this.monitorType = monitorType;
    }

    public Integer getMonitorProperty() {
        return monitorProperty;
    }

    public void setMonitorProperty(Integer monitorProperty) {
        this.monitorProperty = monitorProperty;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getMonitorNum() {
        return monitorNum;
    }

    public void setMonitorNum(Integer monitorNum) {
        this.monitorNum = monitorNum;
    }

    public Integer getMonitorMinute() {
        return monitorMinute;
    }

    public void setMonitorMinute(Integer monitorMinute) {
        this.monitorMinute = monitorMinute;
    }

    public String getMonExtField1() {
        return monExtField1;
    }

    public void setMonExtField1(String monExtField1) {
        this.monExtField1 = monExtField1;
    }

    public String getMonExtField2() {
        return monExtField2;
    }

    public void setMonExtField2(String monExtField2) {
        this.monExtField2 = monExtField2;
    }

    public String getMonExtField3() {
        return monExtField3;
    }

    public void setMonExtField3(String monExtField3) {
        this.monExtField3 = monExtField3;
    }

    public JSONArray getFacegroupId() {
        return facegroupId;
    }

    public void setFacegroupId(JSONArray facegroupId) {
        this.facegroupId = facegroupId;
    }

    public JSONArray getDevices() {
        return devices;
    }

    public void setDevices(JSONArray devices) {
        this.devices = devices;
    }

    public Long getMonitorTimeB() {
        return monitorTimeB;
    }

    public void setMonitorTimeB(Long monitorTimeB) {
        this.monitorTimeB = monitorTimeB;
    }

    @Override
    public String toString() {
        return "MonitorDTO{" +
                "monitorId='" + monitorId + '\'' +
                ", topn=" + topn +
                ", alarmThreshold=" + alarmThreshold +
                ", monitorName='" + monitorName + '\'' +
                ", monitorStatus=" + monitorStatus +
                ", monitorTimeType=" + monitorTimeType +
                ", monitorTimeE=" + monitorTimeE +
                ", monitorType=" + monitorType +
                ", monitorProperty=" + monitorProperty +
                ", userId='" + userId + '\'' +
                ", remark='" + remark + '\'' +
                ", monitorNum=" + monitorNum +
                ", monitorMinute=" + monitorMinute +
                ", monExtField1='" + monExtField1 + '\'' +
                ", monExtField2='" + monExtField2 + '\'' +
                ", monExtField3='" + monExtField3 + '\'' +
                ", facegroupId=" + facegroupId +
                ", devices=" + devices +
                ", monitorTimeB=" + monitorTimeB +
                '}';
    }

}
