package com.ss.facesys.web.app.recog.query;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.StringJoiner;

/**
 * 1:N 抓拍库参数
 *
 * @author FrancisYs
 * @date 2019/12/20
 * @email yaoshuai@ss-cas.com
 */
public class RecogCaptureQuery {

    private List<Integer> deviceIds;
    private List<String> vplatDeviceIds;
    @NotBlank(message = "{recog.img.empty}")
    private String img;
    @NotNull(message = "{recog.imgType.empty}")
    private Integer imgType;
    @NotNull(message = "{recog.captureTimeB.empty}")
    private Long captureTimeB;
    @NotNull(message = "{recog.captureTimeE.empty}")
    private Long captureTimeE;
    private Integer glassesState;
    private Integer sunglassesState;
    private Integer maskState;
    private Integer minorityState;
    private Integer ageB;
    private Integer ageE;
    private Integer gender;
    private Integer topN;
    private Double thresholdMin;
    private Double thresholdMax;
    private String userId;

    public List<Integer> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Integer> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public List<String> getVplatDeviceIds() {
        return vplatDeviceIds;
    }

    public void setVplatDeviceIds(List<String> vplatDeviceIds) {
        this.vplatDeviceIds = vplatDeviceIds;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public Long getCaptureTimeB() {
        return captureTimeB;
    }

    public void setCaptureTimeB(Long captureTimeB) {
        this.captureTimeB = captureTimeB;
    }

    public Long getCaptureTimeE() {
        return captureTimeE;
    }

    public void setCaptureTimeE(Long captureTimeE) {
        this.captureTimeE = captureTimeE;
    }

    public Integer getGlassesState() {
        return glassesState;
    }

    public void setGlassesState(Integer glassesState) {
        this.glassesState = glassesState;
    }

    public Integer getSunglassesState() {
        return sunglassesState;
    }

    public void setSunglassesState(Integer sunglassesState) {
        this.sunglassesState = sunglassesState;
    }

    public Integer getMaskState() {
        return maskState;
    }

    public void setMaskState(Integer maskState) {
        this.maskState = maskState;
    }

    public Integer getMinorityState() {
        return minorityState;
    }

    public void setMinorityState(Integer minorityState) {
        this.minorityState = minorityState;
    }

    public Integer getAgeB() {
        return ageB;
    }

    public void setAgeB(Integer ageB) {
        this.ageB = ageB;
    }

    public Integer getAgeE() {
        return ageE;
    }

    public void setAgeE(Integer ageE) {
        this.ageE = ageE;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getTopN() {
        return topN;
    }

    public void setTopN(Integer topN) {
        this.topN = topN;
    }

    public Double getThresholdMin() {
        return thresholdMin;
    }

    public void setThresholdMin(Double thresholdMin) {
        this.thresholdMin = thresholdMin;
    }

    public Double getThresholdMax() {
        return thresholdMax;
    }

    public void setThresholdMax(Double thresholdMax) {
        this.thresholdMax = thresholdMax;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RecogCaptureQuery.class.getSimpleName() + "[", "]")
                .add("deviceIds=" + deviceIds)
                .add("vplatDeviceIds=" + vplatDeviceIds)
                .add("img='" + img + "'")
                .add("imgType=" + imgType)
                .add("captureTimeB=" + captureTimeB)
                .add("captureTimeE=" + captureTimeE)
                .add("glassesState=" + glassesState)
                .add("sunglassesState=" + sunglassesState)
                .add("maskState=" + maskState)
                .add("minorityState=" + minorityState)
                .add("ageB=" + ageB)
                .add("ageE=" + ageE)
                .add("gender=" + gender)
                .add("topN=" + topN)
                .add("thresholdMin=" + thresholdMin)
                .add("thresholdMax=" + thresholdMax)
                .add("userId=" + userId)
                .toString();
    }

}
