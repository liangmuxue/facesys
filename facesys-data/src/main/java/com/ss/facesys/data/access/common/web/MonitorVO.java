package com.ss.facesys.data.access.common.web;


import com.ss.facesys.data.baseinfo.common.web.Device;
import com.ss.request.Pagination;
import com.ss.valide.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * MonitorVO
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
public class MonitorVO extends Pagination {

    /** 布控编号 */
    @NotBlank(message = "monitor.monitorId.empty", groups = {APIGetsGroup.class, APIEditGroup.class, APIDeltGroup.class, APIKeyStateGroup.class})
    private String monitorId;
    /** 报警 topn */
    private Integer topn;
    /** 报警阈值 0~1 */
    @NotNull(message = "monitor.alarmThreshold.empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private Float alarmThreshold;
    /** 布控名称 */
    @NotBlank(message = "monitor.monitorName.empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private String monitorName;
    /** 布控状态 [1-启用,2-停用] */
    @NotNull(message = "monitor.monitorStatus.empty", groups = {APIAddGroup.class, APIEditGroup.class, APIKeyStateGroup.class})
    private Integer monitorStatus;
    /** 布控时间类型 [0-永久,1-一天,3-三天,7-一周,30-一个月,19-自定义] */
    @NotNull(message = "monitor.monitorTimeType.empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer monitorTimeType;
    /** 布控开始时间 13位时间戳 */
    private Long monitorTimeB;
    /** 布控结束时间 13位时间戳 */
    private Long monitorTimeE;
    /** 布控类型 [1-黑名单报警,2-陌生人报警,3-聚集报警] */
    @NotNull(message = "monitor.monitorType.empty", groups = {APIAddGroup.class, APIEditGroup.class})
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
    private List<String> facegroupId;
    /** JSON数组对象字符串，用于传参 */
    @NotNull(message = "monitor.devices.empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private List<Device> devices;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备ID集合
     */
    private List<String> deviceIds;
    /**
     * 人像库标识类型
     */
    private String type;
    /**
     * 底库名称
     */
    private String facegroupName;

    /** 最早发布时间 */
    private Long releaseTimeStart;
    /** 最晚发布时间 */
    private Long releaseTimeEnd;


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

    public Long getMonitorTimeB() {
        return monitorTimeB;
    }

    public void setMonitorTimeB(Long monitorTimeB) {
        this.monitorTimeB = monitorTimeB;
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

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getFacegroupId() {
        return facegroupId;
    }

    public void setFacegroupIds(List<String> facegroupId) {
        this.facegroupId = facegroupId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public String getFacegroupName() {
        return facegroupName;
    }

    public void setFacegroupName(String facegroupName) {
        this.facegroupName = facegroupName;
    }

    public void setFacegroupId(List<String> facegroupId) {
        this.facegroupId = facegroupId;
    }

    public Long getReleaseTimeStart() {
        return releaseTimeStart;
    }

    public void setReleaseTimeStart(Long releaseTimeStart) {
        this.releaseTimeStart = releaseTimeStart;
    }

    public Long getReleaseTimeEnd() {
        return releaseTimeEnd;
    }

    public void setReleaseTimeEnd(Long releaseTimeEnd) {
        this.releaseTimeEnd = releaseTimeEnd;
    }

    @Override
    public String toString() {
        return "MonitorVO{" +
                "monitorId='" + monitorId + '\'' +
                ", topn=" + topn +
                ", alarmThreshold=" + alarmThreshold +
                ", monitorName='" + monitorName + '\'' +
                ", monitorStatus=" + monitorStatus +
                ", monitorTimeType=" + monitorTimeType +
                ", monitorTimeB=" + monitorTimeB +
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
                ", deviceName='" + deviceName + '\'' +
                ", deviceIds=" + deviceIds +
                ", type='" + type + '\'' +
                ", facegroupName='" + facegroupName + '\'' +
                '}';
    }

}
