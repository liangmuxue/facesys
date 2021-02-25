package com.ss.facesys.data.access.common.dto;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "cw_monitor_task")
public class MonitorTask {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    @Column(name = "monitor_mode")
    private Integer monitorMode;
    @Column(name = "monitor_name")
    private String monitorName;
    @Column(name = "monitor_type")
    private Integer monitorType;
    @Column(name = "monitor_property")
    private Integer monitorProperty;
    @Column(name = "alarm_id")
    private Integer alarmId;
    @Column(name = "start_time")
    private Long startTime;
    @Column(name = "end_time")
    private Long endTime;
    @Column(name = "face_threshold")
    private Float faceThreshold;
    @Column(name = "camera_ids")
    private String cameraIds;
    @Column(name = "facedb_ids")
    private String facedbIds;
    @Column(name = "remark")
    private String remark;
    @Column(name = "state")
    private Integer state;
    @Column(name = "delete_flag")
    private Integer deleteFlag;
    @Column(name = "monitor_code")
    private String monitorCode;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "create_user_id")
    private String createUserId;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "update_user_id")
    private String updateUserId;
    @Column(name = "update_time")
    private Long updateTime;
    @Column(name = "delete_user_id")
    private String deleteUserId;
    @Column(name = "delete_time")
    private Long deleteTime;
    @Column(name = "region_code")
    private String regionCode;
    @Column(name = "adress_ids")
    private String adressIds;

    @Transient
    private String cameraIdsName;
    @Transient
    private String facedbIdsName;
    @Transient
    private String alarmName;
    @Transient
    private String monitorTypeName;
    @Transient
    private String name;
    @Transient
    private String policeUserIdsName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonitorMode() {
        return monitorMode;
    }

    public void setMonitorMode(Integer monitorMode) {
        this.monitorMode = monitorMode;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
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

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Float getFaceThreshold() {
        return faceThreshold;
    }

    public void setFaceThreshold(Float faceThreshold) {
        this.faceThreshold = faceThreshold;
    }

    public String getCameraIds() {
        return cameraIds;
    }

    public void setCameraIds(String cameraIds) {
        this.cameraIds = cameraIds;
    }

    public String getFacedbIds() {
        return facedbIds;
    }

    public void setFacedbIds(String facedbIds) {
        this.facedbIds = facedbIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public Long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAdressIds() {
        return adressIds;
    }

    public void setAdressIds(String adressIds) {
        this.adressIds = adressIds;
    }

    public String getCameraIdsName() {
        return cameraIdsName;
    }

    public void setCameraIdsName(String cameraIdsName) {
        this.cameraIdsName = cameraIdsName;
    }

    public String getFacedbIdsName() {
        return facedbIdsName;
    }

    public void setFacedbIdsName(String facedbIdsName) {
        this.facedbIdsName = facedbIdsName;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getMonitorTypeName() {
        return monitorTypeName;
    }

    public void setMonitorTypeName(String monitorTypeName) {
        this.monitorTypeName = monitorTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoliceUserIdsName() {
        return policeUserIdsName;
    }

    public void setPoliceUserIdsName(String policeUserIdsName) {
        this.policeUserIdsName = policeUserIdsName;
    }
}
