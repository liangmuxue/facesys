package com.ss.spider.system.systeminfo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "cw_ge_system_info")
public class SystemInfo {

    @Id
    @Column(name = "SYSTEM_ID")
    private String systemId;
    @Column(name = "VERSION")
    private String version;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COPY_RIGHT")
    private String copyRight;
    @Column(name = "OCX_VERSION")
    private String ocxVersion;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "LOGIN_IMG")
    private String loginImg;
    @Column(name = "LOGIN_BACKGROUND")
    private String loginBackground;
    @Column(name = "SYSTEM_LOGO")
    private String systemLogo;
    @Column(name = "ICO")
    private String ico;
    @Column(name = "ABOUT_SYSTEM")
    private String aboutSystem;
    @Column(name = "AUTH_EXPIRES")
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

}
