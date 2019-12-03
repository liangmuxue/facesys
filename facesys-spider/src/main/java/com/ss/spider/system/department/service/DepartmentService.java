package com.ss.spider.system.department.service;

import com.ss.exception.ServiceException;
import com.ss.service.SsService;

import java.util.List;
import java.util.Map;

/**
 * DepartmentService
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public interface DepartmentService<Department> extends SsService<Department> {

    /**
     * 查询部门信息列表
     *
     * @param paramDepartment
     * @return
     */
    List<Department> list(Department paramDepartment);

    /**
     * 分页查询部门信息列表
     *
     * @param paramDepartment
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    List<Department> pages(Department paramDepartment, int paramInt1, int paramInt2);

    /**
     * 查询部门详情信息
     *
     * @param paramString
     * @return
     */
    Department get(String paramString);

    /**
     * 新增部门
     *
     * @param paramDepartment
     * @return
     * @throws ServiceException
     */
    String save(Department paramDepartment) throws ServiceException;

    /**
     * 修改部门
     *
     * @param paramDepartment
     * @return
     * @throws ServiceException
     */
    int update(Department paramDepartment) throws ServiceException;

    /**
     * 批量物理删除
     *
     * @param paramList
     * @return
     * @throws ServiceException
     */
    int delete(List<String> paramList) throws ServiceException;

    /**
     * 批量逻辑删除
     *
     * @param paramList
     * @param paramString
     * @return
     * @throws ServiceException
     */
    int discard(List<String> paramList, String paramString) throws ServiceException;


    int discard(String paramString1, String paramString2) throws ServiceException;

    int delete(Department paramDepartment) throws ServiceException;

    List<Department> gets(Map<String, Object> paramMap);

    List<Department> gets(List<String> paramList);

}
