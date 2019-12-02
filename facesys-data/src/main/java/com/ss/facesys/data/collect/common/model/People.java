package com.ss.facesys.data.collect.common.model;

import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.util.constant.CommonConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * People
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_people")
public class People {

    private Integer id;
    private String peopleId;
    private String villageCode;
    private Integer peopleType;
    private Integer credentialType;
    private String credentialNo;
    private String peopleName;
    private String gender;
    private String genderCode;
    private String nation;
    private Integer nationCode;
    private String birthDate;
    private String origin;
    private String originCode;
    private String domicile;
    private String domicileCode;
    private Integer streetCode;
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
    private Short source;
    private Long createTime;
    private Long updateTime;
    private String label;
    private String jsonData;
    @Transient
    private String province;
    @Transient
    private String provinceCode;
    @Transient
    private String labelStr;
    private String servicePlace;
    @Transient
    private String value;
    @Transient
    private String type;
    @Transient
    private Integer age;
    @Transient
    private Integer num;
    @Transient
    private String enumName;
    @Transient
    private Integer maxAge;
    @Transient
    private Integer minAge;
    @Transient
    private Integer rowNum;
    @Transient
    private Map<String, String> sqlMap;
    @Transient
    private List<BaseEnums> labels;
    @Transient
    private Integer[] labelArr;
    private Integer isLeave;
    private Long leaveTime;
    @Transient
    private String peopleTypeName;
    @Transient
    private String credentialTypeName;
    @Transient
    private String streetName;

    /** 人员房屋所在楼栋名称 */
    @Transient
    private String buildingName;
    /** 人员房屋所在单元名称 */
    @Transient
    private String unitName;
    /** 人员房屋所在楼层数 */
    @Transient
    private Integer floor;
    /** 人员房屋所在房屋编号 */
    @Transient
    private String houseNo;
    /** 人员房屋地址 */
    @Transient
    private String houseAddress;

    public Integer[] getLabelArr() {
        return this.labelArr;
    }


    public void setLabelArr(Integer[] labelArr) {
        this.labelArr = labelArr;
    }


    public List<BaseEnums> getLabels() {
        return this.labels;
    }


    public void setLabels(List<BaseEnums> labels) {
        this.labels = labels;
    }


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


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public Integer getPeopleType() {
        return this.peopleType;
    }


    public void setPeopleType(Integer peopleType) {
        this.peopleType = peopleType;
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


    public String getPeopleName() {
        return this.peopleName;
    }


    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }


    public String getGender() {
        return this.gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getGenderCode() {
        return this.genderCode;
    }


    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }


    public String getNation() {
        return this.nation;
    }


    public void setNation(String nation) {
        this.nation = nation;
    }


    public Integer getNationCode() {
        return this.nationCode;
    }


    public void setNationCode(Integer nationCode) {
        this.nationCode = nationCode;
    }


    public String getBirthDate() {
        return this.birthDate;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public String getOrigin() {
        return this.origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getOriginCode() {
        return this.originCode;
    }


    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }


    public String getDomicile() {
        return this.domicile;
    }


    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }


    public String getDomicileCode() {
        return this.domicileCode;
    }


    public void setDomicileCode(String domicileCode) {
        this.domicileCode = domicileCode;
    }


    public Integer getStreetCode() {
        return this.streetCode;
    }


    public void setStreetCode(Integer streetCode) {
        this.streetCode = streetCode;
    }


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


    public String getDomicileDetailAddress() {
        return this.domicileDetailAddress;
    }


    public void setDomicileDetailAddress(String domicileDetailAddress) {
        this.domicileDetailAddress = domicileDetailAddress;
    }


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


    public String getResidenceDetailAddres() {
        return this.residenceDetailAddres;
    }


    public void setResidenceDetailAddres(String residenceDetailAddres) {
        this.residenceDetailAddres = residenceDetailAddres;
    }


    public String getResidenceAddress() {
        return this.residenceAddress;
    }


    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }


    public String getEducation() {
        return this.education;
    }


    public void setEducation(String education) {
        this.education = education;
    }


    public Integer getEducationCode() {
        return this.educationCode;
    }


    public void setEducationCode(Integer educationCode) {
        this.educationCode = educationCode;
    }


    public String getPolitical() {
        return this.political;
    }


    public void setPolitical(String political) {
        this.political = political;
    }


    public Integer getPoliticalCode() {
        return this.politicalCode;
    }


    public void setPoliticalCode(Integer politicalCode) {
        this.politicalCode = politicalCode;
    }


    public String getMaritalStatus() {
        return this.maritalStatus;
    }


    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }


    public Integer getMaritalStatusCode() {
        return this.maritalStatusCode;
    }


    public void setMaritalStatusCode(Integer maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }


    public String getSpouseName() {
        return this.spouseName;
    }


    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }


    public String getSpouseNO() {
        return this.spouseNO;
    }


    public void setSpouseNO(String spouseNO) {
        this.spouseNO = spouseNO;
    }


    public String getNationality() {
        return this.nationality;
    }


    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public Integer getNationalityCode() {
        return this.nationalityCode;
    }


    public void setNationalityCode(Integer nationalityCode) {
        this.nationalityCode = nationalityCode;
    }


    public Long getEntryTime() {
        return this.entryTime;
    }


    public void setEntryTime(Long entryTime) {
        this.entryTime = entryTime;
    }


    public String getSurnameEng() {
        return this.surnameEng;
    }


    public void setSurnameEng(String surnameEng) {
        this.surnameEng = surnameEng;
    }


    public String getNameEng() {
        return this.nameEng;
    }


    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }


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


    public Short getSource() {
        return this.source;
    }


    public void setSource(Short source) {
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


    public String getJsonData() {
        return this.jsonData;
    }


    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }


    public String getLabel() {
        return this.label;
    }


    public void setLabel(String label) {
        this.label = label;
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


    public String getLabelStr() {
        return this.labelStr;
    }


    public void setLabelStr(String labelStr) {
        this.labelStr = labelStr;
    }


    public String getServicePlace() {
        return this.servicePlace;
    }


    public void setServicePlace(String servicePlace) {
        this.servicePlace = servicePlace;
    }


    public String getValue() {
        return this.value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public String getType() {
        return this.type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Integer getAge() {
        return this.age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }


    public Integer getNum() {
        return this.num;
    }


    public void setNum(Integer num) {
        this.num = num;
    }


    public Integer getMaxAge() {
        return this.maxAge;
    }


    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }


    public Integer getMinAge() {
        return this.minAge;
    }


    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }


    public Map<String, String> getSqlMap() {
        if (this.sqlMap == null) {
            this.sqlMap = new HashMap(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
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


    public People() {
    }


    public People(Integer minAge, Integer maxAge, String villageCode) {
        this.maxAge = maxAge;
        this.minAge = minAge;
        this.villageCode = villageCode;
    }


    public String getEnumName() {
        return this.enumName;
    }


    public void setEnumName(String enumName) {
        this.enumName = enumName;
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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

}
