package com.ss.spider.system.user.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
* 账户密码
* @author chao
* @create 2019/12/3
* @email lishuangchao@ss-cas.com
**/
public class ResetPasswordForm implements Serializable {

    private static final long serialVersionUID = -1L;
    private String userId;
    @NotBlank(message = "{user.ids.empty}")
    @Length(min = 1, max = 32, message = "{user.ids.length}")
    private String opUserId;
    @NotBlank(message = "{user.newPassword.empty}")
    @Length(min = 8, max = 23, message = "{user.newPassword.length}")
    private String newPassword;
    @NotBlank(message = "{updated.user.empty}")
    @Length(min = 1, max = 32, message = "{updated.user.length}")
    public String updateUserId;

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


    public String getNewPassword() {
        return this.newPassword;
    }


    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    public String getUpdateUserId() {
        return this.updateUserId;
    }


    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

}
