package com.ss.spider.system.systeminfo.form;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class SystemInfoForm {

    @NotBlank(message = "{systemInfo.id.empty}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{systemInfo.id.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String systemId;
    @NotBlank(message = "{systemInfo.version.empty}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{systemInfo.version.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String version;
    @NotBlank(message = "{systemInfo.name.empty}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{systemInfo.name.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String name;
    @Length(max = 512, message = "{systemInfo.copyRight.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String copyRight;
    @Length(max = 64, message = "{systemInfo.ocxVersion.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String ocxVersion;
    @Length(max = 255, message = "{systemInfo.remark.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String remark;
    private String loginImg;
    private String loginBackground;
    private String systemLogo;
    private String ico;
    @Length(max = 64, message = "{systemInfo.aboutSystem.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String aboutSystem;
    @Length(max = 64, message = "{systemInfo.authExpires.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String authExpires;
    private String userId;

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


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
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
