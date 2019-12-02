package com.ss.facesys.data.resource.common.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.util.export.excel.annotation.ExcelField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 实有人口实体
 *
 * @author FrancisYs
 * @date 2019/08/09
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_people")
public class People implements Serializable {

    private static final long serialVersionUID = 5034187675796408712L;
    @Id
    private Integer id;
    private String peopleId;
    private String villageCode;
    private Integer peopleType;
    private Integer credentialType;
    private String credentialNo;
    private String peopleName;
    private String gender;
    private Integer genderCode;
    private String nation;
    private Integer nationCode;
    private String birthDate;
    private String origin;
    private String originCode;
    private String domicile;
    private String domicileCode;
    private String streetCode;
    private String domicileRoadName;
    private String domicileRoadCode;
    private String domicileDetailAddress;
    private String domicileAddress;
    private String residence;
    private String residenceCode;
    private String residenceRoadName;
    private String residenceRoadCode;
    private String residenceDetailAddres;
    private String residenceAddress;
    private String education;
    private Integer educationCode;
    private String political;
    private Integer politicalCode;
    private String maritalStatus;
    private Integer maritalStatusCode;
    private String spouseName;
    private String spouseNO;
    private String nationality;
    private Integer nationalityCode;
    private Long entryTime;
    private String surnameEng;
    private String nameEng;
    private String phoneNo;
    private String idCardPic;
    private String facePic;
    private Integer source;
    private Long createTime;
    private Long updateTime;
    private String label;
    private String servicePlace;
    private Integer isLeave;
    private Long leaveTime;
    private String jsonData;
    @Column(name = "delete_flag")
    private Integer deleteFlag;
    @Transient
    private Integer rowNum;
    @Transient
    private Map<String, String> sqlMap;
    @Transient
    private List<BaseEnums> labels;
    @Transient
    private Integer[] labelArr;
    @Transient
    private String villageName;
    @Transient
    private String peopleTypeName;
    @Transient
    private String credentialTypeName;
    @Transient
    private String streetName;
    @Transient
    private String sourceName;
    @Transient
    private String province;
    @Transient
    private String provinceCode;
    @Transient
    private String facedbfaceId;
    @Transient
    private String facedbId;
    @Transient
    private String idCardPicBaseString;
    @Transient
    private String facePicBaseString;
    @Transient
    private String userIds;

    /**
     * 收藏状态
     */
    @Transient
    private Integer collectStatus;
    /**
     * 人口人像库关联表主键id
     */
    @Transient
    private String ssFacedbPeopleId;
    /**
     * 智能分析处置主键
     */
    @Transient
    private String recordId;

