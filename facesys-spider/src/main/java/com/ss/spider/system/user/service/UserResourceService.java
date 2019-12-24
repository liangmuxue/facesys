package com.ss.spider.system.user.service;

import com.ss.service.SsService;

import java.util.List;

/**
* 账户权限关联操作
* @author chao
* @create 2019/12/23
* @email lishuangchao@ss-cas.com
**/
public interface UserResourceService<UserResource> extends SsService<UserResource> {

    /**
     * 查询账户像机关联列表
     * @param paramUserResource
     * @return
     */
    List<UserResource> cameraList(UserResource paramUserResource);

    /**
     * 修改账户像机关联信息
     * @param paramUserResource
     * @return
     */
    String cameraEdit(UserResource paramUserResource);

    /**
     * 查询账户人像库关联列表
     * @param paramUserResource
     * @return
     */
    List<UserResource> facedbList(UserResource paramUserResource);

    /**
     * 修改账户人像库关联信息
     * @param paramUserResource
     * @return
     */
    String facedbEdit(UserResource paramUserResource);
}
