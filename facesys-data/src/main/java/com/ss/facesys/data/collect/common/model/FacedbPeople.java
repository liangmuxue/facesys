package com.ss.facesys.data.collect.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * FacedbPeople 人口人像库关联信息
 *
 * @author FrancisYs
 * @date 2019/9/4
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_facedb_people")
public class FacedbPeople implements Serializable {

    private static final long serialVersionUID = -8679383129622539281L;

    /**
     * 主键id
     */
    @Id
    private String id;
    /**
     * 人口编号
     */
    @Column(name = "people_id")
    private String peopleId;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 社区人像库主键id
     */
    @Column(name = "facedb_id")
    private Integer facedbId;
    /**
     * 欧神人像集id
     */
    @Column(name = "facedb_face_id")
    private String facedbFaceId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;
    /**
     * 创建人编号
     */
    @Column(name = "create_user")
    private String createUser;
    /**
     * 最后更新时间
     */
    @Column(name = "update_time")
    private Long updateTime;
    /**
     * 最后更新人编号
     */
    @Column(name = "update_user")
    private String updateUser;
    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    private Long deleteTime;
    /**
     * 删除人编号
     */
    @Column(name = "delete_user")
    private String deleteUser;
    /**
     * 删除标识：0-未删除，1-已删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 当前用户编号
     */
    @Transient
    private String userIds;

    public FacedbPeople() { }

    public FacedbPeople(String id) {
        this.id = id;
    }

    public FacedbPeople(String peopleId, Integer facedbId) {
        this.peopleId = peopleId;
        this.facedbId = facedbId;
    }

    public FacedbPeople(String userIds, String remark, String peopleId, Integer facedbId) {
        this.userIds = userIds;
        this.remark = remark;
        this.peopleId = peopleId;
        this.facedbId = facedbId;
    }

    public FacedbPeople(String peopleId, Integer facedbId, String facedbFaceId) {
        this.peopleId = peopleId;
        this.facedbId = facedbId;
        this.facedbFaceId = facedbFaceId;
    }

    public FacedbPeople(String peopleId, Integer facedbId, String facedbFaceId, String remark) {
        this.peopleId = peopleId;
        this.facedbId = facedbId;
        this.facedbFaceId = facedbFaceId;
        this.remark = remark;
    }

    public FacedbPeople(String id, Integer facedbId, String facedbFaceId, Long time, String user, boolean insert) {
        if (insert) {
            this.peopleId = id;
            this.createTime = time;
            this.createUser = user;
        } else {
            this.id = id;
            this.updateTime = time;
            this.updateUser = user;
        }
        this.facedbId = facedbId;
        this.facedbFaceId = facedbFaceId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "FacedbPeople{" +
                "id='" + id + '\'' +
                ", peopleId='" + peopleId + '\'' +
                ", remark='" + remark + '\'' +
                ", facedbId=" + facedbId +
                ", facedbFaceId='" + facedbFaceId + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", deleteTime=" + deleteTime +
                ", deleteUser='" + deleteUser + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", userIds='" + userIds + '\'' +
                '}';
    }

}
