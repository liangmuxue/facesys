package com.ss.facesys.data.collect.service;

import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.collect.client.ISnapRecordService;
import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.facesys.data.collect.mapper.SnapRecordMapper;
import com.ss.facesys.data.collect.common.web.SnapRecordVO;
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
        return page;
    }
}
