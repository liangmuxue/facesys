package com.ss.spider.log.service.impl;

import com.ss.service.AbstractCWServiceImpl;
import com.ss.spider.log.bean.dto.LogInsertDTO;
import com.ss.spider.log.bean.dto.QueryDTO;
import com.ss.spider.log.bean.vo.AppLogVO;
import com.ss.spider.log.mapper.AppLogMapper;
import com.ss.spider.log.model.AppLog;
import com.ss.spider.log.model.AppLogOpModel;
import com.ss.spider.log.model.AppLogUser;
import com.ss.spider.log.service.AppLogService;
import com.ss.tools.UUIDUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppLogServiceImpl
        extends AbstractCWServiceImpl<AppLog>
        implements AppLogService<AppLog> {

    @Autowired
    private AppLogMapper appLogMapper;

    public String insert(LogInsertDTO insertDTO) {
        AppLog appLog = new AppLog();
        BeanUtils.copyProperties(insertDTO, appLog);
        appLog.setAppLog(UUIDUtils.getUUID());
        this.appLogMapper.insert(appLog);
        return appLog.getAppLog();
    }


    public AppLogUser getUser(String userId) {
        return this.appLogMapper.getUser(userId);
    }


    public List<AppLogUser> getUserByLoginName(String loginName) {
        return this.appLogMapper.getUserByLoginName(loginName);
    }


    public List<AppLogVO> pages(QueryDTO para, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page<AppLog> data = (Page) this.appLogMapper.query(para);

        Page<AppLogVO> result = new Page<AppLogVO>(data.getPageNum(), data.getPageSize(), data.isCount());

        result.setStartRow(data.getStartRow());
        result.setEndRow(data.getEndRow());
        result.setTotal(data.getTotal());
        result.setPages(data.getPages());
        result.setReasonable(data.getReasonable());
        for (AppLog appLog : data) {
            AppLogVO appLogVO = new AppLogVO();
            BeanUtils.copyProperties(appLog, appLogVO);
            result.add(appLogVO);
        }

        return result;
    }


    public List<AppLog> list(QueryDTO para) {
        return this.appLogMapper.query(para);
    }


    public List<AppLogOpModel> getModelList() {
        return this.appLogMapper.getOpModelList();
    }

}
