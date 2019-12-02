package com.ss.spider.system.department.service;

import com.ss.exception.ServiceException;
import com.ss.service.CWService;

import java.util.List;
import java.util.Map;

public interface DepartmentService<Department> extends CWService<Department> {

    List<Department> gets(Map<String, Object> paramMap);

    List<Department> gets(List<String> paramList);

    Department get(String paramString);

    List<Department> list(Department paramDepartment);

    List<Department> pages(Department paramDepartment, int paramInt1, int paramInt2);

    String save(Department paramDepartment) throws ServiceException;

    int update(Department paramDepartment) throws ServiceException;

    int delete(Department paramDepartment) throws ServiceException;

    int delete(List<String> paramList) throws ServiceException;

    int discard(List<String> paramList, String paramString) throws ServiceException;

    int discard(String paramString1, String paramString2) throws ServiceException;

}