    @Transient
    private String idCardPicFull;
    @Transient
    private String facePicFull;

    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getPeopleId() {
        return this.peopleId;
    }


    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }


    @ExcelField(title = "小区编号", type = 0, align = 2, sort = 1)
    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    @ExcelField(title = "户籍类别", type = 0, align = 2, sort = 2)
    public Integer getPeopleType() {
        return this.peopleType;
    }


    public void setPeopleType(Integer peopleType) {
        this.peopleType = peopleType;
    }


    @ExcelField(title = "证件类型", type = 0, align = 2, sort = 3)
    public Integer getCredentialType() {
        return this.credentialType;
    }


    public void setCredentialType(Integer credentialType) {
        this.credentialType = credentialType;
    }


    @ExcelField(title = "证件号码", type = 0, align = 2, sort = 4)
    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    @ExcelField(title = "姓名", type = 0, align = 2, sort = 5)
    public String getPeopleName() {
        return this.peopleName;
    }


    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }


    @ExcelField(title = "性别", type = 0, align = 2, sort = 6)
    public String getGender() {
        return this.gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    @ExcelField(title = "性别代码", type = 0, align = 2, sort = 7)
    public Integer getGenderCode() {
        return this.genderCode;
    }


    public void setGenderCode(Integer genderCode) {
        this.genderCode = genderCode;
    }


    @ExcelField(title = "民族", type = 0, align = 2, sort = 8)
    public String getNation() {
        return this.nation;
    }


    public void setNation(String nation) {
        this.nation = nation;
    }


    @ExcelField(title = "民族代码", type = 0, align = 2, sort = 9)
    public Integer getNationCode() {
        return this.nationCode;
    }


    public void setNationCode(Integer nationCode) {
        this.nationCode = nationCode;
    }


    @ExcelField(title = "出生日期", type = 0, align = 2, sort = 10)
    public String getBirthDate() {
        return this.birthDate;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    @ExcelField(title = "籍贯市", type = 0, align = 2, sort = 11)
    public String getOrigin() {
        return this.origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    @ExcelField(title = "籍贯市代码", type = 0, align = 2, sort = 12)
    public String getOriginCode() {
        return this.originCode;
    }


    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }


    @ExcelField(title = "户籍地行政区划", type = 0, align = 2, sort = 13)
    public String getDomicile() {
        return this.domicile;
    }


    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }


    @ExcelField(title = "户籍地行政区划代码", type = 0, align = 2, sort = 14)
    public String getDomicileCode() {
        return this.domicileCode;
    }


    public void setDomicileCode(String domicileCode) {
        this.domicileCode = domicileCode;
    }


    @ExcelField(title = "户籍所在街道", type = 0, align = 2, sort = 15)
    public String getStreetCode() {
        return this.streetCode;
    }


    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }


    @ExcelField(title = "户籍地路名", type = 0, align = 2, sort = 16)
    public String getDomicileRoadName() {
        return this.domicileRoadName;
    }


    public void setDomicileRoadName(String domicileRoadName) {
        this.domicileRoadName = domicileRoadName;
    }


    public String getDomicileRoadCode() {
        return this.domicileRoadCode;
    }


    public void setDomicileRoadCode(String domicileRoadCode) {
        this.domicileRoadCode = domicileRoadCode;
    }


    @ExcelField(title = "户籍地详址", type = 0, align = 2, sort = 17)
    public String getDomicileDetailAddress() {
        return this.domicileDetailAddress;
    }


    public void setDomicileDetailAddress(String domicileDetailAddress) {
        this.domicileDetailAddress = domicileDetailAddress;
    }


    @ExcelField(title = "户籍地址", type = 0, align = 2, sort = 18)
    public String getDomicileAddress() {
        return this.domicileAddress;
    }


    public void setDomicileAddress(String domicileAddress) {
        this.domicileAddress = domicileAddress;
    }


    public String getResidence() {
        return this.residence;
    }


    public void setResidence(String residence) {
        this.residence = residence;
    }


    public String getResidenceCode() {
        return this.residenceCode;
    }


    public void setResidenceCode(String residenceCode) {
        this.residenceCode = residenceCode;
    }


    public String getResidenceRoadName() {
        return this.residenceRoadName;
    }


    public void setResidenceRoadName(String residenceRoadName) {
        this.residenceRoadName = residenceRoadName;
    }


    public String getResidenceRoadCode() {
        return this.residenceRoadCode;
    }


    public void setResidenceRoadCode(String residenceRoadCode) {
        this.residenceRoadCode = residenceRoadCode;
    }


    @ExcelField(title = "居住地详址", type = 0, align = 2, sort = 19)
    public String getResidenceDetailAddres() {
        return this.residenceDetailAddres;
    }


    public void setResidenceDetailAddres(String residenceDetailAddres) {
        this.residenceDetailAddres = residenceDetailAddres;
    }


    @ExcelField(title = "居住地址", type = 0, align = 2, sort = 20)
    public String getResidenceAddress() {
        return this.residenceAddress;
    }


    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }


    @ExcelField(title = "文化程度", type = 0, align = 2, sort = 21)
    public String getEducation() {
        return this.education;
    }


    public void setEducation(String education) {
        this.education = education;
    }


    @ExcelField(title = "文化程度代码", type = 0, align = 2, sort = 22)
    public Integer getEducationCode() {
        return this.educationCode;
    }


    public void setEducationCode(Integer educationCode) {
        this.educationCode = educationCode;
    }


    @ExcelField(title = "政治面貌", type = 0, align = 2, sort = 23)
    public String getPolitical() {
        return this.political;
    }


    public void setPolitical(String political) {
        this.political = political;
    }


    @ExcelField(title = "政治面貌代码", type = 0, align = 2, sort = 24)
    public Integer getPoliticalCode() {
        return this.politicalCode;
    }


    public void setPoliticalCode(Integer politicalCode) {
        this.politicalCode = politicalCode;
    }


    @ExcelField(title = "婚姻状况", type = 0, align = 2, sort = 25)
    public String getMaritalStatus() {
        return this.maritalStatus;
    }


    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }


    @ExcelField(title = "婚姻状况代码", type = 0, align = 2, sort = 26)
    public Integer getMaritalStatusCode() {
        return this.maritalStatusCode;
    }


    public void setMaritalStatusCode(Integer maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }


    @ExcelField(title = "配偶姓名", type = 0, align = 2, sort = 27)
    public String getSpouseName() {
        return this.spouseName;
    }


    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }


    @ExcelField(title = "配偶证件号码", type = 0, align = 2, sort = 28)
    public String getSpouseNO() {
        return this.spouseNO;
    }


    public void setSpouseNO(String spouseNO) {
        this.spouseNO = spouseNO;
    }


    @ExcelField(title = "国籍", type = 0, align = 2, sort = 29)
    public String getNationality() {
        return this.nationality;
    }


    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    @ExcelField(title = "国家代码", type = 0, align = 2, sort = 30)
    public Integer getNationalityCode() {
        return this.nationalityCode;
    }


    public void setNationalityCode(Integer nationalityCode) {
        this.nationalityCode = nationalityCode;
    }


    @ExcelField(title = "入境时间", type = 0, align = 2, sort = 31)
    public Long getEntryTime() {
        return this.entryTime;
    }


    public void setEntryTime(Long entryTime) {
        this.entryTime = entryTime;
    }


    @ExcelField(title = "英文姓", type = 0, align = 2, sort = 32)
    public String getSurnameEng() {
        return this.surnameEng;
    }


    public void setSurnameEng(String surnameEng) {
        this.surnameEng = surnameEng;
    }


    @ExcelField(title = "英文名", type = 0, align = 2, sort = 33)
    public String getNameEng() {
        return this.nameEng;
    }


    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }


    @ExcelField(title = "手机号码", type = 0, align = 2, sort = 34)
    public String getPhoneNo() {
        return this.phoneNo;
    }


    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public String getIdCardPic() {
        return this.idCardPic;
    }


    public void setIdCardPic(String idCardPic) {
        this.idCardPic = idCardPic;
    }


    public String getFacePic() {
        return this.facePic;
    }


    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }


    @ExcelField(title = "数据来源", type = 0, align = 2, sort = 35)
    public Integer getSource() {
        return this.source;
    }


    public void setSource(Integer source) {
        this.source = source;
    }


    public Long getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


    public Long getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }


    @ExcelField(title = "人员标签", type = 0, align = 2, sort = 36)
    public String getLabel() {
        return this.label;
    }


    public void setLabel(String label) {
        this.label = label;
    }


    public String getServicePlace() {
        return this.servicePlace;
    }


    public void setServicePlace(String servicePlace) {
        this.servicePlace = servicePlace;
    }


    public Integer getIsLeave() {
        return this.isLeave;
    }


    public void setIsLeave(Integer isLeave) {
        this.isLeave = isLeave;
    }


    public Long getLeaveTime() {
        return this.leaveTime;
    }


    public void setLeaveTime(Long leaveTime) {
        this.leaveTime = leaveTime;
    }


    public String getJsonData() {
        return this.jsonData;
    }


    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
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


    public List<BaseEnums> getLabels() {
        if (this.labels == null) {
            this.labels = Lists.newArrayList();
        }
        return this.labels;
    }


    public void setLabels(List<BaseEnums> labels) {
        this.labels = labels;
    }


    public Integer[] getLabelArr() {
        return this.labelArr;
    }


    public void setLabelArr(Integer[] labelArr) {
        this.labelArr = labelArr;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public String getPeopleTypeName() {
        return this.peopleTypeName;
    }


    public void setPeopleTypeName(String peopleTypeName) {
        this.peopleTypeName = peopleTypeName;
    }


    public String getCredentialTypeName() {
        return this.credentialTypeName;
    }


    public void setCredentialTypeName(String credentialTypeName) {
        this.credentialTypeName = credentialTypeName;
    }


    public String getStreetName() {
        return this.streetName;
    }


    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }


    public String getSourceName() {
        return this.sourceName;
    }


    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }


    public String getProvince() {
        return this.province;
    }


    public void setProvince(String province) {
        this.province = province;
    }


    public String getProvinceCode() {
        return this.provinceCode;
    }


    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }


    public String getFacedbfaceId() {
        return this.facedbfaceId;
    }


    public void setFacedbfaceId(String facedbfaceId) {
        this.facedbfaceId = facedbfaceId;
    }


    public String getFacedbId() {
        return this.facedbId;
    }


    public void setFacedbId(String facedbId) {
        this.facedbId = facedbId;
    }


    public String getIdCardPicBaseString() {
        return this.idCardPicBaseString;
    }


    public void setIdCardPicBaseString(String idCardPicBaseString) {
        this.idCardPicBaseString = idCardPicBaseString;
    }


    public String getFacePicBaseString() {
        return this.facePicBaseString;
    }


    public void setFacePicBaseString(String facePicBaseString) {
        this.facePicBaseString = facePicBaseString;
    }

    public Integer getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Integer collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getSsFacedbPeopleId() {
        return ssFacedbPeopleId;
    }

    public void setSsFacedbPeopleId(String ssFacedbPeopleId) {
        this.ssFacedbPeopleId = ssFacedbPeopleId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getIdCardPicFull() {
        return idCardPicFull;
    }

    public void setIdCardPicFull(String idCardPicFull) {
        this.idCardPicFull = idCardPicFull;
    }

    public String getFacePicFull() {
        return facePicFull;
    }

    public void setFacePicFull(String facePicFull) {
        this.facePicFull = facePicFull;
    }

}
