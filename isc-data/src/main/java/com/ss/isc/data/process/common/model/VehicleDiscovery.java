package com.ss.isc.data.process.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * VehicleDiscovery 车辆感知发现
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_vehicle_discovery")
public class VehicleDiscovery implements Serializable {

    private static final long serialVersionUID = 5213100840596968736L;
    private String id;
    private Integer vehicleId;
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
    @Transient
    private String address;
    @Transient
    private Integer totalDays;
    @Transient
    private List<VehicleRecord> recordList;
    @Transient
    private String villageName;
    @Transient
    private String carType;
    @Transient
    private String plateColor;
    @Transient
    private String plateType;
    @Transient
    private String registerName;
    @Transient
    private String contactTel;
    @Transient
    private String credentialType;
    @Transient
    private String credentialNo;
    @Transient
    private String buildingName;
    @Transient
    private String unitName;
    @Transient
    private String floor;
    @Transient
    private String houseNo;
    @Transient
    private String cameraName;
    @Transient
    private String dayBegin;
    @Transient
    private String dayEnd;
    @Transient
    private String alarmTime;
    @Transient
    private Date lastInTime;
    @Transient
    private Date lastOutTime;
    @Transient
    private String platePic;

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


    public Integer getTotalDays() {
        return this.totalDays;
    }


    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }


    public List<VehicleRecord> getRecordList() {
        return this.recordList;
    }


    public void setRecordList(List<VehicleRecord> recordList) {
        this.recordList = recordList;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public String getCarType() {
        return this.carType;
    }


    public void setCarType(String carType) {
        this.carType = carType;
    }


    public String getPlateColor() {
        return this.plateColor;
    }


    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }


    public String getPlateType() {
        return this.plateType;
    }


    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }


    public String getRegisterName() {
        return this.registerName;
    }


    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }


    public String getContactTel() {
        return this.contactTel;
    }


    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }


    public String getCredentialType() {
        return this.credentialType;
    }


    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }


    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    public String getBuildingName() {
        return this.buildingName;
    }


    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }


    public String getUnitName() {
        return this.unitName;
    }


    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }


    public String getFloor() {
        return this.floor;
    }


    public void setFloor(String floor) {
        this.floor = floor;
    }


    public String getHouseNo() {
        return this.houseNo;
    }


    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }


    public String getCameraName() {
        return this.cameraName;
    }


    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }


    public String getDayBegin() {
        return this.dayBegin;
    }


    public void setDayBegin(String dayBegin) {
        this.dayBegin = dayBegin;
    }


    public String getDayEnd() {
        return this.dayEnd;
    }


    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }


    public String getAlarmTime() {
        return this.alarmTime;
    }


    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }


    public Date getLastInTime() {
        return this.lastInTime;
    }


    public void setLastInTime(Date lastInTime) {
        this.lastInTime = lastInTime;
    }


    public Date getLastOutTime() {
        return this.lastOutTime;
    }


    public void setLastOutTime(Date lastOutTime) {
        this.lastOutTime = lastOutTime;
    }


    public String getPlatePic() {
        return this.platePic;
    }


    public void setPlatePic(String platePic) {
        this.platePic = platePic;
    }


    public Integer getVehicleId() {
        return this.vehicleId;
    }


    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

}
