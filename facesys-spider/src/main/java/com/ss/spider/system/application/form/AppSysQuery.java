package com.ss.spider.system.application.form;

import com.ss.request.Pagination;
import org.hibernate.validator.constraints.NotBlank;


public class AppSysQuery extends Pagination {

    private static final long serialVersionUID = 1L;
    @NotBlank(message = "{app.ids.empty}", groups = {com.ss.valide.APIGetsGroup.class, com.ss.valide.APIDeltGroup.class})
    private String appId;
    private String code;
    private String name;
    private String version;
    private String userId;

    public String getAppId() {
        return this.appId;
    }


    public void setAppId(String appId) {
        this.appId = appId;
    }


    public String getCode() {
        return this.code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getVersion() {
        return this.version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

}
