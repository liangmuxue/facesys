package com.ss.isc.data.process.common.model;

import com.ss.isc.data.baseinfo.common.model.BaseEnums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * PeopleProcess 处置信息
 * @author FrancisYs
 * @date 2019/8/25
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_process_info")
public class PeopleProcess implements Serializable {

    private static final long serialVersionUID = -2933514855397187062L;
    private String id;
    private String recordId;
    private Integer processType;
    private String processUserId;
    private Date processTime;
    private String remark;
    @Transient
    private String processUserName;
    private Integer state;
    private String label;
    private Date createTime;
    private Date updateTime;
    @Transient
    List<BaseEnums> labels = new ArrayList();


    public PeopleProcess() {
    }


    public PeopleProcess(String recordId, Integer processType) {
        this.recordId = recordId;
        this.processType = processType;
    }


    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = (id == null) ? null : id.trim();
    }


    public String getRecordId() {
        return this.recordId;
    }


    public void setRecordId(String recordId) {
        this.recordId = (recordId == null) ? null : recordId.trim();
    }


    public Integer getProcessType() {
        return this.processType;
    }


    public void setProcessType(Integer processType) {
        this.processType = processType;
    }


    public String getProcessUserId() {
        return this.processUserId;
    }


    public void setProcessUserId(String processUserId) {
        this.processUserId = (processUserId == null) ? null : processUserId.trim();
    }


    public Date getProcessTime() {
        return this.processTime;
    }


    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = (remark == null) ? null : remark.trim();
    }


    public String getProcessUserName() {
        return this.processUserName;
    }


    public void setProcessUserName(String processUserName) {
        this.processUserName = (processUserName == null) ? null : processUserName.trim();
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public String getLabel() {
        return this.label;
    }


    public void setLabel(String label) {
        this.label = label;
    }


    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public List<BaseEnums> getLabels() {
        return this.labels;
    }


    public void setLabels(List<BaseEnums> labels) {
        this.labels = labels;
    }

}
