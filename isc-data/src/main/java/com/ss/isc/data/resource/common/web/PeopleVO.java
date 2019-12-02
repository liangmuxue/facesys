package com.ss.isc.data.resource.common.web;

import com.ss.valide.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * PeopleVO：实有人口视图对象
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
public class PeopleVO implements Serializable {

    private static final long serialVersionUID = 3782768362477822534L;

    @NotNull(message = "{people.id.empty}", groups = {APIEditGroup.class, APIGetsGroup.class})
    private Integer id;
    private String peopleId;
    @NotBlank(message = "{people.villageCode.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String villageCode;
    @NotNull(message = "{people.peopleType.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer peopleType;
    @NotNull(message = "{people.credentialType.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer credentialType;
    @NotBlank(message = "{people.credentialNo.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String credentialNo;
    @NotBlank(message = "{people.peopleName.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String peopleName;
    private String gender;
    @NotNull(message = "{people.genderCode.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer genderCode;
    private String nation;
    private Integer nationCode;
    @NotBlank(message = "{people.birthDate.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
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

    /** 多个实有人口主键 */
    @NotBlank(message = "{people.idArr.empty}", groups = {APIDeltGroup.class})
    private String idArr;
    /** 多个实有人口编号 */
    @NotBlank(message = "{people.peopleIds.empty}", groups = {APIOpStatusGroup.class})
    private String peopleIds;
    /** 当前系统用户编号 */
    @NotBlank(message = "{people.userIds.empty}", groups = {APIOpStatusGroup.class})
    private String userIds;
    /** 实有人口收藏操作类型：0-取消收藏；1-收藏 */
    @NotNull(message = "{people.operationType.empty}", groups = {APIOpStatusGroup.class})
    private Integer operationType;
    /** 智能分析处置主键 */
    private String recordId;

    private String idCardPicFull;
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


    public Integer getGenderCode() {
        return this.genderCode;
    }


    public void setGenderCode(Integer genderCode) {
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


    public String getStreetCode() {
        return this.streetCode;
    }


    public void setStreetCode(String streetCode) {
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


    public String getIdArr() {
        return idArr;
    }

    public void setIdArr(String idArr) {
        this.idArr = idArr;
    }

    public String getPeopleIds() {
        return peopleIds;
    }

    public void setPeopleIds(String peopleIds) {
        this.peopleIds = peopleIds;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
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

    @Override
    public String toString() {
        return "PeopleVO{" +
                "id=" + id +
                ", peopleId='" + peopleId + '\'' +
                ", villageCode='" + villageCode + '\'' +
                ", peopleType=" + peopleType +
                ", credentialType=" + credentialType +
                ", credentialNo='" + credentialNo + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", gender='" + gender + '\'' +
                ", genderCode=" + genderCode +
                ", nation='" + nation + '\'' +
                ", nationCode=" + nationCode +
                ", birthDate='" + birthDate + '\'' +
                ", origin='" + origin + '\'' +
                ", originCode='" + originCode + '\'' +
                ", domicile='" + domicile + '\'' +
                ", domicileCode='" + domicileCode + '\'' +
                ", streetCode='" + streetCode + '\'' +
                ", domicileRoadName='" + domicileRoadName + '\'' +
                ", domicileRoadCode='" + domicileRoadCode + '\'' +
                ", domicileDetailAddress='" + domicileDetailAddress + '\'' +
                ", domicileAddress='" + domicileAddress + '\'' +
                ", residence='" + residence + '\'' +
                ", residenceCode='" + residenceCode + '\'' +
                ", residenceRoadName='" + residenceRoadName + '\'' +
                ", residenceRoadCode='" + residenceRoadCode + '\'' +
                ", residenceDetailAddres='" + residenceDetailAddres + '\'' +
                ", residenceAddress='" + residenceAddress + '\'' +
                ", education='" + education + '\'' +
                ", educationCode=" + educationCode +
                ", political='" + political + '\'' +
                ", politicalCode=" + politicalCode +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", maritalStatusCode=" + maritalStatusCode +
                ", spouseName='" + spouseName + '\'' +
                ", spouseNO='" + spouseNO + '\'' +
                ", nationality='" + nationality + '\'' +
                ", nationalityCode=" + nationalityCode +
                ", entryTime=" + entryTime +
                ", surnameEng='" + surnameEng + '\'' +
                ", nameEng='" + nameEng + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", idCardPic='" + idCardPic + '\'' +
                ", facePic='" + facePic + '\'' +
                ", source=" + source +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", label='" + label + '\'' +
                ", servicePlace='" + servicePlace + '\'' +
                ", isLeave=" + isLeave +
                ", leaveTime=" + leaveTime +
                ", jsonData='" + jsonData + '\'' +
                ", idArr='" + idArr + '\'' +
                ", peopleIds='" + peopleIds + '\'' +
                ", userIds='" + userIds + '\'' +
                ", operationType='" + operationType + '\'' +
                '}';
    }

}
