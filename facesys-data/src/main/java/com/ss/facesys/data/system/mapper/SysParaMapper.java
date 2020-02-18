package com.ss.facesys.data.system.mapper;

import com.ss.facesys.data.system.common.model.SysPara;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysParaMapper extends SsMapper<SysPara> {

    void reset();

    void updateParaValueByCode(SysPara sysPara);

}
