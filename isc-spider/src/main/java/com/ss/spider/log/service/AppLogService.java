package com.ss.spider.log.service;

import com.ss.service.CWService;
import com.ss.spider.log.bean.dto.LogInsertDTO;
import com.ss.spider.log.bean.dto.QueryDTO;
import com.ss.spider.log.bean.vo.AppLogVO;
import com.ss.spider.log.model.AppLogOpModel;
import com.ss.spider.log.model.AppLogUser;

import java.util.List;

public interface AppLogService<AppLog> extends CWService<AppLog> {

    String insert(LogInsertDTO paramLogInsertDTO);

    AppLogUser getUser(String paramString);

    List<AppLogUser> getUserByLoginName(String paramString);

    List<AppLogVO> pages(QueryDTO paramQueryDTO, int paramInt1, int paramInt2);

    List<AppLog> list(QueryDTO paramQueryDTO);

    List<AppLogOpModel> getModelList();

}
