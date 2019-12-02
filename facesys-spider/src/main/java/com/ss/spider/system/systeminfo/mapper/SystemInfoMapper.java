package com.ss.spider.system.systeminfo.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.systeminfo.model.SystemInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemInfoMapper extends SsMapper<SystemInfo> {

    List<SystemInfo> pages(SystemInfo paramSystemInfo);

    List<SystemInfo> list(SystemInfo paramSystemInfo);

    List<SystemInfo> gets(Map<String, Object> paramMap);

    int remove(Map<String, Object> paramMap);

}
