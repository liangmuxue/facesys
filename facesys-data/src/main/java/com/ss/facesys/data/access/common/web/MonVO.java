package com.ss.facesys.data.access.common.web;

import com.ss.facesys.data.baseinfo.common.web.Device;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIKeyStateGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * MonitorVO
 * @author zhangao
 * @date 2021/2/24
 * @email zhangao@ss-cas.com
 */
public class MonVO {

    @NotNull(message = "monitor.monitorMode.empty", groups = {APIAddGroup.class})
    private Integer monitorMode;
    @NotBlank(message = "monitor.monitorName.empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private String monitorName;
    @NotNull(message = "monitor.monitorType.empty", groups = {APIAddGroup.class})
    private Integer monitorType;
    @NotNull(message = "monitor.monitorProperty.empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer monitorProperty;
    @NotNull(message = "monitor.alarmId .empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer alarmId;
    @NotNull(message = "monitor.startTime .empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private Long startTime;
    @NotNull(message = "monitor.endTime .empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private Long endTime;
    @NotNull(message = "monitor.faceThreshold .empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private Float faceThreshold;
    //@NotNull(message = "monitor.bodyThreshold .empty", groups = {APIAddGroup.class, APIEditGroup.class})
    //private Float bodyThreshold;
    @NotNull(message = "monitor.state .empty", groups = {APIAddGroup.class, APIEditGroup.class,APIKeyStateGroup.class})
    private Integer state;
    private String remark;
    /*@NotNull(message = "monitor.cameraProductCodes.empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private List<String> cameraProductCodes;*/
    private List<Device> devices;
    private String facedbIds;
    private String cameraIds;
    private String personcardDeviceIds;
    private List<String> cameraIdList;
    private List<String> facedbIdList;
    private String userId;
    private String monitorCode;
    private String createUserId;
    private String updateUserId;
    private String deleteUserId;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
    private String facedbIdsStr;
    private String cameraIdsStr;
    private String monitorId;
    private Integer deleteFlag;
    private String orgId;
    private List orgIds;
    private Integer currentPage;
    private Integer pageSize;
    private Long nowTime;
    @NotBlank(message = "monitor.regionCode.empty", groups = {APIAddGroup.class, APIEditGroup.class})
    private String regionCode;
    @NotBlank(message = "monitor.adressIds.empty", groups = {APIAddGroup.class,APIEditGroup.class})
    private String adressIds;

    private String monitorPictures;
    private List <String> ids;

    private Integer cameraId;
    private Integer personcardDeviceId;

    private String facedbId;
    @NotNull(message = "monitor.adressIds.empty", groups = {APIEditGroup.class,APIKeyStateGroup.class, APIDeltGroup.class})
    private Integer id;

    @NotBlank(message = "monitor.policeUserIds.empty", groups = {APIAddGroup.class,APIEditGroup.class})
    private String policeUserIds;



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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public String getFacedbIds() {
        return facedbIds;
    }

    public void setFacedbIds(String facedbIds) {
        this.facedbIds = facedbIds;
    }

    public String getCameraIds() {
        return cameraIds;
    }

    public void setCameraIds(String cameraIds) {
        this.cameraIds = cameraIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getFacedbIdsStr() {
        return facedbIdsStr;
    }

    public void setFacedbIdsStr(String facedbIdsStr) {
        this.facedbIdsStr = facedbIdsStr;
    }

    public String getCameraIdsStr() {
        return cameraIdsStr;
    }

    public void setCameraIdsStr(String cameraIdsStr) {
        this.cameraIdsStr = cameraIdsStr;
    }

    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List orgIds) {
        this.orgIds = orgIds;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getNowTime() {
        return nowTime;
    }

    public void setNowTime(Long nowTime) {
        this.nowTime = nowTime;
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

    public String getMonitorPictures() {
        return monitorPictures;
    }

    public void setMonitorPictures(String monitorPictures) {
        this.monitorPictures = monitorPictures;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public Integer getPersoncardDeviceId() {
        return personcardDeviceId;
    }

    public void setPersoncardDeviceId(Integer personcardDeviceId) {
        this.personcardDeviceId = personcardDeviceId;
    }

    public String getFacedbId() {
        return facedbId;
    }

    public void setFacedbId(String facedbId) {
        this.facedbId = facedbId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceUserIds() {
        return policeUserIds;
    }

    public void setPoliceUserIds(String policeUserIds) {
        this.policeUserIds = policeUserIds;
    }

    public List<String> getCameraIdList() {
        return cameraIdList;
    }

    public void setCameraIdList(List<String> cameraIdList) {
        this.cameraIdList = cameraIdList;
    }

    public List<String> getFacedbIdList() {
        return facedbIdList;
    }

    public void setFacedbIdList(List<String> facedbIdList) {
        this.facedbIdList = facedbIdList;
    }

    public String getPersoncardDeviceIds() {
        return personcardDeviceIds;
    }

    public void setPersoncardDeviceIds(String personcardDeviceIds) {
        this.personcardDeviceIds = personcardDeviceIds;
    }
}
