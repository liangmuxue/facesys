package com.ss.spider.system.resource.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class UserResourceQuery implements Serializable {

    private static final long serialVersionUID = -3898873748685868901L;
    @NotBlank(message = "{user.ids.empty}")
    @Length(min = 1, max = 32, message = "{user.ids.length}")
    private String userId;
    @NotBlank(message = "{app.ids.empty}")
    @Length(min = 1, max = 32, message = "{app.ids.length}")
    private String appId;

    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getAppId() {
        return this.appId;
    }


    public void setAppId(String appId) {
        this.appId = appId;
    }

}
