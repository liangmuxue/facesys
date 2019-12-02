package com.ss.isc.data.collect.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.request.Pagination;
import com.ss.valide.APIRegisterRecogGroup;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * CaptureImgVO 抓拍图片视图对象
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
public class CaptureImgVO extends Pagination implements Serializable {

    private static final long serialVersionUID = 9134615178743910043L;

    private Integer imgType;
    @NotBlank(message = "图片内容为空", groups = {APIRegisterRecogGroup.class})
    private String img;
    @NotNull(message = "抓拍最早时间为空", groups = {APIRegisterRecogGroup.class})
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date captureTimeB;
    @NotNull(message = "抓拍最晚时间为空", groups = {APIRegisterRecogGroup.class})
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date captureTimeE;
    private String[] deviceIds;
    private String deviceIdStr;
    @Range(min = 0L, max = 1L, message = "阈值下限应在[0,1]范围之间", groups = {APIRegisterRecogGroup.class})
    private Float thresholdMin;
    @Range(min = 0L, max = 1L, message = "阈值上限应在[0,1]范围之间", groups = {APIRegisterRecogGroup.class})
    private Float thresholdMax;
    private String villageCode;
    private String villageCodes;
    @NotNull(message = "请求类型为空", groups = {APIRegisterRecogGroup.class})
    private Integer requestType;
    private String[] villages;
    @NotBlank(message = "当前用户编号为空", groups = {APIRegisterRecogGroup.class})
    private String userIds;

    public String getUserIds() {
        return this.userIds;
    }
    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
    public String[] getVillages() {
        return this.villages;
    }
    public void setVillages(String[] villages) {
        this.villages = villages;
    }
    public Integer getImgType() {
        return this.imgType;
    }
    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public Date getCaptureTimeB() {
        return this.captureTimeB;
    }
    public void setCaptureTimeB(Date captureTimeB) {
        this.captureTimeB = captureTimeB;
    }
    public Date getCaptureTimeE() {
        return this.captureTimeE;
    }
    public void setCaptureTimeE(Date captureTimeE) {
        this.captureTimeE = captureTimeE;
    }
    public String[] getDeviceIds() {
        return this.deviceIds;
    }
    public void setDeviceIds(String[] deviceIds) {
        this.deviceIds = deviceIds;
    }
    public Float getThresholdMin() {
        return this.thresholdMin;
    }
    public void setThresholdMin(Float thresholdMin) {
        this.thresholdMin = thresholdMin;
    }
    public Float getThresholdMax() {
        return this.thresholdMax;
    }
    public void setThresholdMax(Float thresholdMax) {
        this.thresholdMax = thresholdMax;
    }
    public String getVillageCode() {
        return this.villageCode;
    }
    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }
    public Integer getRequestType() {
        return this.requestType;
    }
    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getDeviceIdStr() {
        return deviceIdStr;
    }

    public void setDeviceIdStr(String deviceIdStr) {
        this.deviceIdStr = deviceIdStr;
    }

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    @Override
    public String toString() {
        return "CaptureImgVO{" +
                "imgType=" + imgType +
                ", img='" + img + '\'' +
                ", captureTimeB=" + captureTimeB +
                ", captureTimeE=" + captureTimeE +
                ", deviceIds=" + Arrays.toString(deviceIds) +
                ", deviceIdStr='" + deviceIdStr + '\'' +
                ", thresholdMin=" + thresholdMin +
                ", thresholdMax=" + thresholdMax +
                ", villageCode='" + villageCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", requestType=" + requestType +
                ", villages=" + Arrays.toString(villages) +
                ", userIds='" + userIds + '\'' +
                '}';
    }

}
