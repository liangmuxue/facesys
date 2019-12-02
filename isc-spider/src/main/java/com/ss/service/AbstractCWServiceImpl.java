package com.ss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;


public abstract class AbstractCWServiceImpl<T> extends Object implements CWService<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected Mapper<T> mapper;

}
