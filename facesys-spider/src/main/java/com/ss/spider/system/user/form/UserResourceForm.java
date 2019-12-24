package com.ss.spider.system.user.form;

import org.hibernate.validator.constraints.NotBlank;

/**
* 账户像机权限实体类
* @author chao
* @create 2019/12/23
* @email lishuangchao@ss-cas.com
**/
public class UserResourceForm {

    @NotBlank(message = "{user.ids.empty}")
    private String userIds;
    private String resourceIds;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }
}
