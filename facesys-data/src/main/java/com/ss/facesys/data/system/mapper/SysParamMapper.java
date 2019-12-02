package com.ss.facesys.data.system.mapper;

import com.ss.facesys.data.system.common.model.SysParam;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysParamMapper extends SsMapper<SysParam> {
  List<SysParam> queryByFilter(SysParam paramSysParam);
  
  void batchModSysParam(List<SysParam> paramList);
  
  void addSysParam(SysParam paramSysParam);
}
