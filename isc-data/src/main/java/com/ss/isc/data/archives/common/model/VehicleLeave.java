package com.ss.isc.data.archives.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "cw_vehicle_leave")
public class VehicleLeave implements Serializable {
    private static final long serialVersionUID = 6700429995633477026L;
    private String id;
    private String tollgateID;
    private String cameraId;
    private String villageCode;
    private String plateNumber;
    private Date inOutTime;
    private String platePicUrl;
    private String plateNoPicUrl;
    private String channelName;
    private Integer days;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private String remark;
    private Integer flag;
    @Transient
    private String address;
    @Transient
    private String detailId;
    @Transient
    private String dayBegin;
    @Transient
    private String dayEnd;
    @Transient
    private Integer pageNum;
    @Transient
    private Integer pageSize;
    @Transient
    private String userIds;
    @Transient
    private String sqlString;
    @Transient
    private String villageName;
    @Transient
    private String statusName;

    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getTollgateID() {
        return this.tollgateID;
    }


    public void setTollgateID(String tollgateID) {
        this.tollgateID = tollgateID;
    }


    public String getCameraId() {
        return this.cameraId;
    }


    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getPlateNumber() {
        return this.plateNumber;
    }


    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


    public String getPlatePicUrl() {
        return this.platePicUrl;
    }


    public void setPlatePicUrl(String platePicUrl) {
        this.platePicUrl = platePicUrl;
    }


    public String getPlateNoPicUrl() {
        return this.plateNoPicUrl;
    }


    public void setPlateNoPicUrl(String plateNoPicUrl) {
        this.plateNoPicUrl = plateNoPicUrl;
    }


    public String getChannelName() {
        return this.channelName;
    }


    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


    public Integer getDays() {
        return this.days;
    }


    public void setDays(Integer days) {
        this.days = days;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Date getInOutTime() {
        return this.inOutTime;
    }


    public void setInOutTime(Date inOutTime) {
        this.inOutTime = inOutTime;
    }


    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public VehicleLeave() {
    }


    public VehicleLeave(String villageCode, Integer status) {
        this.villageCode = villageCode;
        this.status = status;
    }

    public String getDetailId() {
        return this.detailId;
    }


    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }


    public Integer getFlag() {
        return this.flag;
    }


    public void setFlag(Integer flag) {
        this.flag = flag;
    }


    public String getDayEnd() {
        return this.dayEnd;
    }


    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }


    public String getDayBegin() {
        return this.dayBegin;
    }


    public void setDayBegin(String dayBegin) {
        this.dayBegin = dayBegin;
    }


    public Integer getPageNum() {
        return this.pageNum;
    }


    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


    public Integer getPageSize() {
        return this.pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public String getSqlString() {
        return this.sqlString;
    }


    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
