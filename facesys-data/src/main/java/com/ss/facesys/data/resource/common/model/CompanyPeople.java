package com.ss.facesys.data.resource.common.model;

import com.ss.facesys.util.export.excel.annotation.ExcelField;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Table(name = "cw_base_company_people")
public class CompanyPeople
        implements Serializable {
    private static final long serialVersionUID = 2804347929240689471L;
    @Id
    private Integer id;
    private String villageCode;
    private String companyCode;
    private String peopleName;
    private String gender;
    private Integer genderCode;
    private Integer credentialType;
    private String credentialNo;
    private String domicileAddress;
    private String residenceAdress;
    private String phone;
    private Date entrydate;
    private String jobTitle;
    private String idCardPicUrl;
    private String remark;
    private Date createTime;
    private Date updateTime;
    @Transient
    private String nation;
    @Transient
    private String nationCode;
    @Transient
    private String companyAdress;
    @Transient
    private String companyName;
    @Transient
    private Integer pageNum;
    @Transient
    private Integer pageSize;

    public Integer getPageNum() {
        return this.pageNum;
    }


    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


    public Integer getPageSize() {
        return this.pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getCompanyName() {
        return this.companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getNation() {
        return this.nation;
    }


    public void setNation(String nation) {
        this.nation = nation;
    }


    public String getCompanyAdress() {
        return this.companyAdress;
    }


    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }


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


    public String getCompanyCode() {
        return this.companyCode;
    }


    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    @ExcelField(title = "人员姓名", type = 0, align = 2, sort = 1)
    public String getPeopleName() {
        return this.peopleName;
    }


    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }


    @ExcelField(title = "性别", type = 0, align = 2, sort = 2)
    public String getGender() {
        return this.gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    @ExcelField(title = "性别代码", type = 0, align = 2, sort = 3)
    public Integer getGenderCode() {
        return this.genderCode;
    }


    public void setGenderCode(Integer genderCode) {
        this.genderCode = genderCode;
    }


    @ExcelField(title = "证据类型", type = 0, align = 2, sort = 4)
    public Integer getCredentialType() {
        return this.credentialType;
    }


    public void setCredentialType(Integer credentialType) {
        this.credentialType = credentialType;
    }


    @ExcelField(title = "证据号码", type = 0, align = 2, sort = 5)
    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    @ExcelField(title = "户籍地", type = 0, align = 2, sort = 6)
    public String getDomicileAddress() {
        return this.domicileAddress;
    }


    public void setDomicileAddress(String domicileAddress) {
        this.domicileAddress = domicileAddress;
    }


    @ExcelField(title = "居住地", type = 0, align = 2, sort = 7)
    public String getResidenceAdress() {
        return this.residenceAdress;
    }


    public void setResidenceAdress(String residenceAdress) {
        this.residenceAdress = residenceAdress;
    }


    @ExcelField(title = "联系方式", type = 0, align = 2, sort = 8)
    public String getPhone() {
        return this.phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    @ExcelField(title = "入职日期", type = 0, align = 2, sort = 9)
    public Date getEntrydate() {
        return this.entrydate;
    }


    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }


    @ExcelField(title = "职位名称", type = 0, align = 2, sort = 10)
    public String getJobTitle() {
        return this.jobTitle;
    }


    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    public String getIdCardPicUrl() {
        return this.idCardPicUrl;
    }


    public void setIdCardPicUrl(String idCardPicUrl) {
        this.idCardPicUrl = idCardPicUrl;
    }


    @ExcelField(title = "备注", type = 0, align = 2, sort = 11)
    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }
}
