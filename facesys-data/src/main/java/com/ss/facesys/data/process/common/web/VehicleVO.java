package com.ss.facesys.data.process.common.web;

import com.ss.request.Pagination;

import java.io.Serializable;

/**
 * VehicleDTO 车辆预警
 *
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
public class VehicleVO extends Pagination implements Serializable {

    private static final long serialVersionUID = 6556978701287080281L;
    private Integer id;
    private String beginTime;
    private String endTime;
    private String villageCode;
    private String villageCodes;
    private String plateNumber;
    private Integer status;
    private String userIds;
    private String sqlString;
    private String dayBegin;
    private String dayEnd;
    private Integer inOutType;
    private String handleId;

    /** 处置状态：实时预警查询-1；历史预警查询-0； */
    private Integer state;

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getBeginTime() {
        return this.beginTime;
    }
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
    public String getEndTime() {
        return this.endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
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
    public Integer getInOutType() {
        return this.inOutType;
    }
    public void setInOutType(Integer inOutType) {
        this.inOutType = inOutType;
    }
    public String getHandleId() {
        return this.handleId;
    }
    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "VehicleVO{" +
                "id=" + id +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", villageCode='" + villageCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", status=" + status +
                ", userIds='" + userIds + '\'' +
                ", sqlString='" + sqlString + '\'' +
                ", dayBegin='" + dayBegin + '\'' +
                ", dayEnd='" + dayEnd + '\'' +
                ", inOutType=" + inOutType +
                ", handleId='" + handleId + '\'' +
                '}';
    }

}
