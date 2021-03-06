package com.ss.facesys.data.system.common.dto;

import com.ss.facesys.data.system.common.model.CollectionCaptureDetail;
import com.ss.facesys.data.system.common.model.CollectionPersoncardDetail;

import java.util.StringJoiner;

/**
 * ImgCollectionResultDTO
 *
 * @author FrancisYs
 * @date 2020/2/20
 * @email yaoshuai@ss-cas.com
 */
public class ImgCollectionResultDTO {

    private Integer id;
    private Integer dataType;
    private Integer dataId;
    private String remark;
    private Long collectionTime;
    private String userId;
    private String collectionUrl;
    private String deviceName;
    private Long dataCreateTime;
    private CollectionCaptureDetail collectionCaptureDetail;
    private CollectionPersoncardDetail collectionPersoncardDetail;
    private String panoramaUrl;
    private Integer age;
    private Integer gender;
    private String genderName;
    private String nationName;
    private Integer maskState;
    private Integer glassesState;
    private Integer sunGlassesState;
    private String peopleName;
    private Integer nation;
    private String birthday;
    private String cardNo;
    private String peopleAddress;
    private String issuanceAuthority;
    private Long cardStartTime;
    private Long cardEndTime;
    private String address;
    private String faceImg;
    private Integer minority;
    private Float recogScore;
    private Integer resultCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Long collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCollectionUrl() {
        return collectionUrl;
    }

    public void setCollectionUrl(String collectionUrl) {
        this.collectionUrl = collectionUrl;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getDataCreateTime() {
        return dataCreateTime;
    }

    public void setDataCreateTime(Long dataCreateTime) {
        this.dataCreateTime = dataCreateTime;
    }

    public CollectionCaptureDetail getCollectionCaptureDetail() {
        return collectionCaptureDetail;
    }

    public void setCollectionCaptureDetail(CollectionCaptureDetail collectionCaptureDetail) {
        this.collectionCaptureDetail = collectionCaptureDetail;
    }

    public CollectionPersoncardDetail getCollectionPersoncardDetail() {
        return collectionPersoncardDetail;
    }

    public void setCollectionPersoncardDetail(CollectionPersoncardDetail collectionPersoncardDetail) {
        this.collectionPersoncardDetail = collectionPersoncardDetail;
    }

    public String getPanoramaUrl() {
        return panoramaUrl;
    }

    public void setPanoramaUrl(String panoramaUrl) {
        this.panoramaUrl = panoramaUrl;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public Integer getMaskState() {
        return maskState;
    }

    public void setMaskState(Integer maskState) {
        this.maskState = maskState;
    }

    public Integer getGlassesState() {
        return glassesState;
    }

    public void setGlassesState(Integer glassesState) {
        this.glassesState = glassesState;
    }

    public Integer getSunGlassesState() {
        return sunGlassesState;
    }

    public void setSunGlassesState(Integer sunGlassesState) {
        this.sunGlassesState = sunGlassesState;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPeopleAddress() {
        return peopleAddress;
    }

    public void setPeopleAddress(String peopleAddress) {
        this.peopleAddress = peopleAddress;
    }

    public String getIssuanceAuthority() {
        return issuanceAuthority;
    }

    public void setIssuanceAuthority(String issuanceAuthority) {
        this.issuanceAuthority = issuanceAuthority;
    }

    public Long getCardStartTime() {
        return cardStartTime;
    }

    public void setCardStartTime(Long cardStartTime) {
        this.cardStartTime = cardStartTime;
    }

    public Long getCardEndTime() {
        return cardEndTime;
    }

    public void setCardEndTime(Long cardEndTime) {
        this.cardEndTime = cardEndTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public Integer getMinority() {
        return minority;
    }

    public void setMinority(Integer minority) {
        this.minority = minority;
    }

    public Float getRecogScore() {
        return recogScore;
    }

    public void setRecogScore(Float recogScore) {
        this.recogScore = recogScore;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }
}
