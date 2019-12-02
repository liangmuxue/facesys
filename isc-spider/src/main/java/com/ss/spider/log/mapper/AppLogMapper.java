package com.ss.spider.log.mapper;

import com.ss.mapper.CWMapper;
import com.ss.spider.log.bean.dto.QueryDTO;
import com.ss.spider.log.model.AppLog;
import com.ss.spider.log.model.AppLogOpModel;
import com.ss.spider.log.model.AppLogUser;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppLogMapper extends CWMapper<AppLog> {

    AppLogUser getUser(String paramString);

    List<AppLogUser> getUserByLoginName(String paramString);

    List<AppLog> query(QueryDTO paramQueryDTO);

    List<AppLog> queryFull(QueryDTO paramQueryDTO);

    List<AppLogOpModel> getOpModelList();

}
