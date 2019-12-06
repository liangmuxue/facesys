package com.ss.facesys.data.collect.common.model;

import com.ss.entity.DTEntity;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Facedb 人像库信息
 *
 * @author FrancisYs
 * @date 2019/12/5
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_facedb_info")
public class Facedb extends DTEntity implements Serializable {

    private static final long serialVersionUID = 6398339553875137718L;

    /**
     * 主键id
     */
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    /**
     * 人像库名称
     */
    private String name;
    /**
     * 人像库用途，字典：FACEDB_MODEL
     */
    private Integer model;
    /**
     * 人像库标识类型，字典类型：FACEDB_TYPE
     */
    private String type;
    /**
     * 人像数量
     */
    @Column(name = "face_count")
    private Integer faceCount;
    /**
     * 布控状态，字典：FACEDB_MONITOR_STATE
     */
    @Column(name = "monitor_state")
    private Integer monitorState;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 所属单位ID
     */
    @Column(name = "org_id")
    private String orgId;
    /**
     * 欧神人像库id
     */
    @Column(name = "facedb_id")
    private String facedbId;
    /**
     * 数据状态，字典：DATA_STATUS
     */
    private Integer status;

    /**
     * 标识中文描述
     */
    @Transient
    private String typeName;
    /**
     * 布控状态中文描述
     */
    @Transient
    private String monitorStateName;
    /**
     * 多个标识类型条件
     */
    @Transient
    private String types;


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

    public Integer getFaceCount() {
        return faceCount;
    }

    public void setFaceCount(Integer faceCount) {
        this.faceCount = faceCount;
    }

    public Integer getMonitorState() {
        return monitorState;
    }

    public void setMonitorState(Integer monitorState) {
        this.monitorState = monitorState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getFacedbId() {
        return facedbId;
    }

    public void setFacedbId(String facedbId) {
        this.facedbId = facedbId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMonitorStateName() {
        return monitorStateName;
    }

    public void setMonitorStateName(String monitorStateName) {
        this.monitorStateName = monitorStateName;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Facedb.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("model=" + model)
                .add("type='" + type + "'")
                .add("faceCount=" + faceCount)
                .add("monitorState=" + monitorState)
                .add("remark='" + remark + "'")
                .add("orgId='" + orgId + "'")
                .add("facedbId='" + facedbId + "'")
                .add("status=" + status)
                .add("typeName='" + typeName + "'")
                .add("monitorStateName='" + monitorStateName + "'")
                .add("types='" + types + "'")
                .toString();
    }

}
