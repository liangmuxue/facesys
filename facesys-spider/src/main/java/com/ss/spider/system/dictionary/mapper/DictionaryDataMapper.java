package com.ss.spider.system.dictionary.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.dictionary.model.DictionaryData;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DictionaryDataMapper extends SsMapper<DictionaryData> {

    List<DictionaryData> pages(DictionaryData paramDictionaryData);

    List<DictionaryData> list(DictionaryData paramDictionaryData);

    List<DictionaryData> gets(Map<String, Object> paramMap);

    int save(DictionaryData paramDictionaryData);

    int update(DictionaryData paramDictionaryData);

    int remove(Map<String, Object> paramMap);

}
