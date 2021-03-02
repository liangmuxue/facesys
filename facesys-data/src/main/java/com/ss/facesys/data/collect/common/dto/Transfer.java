package com.ss.facesys.data.collect.common.dto;

/**
 * com.ss.facesys.data.collect.common.dto
 *
 * @author 李爽超 chao
 * @create 2021/02/25
 * @email lishuangchao@ss-cas.com
 **/
public class Transfer {

    private Integer id;
    private String type;
    private String deviceName;
    private Long captureTime;
    private String captureUrl;
    private String panoramaUrl;
    private String faceUrl;
    private String peopleName;
    private String cardNo;
    private String facedbName;
    private Float recogScore;
    private String genderName;
    private String ageTypeName;
    private String nationName;
    private Integer glasses;
    private Integer sunglasses;
    private Integer mask;
    private Integer minority;
    private Integer todayCaptureTotal;
    private Integer thisMonthCaptureTotal;
    private Integer todayAlarmTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Long captureTime) {
        this.captureTime = captureTime;
    }

    public String getCaptureUrl() {
        return captureUrl;
    }

    public void setCaptureUrl(String captureUrl) {
        this.captureUrl = captureUrl;
    }

    public String getPanoramaUrl() {
        return panoramaUrl;
    }

    public void setPanoramaUrl(String panoramaUrl) {
        this.panoramaUrl = panoramaUrl;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getFacedbName() {
        return facedbName;
    }

    public void setFacedbName(String facedbName) {
        this.facedbName = facedbName;
    }

    public Float getRecogScore() {
        return recogScore;
    }

    public void setRecogScore(Float recogScore) {
        this.recogScore = recogScore;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getAgeTypeName() {
        return ageTypeName;
    }

    public void setAgeTypeName(String ageTypeName) {
        this.ageTypeName = ageTypeName;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public Integer getGlasses() {
        return glasses;
    }

    public void setGlasses(Integer glasses) {
        this.glasses = glasses;
    }

    public Integer getSunglasses() {
        return sunglasses;
    }

    public void setSunglasses(Integer sunglasses) {
        this.sunglasses = sunglasses;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Integer getMinority() {
        return minority;
    }

    public void setMinority(Integer minority) {
        this.minority = minority;
    }

    public Integer getTodayCaptureTotal() {
        return todayCaptureTotal;
    }

    public void setTodayCaptureTotal(Integer todayCaptureTotal) {
        this.todayCaptureTotal = todayCaptureTotal;
    }

    public Integer getThisMonthCaptureTotal() {
        return thisMonthCaptureTotal;
    }

    public void setThisMonthCaptureTotal(Integer thisMonthCaptureTotal) {
        this.thisMonthCaptureTotal = thisMonthCaptureTotal;
    }

    public Integer getTodayAlarmTotal() {
        return todayAlarmTotal;
    }

    public void setTodayAlarmTotal(Integer todayAlarmTotal) {
        this.todayAlarmTotal = todayAlarmTotal;
    }
}
