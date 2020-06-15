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

    @Override
    public String toString() {
        return new StringJoiner(", ", ImgCollectionResultDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("dataType=" + dataType)
                .add("dataId=" + dataId)
                .add("remark='" + remark + "'")
                .add("collectionTime=" + collectionTime)
                .add("userId='" + userId + "'")
                .add("collectionUrl='" + collectionUrl + "'")
                .add("deviceName='" + deviceName + "'")
                .add("dataCreateTime=" + dataCreateTime)
                .add("collectionCaptureDetail=" + collectionCaptureDetail)
                .add("collectionPersoncardDetail=" + collectionPersoncardDetail)
                .toString();
    }

}
