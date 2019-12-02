package com.ss.spider.system.user.service;

import com.ss.exception.ServiceException;
import com.ss.service.SsService;

import java.util.List;

public interface UserRoleService<UserRole> extends SsService<UserRole> {

    List<UserRole> list(UserRole paramUserRole);

    int save(UserRole paramUserRole) throws ServiceException;

    int batchSave(List<UserRole> paramList1, List<String> paramList2) throws ServiceException;

    int delete(UserRole paramUserRole) throws ServiceException;

    int delete(List<UserRole> paramList) throws ServiceException;

    int delete(String paramString) throws ServiceException;

}
