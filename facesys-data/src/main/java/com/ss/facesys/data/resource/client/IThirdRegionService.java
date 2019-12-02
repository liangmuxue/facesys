package com.ss.facesys.data.resource.client;

import com.ss.facesys.data.resource.common.model.ThirdRegion;
import java.util.List;

public interface IThirdRegionService {
  int batchCompareThirdRegion(List<ThirdRegion> paramList);
  
  ThirdRegion selectOne(ThirdRegion paramThirdRegion);
  
  Integer getRegionTypeByThirdId(String paramString);
}
