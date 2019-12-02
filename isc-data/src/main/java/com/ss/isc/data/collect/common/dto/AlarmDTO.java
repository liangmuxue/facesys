package com.ss.isc.data.collect.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import javax.persistence.Transient;

/**
 * AlarmDTO 预警信息数据传输对象
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
public class AlarmDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private String minSim;
    private String maxSim;
    private List<String> cameraIds;
    private List<String> libraryIds;
    private String controllerNO;
    private Integer monitorType;
    private String villageCode;
    private Integer pageNum;
    private Integer pageSize;
    private Integer topSeq;
    @Transient
    private String userIds;
    @Transient
    private String sqlString;
    @Transient
    private String cameraIdsString;
    @Transient
    private String libraryIdsString;

    public String getMinSim() {
        return this.minSim;
    }


    public void setMinSim(String minSim) {
        this.minSim = minSim;
    }


    public String getMaxSim() {
        return this.maxSim;
    }


    public void setMaxSim(String maxSim) {
        this.maxSim = maxSim;
    }


    public List<String> getCameraIds() {
        return this.cameraIds;
    }


    public void setCameraIds(List<String> cameraIds) {
        this.cameraIds = cameraIds;
    }


    public List<String> getLibraryIds() {
        return this.libraryIds;
    }


    public void setLibraryIds(List<String> libraryIds) {
        this.libraryIds = libraryIds;
    }


    public String getControllerNO() {
        return this.controllerNO;
    }


    public void setControllerNO(String controllerNO) {
        this.controllerNO = controllerNO;
    }


    public Integer getMonitorType() {
        return this.monitorType;
    }


    public void setMonitorType(Integer monitorType) {
        this.monitorType = monitorType;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
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


    public Integer getTopSeq() {
        return this.topSeq;
    }


    public void setTopSeq(Integer topSeq) {
        this.topSeq = topSeq;
    }


    public Date getBeginTime() {
        return this.beginTime;
    }


    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }


    public Date getEndTime() {
        return this.endTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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


    public String getCameraIdsString() {
        return this.cameraIdsString;
    }


    public void setCameraIdsString(String cameraIdsString) {
        this.cameraIdsString = cameraIdsString;
    }


    public String getLibraryIdsString() {
        return this.libraryIdsString;
    }


    public void setLibraryIdsString(String libraryIdsString) {
        this.libraryIdsString = libraryIdsString;
    }


    public String toString() {
        return "AlarmDTO [beginTime=" + this.beginTime + ", endTime=" + this.endTime + ", minSim=" + this.minSim + ", maxSim=" + this.maxSim + ", cameraIds=" + this.cameraIds + ", libraryIds=" + this.libraryIds + ", controllerNO=" + this.controllerNO + ", monitorType=" + this.monitorType + ", villageCode=" + this.villageCode + ", pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", topSeq=" + this.topSeq + ", userIds=" + this.userIds + ", sqlString=" + this.sqlString + ", cameraIdsString=" + this.cameraIdsString + ", libraryIdsString=" + this.libraryIdsString + "]";
    }

}
