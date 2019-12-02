package com.ss.spider.system.sequence.service;

import com.ss.exception.ServiceException;
import com.ss.service.CWService;

public interface AppSequenceService<AppSequence> extends CWService<AppSequence> {

    String getNextVal(String paramString, char paramChar, int paramInt) throws ServiceException;

    Integer getCurrVal(String paramString);

    int save(AppSequence paramAppSequence) throws ServiceException;

    int update(AppSequence paramAppSequence) throws ServiceException;

    Integer getNextVal(String paramString) throws ServiceException;

    int remove(String paramString) throws ServiceException;

}
