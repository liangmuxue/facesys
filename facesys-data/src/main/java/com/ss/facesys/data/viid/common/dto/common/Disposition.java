package com.ss.facesys.data.viid.common.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* VIID-布控实体类
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class Disposition {
    @JsonProperty("DispositionID")
    private String dispositionID;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("DispositionCategory")
    private String dispositionCategory;
    @JsonProperty("TargetFeature")
    private String targetFeature;
    @JsonProperty("TargetImageURI")
    private String targetImageURI;
    @JsonProperty("PriorityLevel")
    private String priorityLevel;
    @JsonProperty("ApplicantName")
    private String applicantName;
    @JsonProperty("ApplicantInfo")
    private String applicantInfo;
    @JsonProperty("ApplicantOrg")
    private String applicantOrg;
    @JsonProperty("BeginTime")
    private String beginTime;
    @JsonProperty("EndTime")
    private String endTime;
    @JsonProperty("CreatTime")
    private String creatTime;
    @JsonProperty("OperateType")
    private String operateType;
    @JsonProperty("DispositionStatus")
    private String dispositionStatus;
    @JsonProperty("DispositionRange")
    private String dispositionRange;
    @JsonProperty("TollgateList")
    private String tollgateList;
    @JsonProperty("DispositionArea")
    private String dispositionArea;
    @JsonProperty("ReceiveAddr")
    private String receiveAddr;
    @JsonProperty("ReceiveMobile")
    private String receiveMobile;
    @JsonProperty("Reason")
    private String reason;
    @JsonProperty("DispositionRemoveOrg")
    private String dispositionRemoveOrg;
    @JsonProperty("DispositionRemovePerson")
    private String dispositionRemovePerson;
    @JsonProperty("DispositionRemoveTime")
    private String dispositionRemoveTime;
    @JsonProperty("DispositionRemoveReason")
    private String dispositionRemoveReason;
    @JsonProperty("SubImageList")
    private SubImageInfoObject subImageList;
    @JsonProperty("ResultImageDeclare")
    private String resultImageDeclare;
    @JsonProperty("TabID")
    private String tabId;
    @JsonProperty("AlarmSensitivity")
    private Integer alarmSensitivity;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Disposition)) return false;
        Disposition other = (Disposition) o;
        if (!other.canEqual(this)) return false;
        Object this$dispositionID = getDispositionID(), other$dispositionID = other.getDispositionID();
        if ((this$dispositionID == null) ? (other$dispositionID != null) : !this$dispositionID.equals(other$dispositionID))
            return false;
        Object this$title = getTitle(), other$title = other.getTitle();
        if ((this$title == null) ? (other$title != null) : !this$title.equals(other$title)) return false;
        Object this$dispositionCategory = getDispositionCategory(), other$dispositionCategory = other.getDispositionCategory();
        if ((this$dispositionCategory == null) ? (other$dispositionCategory != null) : !this$dispositionCategory.equals(other$dispositionCategory))
            return false;
        Object this$targetFeature = getTargetFeature(), other$targetFeature = other.getTargetFeature();
        if ((this$targetFeature == null) ? (other$targetFeature != null) : !this$targetFeature.equals(other$targetFeature))
            return false;
        Object this$targetImageURI = getTargetImageURI(), other$targetImageURI = other.getTargetImageURI();
        if ((this$targetImageURI == null) ? (other$targetImageURI != null) : !this$targetImageURI.equals(other$targetImageURI))
            return false;
        Object this$priorityLevel = getPriorityLevel(), other$priorityLevel = other.getPriorityLevel();
        if ((this$priorityLevel == null) ? (other$priorityLevel != null) : !this$priorityLevel.equals(other$priorityLevel))
            return false;
        Object this$applicantName = getApplicantName(), other$applicantName = other.getApplicantName();
        if ((this$applicantName == null) ? (other$applicantName != null) : !this$applicantName.equals(other$applicantName))
            return false;
        Object this$applicantInfo = getApplicantInfo(), other$applicantInfo = other.getApplicantInfo();
        if ((this$applicantInfo == null) ? (other$applicantInfo != null) : !this$applicantInfo.equals(other$applicantInfo))
            return false;
        Object this$applicantOrg = getApplicantOrg(), other$applicantOrg = other.getApplicantOrg();
        if ((this$applicantOrg == null) ? (other$applicantOrg != null) : !this$applicantOrg.equals(other$applicantOrg))
            return false;
        Object this$beginTime = getBeginTime(), other$beginTime = other.getBeginTime();
        if ((this$beginTime == null) ? (other$beginTime != null) : !this$beginTime.equals(other$beginTime))
            return false;
        Object this$endTime = getEndTime(), other$endTime = other.getEndTime();
        if ((this$endTime == null) ? (other$endTime != null) : !this$endTime.equals(other$endTime)) return false;
        Object this$creatTime = getCreatTime(), other$creatTime = other.getCreatTime();
        if ((this$creatTime == null) ? (other$creatTime != null) : !this$creatTime.equals(other$creatTime))
            return false;
        Object this$operateType = getOperateType(), other$operateType = other.getOperateType();
        if ((this$operateType == null) ? (other$operateType != null) : !this$operateType.equals(other$operateType))
            return false;
        Object this$dispositionStatus = getDispositionStatus(), other$dispositionStatus = other.getDispositionStatus();
        if ((this$dispositionStatus == null) ? (other$dispositionStatus != null) : !this$dispositionStatus.equals(other$dispositionStatus))
            return false;
        Object this$dispositionRange = getDispositionRange(), other$dispositionRange = other.getDispositionRange();
        if ((this$dispositionRange == null) ? (other$dispositionRange != null) : !this$dispositionRange.equals(other$dispositionRange))
            return false;
        Object this$tollgateList = getTollgateList(), other$tollgateList = other.getTollgateList();
        if ((this$tollgateList == null) ? (other$tollgateList != null) : !this$tollgateList.equals(other$tollgateList))
            return false;
        Object this$dispositionArea = getDispositionArea(), other$dispositionArea = other.getDispositionArea();
        if ((this$dispositionArea == null) ? (other$dispositionArea != null) : !this$dispositionArea.equals(other$dispositionArea))
            return false;
        Object this$receiveAddr = getReceiveAddr(), other$receiveAddr = other.getReceiveAddr();
        if ((this$receiveAddr == null) ? (other$receiveAddr != null) : !this$receiveAddr.equals(other$receiveAddr))
            return false;
        Object this$receiveMobile = getReceiveMobile(), other$receiveMobile = other.getReceiveMobile();
        if ((this$receiveMobile == null) ? (other$receiveMobile != null) : !this$receiveMobile.equals(other$receiveMobile))
            return false;
        Object this$reason = getReason(), other$reason = other.getReason();
        if ((this$reason == null) ? (other$reason != null) : !this$reason.equals(other$reason)) return false;
        Object this$dispositionRemoveOrg = getDispositionRemoveOrg(), other$dispositionRemoveOrg = other.getDispositionRemoveOrg();
        if ((this$dispositionRemoveOrg == null) ? (other$dispositionRemoveOrg != null) : !this$dispositionRemoveOrg.equals(other$dispositionRemoveOrg))
            return false;
        Object this$dispositionRemovePerson = getDispositionRemovePerson(), other$dispositionRemovePerson = other.getDispositionRemovePerson();
        if ((this$dispositionRemovePerson == null) ? (other$dispositionRemovePerson != null) : !this$dispositionRemovePerson.equals(other$dispositionRemovePerson))
            return false;
        Object this$dispositionRemoveTime = getDispositionRemoveTime(), other$dispositionRemoveTime = other.getDispositionRemoveTime();
        if ((this$dispositionRemoveTime == null) ? (other$dispositionRemoveTime != null) : !this$dispositionRemoveTime.equals(other$dispositionRemoveTime))
            return false;
        Object this$dispositionRemoveReason = getDispositionRemoveReason(), other$dispositionRemoveReason = other.getDispositionRemoveReason();
        if ((this$dispositionRemoveReason == null) ? (other$dispositionRemoveReason != null) : !this$dispositionRemoveReason.equals(other$dispositionRemoveReason))
            return false;
        Object this$subImageList = getSubImageList(), other$subImageList = other.getSubImageList();
        if ((this$subImageList == null) ? (other$subImageList != null) : !this$subImageList.equals(other$subImageList))
            return false;
        Object this$resultImageDeclare = getResultImageDeclare(), other$resultImageDeclare = other.getResultImageDeclare();
        if ((this$resultImageDeclare == null) ? (other$resultImageDeclare != null) : !this$resultImageDeclare.equals(other$resultImageDeclare))
            return false;
        Object this$tabId = getTabId(), other$tabId = other.getTabId();
        if ((this$tabId == null) ? (other$tabId != null) : !this$tabId.equals(other$tabId)) return false;
        Object this$alarmSensitivity = getAlarmSensitivity(), other$alarmSensitivity = other.getAlarmSensitivity();
        return !((this$alarmSensitivity == null) ? (other$alarmSensitivity != null) : !this$alarmSensitivity.equals(other$alarmSensitivity));
    }

    protected boolean canEqual(Object other) {
        return other instanceof Disposition;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $dispositionID = getDispositionID();
        result = result * 59 + (($dispositionID == null) ? 0 : $dispositionID.hashCode());
        Object $title = getTitle();
        result = result * 59 + (($title == null) ? 0 : $title.hashCode());
        Object $dispositionCategory = getDispositionCategory();
        result = result * 59 + (($dispositionCategory == null) ? 0 : $dispositionCategory.hashCode());
        Object $targetFeature = getTargetFeature();
        result = result * 59 + (($targetFeature == null) ? 0 : $targetFeature.hashCode());
        Object $targetImageURI = getTargetImageURI();
        result = result * 59 + (($targetImageURI == null) ? 0 : $targetImageURI.hashCode());
        Object $priorityLevel = getPriorityLevel();
        result = result * 59 + (($priorityLevel == null) ? 0 : $priorityLevel.hashCode());
        Object $applicantName = getApplicantName();
        result = result * 59 + (($applicantName == null) ? 0 : $applicantName.hashCode());
        Object $applicantInfo = getApplicantInfo();
        result = result * 59 + (($applicantInfo == null) ? 0 : $applicantInfo.hashCode());
        Object $applicantOrg = getApplicantOrg();
        result = result * 59 + (($applicantOrg == null) ? 0 : $applicantOrg.hashCode());
        Object $beginTime = getBeginTime();
        result = result * 59 + (($beginTime == null) ? 0 : $beginTime.hashCode());
        Object $endTime = getEndTime();
        result = result * 59 + (($endTime == null) ? 0 : $endTime.hashCode());
        Object $creatTime = getCreatTime();
        result = result * 59 + (($creatTime == null) ? 0 : $creatTime.hashCode());
        Object $operateType = getOperateType();
        result = result * 59 + (($operateType == null) ? 0 : $operateType.hashCode());
        Object $dispositionStatus = getDispositionStatus();
        result = result * 59 + (($dispositionStatus == null) ? 0 : $dispositionStatus.hashCode());
        Object $dispositionRange = getDispositionRange();
        result = result * 59 + (($dispositionRange == null) ? 0 : $dispositionRange.hashCode());
        Object $tollgateList = getTollgateList();
        result = result * 59 + (($tollgateList == null) ? 0 : $tollgateList.hashCode());
        Object $dispositionArea = getDispositionArea();
        result = result * 59 + (($dispositionArea == null) ? 0 : $dispositionArea.hashCode());
        Object $receiveAddr = getReceiveAddr();
        result = result * 59 + (($receiveAddr == null) ? 0 : $receiveAddr.hashCode());
        Object $receiveMobile = getReceiveMobile();
        result = result * 59 + (($receiveMobile == null) ? 0 : $receiveMobile.hashCode());
        Object $reason = getReason();
        result = result * 59 + (($reason == null) ? 0 : $reason.hashCode());
        Object $dispositionRemoveOrg = getDispositionRemoveOrg();
        result = result * 59 + (($dispositionRemoveOrg == null) ? 0 : $dispositionRemoveOrg.hashCode());
        Object $dispositionRemovePerson = getDispositionRemovePerson();
        result = result * 59 + (($dispositionRemovePerson == null) ? 0 : $dispositionRemovePerson.hashCode());
        Object $dispositionRemoveTime = getDispositionRemoveTime();
        result = result * 59 + (($dispositionRemoveTime == null) ? 0 : $dispositionRemoveTime.hashCode());
        Object $dispositionRemoveReason = getDispositionRemoveReason();
        result = result * 59 + (($dispositionRemoveReason == null) ? 0 : $dispositionRemoveReason.hashCode());
        Object $subImageList = getSubImageList();
        result = result * 59 + (($subImageList == null) ? 0 : $subImageList.hashCode());
        Object $resultImageDeclare = getResultImageDeclare();
        result = result * 59 + (($resultImageDeclare == null) ? 0 : $resultImageDeclare.hashCode());
        Object $tabId = getTabId();
        result = result * 59 + (($tabId == null) ? 0 : $tabId.hashCode());
        Object $alarmSensitivity = getAlarmSensitivity();
        return result * 59 + (($alarmSensitivity == null) ? 0 : $alarmSensitivity.hashCode());
    }

    public String toString() {
        return "Disposition(dispositionID=" + getDispositionID() + ", title=" + getTitle() + ", dispositionCategory=" + getDispositionCategory() + ", targetFeature=" + getTargetFeature() + ", targetImageURI=" + getTargetImageURI() + ", priorityLevel=" + getPriorityLevel() + ", applicantName=" + getApplicantName() + ", applicantInfo=" + getApplicantInfo() + ", applicantOrg=" + getApplicantOrg() + ", beginTime=" + getBeginTime() + ", endTime=" + getEndTime() + ", creatTime=" + getCreatTime() + ", operateType=" + getOperateType() + ", dispositionStatus=" + getDispositionStatus() + ", dispositionRange=" + getDispositionRange() + ", tollgateList=" + getTollgateList() + ", dispositionArea=" + getDispositionArea() + ", receiveAddr=" + getReceiveAddr() + ", receiveMobile=" + getReceiveMobile() + ", reason=" + getReason() + ", dispositionRemoveOrg=" + getDispositionRemoveOrg() + ", dispositionRemovePerson=" + getDispositionRemovePerson() + ", dispositionRemoveTime=" + getDispositionRemoveTime() + ", dispositionRemoveReason=" + getDispositionRemoveReason() + ", subImageList=" + getSubImageList() + ", resultImageDeclare=" + getResultImageDeclare() + ", tabId=" + getTabId() + ", alarmSensitivity=" + getAlarmSensitivity() + ")";
    }

    public String getDispositionID() {
        return this.dispositionID;
    }

    public void setDispositionID(String dispositionID) {
        this.dispositionID = dispositionID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDispositionCategory() {
        return this.dispositionCategory;
    }

    public void setDispositionCategory(String dispositionCategory) {
        this.dispositionCategory = dispositionCategory;
    }

    public String getTargetFeature() {
        return this.targetFeature;
    }

    public void setTargetFeature(String targetFeature) {
        this.targetFeature = targetFeature;
    }

    public String getTargetImageURI() {
        return this.targetImageURI;
    }

    public void setTargetImageURI(String targetImageURI) {
        this.targetImageURI = targetImageURI;
    }

    public String getPriorityLevel() {
        return this.priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getApplicantName() {
        return this.applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantInfo() {
        return this.applicantInfo;
    }

    public void setApplicantInfo(String applicantInfo) {
        this.applicantInfo = applicantInfo;
    }

    public String getApplicantOrg() {
        return this.applicantOrg;
    }

    public void setApplicantOrg(String applicantOrg) {
        this.applicantOrg = applicantOrg;
    }

    public String getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getOperateType() {
        return this.operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getDispositionStatus() {
        return this.dispositionStatus;
    }

    public void setDispositionStatus(String dispositionStatus) {
        this.dispositionStatus = dispositionStatus;
    }

    public String getDispositionRange() {
        return this.dispositionRange;
    }

    public void setDispositionRange(String dispositionRange) {
        this.dispositionRange = dispositionRange;
    }

    public String getTollgateList() {
        return this.tollgateList;
    }

    public void setTollgateList(String tollgateList) {
        this.tollgateList = tollgateList;
    }

    public String getDispositionArea() {
        return this.dispositionArea;
    }

    public void setDispositionArea(String dispositionArea) {
        this.dispositionArea = dispositionArea;
    }

    public String getReceiveAddr() {
        return this.receiveAddr;
    }

    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public String getReceiveMobile() {
        return this.receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDispositionRemoveOrg() {
        return this.dispositionRemoveOrg;
    }

    public void setDispositionRemoveOrg(String dispositionRemoveOrg) {
        this.dispositionRemoveOrg = dispositionRemoveOrg;
    }

    public String getDispositionRemovePerson() {
        return this.dispositionRemovePerson;
    }

    public void setDispositionRemovePerson(String dispositionRemovePerson) {
        this.dispositionRemovePerson = dispositionRemovePerson;
    }

    public String getDispositionRemoveTime() {
        return this.dispositionRemoveTime;
    }

    public void setDispositionRemoveTime(String dispositionRemoveTime) {
        this.dispositionRemoveTime = dispositionRemoveTime;
    }

    public String getDispositionRemoveReason() {
        return this.dispositionRemoveReason;
    }

    public void setDispositionRemoveReason(String dispositionRemoveReason) {
        this.dispositionRemoveReason = dispositionRemoveReason;
    }

    public SubImageInfoObject getSubImageList() {
        return this.subImageList;
    }

    public void setSubImageList(SubImageInfoObject subImageList) {
        this.subImageList = subImageList;
    }

    public String getResultImageDeclare() {
        return this.resultImageDeclare;
    }

    public void setResultImageDeclare(String resultImageDeclare) {
        this.resultImageDeclare = resultImageDeclare;
    }

    public String getTabId() {
        return this.tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public Integer getAlarmSensitivity() {
        return this.alarmSensitivity;
    }

    public void setAlarmSensitivity(Integer alarmSensitivity) {
        this.alarmSensitivity = alarmSensitivity;
    }
}
