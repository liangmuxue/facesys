package com.ss.facesys.data.resource.common.model;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 人车关联实体类
 * @author chao
 * @create 2019/9/23
 * @email lishuangchao@ss-cas.com
 **/
public class PeopleVehicle implements Serializable {
    private static final long serialVersionUID = -1796654555172242034L;
    @NotNull(message = "{PeopleVehicle.id.empty}", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIListGroup.class})
    private Integer id;
    @NotBlank(message = "{PeopleVehicle.peopleId.empty}", groups = {com.ss.valide.APIEditGroup.class})
    private String peopleId;
    private String villageCode;
    private String plateNo;
    private Integer plateColor;
    private Integer plateType;
    private Integer carType;
    private Integer carColor;
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
    private String platePic;
    private Integer pageSize;
    private String peopleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Integer getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(Integer plateColor) {
        this.plateColor = plateColor;
    }

    public Integer getPlateType() {
        return plateType;
    }

    public void setPlateType(Integer plateType) {
        this.plateType = plateType;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getCarColor() {
        return carColor;
    }

    public void setCarColor(Integer carColor) {
        this.carColor = carColor;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public Integer getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(Integer credentialType) {
        this.credentialType = credentialType;
    }

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPlatePic() {
        return platePic;
    }

    public void setPlatePic(String platePic) {
        this.platePic = platePic;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
}
