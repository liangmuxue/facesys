package com.ss.facesys.data.collect.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.request.Pagination;
import com.ss.valide.APIKeyStateGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * AlarmDTO 预警信息视图对象
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
public class AlarmVO extends Pagination {

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
    private String villageCodes;
    private Integer topSeq;
    private Integer state;
    @Transient
    @NotBlank(message = "userIds.empty", groups = {APIKeyStateGroup.class})
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    @Override
    public String toString() {
        return "AlarmVO{" +
                "beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", minSim='" + minSim + '\'' +
                ", maxSim='" + maxSim + '\'' +
                ", cameraIds=" + cameraIds +
                ", libraryIds=" + libraryIds +
                ", controllerNO='" + controllerNO + '\'' +
                ", monitorType=" + monitorType +
                ", villageCode='" + villageCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", topSeq=" + topSeq +
                ", state=" + state +
                ", userIds='" + userIds + '\'' +
                ", sqlString='" + sqlString + '\'' +
                ", cameraIdsString='" + cameraIdsString + '\'' +
                ", libraryIdsString='" + libraryIdsString + '\'' +
                '}';
    }

}
