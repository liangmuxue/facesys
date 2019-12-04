package com.ss.spider.system.role.service;

import com.ss.exception.ServiceException;
import com.ss.service.SsService;
import com.ss.spider.system.resource.model.ResourceTree;
import com.ss.spider.system.role.form.RoleResourceQuery;
import java.util.List;
/**
* 角色资源操作
* @author chao
* @create 2019/12/4
* @email lishuangchao@ss-cas.com
**/
public interface RoleResourceService<RoleResource> extends SsService<RoleResource> {

    List<RoleResource> list(RoleResource paramRoleResource);

    /**
     * 查询角色资源列表
     * @param entity
     * @return
     */
    List<ResourceTree> tree(RoleResourceQuery entity);

    int save(RoleResource paramRoleResource) throws ServiceException;

    /**
     * 添加角色资源
     * @param paramList1
     * @param paramList2
     * @return
     * @throws ServiceException
     */
    int batchSave(List<RoleResource> paramList1, List<String> paramList2) throws ServiceException;

    int delete(RoleResource paramRoleResource) throws ServiceException;

    /**
     * 删除角色权限
     * @param paramList
     * @return
     * @throws ServiceException
     */
    int delete(List<RoleResource> paramList) throws ServiceException;

    int delete(String paramString) throws ServiceException;

}
