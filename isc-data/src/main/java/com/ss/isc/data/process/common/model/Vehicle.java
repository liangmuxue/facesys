package com.ss.isc.data.process.common.model;

import com.ss.isc.data.baseinfo.common.model.MediaInfo;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIListGroup;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Vehicle 车辆
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 4816489034449096712L;
    private Integer id;
    private String villageCode;
    private String plateNo;
    private Integer plateColor;
    private Integer plateType;
    private Integer carType;
    private String registerName;
    private Integer credentialType;
    private String credentialNo;
    private String contactTel;
    private String buildingNo;
    private String unitNo;
    private String floorNo;
    private String houseNo;
    private Date createTime;
    private Date updateTime;
    private Integer isLeave;
    @Transient
    @NotBlank(message = "{Vehicle.handleId.empty}", groups = {APIAddGroup.class})
    private String handleId;
    @Transient
    @NotBlank(message = "{Vehicle.handleIds.empty}", groups = {APIListGroup.class})
    private String handleIds;
    @Transient
    @NotNull(message = "{Vehicle.status.empty}", groups = {APIAddGroup.class})
    @Range(min = 2L, max = 3L, groups = {APIAddGroup.class}, message = "{Vehicle.state.range}")
    private Integer status;
    @Transient
    @NotNull(message = "{Vehicle.processType.empty}", groups = {APIAddGroup.class})
    private Integer processType;
    @Transient
    @NotBlank(message = "{Vehicle.processUserId.empty}", groups = {APIAddGroup.class})
    private String processUserId;
    private List<MediaInfo> mediaInfos;
    @Transient
    private String remark;
    @Transient
    private String processId;
    @Transient
    private Date inOutTime;
    @Transient
    private Integer days;
    private String platePic;

    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getPlateNo() {
        return this.plateNo;
    }


    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }


    public Integer getPlateColor() {
        return this.plateColor;
    }


    public void setPlateColor(Integer plateColor) {
        this.plateColor = plateColor;
    }


    public Integer getPlateType() {
        return this.plateType;
    }


    public void setPlateType(Integer plateType) {
        this.plateType = plateType;
    }


    public Integer getCarType() {
        return this.carType;
    }


    public void setCarType(Integer carType) {
        this.carType = carType;
    }


    public String getRegisterName() {
        return this.registerName;
    }


    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }


    public Integer getCredentialType() {
        return this.credentialType;
    }


    public void setCredentialType(Integer credentialType) {
        this.credentialType = credentialType;
    }


    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    public String getContactTel() {
        return this.contactTel;
    }


    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }


    public String getBuildingNo() {
        return this.buildingNo;
    }


    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }


    public String getFloorNo() {
        return this.floorNo;
    }


    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }


    public String getHouseNo() {
        return this.houseNo;
    }


    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
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


    public static long getSerialversionuid() {
        return 4816489034449096712L;
    }


    public String getUnitNo() {
        return this.unitNo;
    }


    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }


    public Integer getIsLeave() {
        return this.isLeave;
    }


    public void setIsLeave(Integer isLeave) {
        this.isLeave = isLeave;
    }


    public String getHandleId() {
        return this.handleId;
    }


    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getProcessUserId() {
        return this.processUserId;
    }


    public void setProcessUserId(String processUserId) {
        this.processUserId = processUserId;
    }


    public Integer getProcessType() {
        return this.processType;
    }


    public void setProcessType(Integer processType) {
        this.processType = processType;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getProcessId() {
        return this.processId;
    }


    public void setProcessId(String processId) {
        this.processId = processId;
    }


    public List<MediaInfo> getMediaInfos() {
        return this.mediaInfos;
    }


    public void setMediaInfos(List<MediaInfo> mediaInfos) {
        this.mediaInfos = mediaInfos;
    }


    public Date getInOutTime() {
        return this.inOutTime;
    }


    public void setInOutTime(Date inOutTime) {
        this.inOutTime = inOutTime;
    }


    public Integer getDays() {
        return this.days;
    }


    public void setDays(Integer days) {
        this.days = days;
    }


    public String getPlatePic() {
        return this.platePic;
    }


    public void setPlatePic(String platePic) {
        this.platePic = platePic;
    }

    public String getHandleIds() {
        return handleIds;
    }

    public void setHandleIds(String handleIds) {
        this.handleIds = handleIds;
    }

}
