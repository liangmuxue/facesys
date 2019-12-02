package com.ss.spider.system.user.form;

import com.ss.annotation.IncludeParam;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class UpdateMyUserForm implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{user.ids.empty}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{user.ids.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String opUserId;
    @Length(max = 30, message = "{user.workcode.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String workCode;
    @Length(min = 1, max = 20, message = "{user.name.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String name;
    @Email(message = "{email.format.error}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(max = 50, message = "{email.max.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String email;
    @NotBlank(message = "{user.telephone.empty}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(max = 32, message = "{user.telephone.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String phoneNumber;
    private String pictureUrl;
    @IncludeParam(include = com.ss.enums.CommonEnumClass.Gender.class, message = "{gender.enum.error}", groups = {com.ss.valide.APIEditGroup.class})
    private Integer sex;
    @Length(max = 16, message = "{user.brithday.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String brithday;

    public String getOpUserId() {
        return this.opUserId;
    }


    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }


    public String getWorkCode() {
        return this.workCode;
    }


    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPictureUrl() {
        return this.pictureUrl;
    }


    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    public Integer getSex() {
        return this.sex;
    }


    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getBrithday() {
        return this.brithday;
    }


    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

}
