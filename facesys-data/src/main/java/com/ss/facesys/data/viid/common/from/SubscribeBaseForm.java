package com.ss.facesys.data.viid.common.from;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
* VIID-订阅实体类
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class SubscribeBaseForm {
    @JsonProperty("SubscribeID")
    @JSONField(name = "SubscribeID")
    private String subscribeId;
    @JsonProperty("Title")
    @JSONField(name = "Title")
    private String title;
    @JsonProperty("SubscribeDetail")
    @JSONField(name = "SubscribeDetail")
    private String subscribeDetail;
    @JsonProperty("ResourceURI")
    @JSONField(name = "ResourceURI")
    private String resourceURI;
    @JsonProperty("ApplicantName")
    @JSONField(name = "ApplicantName")
    private String applicantName;
    @JsonProperty("ApplicantOrg")
    @JSONField(name = "ApplicantOrg")
    private String applicantOrg;
    @JsonProperty("BeginTime")
    @JSONField(name = "BeginTime")
    private String beginTime;
    @JsonProperty("EndTime")
    @JSONField(name = "EndTime")
    private String endTime;
    @JsonProperty("ReceiveAddr")
    @JSONField(name = "ReceiveAddr")
    private String receiveAddr;
    @JsonProperty("ReportInterval")
    @JSONField(name = "ReportInterval")
    private Integer reportInterval;
    @JsonProperty("Reason")
    @JSONField(name = "Reason")
    private String reason;
    @JsonProperty("OperateType")
    @JSONField(name = "OperateType")
    private Integer operateType;
    @JsonProperty("SubscribeStatus")
    @JSONField(name = "SubscribeStatus")
    private Integer subscribeStatus;
    @JsonProperty("SubscribeCancelOrg")
    @JSONField(name = "SubscribeCancelOrg")
    private String subscribeCancelOrg;
    @JsonProperty("SubscribeCancelPerson")
    @JSONField(name = "SubscribeCancelPerson")
    private String subscribeCancelPerson;
    @JsonProperty("CancelTime")
    @JSONField(name = "CancelTime")
    private String cancelTime;
    @JsonProperty("CancelReason")
    @JSONField(name = "CancelReason")
    private String cancelReason;

    public String getSubscribeId() {
        return this.subscribeId;
    }

    public void setSubscribeId(final String subscribeId) {
        this.subscribeId = subscribeId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSubscribeDetail() {
        return this.subscribeDetail;
    }

    public void setSubscribeDetail(final String subscribeDetail) {
        this.subscribeDetail = subscribeDetail;
    }

    public String getResourceURI() {
        return this.resourceURI;
    }

    public void setResourceURI(final String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getApplicantName() {
        return this.applicantName;
    }

    public void setApplicantName(final String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantOrg() {
        return this.applicantOrg;
    }

    public void setApplicantOrg(final String applicantOrg) {
        this.applicantOrg = applicantOrg;
    }

    public String getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(final String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }

    public String getReceiveAddr() {
        return this.receiveAddr;
    }

    public void setReceiveAddr(final String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public Integer getReportInterval() {
        return this.reportInterval;
    }

    public void setReportInterval(final Integer reportInterval) {
        this.reportInterval = reportInterval;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public Integer getOperateType() {
        return this.operateType;
    }

    public void setOperateType(final Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getSubscribeStatus() {
        return this.subscribeStatus;
    }

    public void setSubscribeStatus(final Integer subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    public String getSubscribeCancelOrg() {
        return this.subscribeCancelOrg;
    }

    public void setSubscribeCancelOrg(final String subscribeCancelOrg) {
        this.subscribeCancelOrg = subscribeCancelOrg;
    }

    public String getSubscribeCancelPerson() {
        return this.subscribeCancelPerson;
    }

    public void setSubscribeCancelPerson(final String subscribeCancelPerson) {
        this.subscribeCancelPerson = subscribeCancelPerson;
    }

    public String getCancelTime() {
        return this.cancelTime;
    }

    public void setCancelTime(final String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelReason() {
        return this.cancelReason;
    }

    public void setCancelReason(final String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SubscribeBaseForm)) {
            return false;
        }
        final SubscribeBaseForm other = (SubscribeBaseForm) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$subscribeId = this.getSubscribeId();
        final Object other$subscribeId = other.getSubscribeId();
        Label_0065:
        {
            if (this$subscribeId == null) {
                if (other$subscribeId == null) {
                    break Label_0065;
                }
            } else if (this$subscribeId.equals(other$subscribeId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        Label_0102:
        {
            if (this$title == null) {
                if (other$title == null) {
                    break Label_0102;
                }
            } else if (this$title.equals(other$title)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$subscribeDetail = this.getSubscribeDetail();
        final Object other$subscribeDetail = other.getSubscribeDetail();
        Label_0139:
        {
            if (this$subscribeDetail == null) {
                if (other$subscribeDetail == null) {
                    break Label_0139;
                }
            } else if (this$subscribeDetail.equals(other$subscribeDetail)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$resourceURI = this.getResourceURI();
        final Object other$resourceURI = other.getResourceURI();
        Label_0176:
        {
            if (this$resourceURI == null) {
                if (other$resourceURI == null) {
                    break Label_0176;
                }
            } else if (this$resourceURI.equals(other$resourceURI)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$applicantName = this.getApplicantName();
        final Object other$applicantName = other.getApplicantName();
        Label_0213:
        {
            if (this$applicantName == null) {
                if (other$applicantName == null) {
                    break Label_0213;
                }
            } else if (this$applicantName.equals(other$applicantName)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$applicantOrg = this.getApplicantOrg();
        final Object other$applicantOrg = other.getApplicantOrg();
        Label_0250:
        {
            if (this$applicantOrg == null) {
                if (other$applicantOrg == null) {
                    break Label_0250;
                }
            } else if (this$applicantOrg.equals(other$applicantOrg)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$beginTime = this.getBeginTime();
        final Object other$beginTime = other.getBeginTime();
        Label_0287:
        {
            if (this$beginTime == null) {
                if (other$beginTime == null) {
                    break Label_0287;
                }
            } else if (this$beginTime.equals(other$beginTime)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$endTime = this.getEndTime();
        final Object other$endTime = other.getEndTime();
        Label_0324:
        {
            if (this$endTime == null) {
                if (other$endTime == null) {
                    break Label_0324;
                }
            } else if (this$endTime.equals(other$endTime)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$receiveAddr = this.getReceiveAddr();
        final Object other$receiveAddr = other.getReceiveAddr();
        Label_0361:
        {
            if (this$receiveAddr == null) {
                if (other$receiveAddr == null) {
                    break Label_0361;
                }
            } else if (this$receiveAddr.equals(other$receiveAddr)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$reportInterval = this.getReportInterval();
        final Object other$reportInterval = other.getReportInterval();
        Label_0398:
        {
            if (this$reportInterval == null) {
                if (other$reportInterval == null) {
                    break Label_0398;
                }
            } else if (this$reportInterval.equals(other$reportInterval)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$reason = this.getReason();
        final Object other$reason = other.getReason();
        Label_0435:
        {
            if (this$reason == null) {
                if (other$reason == null) {
                    break Label_0435;
                }
            } else if (this$reason.equals(other$reason)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$operateType = this.getOperateType();
        final Object other$operateType = other.getOperateType();
        Label_0472:
        {
            if (this$operateType == null) {
                if (other$operateType == null) {
                    break Label_0472;
                }
            } else if (this$operateType.equals(other$operateType)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$subscribeStatus = this.getSubscribeStatus();
        final Object other$subscribeStatus = other.getSubscribeStatus();
        Label_0509:
        {
            if (this$subscribeStatus == null) {
                if (other$subscribeStatus == null) {
                    break Label_0509;
                }
            } else if (this$subscribeStatus.equals(other$subscribeStatus)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$subscribeCancelOrg = this.getSubscribeCancelOrg();
        final Object other$subscribeCancelOrg = other.getSubscribeCancelOrg();
        Label_0546:
        {
            if (this$subscribeCancelOrg == null) {
                if (other$subscribeCancelOrg == null) {
                    break Label_0546;
                }
            } else if (this$subscribeCancelOrg.equals(other$subscribeCancelOrg)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$subscribeCancelPerson = this.getSubscribeCancelPerson();
        final Object other$subscribeCancelPerson = other.getSubscribeCancelPerson();
        Label_0583:
        {
            if (this$subscribeCancelPerson == null) {
                if (other$subscribeCancelPerson == null) {
                    break Label_0583;
                }
            } else if (this$subscribeCancelPerson.equals(other$subscribeCancelPerson)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$cancelTime = this.getCancelTime();
        final Object other$cancelTime = other.getCancelTime();
        Label_0620:
        {
            if (this$cancelTime == null) {
                if (other$cancelTime == null) {
                    break Label_0620;
                }
            } else if (this$cancelTime.equals(other$cancelTime)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$cancelReason = this.getCancelReason();
        final Object other$cancelReason = other.getCancelReason();
        if (this$cancelReason == null) {
            if (other$cancelReason == null) {
                return true;
            }
        } else if (this$cancelReason.equals(other$cancelReason)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SubscribeBaseForm;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $subscribeId = this.getSubscribeId();
        result = result * 59 + (($subscribeId == null) ? 0 : $subscribeId.hashCode());
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 0 : $title.hashCode());
        final Object $subscribeDetail = this.getSubscribeDetail();
        result = result * 59 + (($subscribeDetail == null) ? 0 : $subscribeDetail.hashCode());
        final Object $resourceURI = this.getResourceURI();
        result = result * 59 + (($resourceURI == null) ? 0 : $resourceURI.hashCode());
        final Object $applicantName = this.getApplicantName();
        result = result * 59 + (($applicantName == null) ? 0 : $applicantName.hashCode());
        final Object $applicantOrg = this.getApplicantOrg();
        result = result * 59 + (($applicantOrg == null) ? 0 : $applicantOrg.hashCode());
        final Object $beginTime = this.getBeginTime();
        result = result * 59 + (($beginTime == null) ? 0 : $beginTime.hashCode());
        final Object $endTime = this.getEndTime();
        result = result * 59 + (($endTime == null) ? 0 : $endTime.hashCode());
        final Object $receiveAddr = this.getReceiveAddr();
        result = result * 59 + (($receiveAddr == null) ? 0 : $receiveAddr.hashCode());
        final Object $reportInterval = this.getReportInterval();
        result = result * 59 + (($reportInterval == null) ? 0 : $reportInterval.hashCode());
        final Object $reason = this.getReason();
        result = result * 59 + (($reason == null) ? 0 : $reason.hashCode());
        final Object $operateType = this.getOperateType();
        result = result * 59 + (($operateType == null) ? 0 : $operateType.hashCode());
        final Object $subscribeStatus = this.getSubscribeStatus();
        result = result * 59 + (($subscribeStatus == null) ? 0 : $subscribeStatus.hashCode());
        final Object $subscribeCancelOrg = this.getSubscribeCancelOrg();
        result = result * 59 + (($subscribeCancelOrg == null) ? 0 : $subscribeCancelOrg.hashCode());
        final Object $subscribeCancelPerson = this.getSubscribeCancelPerson();
        result = result * 59 + (($subscribeCancelPerson == null) ? 0 : $subscribeCancelPerson.hashCode());
        final Object $cancelTime = this.getCancelTime();
        result = result * 59 + (($cancelTime == null) ? 0 : $cancelTime.hashCode());
        final Object $cancelReason = this.getCancelReason();
        result = result * 59 + (($cancelReason == null) ? 0 : $cancelReason.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SubscribeBaseForm(subscribeId=" + this.getSubscribeId() + ", title=" + this.getTitle() + ", subscribeDetail=" + this.getSubscribeDetail() + ", resourceURI=" + this.getResourceURI() + ", applicantName=" + this.getApplicantName() + ", applicantOrg=" + this.getApplicantOrg() + ", beginTime=" + this.getBeginTime() + ", endTime=" + this.getEndTime() + ", receiveAddr=" + this.getReceiveAddr() + ", reportInterval=" + this.getReportInterval() + ", reason=" + this.getReason() + ", operateType=" + this.getOperateType() + ", subscribeStatus=" + this.getSubscribeStatus() + ", subscribeCancelOrg=" + this.getSubscribeCancelOrg() + ", subscribeCancelPerson=" + this.getSubscribeCancelPerson() + ", cancelTime=" + this.getCancelTime() + ", cancelReason=" + this.getCancelReason() + ")";
    }
}
