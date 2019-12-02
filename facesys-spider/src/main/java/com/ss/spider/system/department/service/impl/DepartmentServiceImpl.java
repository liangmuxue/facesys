package com.ss.spider.system.department.service.impl;

import com.ss.exception.ServiceException;
import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.department.mapper.DepartmentMapper;
import com.ss.spider.system.department.model.Department;
import com.ss.spider.system.department.service.DepartmentService;
import com.ss.tools.UUIDUtils;
import com.github.pagehelper.PageHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("departmentService")
public class DepartmentServiceImpl
        extends AbstractSsServiceImpl<Department>
        implements DepartmentService<Department> {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Department> gets(Map<String, Object> args) {
        return this.departmentMapper.gets(args);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Department> list(Department entity) {
        return this.departmentMapper.list(entity);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Department> pages(Department entity, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return this.departmentMapper.pages(entity);
    }

    @Override
    public List<Department> gets(final List<String> departIds) {
        return gets(new HashMap<String, Object>(1) {

        });
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Department get(final String departId) {
        return get(new HashMap<String, Object>() {

        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String save(final Department entity) throws ServiceException {
        entity.setDepartId(UUIDUtils.getUUID());

        if (get(new HashMap<String, Object>(1) {
        }) != null) {

            throw new ServiceException("部门编号[" + entity.getDepartCode() + "]已存在");
        }

        if (get(new HashMap<String, Object>(2) {
        }) != null) {

            throw new ServiceException("部门名称[" + entity.getDepartCname() + "]已存在");
        }

        try {
            this.departmentMapper.save(entity);
        } catch (Exception e) {
            this.logger.error("新增部门信息失败，原因：", e);
            throw new ServiceException("新增部门信息失败", e);
        }

        return entity.getDepartId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int update(final Department entity) throws ServiceException {
        Department preDepart = get(new HashMap<String, Object>(1) {

        });

        if (preDepart != null && !preDepart.getDepartId().equals(entity.getDepartId())) {
            throw new ServiceException("部门编号[" + entity.getDepartCode() + "]已存在");
        }

        preDepart = get(new HashMap<String, Object>(2) {

        });

        if (preDepart != null && !preDepart.getDepartId().equals(entity.getDepartId())) {
            throw new ServiceException("部门名称[" + entity.getDepartCname() + "]已存在");
        }

        try {
            return this.departmentMapper.update(entity);
        } catch (Exception e) {
            this.logger.error("修改部门信息失败，原因：", e);
            throw new ServiceException("修改部门信息失败", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(Department args) throws ServiceException {
        try {
            return this.departmentMapper.delete(args);
        } catch (Exception e) {
            this.logger.error("物理删除部门信息失败，原因：", e);
            throw new ServiceException("物理删除部门信息失败", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final List<String> departIds) throws ServiceException {
        return delete(new HashMap<String, Object>(1) {

        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int discard(final List<String> departIds, final String userId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {

        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int discard(final String departId, final String userId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {

        });
    }


    private Department get(Map<String, Object> params) {
        List<Department> list = this.departmentMapper.gets(params);
        return CollectionUtils.isEmpty(list) ? null : (Department) list.get(0);
    }


    private int discard(Map<String, Object> args) throws ServiceException {
        try {
            return this.departmentMapper.discard(args);
        } catch (Exception e) {
            this.logger.error("逻辑删除部门信息失败，原因：", e);
            throw new ServiceException("逻辑删除部门信息失败", e);
        }
    }


    private int delete(Map<String, Object> args) throws ServiceException {
        try {
            return this.departmentMapper.remove(args);
        } catch (Exception e) {
            this.logger.error("物理删除部门信息失败，原因：", e);
            throw new ServiceException("物理删除部门信息失败", e);
        }
    }

}
