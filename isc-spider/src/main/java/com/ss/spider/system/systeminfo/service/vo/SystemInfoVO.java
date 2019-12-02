package com.ss.spider.system.systeminfo.service.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class SystemInfoVO implements Serializable {

    private static final long serialVersionUID = -941643382736045851L;
    private String systemId;
    private String version;
    private String name;
    private String copyRight;
    private String ocxVersion;
    private String remark;
    private String loginImg;
    private String loginImgFull;
    private String loginBackground;
    private String loginBackgroundFull;
    private String systemLogo;
    private String systemLogoFull;
    private String ico;
    private String icoFull;
    private String aboutSystem;
    private String authExpires;

    public String getSystemId() {
        return this.systemId;
    }


    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }


    public String getVersion() {
        return this.version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCopyRight() {
        return this.copyRight;
    }


    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }


    public String getOcxVersion() {
        return this.ocxVersion;
    }


    public void setOcxVersion(String ocxVersion) {
        this.ocxVersion = ocxVersion;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getLoginImg() {
        return this.loginImg;
    }


    public void setLoginImg(String loginImg) {
        this.loginImg = loginImg;
    }


    public String getLoginBackground() {
        return this.loginBackground;
    }


    public void setLoginBackground(String loginBackground) {
        this.loginBackground = loginBackground;
    }


    public String getSystemLogo() {
        return this.systemLogo;
    }


    public void setSystemLogo(String systemLogo) {
        this.systemLogo = systemLogo;
    }


    public String getIco() {
        return this.ico;
    }


    public void setIco(String ico) {
        this.ico = ico;
    }


    public String getAboutSystem() {
        return this.aboutSystem;
    }


    public void setAboutSystem(String aboutSystem) {
        this.aboutSystem = aboutSystem;
    }


    public String getAuthExpires() {
        return this.authExpires;
    }


    public void setAuthExpires(String authExpires) {
        this.authExpires = authExpires;
    }


    public String getLoginImgFull() {
        return this.loginImgFull;
    }


    public void setLoginImgFull(String loginImgFull) {
        this.loginImgFull = loginImgFull;
    }


    public String getLoginBackgroundFull() {
        return this.loginBackgroundFull;
    }


    public void setLoginBackgroundFull(String loginBackgroundFull) {
        this.loginBackgroundFull = loginBackgroundFull;
    }


    public String getSystemLogoFull() {
        return this.systemLogoFull;
    }


    public void setSystemLogoFull(String systemLogoFull) {
        this.systemLogoFull = systemLogoFull;
    }


    public String getIcoFull() {
        return this.icoFull;
    }


    public void setIcoFull(String icoFull) {
        this.icoFull = icoFull;
    }


    public String toString() {
        return (new ToStringBuilder(this))
                .append("systemId", this.systemId)
                .append("version", this.version)
                .append("name", this.name)
                .append("copyRight", this.copyRight)
                .append("ocxVersion", this.ocxVersion)
                .append("remark", this.remark)
                .append("loginImg", this.loginImg)
                .append("loginImgFull", this.loginImgFull)
                .append("loginBackground", this.loginBackground)
                .append("loginBackgroundFull", this.loginBackgroundFull)
                .append("systemLogo", this.systemLogo)
                .append("systemLogoFull", this.systemLogoFull)
                .append("ico", this.ico)
                .append("icoFull", this.icoFull)
                .append("aboutSystem", this.aboutSystem)
                .append("authExpires", this.authExpires)
                .toString();
    }

}
