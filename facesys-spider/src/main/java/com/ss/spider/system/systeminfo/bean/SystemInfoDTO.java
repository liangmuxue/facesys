package com.ss.spider.system.systeminfo.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class SystemInfoDTO implements Serializable {

    private static final long serialVersionUID = 4101994110955155235L;
    private String systemId;
    private String version;
    private String name;
    private String copyRight;
    private String ocxVersion;
    private String remark;
    private String loginImg;
    private String loginBackground;
    private String systemLogo;
    private String ico;
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


    public String toString() {
        return (new ToStringBuilder(this))
                .append("systemId", this.systemId)
                .append("version", this.version)
                .append("name", this.name)
                .append("copyRight", this.copyRight)
                .append("ocxVersion", this.ocxVersion)
                .append("remark", this.remark)
                .append("loginImg", this.loginImg)
                .append("loginBackground", this.loginBackground)
                .append("systemLogo", this.systemLogo)
                .append("ico", this.ico)
                .append("aboutSystem", this.aboutSystem)
                .append("authExpires", this.authExpires)
                .toString();
    }

}
