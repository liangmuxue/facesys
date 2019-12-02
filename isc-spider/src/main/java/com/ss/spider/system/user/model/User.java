package com.ss.spider.system.user.model;

import com.ss.common.Constants;
import com.ss.entity.DTEntity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ss.spider.system.resource.model.Resource;
import com.ss.spider.system.resource.model.ResourceTree;
import org.apache.commons.lang3.StringUtils;


@Table(name = "CW_GE_USER")
public class User extends DTEntity {

    private static final long serialVersionUID = -5335331512374529436L;
    @Id
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
    private Integer sex;
    @Column(name = "BRITHDAY")
    private String brithday;
    @Column(name = "APP_ON_OFF")
    private Integer appOnOff;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "PICTURE_URL")
    private String pictureUrl;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "INIT_FLAG")
    private Integer initFlag;
    @Transient
    private String roleName;
    @Transient
    private List<String> orgIds;
    @Transient
    private List<String> roleIds;
    @Transient
    private List<ResourceTree> children;
    @Transient
    private List<Resource> resourceList;;

    public List<String> getOrgIds() {
        return this.orgIds;
    }


    public void setOrgIds(List<String> orgIds) {
        this.orgIds = orgIds;
    }


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


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getPosition() {
        return this.position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
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


    public Integer getAppOnOff() {
        return this.appOnOff;
    }


    public void setAppOnOff(Integer appOnOff) {
        this.appOnOff = appOnOff;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Integer getInitFlag() {
        return this.initFlag;
    }


    public void setInitFlag(Integer initFlag) {
        this.initFlag = initFlag;
    }


    public String getRoleName() {
        return this.roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPictureUrlFull() {
        String temp = "";
        if (StringUtils.isNotBlank(this.pictureUrl)) {
            temp = Constants.NGINX_IMAGE_PATH + this.pictureUrl;
        }
        return temp;
    }


    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public List<ResourceTree> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceTree> children) {
        this.children = children;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
}
