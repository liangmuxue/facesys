package com.ss.facesys.data.resource.common.model;

import com.ss.facesys.util.export.excel.annotation.ExcelField;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "cw_base_company")
public class Company
        implements Serializable {
    private static final long serialVersionUID = 2786590431592340916L;
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
    private List<String> ids;
    @Transient
    private Map<String, String> sqlMap;

    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    @ExcelField(title = "统一社会信用代码", type = 0, align = 2, sort = 1)
    public String getCompanyCode() {
        return this.companyCode;
    }


    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    @ExcelField(title = "小区编号", type = 0, align = 2, sort = 2)
    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    @ExcelField(title = "单位名称", type = 0, align = 2, sort = 3)
    public String getCompanyName() {
        return this.companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    @ExcelField(title = "单位类型", type = 0, align = 2, sort = 4)
    public Integer getCompanyType() {
        return this.companyType;
    }


    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }


    @ExcelField(title = "单位规模", type = 0, align = 2, sort = 5)
    public Integer getCompanySize() {
        return this.companySize;
    }


    public void setCompanySize(Integer companySize) {
        this.companySize = companySize;
    }


    @ExcelField(title = "单位经济类型", type = 0, align = 2, sort = 6)
    public Integer getEconomicType() {
        return this.economicType;
    }


    public void setEconomicType(Integer economicType) {
        this.economicType = economicType;
    }


    @ExcelField(title = "重点单位类型", type = 0, align = 2, sort = 7)
    public Integer getImportantFlag() {
        return this.importantFlag;
    }


    public void setImportantFlag(Integer importantFlag) {
        this.importantFlag = importantFlag;
    }


    @ExcelField(title = "单位地址", type = 0, align = 2, sort = 8)
    public String getCompanyAdress() {
        return this.companyAdress;
    }


    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }


    @ExcelField(title = "注册地详址", type = 0, align = 2, sort = 9)
    public String getCompanyRegisterAddress() {
        return this.companyRegisterAddress;
    }


    public void setCompanyRegisterAddress(String companyRegisterAddress) {
        this.companyRegisterAddress = companyRegisterAddress;
    }


    @ExcelField(title = "联系电话(单位)", type = 0, align = 2, sort = 10)
    public String getCompanyTel() {
        return this.companyTel;
    }


    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }


    @ExcelField(title = "组织机构代码", type = 0, align = 2, sort = 11)
    public String getOrganCode() {
        return this.organCode;
    }


    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }


    @ExcelField(title = "法定代表人姓名", type = 0, align = 2, sort = 12)
    public String getLeaderName() {
        return this.leaderName;
    }


    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }


    @ExcelField(title = "证件类型", type = 0, align = 2, sort = 13)
    public Integer getCredentialType() {
        return this.credentialType;
    }


    public void setCredentialType(Integer credentialType) {
        this.credentialType = credentialType;
    }


    @ExcelField(title = "证件号码", type = 0, align = 2, sort = 14)
    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    @ExcelField(title = "联系电话(法人)", type = 0, align = 2, sort = 15)
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


    @ExcelField(title = "坐标体系", type = 0, align = 2, sort = 16)
    public Integer getGisType() {
        return this.gisType;
    }


    public void setGisType(Integer gisType) {
        this.gisType = gisType;
    }


    @ExcelField(title = "单位经度", type = 0, align = 2, sort = 17)
    public Double getLon() {
        return this.lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    @ExcelField(title = "单位纬度", type = 0, align = 2, sort = 18)
    public Double getLat() {
        return this.lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    @ExcelField(title = "单位高度", type = 0, align = 2, sort = 19)
    public Double getAlt() {
        return this.alt;
    }


    public void setAlt(Double alt) {
        this.alt = alt;
    }


    @ExcelField(title = "单位描述", type = 0, align = 2, sort = 20)
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


    public Integer getRowNum() {
        return this.rowNum;
    }


    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
