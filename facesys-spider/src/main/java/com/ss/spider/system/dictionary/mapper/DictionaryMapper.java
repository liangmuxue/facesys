package com.ss.spider.system.dictionary.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.dictionary.model.Dictionary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DictionaryMapper extends SsMapper<Dictionary> {

    List<Dictionary> pages(Dictionary paramDictionary);

    List<Dictionary> list(Dictionary paramDictionary);

    List<Dictionary> gets(Map<String, Object> paramMap);

    int save(Dictionary paramDictionary);

    int update(Dictionary paramDictionary);

    int remove(Map<String, Object> paramMap);

}
