package com.ss.isc.data.resource.client;

import com.ss.isc.data.resource.common.model.ThirdRegion;
import java.util.List;

public interface IThirdRegionService {
  int batchCompareThirdRegion(List<ThirdRegion> paramList);
  
  ThirdRegion selectOne(ThirdRegion paramThirdRegion);
  
  Integer getRegionTypeByThirdId(String paramString);
}
