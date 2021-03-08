package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.facesys.data.collect.common.web.SnapRecordVO;

import java.util.List;

public interface ISnapRecordService {

    List<SnapRecord> page(SnapRecordVO snapRecordVO);
}
