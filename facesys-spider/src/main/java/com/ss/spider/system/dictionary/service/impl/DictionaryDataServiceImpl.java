package com.ss.spider.system.dictionary.service.impl;

import com.ss.exception.ServiceException;
import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.dictionary.mapper.DictionaryDataMapper;
import com.ss.spider.system.dictionary.model.DictionaryData;
import com.ss.spider.system.dictionary.service.DictionaryDataService;
import com.github.pagehelper.PageHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service
public class DictionaryDataServiceImpl
        extends AbstractSsServiceImpl<DictionaryData>
        implements DictionaryDataService<DictionaryData> {

    @Autowired
    private DictionaryDataMapper dictionaryDataMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<DictionaryData> pages(DictionaryData dictionary, int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return this.dictionaryDataMapper.pages(dictionary);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<DictionaryData> list(DictionaryData dictionaryData) {
        return this.dictionaryDataMapper.list(dictionaryData);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<DictionaryData> gets(Map<String, Object> para) {
        return this.dictionaryDataMapper.gets(para);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int save(final DictionaryData dictionary) throws ServiceException {
        List<DictionaryData> dictionaries = gets(new HashMap<String, Object>(1) {

        });
        if (!CollectionUtils.isEmpty(dictionaries)) {
            throw new ServiceException("字典编号[" + dictionary.getDataTypeCode() + "]已存在");
        }

        try {
            return this.dictionaryDataMapper.save(dictionary);
        } catch (Exception e) {
            this.logger.error("新增字典内容错误，原因：" + e.getMessage());
            throw new ServiceException("新增字典内容错误", e);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int update(final DictionaryData dictionary) throws ServiceException {
        List<DictionaryData> dictionaries = gets(new HashMap<String, Object>(1) {

        });
        if (!CollectionUtils.isEmpty(dictionaries) && !((DictionaryData) dictionaries.get(0))
                .getDataTypeCode().equals(dictionary
                        .getDataTypeCode())) {
            throw new ServiceException("字典编号[" + dictionary.getDataTypeCode() + "]已存在");
        }
        try {
            return this.dictionaryDataMapper.update(dictionary);
        } catch (Exception e) {
            this.logger.error("编辑字典内容错误，原因：" + e.getMessage());
            throw new ServiceException("编辑字典内容错误", e);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int remove(Map<String, Object> para) throws ServiceException {
        try {
            return this.dictionaryDataMapper.remove(para);
        } catch (Exception e) {
            this.logger.error("删除字典内容错误，原因：" + e.getMessage());
            throw new ServiceException("删除字典内容错误", e);
        }
    }

}
