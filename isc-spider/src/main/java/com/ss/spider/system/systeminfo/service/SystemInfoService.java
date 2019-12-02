package com.ss.spider.system.systeminfo.service;

import com.ss.exception.ServiceException;
import com.ss.spider.system.systeminfo.bean.SystemInfoDTO;
import com.ss.spider.system.systeminfo.model.SystemInfo;
import com.ss.spider.system.systeminfo.service.vo.SystemInfoVO;

import java.util.List;
import java.util.Map;

public interface SystemInfoService {

    Integer save(SystemInfoDTO paramSystemInfoDTO) throws ServiceException;

    Integer update(SystemInfoDTO paramSystemInfoDTO) throws ServiceException;

    SystemInfoVO get();

    List<SystemInfo> pages(SystemInfo paramSystemInfo, int paramInt1, int paramInt2);

    List<SystemInfo> list(SystemInfo paramSystemInfo);

    List<SystemInfo> gets(Map<String, Object> paramMap);

    Integer remove(Map<String, Object> paramMap) throws ServiceException;

}
