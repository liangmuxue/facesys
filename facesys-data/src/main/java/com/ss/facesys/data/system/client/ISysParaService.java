package com.ss.facesys.data.system.client;

import com.ss.facesys.data.system.common.model.SysPara;

import java.util.List;
import java.util.Map;

public interface ISysParaService {

    Map<String, String> getSysParaInfoMap(SysPara sysPara);

    String reset(SysPara sysPara);

    String updateSysPara(List<SysPara> sysParaList);

}
