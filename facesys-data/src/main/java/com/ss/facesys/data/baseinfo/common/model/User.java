package com.ss.facesys.data.baseinfo.common.model;

import com.ss.facesys.util.constant.CommonConstant;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * User
 *
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_ge_user")
public class User implements Serializable {

    private static final long serialVersionUID = 6035225092199121479L;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "DEPART_ID")
    private String departId;
    @Column(name = "ORG_ID")
    private String orgId;
    @Column(name = "WORK_CODE")
    private String workCode;
    @Column(name = "LOGIN_NAME")
    private String loginName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "SALT")
    private String salt;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SEX")
    private Double sex;
    @Column(name = "BRITHDAY")
    private String birthday;
    @Column(name = "APP_ON_OFF")
    private Double appOnOff;
    @Column(name = "STATUS")
    private Double status;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "EMAIL")
    private String emall;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "PICTURE_URL")
    private String pictureUrl;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "INIT_FLAG")
    private Double initFlag;
    @Column(name = "CREATED_TIME")
    private Double createdTime;
    @Column(name = "CREATED_USERID")
    private String createdUserid;
    @Column(name = "UPDATED_TIME")
    private Double updatedTime;
    @Column(name = "UPDATED_USERID")
    private String updatedUserId;
    @Column(name = "DELETED_TIME")
    private Double deletedTime;
    @Column(name = "DELETED_USERID")
    private String deletedUserId;

    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getDepartId() {
        return this.departId;
    }


    public void setDepartId(String departId) {
        this.departId = departId;
    }


    public String getOrgId() {
        return this.orgId;
    }


    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public String getWorkCode() {
        return this.workCode;
    }


    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }


    public String getLoginName() {
        return this.loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getSalt() {
        return this.salt;
    }


    public void setSalt(String salt) {
        this.salt = salt;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Double getSex() {
        return this.sex;
    }


    public void setSex(Double sex) {
        this.sex = sex;
    }


    public String getBirthday() {
        return this.birthday;
    }


    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public Double getAppOnOff() {
        return this.appOnOff;
    }


    public void setAppOnOff(Double appOnOff) {
        this.appOnOff = appOnOff;
    }


    public Double getStatus() {
        return this.status;
    }


    public void setStatus(Double status) {
        this.status = status;
    }


    public String getPosition() {
        return this.position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public String getEmall() {
        return this.emall;
    }


    public void setEmall(String emall) {
        this.emall = emall;
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


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Double getInitFlag() {
        return this.initFlag;
    }


    public void setInitFlag(Double initFlag) {
        this.initFlag = initFlag;
    }


    public Double getCreatedTime() {
        return this.createdTime;
    }


    public void setCreatedTime(Double createdTime) {
        this.createdTime = createdTime;
    }


    public String getCreatedUserid() {
        return this.createdUserid;
    }


    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid;
    }


    public Double getUpdatedTime() {
        return this.updatedTime;
    }


    public void setUpdatedTime(Double updatedTime) {
        this.updatedTime = updatedTime;
    }


    public String getUpdatedUserId() {
        return this.updatedUserId;
    }


    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }


    public Double getDeletedTime() {
        return this.deletedTime;
    }


    public void setDeletedTime(Double deletedTime) {
        this.deletedTime = deletedTime;
    }


    public String getDeletedUserId() {
        return this.deletedUserId;
    }


    public void setDeletedUserId(String deletedUserId) {
        this.deletedUserId = deletedUserId;
    }


    public boolean isAdmin() {
        return isAdmin(this.userId);
    }


    public static boolean isAdmin(String id) {
        return CommonConstant.ADMIN_USER_ID.equals(id);
    }

}
