package com.ss.spider.system.user.form;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
* 账户权限资源关联查询实体类
* @author chao
* @create 2019/12/23
* @email lishuangchao@ss-cas.com
**/
public class UserResourceQuery implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{user.ids.empty}")
    private String userIds;
    private Integer resourceId;
    private String orgId;
    private String resourceName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
