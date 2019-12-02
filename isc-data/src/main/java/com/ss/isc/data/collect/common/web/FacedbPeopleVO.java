package com.ss.isc.data.collect.common.web;

import com.ss.request.Pagination;
import com.ss.valide.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * FacedbPeopleVO
 *
 * @author FrancisYs
 * @date 2019/9/4
 * @email yaoshuai@ss-cas.com
 */
public class FacedbPeopleVO extends Pagination {

    /**
     * 主键id
     */
    @NotNull(message = "{facedbPeople.id.empty}", groups = {APIDeltGroup.class})
    private String id;
    /**
     * 人口编号
     */
    @NotNull(message = "{facedbPeople.peopleId.empty}", groups = {APIAddGroup.class})
    private String peopleId;
    /**
     * 社区人像库主键id
     */
    @NotNull(message = "{facedbPeople.facedbId.empty}", groups = {APIAddGroup.class, APIPageGroup.class})
    private Integer facedbId;
    /**
     * 欧神人像集id
     */
    private String facedbFaceId;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 当前用户编号
     */
    @NotNull(message = "{userIds.empty}", groups = {APIAddGroup.class, APIDeltGroup.class})
    private String userIds;
    /**
     * 模糊搜索条件：姓名/身份证/手机号
     */
    private String inputQuery;
    /**
     * 民族编号
     */
    private Integer nationCode;
    /**
     * 姓名
     */
    private String peopleName;
    /**
     * 证件号码
     */
    private String credentialNo;
    /**
     * 手机号码
     */
    private String phoneNo;
    /**
     * 民族中文描述
     */
    private String nation;
    /**
     * 人员标签
     */
    private String label;
    /**
     * 人脸照路径
     */
    private String facePic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public Integer getFacedbId() {
        return facedbId;
    }

    public void setFacedbId(Integer facedbId) {
        this.facedbId = facedbId;
    }

    public String getFacedbFaceId() {
        return facedbFaceId;
    }

    public void setFacedbFaceId(String facedbFaceId) {
        this.facedbFaceId = facedbFaceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getInputQuery() {
        return inputQuery;
    }

    public void setInputQuery(String inputQuery) {
        this.inputQuery = inputQuery;
    }

    public Integer getNationCode() {
        return nationCode;
    }

    public void setNationCode(Integer nationCode) {
        this.nationCode = nationCode;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }

    @Override
    public String toString() {
        return "FacedbPeopleVO{" +
                "id='" + id + '\'' +
                ", peopleId='" + peopleId + '\'' +
                ", facedbId=" + facedbId +
                ", facedbFaceId='" + facedbFaceId + '\'' +
                ", remark='" + remark + '\'' +
                ", userIds='" + userIds + '\'' +
                ", inputQuery='" + inputQuery + '\'' +
                ", nationCode=" + nationCode +
                ", peopleName='" + peopleName + '\'' +
                ", credentialNo='" + credentialNo + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", nation='" + nation + '\'' +
                ", label='" + label + '\'' +
                ", facePic='" + facePic + '\'' +
                '}';
    }

}
