package com.ss.facesys.data.collect.common.web;

import org.hibernate.validator.constraints.NotBlank;

/**
 * VIID 订阅
 * @author 李爽超 chao
 * @create 2019/10/28
 * @email lishuangchao@ss-cas.com
 **/
public class SubscribeVO {

    @NotBlank(message = "subscribe.subscribeId.empty", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class, com.ss.valide.APIDeltGroup.class})
    private String subscribeId;
    @NotBlank(message = "subscribe.title.empty", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIAddGroup.class})
    private String title;
    @NotBlank(message = "subscribe.subscribeDetail.empty", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIAddGroup.class})
    private String subscribeDetail;
    @NotBlank(message = "subscribe.resourceURI.empty", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIAddGroup.class})
    private String resourceURI;
    @NotBlank(message = "subscribe.applicantName.empty", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIAddGroup.class})
    private String applicantName;
    @NotBlank(message = "subscribe.applicantOrg.empty", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIAddGroup.class})
    private String applicantOrg;
    @NotBlank(message = "subscribe.beginTime.empty", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIAddGroup.class})
    private String beginTime;
    @NotBlank(message = "subscribe.endTime.empty", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIAddGroup.class})
    private String endTime;
    @NotBlank(message = "subscribe.receiveAddr.empty", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIAddGroup.class})
    private String receiveAddr;
    private Integer reportInterval;
    private String reason;
    private Integer subscribeStatus;
    private String subscribeCancelOrg;
    private String subscribeCancelPerson;
    private String cancelTime;
    private String cancelReason;

    public String getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubscribeDetail() {
        return subscribeDetail;
    }

    public void setSubscribeDetail(String subscribeDetail) {
        this.subscribeDetail = subscribeDetail;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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

    public String getReceiveAddr() {
        return receiveAddr;
    }

    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public Integer getReportInterval() {
        return reportInterval;
    }

    public void setReportInterval(Integer reportInterval) {
        this.reportInterval = reportInterval;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(Integer subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    public String getSubscribeCancelOrg() {
        return subscribeCancelOrg;
    }

    public void setSubscribeCancelOrg(String subscribeCancelOrg) {
        this.subscribeCancelOrg = subscribeCancelOrg;
    }

    public String getSubscribeCancelPerson() {
        return subscribeCancelPerson;
    }

    public void setSubscribeCancelPerson(String subscribeCancelPerson) {
        this.subscribeCancelPerson = subscribeCancelPerson;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
