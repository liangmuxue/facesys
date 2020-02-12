package com.ss.spider.system.user.service.impl;

import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.user.mapper.UserResourceMapper;
import com.ss.spider.system.user.model.UserResource;
import com.ss.spider.system.user.service.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* 账户权限关联操作
* @author chao
* @create 2019/12/23
* @email lishuangchao@ss-cas.com
**/
@Service("userResourceService")
public class UserResourceServiceImpl extends AbstractSsServiceImpl<UserResource> implements UserResourceService<UserResource> {

    @Autowired
    private UserResourceMapper userResourceMapper;

    /**
     * 查询账户像机关联列表
     * @param userResource
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserResource> cameraList(UserResource userResource) {
        //查询像机列表
        List<UserResource> allCameraList = this.userResourceMapper.allCameraList(userResource);
        //查找拥有权限像机编号
        List<UserResource> list = this.userResourceMapper.cameraList(userResource);
        if (allCameraList.size() > 0){
            for (int i = 0; i < allCameraList.size(); i++){
                for (int j = 0; j < list.size(); j++){
                    if (allCameraList.get(i).getResourceId().equals(list.get(j).getResourceId())){
                        allCameraList.get(i).setAuthority(1);
                    }
                }
            }
            return allCameraList;
        } else {
            return null;
        }
    }

    /**
     * 修改账户像机关联信息
     * @param userResource
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public String cameraEdit(UserResource userResource) {
        String message;
        userResource.setType(1);
        //移除像机权限
        int add = this.userResourceMapper.remove(userResource);
        if (userResource.getResourceIds() != null) {
            //添加像机权限
            add = this.userResourceMapper.add(userResource);
        }
        if (add > 0){
            message = "success";
        } else {
            message = "error";
        }
        return message;
    }

    /**
     * 查询账户人像库关联列表
     * @param userResource
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserResource> facedbList(UserResource userResource) {
        //查询人像库列表
        List<UserResource> allFacedbList = this.userResourceMapper.allFacedbList(userResource);
        //查找用人像库权限编号
        List<UserResource> list = this.userResourceMapper.facedbList(userResource);
        if (allFacedbList.size() > 0){
            for (int i = 0; i < allFacedbList.size(); i++){
                for (int j = 0; j < list.size(); j++){
                    if (allFacedbList.get(i).getResourceId().equals(list.get(j).getResourceId())){
                        allFacedbList.get(i).setAuthority(1);
                    }
                }
            }
            return allFacedbList;
        } else {
            return null;
        }
    }

    /**
     * 修改账户人像库关联信息
     * @param userResource
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public String facedbEdit(UserResource userResource) {
        String message;
        userResource.setType(3);
        //移除人像库权限
        int add = this.userResourceMapper.remove(userResource);
        if (userResource.getResourceIds() != null) {
            //添加人像库权限
            add = this.userResourceMapper.add(userResource);
        }
        if (add > 0){
            message = "success";
        } else {
            message = "error";
        }
        return message;
    }

    /**
     * 查询账户人证设备关联列表
     * @param userResource
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserResource> deviceList(UserResource userResource) {
        //查询人证设备列表
        List<UserResource> allDeviceList = this.userResourceMapper.allDeviceList(userResource);
        //查找人证设备权限编号
        List<UserResource> list = this.userResourceMapper.deviceList(userResource);
        if (allDeviceList.size() > 0){
            for (int i = 0; i < allDeviceList.size(); i++){
                for (int j = 0; j < list.size(); j++){
                    if (allDeviceList.get(i).getResourceId().equals(list.get(j).getResourceId())){
                        allDeviceList.get(i).setAuthority(1);
                    }
                }
            }
            return allDeviceList;
        } else {
            return null;
        }
    }

    /**
     * 修改账户人证设备关联信息
     * @param userResource
     * @return
     */
    @Override
    public String deviceEdit(UserResource userResource) {
        String message;
        userResource.setType(2);
        //移除人证设备权限
        int add = this.userResourceMapper.remove(userResource);
        //添加人证设备权限
        if (userResource.getResourceIds() != null) {
            add = this.userResourceMapper.add(userResource);
        }
        if (add > 0){
            message = "success";
        } else {
            message = "error";
        }
        return message;
    }
}
