package com.ss.facesys.data.process.common.web;

import com.ss.facesys.data.baseinfo.common.model.MediaInfo;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIGetsGroup;
import com.ss.valide.APIListGroup;
import com.ss.valide.APIOpStatusGroup;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * PeopleProcessVO 人口预警处理
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
public class PeopleProcessVO implements Serializable {

    private static final long serialVersionUID = -559672819535048331L;

    @NotBlank(message = "{peopleProcessVO.recordId.empty}", groups = {APIGetsGroup.class, APIAddGroup.class})
    private String recordId;
    @NotBlank(message = "{peopleProcessVO.recordIds.empty}", groups = {APIListGroup.class})
    private String recordIds;
    private Date recordTime;
    @NotNull(message = "{peopleProcessVO.processType.empty}", groups = {APIAddGroup.class})
    private Integer processType;
    @NotBlank(message = "{peopleProcessVO.processUserId.empty}", groups = {APIAddGroup.class, APIListGroup.class, APIOpStatusGroup.class})
    private String processUserId;
    private String remark;
    @NotNull(message = "{peopleProcessVO.state.empty}", groups = {APIAddGroup.class, APIListGroup.class})
    @Range(min = 2L, max = 3L, groups = {APIAddGroup.class}, message = "{peopleProcessVO.state.range}")
    private Integer state;
    private List<MediaInfo> mediaInfos;
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
    private Date entryTime;
    private String surnameEng;
    private String nameEng;
    private String phoneNo;
    private String idCardPic;
    private String facePic;
    private Short source;
    private Date createTime;
    private Date updateTime;
    private String label;
    private String servicePlace;
    private Integer[] labelValue;

    /**
     * 一键处置操作类型：1-人口预警，2-车辆预警
     */
    @NotNull(message = "{process.operationType.empty}", groups = {APIOpStatusGroup.class})
    private Integer operationType;

    public String getRecordId() {
        return this.recordId;
    }


    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }


    public Date getRecordTime() {
        return this.recordTime;
    }


    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }


    public Integer getProcessType() {
        return this.processType;
    }


    public void setProcessType(Integer processType) {
        this.processType = processType;
    }


    public String getProcessUserId() {
        return this.processUserId;
    }


    public void setProcessUserId(String processUserId) {
        this.processUserId = processUserId;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public List<MediaInfo> getMediaInfos() {
        return this.mediaInfos;
    }


    public void setMediaInfos(List<MediaInfo> mediaInfos) {
        this.mediaInfos = mediaInfos;
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


    public Date getEntryTime() {
        return this.entryTime;
    }


    public void setEntryTime(Date entryTime) {
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


    public Integer[] getLabelValue() {
        return this.labelValue;
    }


    public void setLabelValue(Integer[] labelValue) {
        this.labelValue = labelValue;
    }

    public String getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(String recordIds) {
        this.recordIds = recordIds;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return "PeopleProcessVO{" +
                "recordId='" + recordId + '\'' +
                ", recordIds='" + recordIds + '\'' +
                ", recordTime=" + recordTime +
                ", processType=" + processType +
                ", processUserId='" + processUserId + '\'' +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                ", mediaInfos=" + mediaInfos +
                ", peopleId='" + peopleId + '\'' +
                ", villageCode='" + villageCode + '\'' +
                ", peopleType=" + peopleType +
                ", credentialType=" + credentialType +
                ", credentialNo='" + credentialNo + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", gender='" + gender + '\'' +
                ", genderCode='" + genderCode + '\'' +
                ", nation='" + nation + '\'' +
                ", nationCode=" + nationCode +
                ", birthDate='" + birthDate + '\'' +
                ", origin='" + origin + '\'' +
                ", originCode='" + originCode + '\'' +
                ", domicile='" + domicile + '\'' +
                ", domicileCode='" + domicileCode + '\'' +
                ", streetCode=" + streetCode +
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
                ", labelValue=" + Arrays.toString(labelValue) +
                ", operationType=" + operationType +
                '}';
    }

}
