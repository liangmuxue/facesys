package com.ss.isc.data.collect.common.web;

import com.ss.isc.data.viid.common.dto.common.SubImageInfoObject;
import org.hibernate.validator.constraints.NotBlank;
/**
* VIID-布控
* @author chao
* @create 2019/10/31
* @email lishuangchao@ss-cas.com
**/
public class DispositionVO {

    @NotBlank(message = "disposition.dispositionID.empty", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class, com.ss.valide.APIDeltGroup.class})
    private String dispositionID;
    @NotBlank(message = "disposition.title.empty", groups = {com.ss.valide.APIAddGroup.class})
    private String title;
    @NotBlank(message = "disposition.dispositionCategory.empty", groups = {com.ss.valide.APIAddGroup.class})
    private String dispositionCategory;
    @NotBlank(message = "disposition.targetFeature.empty", groups = {com.ss.valide.APIAddGroup.class})
    private String targetFeature;
    private String targetImageURI;
    private String priorityLevel;
    @NotBlank(message = "disposition.targetFeature.empty", groups = {com.ss.valide.APIAddGroup.class})
    private String applicantName;
    private String applicantInfo;
    @NotBlank(message = "disposition.targetFeature.empty", groups = {com.ss.valide.APIAddGroup.class})
    private String applicantOrg;
    @NotBlank(message = "disposition.targetFeature.empty", groups = {com.ss.valide.APIAddGroup.class})
    private String beginTime;
    @NotBlank(message = "disposition.targetFeature.empty", groups = {com.ss.valide.APIAddGroup.class})
    private String endTime;
    private String creatTime;
    private String operateType;
    private String dispositionStatus;
    @NotBlank(message = "disposition.targetFeature.empty", groups = {com.ss.valide.APIAddGroup.class})
    private String dispositionRange;
    private String tollgateList;
    private String dispositionArea;
    private String receiveAddr;
    private String receiveMobile;
    private String reason;
    private String dispositionRemoveOrg;
    private String dispositionRemovePerson;
    private String dispositionRemoveTime;
    private String dispositionRemoveReason;
    private SubImageInfoObject subImageList;

    public String getDispositionID() {
        return dispositionID;
    }

    public void setDispositionID(String dispositionID) {
        this.dispositionID = dispositionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDispositionCategory() {
        return dispositionCategory;
    }

    public void setDispositionCategory(String dispositionCategory) {
        this.dispositionCategory = dispositionCategory;
    }

    public String getTargetFeature() {
        return targetFeature;
    }

    public void setTargetFeature(String targetFeature) {
        this.targetFeature = targetFeature;
    }

    public String getTargetImageURI() {
        return targetImageURI;
    }

    public void setTargetImageURI(String targetImageURI) {
        this.targetImageURI = targetImageURI;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantInfo() {
        return applicantInfo;
    }

    public void setApplicantInfo(String applicantInfo) {
        this.applicantInfo = applicantInfo;
    }

    public String getApplicantOrg() {
        return applicantOrg;
    }

    public void setApplicantOrg(String applicantOrg) {
        this.applicantOrg = applicantOrg;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getDispositionStatus() {
        return dispositionStatus;
    }

    public void setDispositionStatus(String dispositionStatus) {
        this.dispositionStatus = dispositionStatus;
    }

    public String getDispositionRange() {
        return dispositionRange;
    }

    public void setDispositionRange(String dispositionRange) {
        this.dispositionRange = dispositionRange;
    }

    public String getTollgateList() {
        return tollgateList;
    }

    public void setTollgateList(String tollgateList) {
        this.tollgateList = tollgateList;
    }

    public String getDispositionArea() {
        return dispositionArea;
    }

    public void setDispositionArea(String dispositionArea) {
        this.dispositionArea = dispositionArea;
    }

    public String getReceiveAddr() {
        return receiveAddr;
    }

    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDispositionRemoveOrg() {
        return dispositionRemoveOrg;
    }

    public void setDispositionRemoveOrg(String dispositionRemoveOrg) {
        this.dispositionRemoveOrg = dispositionRemoveOrg;
    }

    public String getDispositionRemovePerson() {
        return dispositionRemovePerson;
    }

    public void setDispositionRemovePerson(String dispositionRemovePerson) {
        this.dispositionRemovePerson = dispositionRemovePerson;
    }

    public String getDispositionRemoveTime() {
        return dispositionRemoveTime;
    }

    public void setDispositionRemoveTime(String dispositionRemoveTime) {
        this.dispositionRemoveTime = dispositionRemoveTime;
    }

    public String getDispositionRemoveReason() {
        return dispositionRemoveReason;
    }

    public void setDispositionRemoveReason(String dispositionRemoveReason) {
        this.dispositionRemoveReason = dispositionRemoveReason;
    }

    public SubImageInfoObject getSubImageList() {
        return subImageList;
    }

    public void setSubImageList(SubImageInfoObject subImageList) {
        this.subImageList = subImageList;
    }
}
