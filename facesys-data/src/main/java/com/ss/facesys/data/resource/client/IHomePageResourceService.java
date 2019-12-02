package com.ss.facesys.data.resource.client;

import com.ss.facesys.data.resource.common.dto.CountDTO;
import com.ss.facesys.data.resource.common.model.AccessDevice;
import com.ss.facesys.data.resource.common.model.Company;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.facesys.data.resource.common.model.Tollgate;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.model.Wifi;
import java.util.List;
import java.util.Map;

public interface IHomePageResourceService {
  List<Company> findCompany(Village paramVillage);
  
  List<Wifi> findWifis(Village paramVillage);
  
  List<Tollgate> findTollgates(Village paramVillage);
  
  List<Region> findRegion(String paramString);
  
  List<Region> searchNextRegion(String paramString);
  
  int peopleCount(String paramString);
  
  int companyCount(String paramString);
  
  int houseCount(String paramString);
  
  List<Village> getVillageInformation(Village paramVillage);
  
  List<AccessDevice> findAccessDevice(Village paramVillage);
  
  List<CountDTO> everyPeopleCount();
  
  CountDTO everyCompanyCount(String paramString);
  
  CountDTO everyHouseCount(String paramString);

  Map selAllOneBidThirty(String villageCode, String periodType);

  Map selPercept(String villageCode, String periodType);
}
