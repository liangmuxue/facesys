package com.ss.spider.system.role.service.impl;

import com.ss.exception.ServiceException;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.role.mapper.RoleMapper;
import com.ss.spider.system.role.mapper.RoleResourceMapper;
import com.ss.spider.system.role.model.Role;
import com.ss.spider.system.role.model.RoleResource;
import com.ss.spider.system.role.service.RoleService;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import com.github.pagehelper.PageHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
* 角色管理
* @author chao
* @create 2019/12/4
* @email lishuangchao@ss-cas.com
**/
@Service("roleService")
public class RoleServiceImpl
        extends AbstractSsServiceImpl<Role>
        implements RoleService<Role> {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    /**
     * 查询角色
     * @param args
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Role> gets(Map<String, Object> args) {
        return this.roleMapper.gets(args);
    }

    /**
     * 查询角色
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Role> list(Role entity) {
        return this.roleMapper.list(entity);
    }

    /**
     * 角色分页查询
     * @param entity
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Role> pages(Role entity, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return this.roleMapper.pages(entity);
    }

    /**
     * 查询角色信息
     * @param params
     * @return
     */
    private Role get(Map<String, Object> params) {
        List<Role> list = gets(params);
        return CollectionUtils.isEmpty(list) ? null : (Role) list.get(0);
    }

    /**
     * 查看角色信息
     * @param roleId
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Role get(final String roleId) {
        return get(new HashMap<String, Object>() {
            {
                put("roleId", roleId);
            }
        });
    }

    /**
     * 添加权限角色
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String save(final Role args) throws ServiceException {
        //验证角色名称是否存在
        if (get(new HashMap<String, Object>(CommonConstant.COMMON_1) {
            {
                put("roleCname", args.getRoleCname());
            }
        }) != null) {
            throw new ServiceException("角色名称[" + args.getRoleCname() + "]已存在");
        }
        try {
            //随机生成主键
            args.setRoleId(UUIDUtils.getUUID());
            //添加角色
            this.roleMapper.save(args);
        } catch (Exception e) {
            this.logger.error("角色信息保存失败，原因：", e);
            throw new ServiceException("角色信息保存失败", e);
        }
        return args.getRoleId();
    }

    /**
     * 修改角色
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int update(final Role args) throws ServiceException {
        //验证角色名称是否存在
        Role preRole = get(new HashMap<String, Object>(1) {
            {
                put("roleCname", args.getRoleCname());
            }
        });
        if (preRole != null && !preRole.getRoleId().equals(args.getRoleId())) {
            throw new ServiceException("角色名称[" + args.getRoleCname() + "]已存在");
        }
        try {
            //修改角色
            return this.roleMapper.update(args);
        } catch (Exception e) {
            this.logger.error("角色信息更新失败，原因：", e);
            throw new ServiceException("角色信息更新失败", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(Role args) throws ServiceException {
        try {
            if (StringUtils.hasText(args.getRoleId())) {

                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(args.getRoleId());
                this.roleResourceMapper.delete(roleResource);
            }

            return this.roleMapper.delete(args);
        } catch (Exception e) {
            this.logger.error("物理删除角色信息失败，原因：", e);
            throw new ServiceException("物理删除角色信息失败", e);
        }
    }

    /**
     * 切换角色状态
     * @param roleIds
     * @param status
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void opStatus(List<String> roleIds, Integer status) throws ServiceException {
        Example example = new Example(Role.class);
        example.createCriteria().andIn("roleId", roleIds);
        List<Role> roles = this.roleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(roles)) {
            throw new ServiceException("角色启用停用失败 没有该角色ID: " + (String) roleIds.get(0) + " 请检查后再试.");
        }
        Set<String> collect = (Set) roles.stream().map(Role::getRoleId).collect(Collectors.toSet());
        List<String> result = (List) roleIds.stream().filter(roleId -> !collect.contains(roleId)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(result)) {
            throw new ServiceException("角色启用停用失败 失败的角色ID: " + (String) result.get(0) + " 请检查后再试.");
        }
        Role role = new Role();
        example.clear();
        example.createCriteria().andIn("roleId", roleIds);
        role.setStatus(status);
        //切换角色状态
        this.roleMapper.updateByExampleSelective(role, example);
    }

    @Override
    public List<Role> gets(final List<String> roleIds) {
        return gets(new HashMap<String, Object>(1) {

        });
    }

    /**
     * 权限角色逻辑删除
     * @param roleIds
     * @param userId
     * @return
     * @throws ServiceException
     */
    @Override
    public int discard(final List<String> roleIds, final String userId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {
            {
                put("roleIds", roleIds);
                put("deletedUserid", userId);
                put("deletedTime", DateUtils.getCurrentTime());
            }
        });
    }

    @Override
    public int delete(final List<String> roleIds) throws ServiceException {
        return delete(new HashMap<String, Object>(1) {

        });
    }

    @Override
    public int discard(final String roleId, final String userId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {

        });
    }

    @Override
    public int delete(final String roleId) throws ServiceException {
        return delete(new HashMap<String, Object>(1) {

        });
    }

    /**
     * 逻辑删除角色
     * @param args
     * @return
     * @throws ServiceException
     */
    private int discard(Map<String, Object> args) throws ServiceException {
        try {
            //删除角色与资源关联信息
            this.roleResourceMapper.remove(args);
            //删除角色
            return this.roleMapper.discard(args);
        } catch (Exception e) {
            this.logger.error("逻辑删除角色信息失败，原因：", e);
            throw new ServiceException("逻辑删除角色信息失败", e);
        }
    }


    private int delete(Map<String, Object> args) throws ServiceException {
        try {
            this.roleResourceMapper.remove(args);
            return this.roleMapper.remove(args);
        } catch (Exception e) {
            this.logger.error("物理删除角色信息失败，原因：", e);
            throw new ServiceException("物理删除角色信息失败", e);
        }
    }

}
