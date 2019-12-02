package com.ss.spider.system.systeminfo.service.impl;

import com.ss.exception.ServiceException;
import com.ss.spider.system.systeminfo.bean.SystemInfoDTO;
import com.ss.spider.system.systeminfo.mapper.SystemInfoMapper;
import com.ss.spider.system.systeminfo.model.SystemInfo;
import com.ss.spider.system.systeminfo.service.SystemInfoService;
import com.ss.spider.system.systeminfo.service.vo.SystemInfoVO;
import com.ss.tools.DateUtils;
import com.ss.util.nasstorage.config.properties.NasstorageProperties;
import com.ss.util.nasstorage.file.NasstorageFile;
import com.ss.util.nasstorage.util.NasstorageUtil;
import com.github.pagehelper.PageHelper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("systemInfoService")
public class SystemInfoServiceImpl implements SystemInfoService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemInfoMapper systemInfoMapper;

    @Autowired
    private NasstorageProperties nasproperties;

    @Value("${nginx.root.url}")
    protected String nginxRootUrl;


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Integer save(SystemInfoDTO addDTO) throws ServiceException {
        SystemInfo systemInfo = new SystemInfo();
        BeanUtils.copyProperties(addDTO, systemInfo);
        Long now = Long.valueOf(DateUtils.getCurrentTime());
        if (StringUtils.isNotEmpty(addDTO.getLoginImg())) {
            NasstorageFile nasstorageLoginImg = (new NasstorageUtil()).nasstorageOem(this.nasproperties, systemInfo
                    .getLoginImg(), null, now);
            if (nasstorageLoginImg != null) {
                systemInfo.setLoginImg(nasstorageLoginImg.getStorageRelativePath());
            }
        }

        if (StringUtils.isNotEmpty(addDTO.getLoginBackground())) {
            NasstorageFile nasstorageLoginBackground = (new NasstorageUtil()).nasstorageOem(this.nasproperties, systemInfo
                    .getLoginBackground(), null, now);
            if (nasstorageLoginBackground != null) {
                systemInfo.setLoginBackground(nasstorageLoginBackground.getStorageRelativePath());
            }
        }
        if (StringUtils.isNotEmpty(addDTO.getSystemLogo())) {
            NasstorageFile nasstorageSystemLogo = (new NasstorageUtil()).nasstorageOem(this.nasproperties, systemInfo
                    .getSystemLogo(), null, now);
            if (nasstorageSystemLogo != null) {
                systemInfo.setSystemLogo(nasstorageSystemLogo.getStorageRelativePath());
            }
        }
        if (StringUtils.isNotEmpty(addDTO.getIco())) {
            NasstorageFile nasstorageIco = (new NasstorageUtil()).nasstorageOem(this.nasproperties, systemInfo
                    .getIco(), null, now);
            if (nasstorageIco != null) {
                systemInfo.setIco(nasstorageIco.getStorageRelativePath());
            }
        }
        return Integer.valueOf(this.systemInfoMapper.insert(systemInfo));
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Integer update(SystemInfoDTO updateDTO) throws ServiceException {
        try {
            SystemInfo systemInfo = new SystemInfo();
            BeanUtils.copyProperties(updateDTO, systemInfo);
            Long now = Long.valueOf(DateUtils.getCurrentTime());
            if (StringUtils.isNotEmpty(updateDTO.getLoginImg())) {
                NasstorageFile nasstorageLoginImg = (new NasstorageUtil()).nasstorageOem(this.nasproperties, systemInfo
                        .getLoginImg(), null, now);
                if (nasstorageLoginImg != null) {
                    systemInfo.setLoginImg(nasstorageLoginImg.getStorageRelativePath());
                }
            }
            if (StringUtils.isNotEmpty(updateDTO.getLoginBackground())) {
                NasstorageFile nasstorageLoginBackground = (new NasstorageUtil()).nasstorageOem(this.nasproperties, systemInfo
                        .getLoginBackground(), null, now);
                if (nasstorageLoginBackground != null) {
                    systemInfo.setLoginBackground(nasstorageLoginBackground.getStorageRelativePath());
                }
            }
            if (StringUtils.isNotEmpty(updateDTO.getSystemLogo())) {
                NasstorageFile nasstorageSystemLogo = (new NasstorageUtil()).nasstorageOem(this.nasproperties, systemInfo
                        .getSystemLogo(), null, now);
                if (nasstorageSystemLogo != null) {
                    systemInfo.setSystemLogo(nasstorageSystemLogo.getStorageRelativePath());
                }
            }
            if (StringUtils.isNotEmpty(updateDTO.getIco())) {
                NasstorageFile nasstorageIco = (new NasstorageUtil()).nasstorageOem(this.nasproperties, systemInfo
                        .getIco(), null, now);
                if (nasstorageIco != null) {
                    systemInfo.setIco(nasstorageIco.getStorageRelativePath());
                }
            }

            if (StringUtils.isEmpty(systemInfo.getSystemId())) {
                return Integer.valueOf(this.systemInfoMapper.insert(systemInfo));
            }
            return Integer.valueOf(this.systemInfoMapper.updateByPrimaryKeySelective(systemInfo));
        } catch (Exception e) {
            this.logger.error("修改系统信息失败，原因：", e);
            throw new ServiceException("修改系统信息失败", e);
        }
    }


    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public SystemInfoVO get() {
        List<SystemInfo> list = this.systemInfoMapper.selectAll();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        SystemInfo systemInfo = (SystemInfo) list.get(0);
        SystemInfoVO systemInfoVO = new SystemInfoVO();
        BeanUtils.copyProperties(list.get(0), systemInfoVO);
        if (StringUtils.isNotEmpty(systemInfo.getLoginImg())) {
            systemInfoVO.setLoginImgFull(this.nginxRootUrl.trim() + systemInfo.getLoginImg());
        }
        if (StringUtils.isNotEmpty(systemInfo.getLoginBackground())) {
            systemInfoVO.setLoginBackgroundFull(this.nginxRootUrl.trim() + systemInfo.getLoginBackground());
        }
        if (StringUtils.isNotEmpty(systemInfo.getSystemLogo())) {
            systemInfoVO.setSystemLogoFull(this.nginxRootUrl.trim() + systemInfo.getSystemLogo());
        }
        if (StringUtils.isNotEmpty(systemInfo.getIco())) {
            systemInfoVO.setIcoFull(this.nginxRootUrl.trim() + systemInfo.getIco());
        }
        return systemInfoVO;
    }


    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<SystemInfo> pages(SystemInfo entity, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return this.systemInfoMapper.pages(entity);
    }


    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<SystemInfo> list(SystemInfo entity) {
        return this.systemInfoMapper.list(entity);
    }


    public List<SystemInfo> gets(Map<String, Object> args) {
        return this.systemInfoMapper.gets(args);
    }


    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Integer remove(Map<String, Object> args) throws ServiceException {
        try {
            return Integer.valueOf(this.systemInfoMapper.remove(args));
        } catch (Exception e) {
            this.logger.error("删除系统信息失败，原因：", e);
            throw new ServiceException("删除系统信息失败", e);
        }
    }

}
