package com.ss.spider.system.user.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
* 修改密码
* @author chao
* @create 2019/10/11
* @email lishuangchao@ss-cas.com
**/
public class PasswordForm implements Serializable {

    private static final long serialVersionUID = -1L;
    private String userId;
    @NotBlank(message = "{user.ids.empty}")
    @Length(min = 1, max = 32, message = "{user.ids.length}")
    private String opUserId;
    @NotBlank(message = "{user.oldPassword.empty}")
    @Length(min = 1, max = 32, message = "{user.oldPassword.length}")
    private String oldPassword;
    @NotBlank(message = "{user.newPassword.empty}")
    @Length(min = 1, max = 32, message = "{user.newPassword.length}")
    private String newPassword;
    @NotBlank(message = "{updated.user.empty}")
    @Length(min = 1, max = 32, message = "{updated.user.length}")
    public String updatedUserid;

    public String getOpUserId() {
        return this.opUserId;
    }


    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getOldPassword() {
        return this.oldPassword;
    }


    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


    public String getNewPassword() {
        return this.newPassword;
    }


    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    public String getUpdatedUserid() {
        return this.updatedUserid;
    }


    public void setUpdatedUserid(String updatedUserid) {
        this.updatedUserid = updatedUserid;
    }

}
