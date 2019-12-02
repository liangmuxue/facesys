package com.ss.spider.system.role.service;

import com.ss.exception.ServiceException;
import com.ss.service.CWService;
import com.ss.spider.system.resource.model.ResourceTree;
import com.ss.spider.system.role.form.RoleResourceQuery;
import java.util.List;
/**
* 角色权限操作
* @author chao
* @create 2019/10/9
* @email lishuangchao@ss-cas.com
**/
public interface RoleResourceService<RoleResource> extends CWService<RoleResource> {

    List<RoleResource> list(RoleResource paramRoleResource);

    /**
     * 查询角色资源列表
     * @param entity
     * @return
     */
    List<ResourceTree> tree(RoleResourceQuery entity);

    int save(RoleResource paramRoleResource) throws ServiceException;

    /**
     * 添加权限
     * @param paramList1
     * @param paramList2
     * @return
     * @throws ServiceException
     */
    int batchSave(List<RoleResource> paramList1, List<String> paramList2) throws ServiceException;

    int delete(RoleResource paramRoleResource) throws ServiceException;

    /**
     * 删除权限
     * @param paramList
     * @return
     * @throws ServiceException
     */
    int delete(List<RoleResource> paramList) throws ServiceException;

    int delete(String paramString) throws ServiceException;

}
