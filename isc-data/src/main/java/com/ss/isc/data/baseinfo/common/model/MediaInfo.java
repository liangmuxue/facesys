package com.ss.isc.data.baseinfo.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * MediaInfo 影像资料表
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_media_info")
public class MediaInfo implements Serializable {

    private static final long serialVersionUID = -4332624298300114281L;
    private Integer id;
    private String recordId;
    private String dateAttachmentName;
    private String dateAttachmentPath;
    private String dateAttachmentBusiType;
    private String dateAttachmentCode;
    private String dateAttachmentDataId;
    private Integer showOrder;
    private String operateUserId;
    private Date operateTime;
    private Date createTime;
    private String createUserId;
    private Date updateTime;
    private String updateUserId;
    private String deleteTime;
    private String deleteUserId;
    private Integer status;
    private String remarks;
    @Transient
    private String dateAttachmentUrl;

    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getRecordId() {
        return this.recordId;
    }


    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }


    public String getDateAttachmentName() {
        return this.dateAttachmentName;
    }


    public void setDateAttachmentName(String dateAttachmentName) {
        this.dateAttachmentName = dateAttachmentName;
    }


    public String getDateAttachmentPath() {
        return this.dateAttachmentPath;
    }


    public void setDateAttachmentPath(String dateAttachmentPath) {
        this.dateAttachmentPath = dateAttachmentPath;
    }


    public String getDateAttachmentBusiType() {
        return this.dateAttachmentBusiType;
    }


    public void setDateAttachmentBusiType(String dateAttachmentBusiType) {
        this.dateAttachmentBusiType = dateAttachmentBusiType;
    }


    public String getDateAttachmentCode() {
        return this.dateAttachmentCode;
    }


    public void setDateAttachmentCode(String dateAttachmentCode) {
        this.dateAttachmentCode = dateAttachmentCode;
    }


    public String getDateAttachmentDataId() {
        return this.dateAttachmentDataId;
    }


    public void setDateAttachmentDataId(String dateAttachmentDataId) {
        this.dateAttachmentDataId = dateAttachmentDataId;
    }


    public Integer getShowOrder() {
        return this.showOrder;
    }


    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }


    public String getOperateUserId() {
        return this.operateUserId;
    }


    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }


    public Date getOperateTime() {
        return this.operateTime;
    }


    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }


    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getCreateUserId() {
        return this.createUserId;
    }


    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getUpdateUserId() {
        return this.updateUserId;
    }


    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }


    public String getDeleteTime() {
        return this.deleteTime;
    }


    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }


    public String getDeleteUserId() {
        return this.deleteUserId;
    }


    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getRemarks() {
        return this.remarks;
    }


    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public String getDateAttachmentUrl() {
        return this.dateAttachmentUrl;
    }


    public void setDateAttachmentUrl(String dateAttachmentUrl) {
        this.dateAttachmentUrl = dateAttachmentUrl;
    }


    public MediaInfo() {
    }


    public MediaInfo(String recordId, Integer status) {
        this.recordId = recordId;
        this.status = status;
    }

}
