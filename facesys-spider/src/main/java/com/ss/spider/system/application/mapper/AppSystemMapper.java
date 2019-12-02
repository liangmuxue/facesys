package com.ss.spider.system.application.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.application.model.AppSystem;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AppSystemMapper extends SsMapper<AppSystem> {

    List<AppSystem> pages(AppSystem paramAppSystem);

    List<AppSystem> list(AppSystem paramAppSystem);

    List<AppSystem> gets(Map<String, Object> paramMap);

    int remove(Map<String, Object> paramMap);

    int save(AppSystem paramAppSystem);

    int update(AppSystem paramAppSystem);

}
