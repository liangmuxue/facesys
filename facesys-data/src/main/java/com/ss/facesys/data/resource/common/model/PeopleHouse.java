package com.ss.facesys.data.resource.common.model;

import com.ss.facesys.util.export.excel.annotation.ExcelField;
import java.io.Serializable;
import java.util.Date;

/**
 * 人房关联实体类
 * @author chao
 * @create 2019/9/23
 * @email lishuangchao@ss-cas.com
 **/
public class PeopleHouse implements Serializable {
    private static final long serialVersionUID = -1796654555172242034L;
    private Integer id;
    private String peopleId;
    private String houseId;
    private String villageCode;
    private String credentialType;
    private String credentialNo;
    private String buildingNo;
    private String unitNo;
    private String houseNo;
    private Integer peopleRelation;
    private Date createTime;
    private Date updateTime;

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

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }


    @ExcelField(title = "小区编号", type = 0, align = 2, sort = 1)
    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    @ExcelField(title = "楼栋编号", type = 0, align = 2, sort = 2)
    public String getBuildingNo() {
        return this.buildingNo;
    }


    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }


    @ExcelField(title = "单元编号", type = 0, align = 2, sort = 3)
    public String getUnitNo() {
        return this.unitNo;
    }


    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }


    @ExcelField(title = "房屋编号", type = 0, align = 2, sort = 4)
    public String getHouseNo() {
        return this.houseNo;
    }


    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }


    @ExcelField(title = "证件类型", type = 0, align = 2, sort = 5)
    public String getCredentialType() {
        return this.credentialType;
    }


    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }


    @ExcelField(title = "证件号码", type = 0, align = 2, sort = 6)
    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    @ExcelField(title = "与房东关系", type = 0, align = 2, sort = 7)
    public Integer getPeopleRelation() {
        return this.peopleRelation;
    }


    public void setPeopleRelation(Integer peopleRelation) {
        this.peopleRelation = peopleRelation;
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


    public String toString() {
        return "PeopleHouse [id=" + this.id + ", peopleId=" + this.peopleId + ", houseId=" + this.houseId + ", villageCode=" + this.villageCode + ", credentialType=" + this.credentialType + ", credentialNo=" + this.credentialNo + ", buildingNo=" + this.buildingNo + ", unitNo=" + this.unitNo + ", houseNo=" + this.houseNo + ", peopleRelation=" + this.peopleRelation + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "]";
    }
}
