package com.ss.facesys.data.archives.common.model;

import com.ss.facesys.data.baseinfo.common.web.BaseQueryEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
* 车辆实体类
* @author chao
* @create 2019/9/23
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_base_vehicle")
public class Vehicle extends BaseQueryEntity implements Serializable {
    private static final long serialVersionUID = 4816489034449096712L;
    private Integer id;
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
    private Integer isLeave;
    private String carTypeName;
    private String plateColorName;
    private String plateTypeName;
    private String carColorName;
    @Transient
    private Integer inOutType;
    @Transient
    private Date inTime;
    @Transient
    private Date outTime;
    @Transient
    private String userIds;
    @Transient
    private String sqlString;
    @Transient
    private String villageName;
    @Transient
    private String buildingName;
    @Transient
    private String unitName;
    @Transient
    private String adress;
    private String platePic;
    @Transient
    private String villageCodes;
    @Transient
    private String houseId;

    public Integer getId() {
        return this.id;
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


    public String getCarTypeName() {
        return this.carTypeName;
    }


    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }


    public Integer getInOutType() {
        return this.inOutType;
    }


    public void setInOutType(Integer inOutType) {
        this.inOutType = inOutType;
    }


    public Date getInTime() {
        return this.inTime;
    }


    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }


    public Date getOutTime() {
        return this.outTime;
    }


    public void setOutTime(Date outTime) {
        this.outTime = outTime;
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


    public Integer getIsLeave() {
        return this.isLeave;
    }


    public void setIsLeave(Integer isLeave) {
        this.isLeave = isLeave;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
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


    public String getPlateColorName() {
        return this.plateColorName;
    }


    public void setPlateColorName(String plateColorName) {
        this.plateColorName = plateColorName;
    }


    public String getPlateTypeName() {
        return this.plateTypeName;
    }


    public void setPlateTypeName(String plateTypeName) {
        this.plateTypeName = plateTypeName;
    }


    public String getPlatePic() {
        return this.platePic;
    }


    public void setPlatePic(String platePic) {
        this.platePic = platePic;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Integer getCarColor() {
        return carColor;
    }

    public void setCarColor(Integer carColor) {
        this.carColor = carColor;
    }

    public String getCarColorName() {
        return carColorName;
    }

    public void setCarColorName(String carColorName) {
        this.carColorName = carColorName;
    }

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
}
