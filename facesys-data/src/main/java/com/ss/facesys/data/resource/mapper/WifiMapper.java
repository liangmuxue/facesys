package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.collect.common.dto.CaptureSumDTO;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.model.Wifi;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WifiMapper extends SsMapper<Wifi> {
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
