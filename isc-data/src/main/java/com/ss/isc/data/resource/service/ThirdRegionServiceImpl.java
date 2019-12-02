package com.ss.isc.data.resource.service;

import com.ss.isc.data.resource.client.IThirdRegionService;
import com.ss.isc.data.resource.common.model.ThirdRegion;
import com.ss.isc.data.resource.mapper.ThirdRegionMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






@Service
@Transactional(rollbackFor = {Exception.class})
public class ThirdRegionServiceImpl
  implements IThirdRegionService
{
  @Autowired
  private ThirdRegionMapper regionMapper;
  
  public int batchCompareThirdRegion(List<ThirdRegion> region) { return this.regionMapper.batchCompareThirdRegion(region); }



  
  public ThirdRegion selectOne(ThirdRegion region) { return (ThirdRegion)this.regionMapper.selectOne(region); }




  
  public Integer getRegionTypeByThirdId(String thirdId) { return this.regionMapper.getRegionTypeByThirdId(thirdId); }
}
