package com.ss.facesys.data.resource.service;

import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.resource.client.IHomePageResourceService;
import com.ss.facesys.data.resource.common.dto.CountDTO;
import com.ss.facesys.data.resource.common.model.AccessDevice;
import com.ss.facesys.data.resource.common.model.Company;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.facesys.data.resource.common.model.Tollgate;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.model.Wifi;
import com.ss.facesys.data.resource.mapper.AccessDeviceMapper;
import com.ss.facesys.data.resource.mapper.CompanyDao;
import com.ss.facesys.data.resource.mapper.RegionMapper;
import com.ss.facesys.data.resource.mapper.ResourceCountMapper;
import com.ss.facesys.data.resource.mapper.TollgateMapper;
import com.ss.facesys.data.resource.mapper.VillageMapper;
import com.ss.facesys.data.resource.mapper.WifiMapper;
import com.ss.facesys.data.statistic.mapper.HomeDataShowMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.file.FilePropertiesUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class HomepageResourceServiceImpl extends BaseServiceImpl implements IHomePageResourceService {
  @Autowired
  private CompanyDao companyMapper;
  @Autowired
  private WifiMapper wifiMapper;
  @Autowired
  private TollgateMapper tollgateMapper;
  @Autowired
  private RegionMapper regionMapper;
  @Autowired
  private ResourceCountMapper resourceCountMapper;
  @Autowired
  private VillageMapper villageMapper;
  @Autowired
  private AccessDeviceMapper accessDeviceMapper;
  @Autowired
  private HomeDataShowMapper homeDataShowMapper;
  
  public List<Company> findCompany(Village village) {
    User user = new User();
    user.setUserId(village.getUserIds());
    Map<String, String> map = new HashMap<String, String>();
    map.put("dsf", dataScopeFilter(user).replace("t1.", ""));
    village.setSqlMap(map);
    
    List<Company> findAllCompanys = this.companyMapper.findAllCompanys(village);
    for (Company company : findAllCompanys) {
      
      if (StringUtils.isNotBlank(company.getCompanyPic()) && !company.getCompanyPic().contains("http")) {
        company.setCompanyPic(FilePropertiesUtil.getHttpUrl() + company.getCompanyPic());
      }
    } 
    
    return findAllCompanys;
  }

  public List<Wifi> findWifis(Village village) {
    User user = new User();
    user.setUserId(village.getUserIds());
    Map<String, String> map = new HashMap<String, String>();
    map.put("dsf", dataScopeFilter(user).replace("t1.", ""));
    village.setSqlMap(map);
    List<Wifi> findWifis = this.wifiMapper.findWifis(village);
    for (Wifi wifi : findWifis) {
      
      if (StringUtils.isNotBlank(wifi.getDevicePicUrl()) && !wifi.getDevicePicUrl().contains("http")) {
        wifi.setDevicePicUrl(FilePropertiesUtil.getHttpUrl() + wifi.getDevicePicUrl());
      }
    } 
    
    return this.wifiMapper.findWifis(village);
  }

  public List<Tollgate> findTollgates(Village village) {
    User user = new User();
    user.setUserId(village.getUserIds());
    Map<String, String> map = new HashMap<String, String>();
    map.put("dsf", dataScopeFilter(user).replace("t1.", ""));
    village.setSqlMap(map);
    List<Tollgate> findTollgates = this.tollgateMapper.findTollgates(village);
    for (Tollgate tollgate : findTollgates) {
      
      if (StringUtils.isNotBlank(tollgate.getTollgatePicUrl()) && !tollgate.getTollgatePicUrl().contains("http")) {
        tollgate.setTollgatePicUrl(FilePropertiesUtil.getHttpUrl() + tollgate.getTollgatePicUrl());
      }
    } 
    return findTollgates;
  }

  public List<Region> findRegion(String regionName) { return this.regionMapper.findRegion(regionName); }

  public List<Region> searchNextRegion(String regionCode) { return this.regionMapper.searchNextRegion(regionCode); }

  public int peopleCount(String villageCode) { return this.resourceCountMapper.peopleCount(villageCode); }

  public int companyCount(String villageCode) { return this.resourceCountMapper.companyCount(villageCode); }

  public int houseCount(String villageCode) { return this.resourceCountMapper.houseCount(villageCode); }

  public List<Village> getVillageInformation(Village village) { return this.villageMapper.findList(village); }

  public List<AccessDevice> findAccessDevice(Village village) {
    User user = new User();
    user.setUserId(village.getUserIds());
    Map<String, String> map = new HashMap<String, String>();
    map.put("dsf", dataScopeFilter(user).replace("t1.", ""));
    village.setSqlMap(map);
    return this.accessDeviceMapper.findAccessDevice(village);
  }

  public List<CountDTO> everyPeopleCount() { return this.resourceCountMapper.everyPeopleCount(); }

  public CountDTO everyCompanyCount(String string) { return this.resourceCountMapper.everyCompanyCount(string); }

  public CountDTO everyHouseCount(String string) { return this.resourceCountMapper.everyHouseCount(string); }
  //一标三实
  public Map selAllOneBidThirty(String villageCode,String periodType) {
    Map map = new HashMap();
    if("1".equals(periodType)){
      //获取日的数据
      map.put("peopleData",this.homeDataShowMapper.selPeopleDay(villageCode));
      map.put("houseData",this.homeDataShowMapper.selHouseDay(villageCode));
      map.put("CompanyData",this.homeDataShowMapper.selCompanyDay(villageCode));
    }
    if("2".equals(periodType)){
      //获取周的数据
      map.put("peopleData",this.homeDataShowMapper.selPeopleWeek(villageCode));
      map.put("houseData",this.homeDataShowMapper.selHouseWeek(villageCode));
      map.put("CompanyData",this.homeDataShowMapper.selCompanyWeek(villageCode));
    }
    if("3".equals(periodType)){
      //获取月的数据
      map.put("peopleData",this.homeDataShowMapper.selPeopleMouth(villageCode));
      map.put("houseData",this.homeDataShowMapper.selHouseMouth(villageCode));
      map.put("CompanyData",this.homeDataShowMapper.selCompanyMouth(villageCode));
    }
    return map;
  }
  //感知增量
  public Map selPercept(String villageCode,String periodType) {
    Map map = new HashMap();
    if("1".equals(periodType)){
      Map passCarData = new HashMap();
      //获取日的数据
      for(int dayNum=0;dayNum <= 29;dayNum++){
        passCarData.put(dayNum,this.homeDataShowMapper.selPerceptDay(villageCode,dayNum));
      }
      map.put("passCarData",passCarData);
    }
    return map;
  }
}
