package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.collect.common.dto.CaptureSumDTO;
import com.ss.isc.data.resource.common.model.Village;
import com.ss.isc.data.resource.common.model.Wifi;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WifiMapper extends CWMapper<Wifi> {
  List<Wifi> findWifis(Village paramVillage);
  
  int insertWifi(Wifi paramWifi);
  
  int updateWifi(Wifi paramWifi);
  
  int deleteWifi(Wifi paramWifi);
  
  List<Wifi> findAllWifis(Wifi paramWifi);
  
  List<CaptureSumDTO> getWifiSum();
  
  List<Wifi> pages(Wifi paramWifi);
  
  Wifi check(Wifi paramWifi);
  
  int updateBatch(List<Wifi> paramList);
  
  List<CaptureSumDTO> getWifiAllSum();
}
