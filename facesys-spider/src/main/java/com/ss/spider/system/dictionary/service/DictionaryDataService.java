package com.ss.spider.system.dictionary.service;

import com.ss.exception.ServiceException;
import com.ss.service.SsService;

import java.util.List;
import java.util.Map;

public interface DictionaryDataService<DictionaryData> extends SsService<DictionaryData> {

    List<DictionaryData> pages(DictionaryData paramDictionaryData, int paramInt1, int paramInt2);

    List<DictionaryData> list(DictionaryData paramDictionaryData);

    List<DictionaryData> gets(Map<String, Object> paramMap);

    int save(DictionaryData paramDictionaryData) throws ServiceException;

    int update(DictionaryData paramDictionaryData) throws ServiceException;

    int remove(Map<String, Object> paramMap) throws ServiceException;

}
