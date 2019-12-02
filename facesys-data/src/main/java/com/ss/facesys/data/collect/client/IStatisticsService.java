package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.dto.CaptureCountDTO;
import com.ss.facesys.data.collect.common.dto.Tendency;
import com.ss.facesys.data.resource.common.dto.CountDTO;
import java.util.List;
import java.util.Map;

public interface IStatisticsService {
  Map<String, Integer> populace(String paramString);
  
  Map<String, Object> statisticsCompany(String paramString);
  
  List<CaptureCountDTO> snapTendency(String paramString);
  
  List<Tendency> addTendency(String paramString);
  
  List<Tendency> leaveTendency(String paramString);
  
  List<CountDTO> populaceByVillages(List<String> paramList);
}
