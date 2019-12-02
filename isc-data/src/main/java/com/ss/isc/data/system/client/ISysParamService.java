package com.ss.isc.data.system.client;

import com.ss.isc.data.system.common.model.SysParam;
import java.util.Map;

public interface ISysParamService {
  Map<String, String> getSysParamList(SysParam paramSysParam);
  
  void modSysParam(Map<String, String> paramMap);
  
  void addSysParam(SysParam paramSysParam);
}
