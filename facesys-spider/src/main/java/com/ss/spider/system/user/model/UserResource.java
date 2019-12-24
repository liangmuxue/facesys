package com.ss.spider.system.user.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
/**
* 账户权限实体类
* @author chao
* @create 2019/12/23
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_ge_user_resource_ref")
public class UserResource implements Serializable {

    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "resource_id")
    private Integer resourceId;
    @Column(name = "type")
    private Integer type;
    @Transient
    private String resourceCode;
    @Transient
    private String resourceName;
    @Transient
    private String orgId;
    @Transient
    private Integer authority = 0;
    @Transient
    private List<String> resourceIds;
    @Transient
    private String facedbTypeName;
    @Transient
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getFacedbTypeName() {
        return facedbTypeName;
    }

    public void setFacedbTypeName(String facedbTypeName) {
        this.facedbTypeName = facedbTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
