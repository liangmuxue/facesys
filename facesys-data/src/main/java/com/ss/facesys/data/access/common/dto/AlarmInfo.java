package com.ss.facesys.data.access.common.dto;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cw_alarm_info")
public class AlarmInfo {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    @Column(name = "alarm_name")
    private String alarmName;
    @Column(name = "tip_flag")
    private Integer tipFlag;
    @Column(name = "voice_flag")
    private Integer voiceFlag;
    @Column(name = "color_code")
    private String colorCode;
    @Column(name = "voice_url")
    private String voiceUrl;
    @Column(name = "default_flag")
    private Integer defaultFlag;
    @Column(name = "delete_flag")
    private Integer deleteFlag;

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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
