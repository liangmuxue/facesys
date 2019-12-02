package com.ss.facesys.data.collect.common.model;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "cw_base_company")
public class Company implements Serializable {
    private static final long serialVersionUID = -1632015616853818280L;
    @Id
    private Integer id;
    private String companyCode;
    private String villageCode;
    private String companyName;
    private Integer companyType;
    private Integer companySize;
    private Integer economicType;
    private Integer importantFlag;
    private String companyAdress;
    private String companyRegisterAddress;
    private String companyTel;
    private String organCode;
    private String leaderName;
    private Integer credentialType;
    private String credentialNo;
    private String leaderTel;
    private String companyPic;
    private Integer gisType;
    private Double lon;
    private Double lat;
    private Double alt;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    @Transient
    private Integer countPeople;
    @Transient
    private String enumName;
    @Transient
    private String villageName;
    @Transient
    private String companyTypeName;
    @Transient
    private String companySizeName;
    @Transient
    private String economicTypeName;
    @Transient
    private String importantFlagName;
    @Transient
    private String credentialTypeName;
    @Transient
    private String gisTypeName;
    @Transient
    private Integer rowNum;
    @Transient
    private String userIds;
    @Transient
    private Map<String, String> sqlMap;
    @Transient
    private String dsf;
    @Transient
    private String villageCodes;
    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getCompanyCode() {
        return this.companyCode;
    }


    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getCompanyName() {
        return this.companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public Integer getCompanyType() {
        return this.companyType;
    }


    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }


    public Integer getCompanySize() {
        return this.companySize;
    }


    public void setCompanySize(Integer companySize) {
        this.companySize = companySize;
    }


    public Integer getEconomicType() {
        return this.economicType;
    }


    public void setEconomicType(Integer economicType) {
        this.economicType = economicType;
    }


    public Integer getImportantFlag() {
        return this.importantFlag;
    }


    public void setImportantFlag(Integer importantFlag) {
        this.importantFlag = importantFlag;
    }


    public String getCompanyAdress() {
        return this.companyAdress;
    }


    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }


    public String getCompanyRegisterAddress() {
        return this.companyRegisterAddress;
    }


    public void setCompanyRegisterAddress(String companyRegisterAddress) {
        this.companyRegisterAddress = companyRegisterAddress;
    }


    public String getCompanyTel() {
        return this.companyTel;
    }


    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }


    public String getOrganCode() {
        return this.organCode;
    }


    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }


    public String getLeaderName() {
        return this.leaderName;
    }


    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
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


    public String getLeaderTel() {
        return this.leaderTel;
    }


    public void setLeaderTel(String leaderTel) {
        this.leaderTel = leaderTel;
    }


    public String getCompanyPic() {
        return this.companyPic;
    }


    public void setCompanyPic(String companyPic) {
        this.companyPic = companyPic;
    }


    public Integer getGisType() {
        return this.gisType;
    }


    public void setGisType(Integer gisType) {
        this.gisType = gisType;
    }


    public Double getLon() {
        return this.lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    public Double getLat() {
        return this.lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    public Double getAlt() {
        return this.alt;
    }


    public void setAlt(Double alt) {
        this.alt = alt;
    }


    public String getDescription() {
        return this.description;
    }


    public void setDescription(String description) {
        this.description = description;
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


    public String getEnumName() {
        return this.enumName;
    }


    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public String getCompanyTypeName() {
        return this.companyTypeName;
    }


    public void setCompanyTypeName(String companyTypeName) {
        this.companyTypeName = companyTypeName;
    }


    public String getCompanySizeName() {
        return this.companySizeName;
    }


    public void setCompanySizeName(String companySizeName) {
        this.companySizeName = companySizeName;
    }


    public String getEconomicTypeName() {
        return this.economicTypeName;
    }


    public void setEconomicTypeName(String economicTypeName) {
        this.economicTypeName = economicTypeName;
    }


    public String getImportantFlagName() {
        return this.importantFlagName;
    }


    public void setImportantFlagName(String importantFlagName) {
        this.importantFlagName = importantFlagName;
    }


    public String getCredentialTypeName() {
        return this.credentialTypeName;
    }


    public void setCredentialTypeName(String credentialTypeName) {
        this.credentialTypeName = credentialTypeName;
    }


    public String getGisTypeName() {
        return this.gisTypeName;
    }


    public void setGisTypeName(String gisTypeName) {
        this.gisTypeName = gisTypeName;
    }

    public Integer getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(Integer countPeople) {
        this.countPeople = countPeople;
    }


    public Map<String, String> getSqlMap() {
        if (this.sqlMap == null) {
            this.sqlMap = Maps.newHashMap();
        }
        return this.sqlMap;
    }


    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }


    public Integer getRowNum() {
        return this.rowNum;
    }


    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public String getDsf() {
        return dsf;
    }

    public void setDsf(String dsf) {
        this.dsf = dsf;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }
}
