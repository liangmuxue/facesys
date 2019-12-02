package com.ss.isc.data.system.mapper;

import com.ss.isc.data.system.common.model.SysParam;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysParamMapper extends CWMapper<SysParam> {
  List<SysParam> queryByFilter(SysParam paramSysParam);
  
  void batchModSysParam(List<SysParam> paramList);
  
  void addSysParam(SysParam paramSysParam);
}
