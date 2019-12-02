package com.ss.isc.data.resource.common.web;

import com.ss.isc.data.resource.common.model.PeopleHouse;
import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
/**
* 人房关联实体类
* @author chao
* @create 2019/9/23
* @email lishuangchao@ss-cas.com
**/
public class PeopleHouseVO implements Serializable {
    private static final long serialVersionUID = -3633756843823832354L;
    @NotBlank(message = "{peopleHouseVO.houseId.empty}", groups = {com.ss.valide.APIListGroup.class, com.ss.valide.APIEditGroup.class})
    private String houseId;
    @NotBlank(message = "{peopleHouseVO.villageCode.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String villageCode;
    @NotBlank(message = "{peopleHouseVO.buildingNo.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String buildingNo;
    @NotBlank(message = "{peopleHouseVO.unitNo.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String unitNo;
    @NotBlank(message = "{peopleHouseVO.houseNo.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String houseNo;
    private List<PeopleHouse> peopleRelations;
    @NotEmpty(message = "{peopleHouseVO.operationType.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String operationType;
    public static final String OPERATIONTYPE_VIEW = "VIEW";
    public static final String OPERATIONTYPE_SAVE = "SAVE";
    private String credentialNo;
    private String peopleName;
    private Integer pageSize;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getBuildingNo() {
        return this.buildingNo;
    }


    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }


    public String getUnitNo() {
        return this.unitNo;
    }


    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }


    public String getHouseNo() {
        return this.houseNo;
    }


    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }


    public List<PeopleHouse> getPeopleRelations() {
        return this.peopleRelations;
    }


    public void setPeopleRelations(List<PeopleHouse> peopleRelations) {
        this.peopleRelations = peopleRelations;
    }


    public String getOperationType() {
        return this.operationType;
    }


    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }


    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    public String getPeopleName() {
        return this.peopleName;
    }


    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }


    public Integer getPageSize() {
        return this.pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String toString() {
        return "PeopleHouseVO [houseId=" + this.houseId + ", villageCode=" + this.villageCode + ", buildingNo=" + this.buildingNo + ", unitNo=" + this.unitNo + ", houseNo=" + this.houseNo + ", peopleRelations=" + this.peopleRelations + "]";
    }
}
