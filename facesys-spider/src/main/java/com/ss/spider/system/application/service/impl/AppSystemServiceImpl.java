package com.ss.spider.system.application.service.impl;

import com.ss.exception.ServiceException;
import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.application.mapper.AppSystemMapper;
import com.ss.spider.system.application.model.AppSystem;
import com.ss.spider.system.application.service.AppSystemService;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import com.github.pagehelper.PageHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("appSystemService")
public class AppSystemServiceImpl
        extends AbstractSsServiceImpl<AppSystem>
        implements AppSystemService<AppSystem> {

    @Autowired
    private AppSystemMapper appSystemMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<AppSystem> pages(AppSystem args, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return this.appSystemMapper.pages(args);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<AppSystem> list(AppSystem args) {
        return this.appSystemMapper.list(args);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<AppSystem> gets(Map<String, Object> args) {
        return this.appSystemMapper.gets(args);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String save(final AppSystem entity) throws ServiceException {
        if (!CollectionUtils.isEmpty(gets(new HashMap<String, Object>(1) {

        }))) {
            throw new ServiceException("应用编号[" + entity.getCode() + "]已存在");
        }

        if (!CollectionUtils.isEmpty(gets(new HashMap<String, Object>(1) {

        }))) {
            throw new ServiceException("应用名称[" + entity.getName() + "]已存在");
        }

        try {
            entity.setAppId(UUIDUtils.getUUID());
            entity.setCreatedTime(Long.valueOf(DateUtils.getCurrentTime()));
            this.appSystemMapper.save(entity);
        } catch (Exception e) {
            this.logger.error("新增应用系统主信息失败，原因：", e);
            throw new ServiceException("新增应用系统主信息失败", e);
        }

        return entity.getAppId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int update(AppSystem args) throws ServiceException {
        try {
            return this.appSystemMapper.update(args);
        } catch (Exception e) {
            this.logger.error("修改应用系统主信息失败，原因：", e);
            throw new ServiceException("修改应用系统主信息失败", e);
        }
    }


    @Override
    public List<AppSystem> gets(final List<String> appIds) {
        return gets(new HashMap<String, Object>(1) {

        });
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public AppSystem get(final String appId) {
        List<AppSystem> appSystemList = gets(new HashMap<String, Object>() {

        });
        return CollectionUtils.isEmpty(appSystemList) ? null : (AppSystem) appSystemList.get(0);
    }

    @Override
    public int remove(final List<String> appIds) throws ServiceException {
        return remove(new HashMap<String, Object>(1) {

        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int remove(final String appId) throws ServiceException {
        return remove(new HashMap<String, Object>(1) {

        });
    }

    private int remove(Map<String, Object> args) throws ServiceException {
        try {
            return this.appSystemMapper.remove(args);
        } catch (Exception e) {
            this.logger.error("删除应用系统主信息失败，原因：", e);
            throw new ServiceException("删除应用系统主信息失败", e);
        }
    }

}
