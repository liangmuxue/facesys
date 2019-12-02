package com.ss.spider.system.user.service.impl;

import com.ss.exception.ServiceException;
import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.user.mapper.UserRoleMapper;
import com.ss.spider.system.user.model.UserRole;
import com.ss.spider.system.user.service.UserRoleService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("userRoleService")
public class UserRoleServiceImpl
        extends AbstractSsServiceImpl<UserRole>
        implements UserRoleService<UserRole> {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserRole> list(UserRole entity) {
        return this.userRoleMapper.list(entity);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int save(UserRole entity) throws ServiceException {
        try {
            return this.userRoleMapper.save(entity);
        } catch (Exception e) {
            this.logger.error("保存用户角色信息失败,原因:", e);
            throw new ServiceException("保存用户角色信息失败", e);
        }
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int batchSave(final List<UserRole> list, final List<String> roleIds) throws ServiceException {
        if (this.userRoleMapper.total(new HashMap<String, Object>(2) {
        }) > 0) {


            throw new ServiceException("用户已经存在相同的角色");
        }

        try {
            return this.userRoleMapper.batchSave(list);
        } catch (Exception e) {
            this.logger.error("批量保存用户角色信息失败,原因:", e);
            throw new ServiceException("批量保存用户角色信息失败", e);
        }
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(UserRole entity) throws ServiceException {
        try {
            return this.userRoleMapper.delete(entity);
        } catch (Exception e) {
            this.logger.error("删除用户角色信息失败,原因:", e);
            throw new ServiceException("删除用户角色信息失败");
        }
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(List<UserRole> list) throws ServiceException {
        try {
            for (UserRole item : list) {
                this.userRoleMapper.delete(item);
            }
        } catch (Exception e) {
            this.logger.error("删除用户角色信息失败,原因:", e);
            throw new ServiceException("删除用户角色信息失败");
        }

        return list.size();
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final String roleId) throws ServiceException {
        return delete(new UserRole() {

        });
    }

}
