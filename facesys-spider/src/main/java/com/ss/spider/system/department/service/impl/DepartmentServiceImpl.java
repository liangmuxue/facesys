package com.ss.spider.system.department.service.impl;

import com.github.pagehelper.PageHelper;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.util.StringUtils;
import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.department.mapper.DepartmentMapper;
import com.ss.spider.system.department.model.Department;
import com.ss.spider.system.department.service.DepartmentService;
import com.ss.tools.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DepartmentServiceImpl
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
@Service("departmentService")
public class DepartmentServiceImpl extends AbstractSsServiceImpl<Department> implements DepartmentService<Department> {

    @Resource
    private DepartmentMapper departmentMapper;

    private List<Department> getList(Department entity) {
        Example example = new Example(Department.class);
        example.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
        if (CollectionUtils.isNotEmpty(entity.getOrgIdList())) {
            example.and().andIn("orgId", entity.getOrgIdList());
        }
        if (StringUtils.isNotBlank(entity.getDepartCname())) {
            example.and().andLike("departCname", entity.getDepartCname());
        }
        return departmentMapper.selectByExample(example);
    }

    /**
     * 查询部门信息列表
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Department> list(Department entity) {
        return getList(entity);
    }

    /**
     * 分页查询部门信息列表
     *
     * @param entity
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Department> pages(Department entity, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return getList(entity);
    }

    /**
     * 查询部门详情信息
     *
     * @param departId
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Department get(final String departId) {
        Department department = new Department();
        department.setDepartId(departId);
        return departmentMapper.selectByPrimaryKey(department);
    }

    /**
     * 校验是否部门编号或者名称是否重复
     *
     * @param entity
     */
    private void duplicateCheck(final Department entity) throws ServiceException {
        Example example = new Example(Department.class);
        example.createCriteria().orEqualTo("departCode", entity.getDepartCode()).orEqualTo("departCname", entity.getDepartCname());
        example.and().andEqualTo("status", StatusEnum.EFFECT.getCode());
        if (StringUtils.isNotBlank(entity.getDepartId())) {
            example.and().andNotEqualTo("departId", entity.getDepartId());
        }
        List<Department> nameAndCodeList = departmentMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(nameAndCodeList)) {
            String message = (nameAndCodeList.get(0).getDepartCode().equals(entity.getDepartCode()) ? "部门编号[" + entity.getDepartCode() : "部门名称[" + entity.getDepartCname()) + "]已存在";
            throw new ServiceException(message);
        }
    }

    /**
     * 新增部门
     *
     * @param entity
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String save(final Department entity) throws ServiceException {
        duplicateCheck(entity);
        entity.setDepartId(UUIDUtils.getUUID());
        this.departmentMapper.insertSelective(entity);
        return entity.getDepartId();
    }

    /**
     * 修改部门
     *
     * @param entity
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int update(final Department entity) throws ServiceException {
        duplicateCheck(entity);
        return departmentMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 批量物理删除
     *
     * @param departIds
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final List<String> departIds) throws ServiceException {
        Example example = new Example(Department.class);
        example.createCriteria().andIn("departId", departIds);
        return departmentMapper.deleteByExample(example);
    }

    /**
     * 批量逻辑删除
     *
     * @param departIds
     * @param userId
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int discard(final List<String> departIds, final String userId) throws ServiceException {
        // 更新字段
        Department param = new Department();
        param.setDeleteTime(System.currentTimeMillis());
        param.setDeleteUserId(userId);
        param.setStatus(StatusEnum.EXPIRE.getCode());
        // 更新条件
        Example example = new Example(Department.class);
        example.createCriteria().andIn("departId", departIds);
        return departmentMapper.updateByExampleSelective(param, example);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int discard(final String departId, final String userId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {

        });
    }

    private int discard(Map<String, Object> args) throws ServiceException {
        try {
            return this.departmentMapper.discard(args);
        } catch (Exception e) {
            this.logger.error("逻辑删除部门信息失败，原因：", e);
            throw new ServiceException("逻辑删除部门信息失败", e);
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
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Department> gets(Map<String, Object> args) {
        return this.departmentMapper.gets(args);
    }

    @Override
    public List<Department> gets(final List<String> departIds) {
        return gets(new HashMap<String, Object>(1) {

        });
    }

}
