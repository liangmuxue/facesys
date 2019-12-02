package com.ss.isc.data.archives.common.web;

import com.ss.valide.APIGetsGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * PeopleVO
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
public class PeopleVO {

    @NotNull(message = "{people.id.empty}", groups = {APIGetsGroup.class})
    private Integer id;
    private String peopleId;
    private String idCardPic;
    private String peopleName;
    private String peopleType;
    private String sex;
    private String credentialNo;
    private String nation;
    private String nationality;
    private String education;
    private String label;
    private List<EnumVO> eList;
    private String maritalStatus;
    private String residenceDetailAddres;
    private String phoneNo;
    private String facePic;
    private String villageCode;
    private Integer isLeave;

    /** 当前系统用户编号 */
    @NotBlank(message = "{people.userIds.empty}", groups = {APIGetsGroup.class})
    private String userIds;

    private String idCardPicFull;
    private String facePicFull;

    public List<EnumVO> geteList() {
        return this.eList;
    }


    public void seteList(List<EnumVO> eList) {
        this.eList = eList;
    }


    public String getPeopleId() {
        return this.peopleId;
    }


    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }


    public String getIdCardPic() {
        return this.idCardPic;
    }


    public void setIdCardPic(String idCardPic) {
        this.idCardPic = idCardPic;
    }


    public String getPeopleName() {
        return this.peopleName;
    }


    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }


    public String getPeopleType() {
        return this.peopleType;
    }


    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }


    public String getSex() {
        return this.sex;
    }


    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    public String getNation() {
        return this.nation;
    }


    public void setNation(String nation) {
        this.nation = nation;
    }


    public String getNationality() {
        return this.nationality;
    }


    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public String getEducation() {
        return this.education;
    }


    public void setEducation(String education) {
        this.education = education;
    }


    public String getLabel() {
        return this.label;
    }


    public void setLabel(String label) {
        this.label = label;
    }


    public String getMaritalStatus() {
        return this.maritalStatus;
    }


    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }


    public String getResidenceDetailAddres() {
        return this.residenceDetailAddres;
    }


    public void setResidenceDetailAddres(String residenceDetailAddres) {
        this.residenceDetailAddres = residenceDetailAddres;
    }


    public String getPhoneNo() {
        return this.phoneNo;
    }


    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public String getFacePic() {
        return this.facePic;
    }


    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public Integer getIsLeave() {
        return this.isLeave;
    }


    public void setIsLeave(Integer isLeave) {
        this.isLeave = isLeave;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                ", idCardPic='" + idCardPic + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", peopleType='" + peopleType + '\'' +
                ", sex='" + sex + '\'' +
                ", credentialNo='" + credentialNo + '\'' +
                ", nation='" + nation + '\'' +
                ", nationality='" + nationality + '\'' +
                ", education='" + education + '\'' +
                ", label='" + label + '\'' +
                ", eList=" + eList +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", residenceDetailAddres='" + residenceDetailAddres + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", facePic='" + facePic + '\'' +
                ", villageCode='" + villageCode + '\'' +
                ", isLeave=" + isLeave +
                ", userIds='" + userIds + '\'' +
                ", idCardPicFull='" + idCardPicFull + '\'' +
                ", facePicFull='" + facePicFull + '\'' +
                '}';
    }

}
