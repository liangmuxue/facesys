package com.ss.facesys.data.archives.common.web;

/**
 * WarningVO 人员预警
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
public class WarningVO {

    private String captureUrlFull;
    private String installAdd;
    private String captureTime;
    private Integer state;
    private Integer amount;
    private Integer leaveDays;
    private Integer days;
    private String warningType;

    public String getCaptureUrlFull() {
        return this.captureUrlFull;
    }


    public void setCaptureUrlFull(String captureUrlFull) {
        this.captureUrlFull = captureUrlFull;
    }


    public String getInstallAdd() {
        return this.installAdd;
    }


    public void setInstallAdd(String installAdd) {
        this.installAdd = installAdd;
    }


    public String getCaptureTime() {
        return this.captureTime;
    }


    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }


    public Integer getLeaveDays() {
        return this.leaveDays;
    }


    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
    }


    public Integer getDays() {
        return this.days;
    }


    public void setDays(Integer days) {
        this.days = days;
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public Integer getAmount() {
        return this.amount;
    }


    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    @Override
    public String toString() {
        return "WarningVO{" +
                "captureUrlFull='" + captureUrlFull + '\'' +
                ", installAdd='" + installAdd + '\'' +
                ", captureTime='" + captureTime + '\'' +
                ", state=" + state +
                ", amount=" + amount +
                ", leaveDays=" + leaveDays +
                ", days=" + days +
                ", warningType='" + warningType + '\'' +
                '}';
    }

}
