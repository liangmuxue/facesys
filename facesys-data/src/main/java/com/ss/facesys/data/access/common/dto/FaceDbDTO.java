package com.ss.facesys.data.access.common.dto;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;

import javax.validation.constraints.NotNull;

/**
 * FaceDbDTO 人像库
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
public class FaceDbDTO {

    @NotNull(message = "{facedb.id.empty}", groups = {APIEditGroup.class, APIGetsGroup.class, APIDeltGroup.class})
    private String id;              // 人像库 id
    @NotNull(message = "{facedb.name.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String name;            // 人像库名称
    private Integer model;          // 用途 [1-通用,2-通用+人口管理分析]
    private String type;            // 标识类型 -- 若 model 值为 2，则此字段必填；对应欧神字典类型：FACEDB_TYPE
    private String refBusfacedbId;
    private Integer state;
    private Long faceCount;
    private Integer monitorState;
    private Integer fromFlag;
    private String remark;
    private String facedbExtField1; // 扩展字段1
    private String facedbExtField2; // 扩展字段2
    private String facedbExtField3; // 扩展字段3
    private String isLinkage;
    private String fromSystem;
    private String createdBy;
    private Long createdTime;
    private Long updatedTime;
    private Long deletedTime;
    private String facedbTypeName;
    private Integer isDelete;

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getModel() {
        return this.model;
    }
    public void setModel(Integer model) {
        this.model = model;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRefBusfacedbId() {
        return this.refBusfacedbId;
    }
    public void setRefBusfacedbId(String refBusfacedbId) {
        this.refBusfacedbId = refBusfacedbId;
    }
    public Integer getState() {
        return this.state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public Long getFaceCount() {
        return this.faceCount;
    }
    public void setFaceCount(Long faceCount) {
        this.faceCount = faceCount;
    }
    public Integer getMonitorState() {
        return this.monitorState;
    }
    public void setMonitorState(Integer monitorState) {
        this.monitorState = monitorState;
    }
    public Integer getFromFlag() {
        return this.fromFlag;
    }
    public void setFromFlag(Integer fromFlag) {
        this.fromFlag = fromFlag;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getIsLinkage() {
        return this.isLinkage;
    }
    public void setIsLinkage(String isLinkage) {
        this.isLinkage = isLinkage;
    }
    public String getFromSystem() {
        return this.fromSystem;
    }
    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Long getCreatedTime() {
        return this.createdTime;
    }
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
    public Long getUpdatedTime() {
        return this.updatedTime;
    }
    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }
    public Long getDeletedTime() {
        return this.deletedTime;
    }
    public void setDeletedTime(Long deletedTime) {
        this.deletedTime = deletedTime;
    }
    public String getFacedbTypeName() {
        return this.facedbTypeName;
    }
    public void setFacedbTypeName(String facedbTypeName) {
        this.facedbTypeName = facedbTypeName;
    }
    public String getFacedbExtField1() {
        return this.facedbExtField1;
    }
    public void setFacedbExtField1(String facedbExtField1) {
        this.facedbExtField1 = facedbExtField1;
    }
    public String getFacedbExtField2() {
        return this.facedbExtField2;
    }
    public void setFacedbExtField2(String facedbExtField2) {
        this.facedbExtField2 = facedbExtField2;
    }
    public String getFacedbExtField3() {
        return this.facedbExtField3;
    }
    public void setFacedbExtField3(String facedbExtField3) {
        this.facedbExtField3 = facedbExtField3;
    }
    public Integer getIsDelete() {
        return this.isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String toString() {
        return "FaceDbDTO [id=" + this.id + ", name=" + this.name + ", model=" + this.model + ", type=" + this.type + ", refBusfacedbId=" + this.refBusfacedbId + ", state=" + this.state + ", faceCount=" + this.faceCount + ", monitorState=" + this.monitorState + ", fromFlag=" + this.fromFlag + ", remark=" + this.remark + ", facedbExtField1=" + this.facedbExtField1 + ", facedbExtField2=" + this.facedbExtField2 + ", facedbExtField3=" + this.facedbExtField3 + ", isLinkage=" + this.isLinkage + ", fromSystem=" + this.fromSystem + ", createdBy=" + this.createdBy + ", createdTime=" + this.createdTime + ", updatedTime=" + this.updatedTime + ", deletedTime=" + this.deletedTime + ", facedbTypeName=" + this.facedbTypeName + ", isDelete=" + this.isDelete + "]";
    }

}
