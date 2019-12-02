package com.ss.spider.system.resource.mapper;

import com.ss.mapper.CWMapper;
import com.ss.spider.system.resource.model.Resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ResourceMapper extends CWMapper<Resource> {
    /**
     * 查询用户资源列表
     * @param paramMap
     * @return
     */
    List<Resource> query(Map<String, Object> paramMap);

    List<Resource> gets(Map<String, Object> paramMap);

    List<Resource> list(Resource paramResource);

    List<Resource> pages(Resource paramResource);

    int save(Resource paramResource);

    int update(Resource paramResource);

    int discard(Map<String, Object> paramMap);

    int remove(Map<String, Object> paramMap);

}
