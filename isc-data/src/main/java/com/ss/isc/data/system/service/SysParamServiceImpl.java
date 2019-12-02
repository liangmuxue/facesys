package com.ss.isc.data.system.service;

import com.ss.isc.data.system.client.ISysParamService;
import com.ss.isc.data.system.common.model.SysParam;
import com.ss.isc.data.system.mapper.SysParamMapper;
import com.ss.isc.util.constant.CommonConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;














@Service
@Transactional(rollbackFor = {Exception.class})
public class SysParamServiceImpl
  implements ISysParamService
{
  @Autowired
  private SysParamMapper sysParamMapper;
  
  public Map<String, String> getSysParamList(SysParam param) {
    List<SysParam> sysParamList = this.sysParamMapper.queryByFilter(param);
    
    Map<String, String> map = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
    if (CollectionUtils.isNotEmpty(sysParamList))
    {
      for (SysParam sysParamDTO : sysParamList)
      {
        map.put(sysParamDTO.getParamKey(), sysParamDTO.getParamValue());
      }
    }
    return map;
  }








  
  public void modSysParam(Map<String, String> sysParamMap) {
    if (sysParamMap.isEmpty()) {
      return;
    }
    
    Set<Map.Entry<String, String>> sysParamEntry = sysParamMap.entrySet();
    List<SysParam> sysParamList = new ArrayList<SysParam>();
    
    for (Map.Entry<String, String> entry : sysParamEntry) {
      
      SysParam sysParamDTO = new SysParam();
      sysParamDTO.setParamKey((String)entry.getKey());
      sysParamDTO.setParamValue((String)entry.getValue());
      sysParamList.add(sysParamDTO);
    } 

    
    this.sysParamMapper.batchModSysParam(sysParamList);
  }








  
  public void addSysParam(SysParam sysParamDTO) {
    this.sysParamMapper.addSysParam(sysParamDTO);
    
    throw new IllegalArgumentException("lalala, 数据回滚");
  }
}
