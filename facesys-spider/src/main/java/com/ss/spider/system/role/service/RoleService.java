package com.ss.spider.system.role.service;

import com.ss.exception.ServiceException;
import com.ss.service.SsService;

import java.util.List;
import java.util.Map;
/**
* 角色相关
* @author chao
* @create 2019/12/4
* @email lishuangchao@ss-cas.com
**/
public interface RoleService<Role> extends SsService<Role> {

    List<Role> gets(Map<String, Object> paramMap);

    /**
     * 查看角色信息
     * @param paramString
     * @return
     */
    Role get(String paramString);

    List<Role> gets(List<String> paramList);

    /**
     * 查询角色
     * @param paramRole
     * @return
     */
    List<Role> list(Role paramRole);

    /**
     * 角色分页查询
     * @param paramRole
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    List<Role> pages(Role paramRole, int paramInt1, int paramInt2);

    /**
     * 添加角色
     * @param paramRole
     * @return
     * @throws ServiceException
     */
    String save(Role paramRole) throws ServiceException;

    /**
     * 修改角色
     * @param paramRole
     * @return
     * @throws ServiceException
     */
    int update(Role paramRole) throws ServiceException;

    int discard(String paramString1, String paramString2) throws ServiceException;

    /**
     * 权限角色逻辑删除
     * @param paramList
     * @param paramString
     * @return
     * @throws ServiceException
     */
    int discard(List<String> paramList, String paramString) throws ServiceException;

    int delete(String paramString) throws ServiceException;

    int delete(List<String> paramList) throws ServiceException;

    int delete(Role paramRole) throws ServiceException;

    /**
     * 切换角色状态
     * @param paramList
     * @param paramInteger
     * @throws ServiceException
     */
    void opStatus(List<String> paramList, Integer paramInteger) throws ServiceException;

}
