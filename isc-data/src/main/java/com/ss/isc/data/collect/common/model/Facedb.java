package com.ss.isc.data.collect.common.model;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIEditGroup;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Facedb 人像库信息
 *
 * @author FrancisYs
 * @date 2019/9/3
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_facedb_info")
public class Facedb implements Serializable {

    private static final long serialVersionUID = 6398339553875137718L;

    /**
     * 主键id
     */
    @Id
    private Integer id;
    /**
     * 人像库名称
     */
    private String name;
    /**
     * 用途 [1-通用,2-通用+人口管理分析]
     */
    private Integer model;
    /**
     * 标识类型：若model值为2，则此字段必填；对应字典类型：FACEDB_TYPE
     */
    private String type;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 欧神人像库id
     */
    @Column(name = "facedb_id")
    private String facedbId;
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
     * 标识中文描述
     */
    @Transient
    private String typeName;
    /**
     * 多个标识类型条件
     */
    @Transient
    private String types;
    /**
     * 当前用户编号
     */
    @Transient
    private String userIds;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFacedbId() {
        return facedbId;
    }

    public void setFacedbId(String facedbId) {
        this.facedbId = facedbId;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "Facedb{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", model=" + model +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", facedbId='" + facedbId + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", deleteTime=" + deleteTime +
                ", deleteUser='" + deleteUser + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", typeName='" + typeName + '\'' +
                ", types='" + types + '\'' +
                ", userIds='" + userIds + '\'' +
                '}';
    }

}
