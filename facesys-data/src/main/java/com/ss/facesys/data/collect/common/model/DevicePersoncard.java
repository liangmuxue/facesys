package com.ss.facesys.data.collect.common.model;

import com.ss.entity.DTEntity;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

/**
 * DevicePersoncard 人证设备
 *
 * @author FrancisYs
 * @date 2020/2/11
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_device_personcard")
public class DevicePersoncard implements Serializable {

    /**
     * 主键id
     */
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;

    /**
     * 汇聚平台数据id
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 设备号
     */
    @Column(name = "device_code")
    private String deviceCode;

    /**
     * IP
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 关联地址类型：1-酒店，2-网吧
     */
    @Column(name = "rel_address_type")
    private Integer relAddressType;

    /**
     * 关联地址主键ID
     */
    @Column(name = "rel_address_id")
    private Integer relAddressId;

    /**
     * 所属单位ID
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 在/离线状态，字典：CAMERA_STATE
     */
    @Column(name = "online_state")
    private Integer onlineState;

    /**
     * 在/离线时长
     */
    @Column(name = "duration_time")
    private Long durationTime;

    /**
     * 布控状态，字典：FACEDB_MONITOR_STATE
     */
    @Column(name = "monitor_state")
    private Integer monitorState;

    /**
     * 数据状态，字典：DATA_STATUS
     */
    private Integer status;

    @Column(name = "delete_time")
    private Long deleteTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name = "create_time")
    private Long createTime;

    /**
     * 机构名称
     */
    @Transient
    private String orgCname;

    /**
     * 关联地址名称
     */
    @Transient
    private String relAddressName;

    /**
     * 在/离线状态描述
     */
    @Transient
    private String onlineStateName;

    /**
     * 布控状态中文描述
     */
    @Transient
    private String monitorStateName;

    /**
     * id集合
     */
    @Transient
    private List<Integer> ids;

    /**
     * 经度
     */
    @Transient
    private Double lon;

    /**
     * 维度
     */
    @Transient
    private Double lat;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getRelAddressType() {
        return relAddressType;
    }

    public void setRelAddressType(Integer relAddressType) {
        this.relAddressType = relAddressType;
    }

    public Integer getRelAddressId() {
        return relAddressId;
    }

    public void setRelAddressId(Integer relAddressId) {
        this.relAddressId = relAddressId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(Integer onlineState) {
        this.onlineState = onlineState;
    }

    public Long getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Long durationTime) {
        this.durationTime = durationTime;
    }

    public Integer getMonitorState() {
        return monitorState;
    }

    public void setMonitorState(Integer monitorState) {
        this.monitorState = monitorState;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getOrgCname() {
        return orgCname;
    }

    public void setOrgCname(String orgCname) {
        this.orgCname = orgCname;
    }

    public String getRelAddressName() {
        return relAddressName;
    }

    public void setRelAddressName(String relAddressName) {
        this.relAddressName = relAddressName;
    }

    public String getOnlineStateName() {
        return onlineStateName;
    }

    public void setOnlineStateName(String onlineStateName) {
        this.onlineStateName = onlineStateName;
    }

    public String getMonitorStateName() {
        return monitorStateName;
    }

    public void setMonitorStateName(String monitorStateName) {
        this.monitorStateName = monitorStateName;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DevicePersoncard.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deviceId='" + deviceId + "'")
                .add("deviceName='" + deviceName + "'")
                .add("deviceCode='" + deviceCode + "'")
                .add("ip='" + ip + "'")
                .add("relAddressType=" + relAddressType)
                .add("relAddressId=" + relAddressId)
                .add("orgId='" + orgId + "'")
                .add("onlineState=" + onlineState)
                .add("durationTime=" + durationTime)
                .add("monitorState=" + monitorState)
                .add("status=" + status)
                .add("deleteTime=" + deleteTime)
                .add("updateTime=" + updateTime)
                .add("createTime=" + createTime)
                .add("orgCname='" + orgCname + "'")
                .add("relAddressName='" + relAddressName + "'")
                .add("onlineStateName='" + onlineStateName + "'")
                .add("monitorStateName='" + monitorStateName + "'")
                .add("ids='" + ids + "'")
                .toString();
    }

}
