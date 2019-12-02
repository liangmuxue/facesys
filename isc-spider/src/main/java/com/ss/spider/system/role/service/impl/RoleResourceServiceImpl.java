package com.ss.spider.system.role.service.impl;

import com.ss.exception.ServiceException;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.service.AbstractCWServiceImpl;
import com.ss.spider.system.resource.model.Resource;
import com.ss.spider.system.resource.model.ResourceTree;
import com.ss.spider.system.resource.service.ResourceService;
import com.ss.spider.system.role.form.RoleResourceQuery;
import com.ss.spider.system.role.mapper.RoleResourceMapper;
import com.ss.spider.system.role.model.RoleResource;
import com.ss.spider.system.role.service.RoleResourceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
* 角色权限操作
* @author chao
* @create 2019/10/9
* @email lishuangchao@ss-cas.com
**/
@Service("roleResourceService")
public class RoleResourceServiceImpl extends AbstractCWServiceImpl<RoleResource> implements RoleResourceService<RoleResource> {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    @Qualifier("resourceService")
    private ResourceService<Resource> resourceService;

    /**
     * 查询权限列表
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RoleResource> list(RoleResource entity) {
        return this.roleResourceMapper.list(entity);
    }

    /**
     * 查询角色资源列表
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ResourceTree> tree(RoleResourceQuery entity) {
        RoleResource roleResource = new RoleResource();
        BeanUtils.copyProperties(entity, roleResource);
        //查询权限列表
        List<RoleResource> roleResourceList = this.list(roleResource);
        //查询用户资源列表
        List<Resource> tree = this.resourceService.query(new HashMap<String, Object>() {{
            put("userId", entity.getUserId());
        }});
        //确定资源列表中拥有的权限
        for (int i = 0; i < tree.size(); i++){
            for (int j= 0; j < roleResourceList.size(); j++){
                if(roleResourceList.get(j).getResourceId() != null && roleResourceList.get(j).getResourceId().equals(tree.get(i).getResourceId())){
                    tree.get(i).setAuthority(CommonConstant.COMMON_1);
                }
            }
        }
        List<ResourceTree> resourceTrees = treeList(tree);
        return resourceTrees;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int save(RoleResource entity) throws ServiceException {
        try {
            return this.roleResourceMapper.save(entity);
        } catch (Exception e) {
            this.logger.error("保存角色资源失败,原因:", e);
            throw new ServiceException("保存角色资源失败", e);
        }
    }

    /**
     * 添加角色权限
     * @param list
     * @param resourceIds
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int batchSave(List<RoleResource> list, List<String> resourceIds) throws ServiceException {
        Map<String, List<RoleResource>> map = new HashMap<String, List<RoleResource>>(list.size());
        for (RoleResource resource : list) {
            List<RoleResource> res = (List) map.get(resource.getRoleId());
            if (CollectionUtils.isEmpty(res)) {
                res = new ArrayList<RoleResource>();
                map.put(resource.getRoleId(), res);
            }
            res.add(resource);
        }
        Set<String> keyset = map.keySet();
        int count = 0;
        for (String roleId : keyset) {
            RoleResource entity = new RoleResource();
            entity.setRoleId(roleId);
            List<RoleResource> resources = (List) map.get(roleId);
            List rList = resources.stream().map(RoleResource::getResourceId).collect(Collectors.toList());
            entity.setResourceIds(rList);
            //查询权限
            List<RoleResource> exists = this.roleResourceMapper.list(entity);
            if (CollectionUtils.isNotEmpty(exists)) {
                for (RoleResource resource : exists) {
                    resources.remove(resource);
                }
            }
            if (CollectionUtils.isEmpty(resources)) {
                continue;
            }
            try {
                //添加权限
                count += this.roleResourceMapper.batchSave(resources);
            } catch (Exception e) {
                this.logger.error("批量保存角色资源失败,原因:", e);
                throw new ServiceException("批量保存角色资源失败", e);
            }
        }
        return count;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(RoleResource entity) throws ServiceException {
        try {
            return this.roleResourceMapper.delete(entity);
        } catch (Exception e) {
            this.logger.error("删除角色关联资源失败,原因:", e);
            throw new ServiceException("删除角色关联资源失败", e);
        }
    }

    /**
     * 删除角色权限
     * @param list
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(List<RoleResource> list) throws ServiceException {
        try {
            for (RoleResource item : list) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("roleId", item.getRoleId());
                //删除权限
                this.roleResourceMapper.remove(temp);
            }
        } catch (Exception e) {
            this.logger.error("删除角色关联资源失败,原因:", e);
            throw new ServiceException("删除角色关联资源失败", e);
        }
        return list.size();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final String roleId) throws ServiceException {
        return delete(new RoleResource() {

        });
    }

    public static List<ResourceTree> treeList(List<Resource> resources){
        List<ResourceTree> treeData = new ArrayList<>();
        ResourceTree one = new ResourceTree();
        ResourceTree two = new ResourceTree();
        ResourceTree three = new ResourceTree();
        //生成树
        for (Resource resource : resources) {
            if(resource.getParentId() == null || "".equals(resource.getParentId())){
                ResourceTree tempOne = new ResourceTree();
                BeanUtils.copyProperties(resource, tempOne);
                one = tempOne;
                treeData.add(tempOne);
            } else if (resource.getParentId().equals(one.getResourceId())){
                if (one.getChildren() == null) {
                    one.setChildren(new ArrayList<>());
                }
                ResourceTree tempTwo = new ResourceTree();
                BeanUtils.copyProperties(resource, tempTwo);
                two = tempTwo;
                one.getChildren().add(tempTwo);
            } else if (resource.getParentId().equals(two.getResourceId())){
                if (two.getChildren() == null) {
                    two.setChildren(new ArrayList<>());
                }
                ResourceTree tempThree = new ResourceTree();
                BeanUtils.copyProperties(resource, tempThree);
                three = tempThree;
                two.getChildren().add(tempThree);
            } else if (resource.getParentId().equals(three.getResourceId())){
                if (three.getChildren() == null) {
                    three.setChildren(new ArrayList<>());
                }
                ResourceTree tempFour = new ResourceTree();
                BeanUtils.copyProperties(resource, tempFour);
                three.getChildren().add(tempFour);
            }
        }
        return treeData;
    }
}
