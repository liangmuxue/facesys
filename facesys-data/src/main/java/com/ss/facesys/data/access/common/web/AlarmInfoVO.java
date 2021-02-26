package com.ss.facesys.data.access.common.web;

import com.ss.valide.APIEditGroup;

import javax.validation.constraints.NotNull;

public class AlarmInfoVO {

    private Integer currentPage;
    private Integer pageSize;
    @NotNull(message = "alarm.alarmId .empty", groups = {APIEditGroup.class})
    private Integer id;
    private String alarmName;
    private Integer tipFlag;
    private Integer voiceFlag;
    private String colorCode;
    private String voiceUrl;
    private Integer defaultFlag;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Integer getTipFlag() {
        return tipFlag;
    }

    public void setTipFlag(Integer tipFlag) {
        this.tipFlag = tipFlag;
    }

    public Integer getVoiceFlag() {
        return voiceFlag;
    }

    public void setVoiceFlag(Integer voiceFlag) {
        this.voiceFlag = voiceFlag;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
}
