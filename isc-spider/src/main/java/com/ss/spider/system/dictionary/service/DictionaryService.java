package com.ss.spider.system.dictionary.service;

import com.ss.exception.ServiceException;
import com.ss.service.CWService;

import java.util.List;
import java.util.Map;

public interface DictionaryService<Dictionary> extends CWService<Dictionary> {

    List<Dictionary> pages(Dictionary paramDictionary, int paramInt1, int paramInt2);

    List<Dictionary> list(Dictionary paramDictionary);

    List<Dictionary> gets(Map<String, Object> paramMap);

    int save(Dictionary paramDictionary) throws ServiceException;

    int update(Dictionary paramDictionary) throws ServiceException;

    int remove(Map<String, Object> paramMap) throws ServiceException;

}
