package com.ss.facesys.data.collect.service;

import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.collect.client.ISnapRecordService;
import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.facesys.data.collect.mapper.SnapRecordMapper;
import com.ss.facesys.data.collect.common.web.SnapRecordVO;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.file.FileConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class SnapRecordServiceImpl implements ISnapRecordService {

    public static final Log logger = LogFactory.getLog(SnapRecordServiceImpl.class);

    @Resource
    private SnapRecordMapper snapRecordMapper;

    @Override
    public List<SnapRecord> page(SnapRecordVO snapRecordVO) {
        PageHelper.startPage(snapRecordVO.getCurrentPage(), snapRecordVO.getPageSize());
        List<SnapRecord> page = this.snapRecordMapper.page(snapRecordVO);
        for (SnapRecord sr: page) {
            if (StringUtils.isNotBlank(sr.getCaptureUrl()) && !sr.getCaptureUrl().contains(FileConstant.FILE_HTTPADD)) {
                sr.setCaptureUrl(FilePropertiesUtil.getHttpUrl() + sr.getCaptureUrl());
            }
            if (StringUtils.isNotBlank(sr.getPanoramaUrl()) && !sr.getPanoramaUrl().contains(FileConstant.FILE_HTTPADD)) {
                sr.setPanoramaUrl(FilePropertiesUtil.getHttpUrl() + sr.getPanoramaUrl());
            }
        }
        return page;
    }
}
