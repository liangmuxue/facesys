package com.ss.facesys.data.system.common.model;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.StringJoiner;

/**
 * ImgCollection
 *
 * @author FrancisYs
 * @date 2020/2/18
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_img_collection")
public class ImgCollection {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    @Column(name = "data_type")
    private Integer dataType;
    @Column(name = "data_id")
    private Integer dataId;
    @Column(name = "remark")
    private String remark;
    @Column(name = "collection_time")
    private Long collectionTime;
    @Column(name = "user_id")
    private String userId;

    @Transient
    private Long collectionTimeMin;
    @Transient
    private Long collectionTimeMax;
    @Transient
    private List<String> captureDeviceIds;
    @Transient
    private List<String> personcardDeviceIds;
    @Transient
    private String captureDataIds;
    @Transient
    private String personcardDataIds;
    @Transient
    private String captureId;

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

    public Long getCollectionTimeMin() {
        return collectionTimeMin;
    }

    public void setCollectionTimeMin(Long collectionTimeMin) {
        this.collectionTimeMin = collectionTimeMin;
    }

    public Long getCollectionTimeMax() {
        return collectionTimeMax;
    }

    public void setCollectionTimeMax(Long collectionTimeMax) {
        this.collectionTimeMax = collectionTimeMax;
    }

    public List<String> getCaptureDeviceIds() {
        return captureDeviceIds;
    }

    public void setCaptureDeviceIds(List<String> captureDeviceIds) {
        this.captureDeviceIds = captureDeviceIds;
    }

    public List<String> getPersoncardDeviceIds() {
        return personcardDeviceIds;
    }

    public void setPersoncardDeviceIds(List<String> personcardDeviceIds) {
        this.personcardDeviceIds = personcardDeviceIds;
    }

    public String getCaptureDataIds() {
        return captureDataIds;
    }

    public void setCaptureDataIds(String captureDataIds) {
        this.captureDataIds = captureDataIds;
    }

    public String getPersoncardDataIds() {
        return personcardDataIds;
    }

    public void setPersoncardDataIds(String personcardDataIds) {
        this.personcardDataIds = personcardDataIds;
    }

    public String getCaptureId() {
        return captureId;
    }

    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ImgCollection.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("dataType=" + dataType)
                .add("dataId=" + dataId)
                .add("remark='" + remark + "'")
                .add("collectionTime=" + collectionTime)
                .add("userId='" + userId + "'")
                .add("collectionTimeMin='" + collectionTimeMin + "'")
                .add("collectionTimeMax='" + collectionTimeMax + "'")
                .add("captureDeviceIds='" + captureDeviceIds + "'")
                .add("personcardDeviceIds='" + personcardDeviceIds + "'")
                .add("captureDataIds='" + captureDataIds + "'")
                .add("personcardDataIds='" + personcardDataIds + "'")
                .toString();
    }

}
