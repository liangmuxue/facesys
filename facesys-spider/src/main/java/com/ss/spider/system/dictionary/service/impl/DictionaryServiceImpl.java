package com.ss.spider.system.dictionary.service.impl;

import com.ss.exception.ServiceException;
import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.dictionary.mapper.DictionaryDataMapper;
import com.ss.spider.system.dictionary.mapper.DictionaryMapper;
import com.ss.spider.system.dictionary.model.Dictionary;
import com.ss.spider.system.dictionary.service.DictionaryService;
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
public class DictionaryServiceImpl
        extends AbstractSsServiceImpl<Dictionary>
        implements DictionaryService<Dictionary> {

    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private DictionaryDataMapper dictionaryDataMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Dictionary> pages(Dictionary dictionary, int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return this.dictionaryMapper.pages(dictionary);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Dictionary> list(Dictionary dictionary) {
        return this.dictionaryMapper.list(dictionary);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Dictionary> gets(Map<String, Object> para) {
        return this.dictionaryMapper.gets(para);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int save(final Dictionary dictionary) throws ServiceException {
        List<Dictionary> dictionaries = gets(new HashMap<String, Object>(1) {

        });
        if (!CollectionUtils.isEmpty(dictionaries)) {
            throw new ServiceException("字典编号[" + dictionary.getDicTypeCode() + "]已存在");
        }

        try {
            return this.dictionaryMapper.save(dictionary);
        } catch (Exception e) {
            this.logger.error("新增字典内容错误，原因：" + e.getMessage());
            throw new ServiceException("新增字典内容错误", e);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int update(final Dictionary dictionary) throws ServiceException {
        List<Dictionary> dictionaries = gets(new HashMap<String, Object>(1) {

        });
        if (!CollectionUtils.isEmpty(dictionaries) && !((Dictionary) dictionaries.get(0))
                .getDicTypeCode().equals(dictionary
                        .getDicTypeCode())) {
            throw new ServiceException("字典编号[" + dictionary.getDicTypeCode() + "]已存在");
        }
        try {
            return this.dictionaryMapper.update(dictionary);
        } catch (Exception e) {
            this.logger.error("编辑字典内容错误，原因：" + e.getMessage());
            throw new ServiceException("编辑字典内容错误", e);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int remove(Map<String, Object> para) throws ServiceException {
        try {
            this.dictionaryDataMapper.remove(para);
            return this.dictionaryMapper.remove(para);
        } catch (Exception e) {
            this.logger.error("删除字典内容错误，原因：" + e.getMessage());
            throw new ServiceException("删除字典内容错误", e);
        }
    }

}
