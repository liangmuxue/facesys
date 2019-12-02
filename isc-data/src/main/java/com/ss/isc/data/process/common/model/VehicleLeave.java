package com.ss.isc.data.process.common.model;

import com.ss.isc.data.baseinfo.common.model.MediaInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * VehicleLeave 车辆感知离开
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
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
    private String days;
    private String status;
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
    private List<MediaInfo> mList;
    @Transient
    private String dayBegin;
    @Transient
    private Date lastInTime;
    @Transient
    private Date lastOutTime;

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


    public String getDays() {
        return this.days;
    }


    public void setDays(String days) {
        this.days = days;
    }


    public String getStatus() {
        return this.status;
    }


    public void setStatus(String status) {
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


    public List<MediaInfo> getmList() {
        return this.mList;
    }


    public void setmList(List<MediaInfo> mList) {
        this.mList = mList;
    }


    public String getDayBegin() {
        return this.dayBegin;
    }


    public void setDayBegin(String dayBegin) {
        this.dayBegin = dayBegin;
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

}
