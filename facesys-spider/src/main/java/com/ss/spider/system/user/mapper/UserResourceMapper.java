package com.ss.spider.system.user.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.user.model.UserResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
/**
* 账户权限关联操作
* @author chao
* @create 2019/12/23
* @email lishuangchao@ss-cas.com
**/
@Component
@Mapper
public interface UserResourceMapper extends SsMapper<UserResource> {
    /**
     * 查询像机列表
     * @param paramUserResource
     * @return
     */
    List<UserResource> allCameraList(UserResource paramUserResource);

    /**
     * 查找拥有权限像机编号
     * @param paramUserResource
     * @return
     */
    List<UserResource> cameraList(UserResource paramUserResource);

    /**
     * 移除权限
     * @param paramUserResource
     * @return
     */
    int remove(UserResource paramUserResource);

    /**
     * 添加权限
     * @param paramUserResource
     * @return
     */
    int add(UserResource paramUserResource);

    /**
     * 查询人像库列表
     * @param paramUserResource
     * @return
     */
    List<UserResource> allFacedbList(UserResource paramUserResource);

    /**
     * 查询人像库权限列表
     * @param paramUserResource
     * @return
     */
    List<UserResource> facedbList(UserResource paramUserResource);

    /**
     * 查询人证设备列表
     * @param paramUserResource
     * @return
     */
    List<UserResource> allDeviceList(UserResource paramUserResource);

    /**
     * 查询人证设备权限列表
     * @param paramUserResource
     * @return
     */
    List<UserResource> deviceList(UserResource paramUserResource);
}
