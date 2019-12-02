package com.ss.facesys.data.collect.service;

import com.ss.facesys.data.baseinfo.common.dto.StatistiscsDTO;
import com.ss.facesys.data.collect.client.IStatisticsService;
import com.ss.facesys.data.collect.common.dto.CaptureCountDTO;
import com.ss.facesys.data.collect.common.dto.Tendency;
import com.ss.facesys.data.collect.common.model.Company;
import com.ss.facesys.data.collect.mapper.AddPersonMapper;
import com.ss.facesys.data.collect.mapper.CaptureMapper;
import com.ss.facesys.data.collect.mapper.CompanyMapper;
import com.ss.facesys.data.collect.mapper.LeavePersonMapper;
import com.ss.facesys.data.collect.mapper.PeopleMapper;
import com.ss.facesys.data.collect.mapper.VehicleMapper;
import com.ss.facesys.data.resource.common.dto.CountDTO;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.constant.CommonConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;













@Service
@Transactional(rollbackFor = {Exception.class})
public class StatisticsServiceImpl
  implements IStatisticsService
{
  private static final String[] COMPANYTYPES = { "enterprises", "selfSupport", "jointVenture", "companyTotal" };

  
  @Autowired
  private PeopleMapper peopleMapper;

  
  @Autowired
  private CompanyMapper companyMapper;

  
  @Autowired
  private CaptureMapper captureMapper;
  
  @Autowired
  private AddPersonMapper addPersonMapper;
  
  @Autowired
  private LeavePersonMapper leavePersonMapper;
  
  @Autowired
  private VehicleMapper vehicleMapper;

  
  public Map<String, Integer> populace(String villageCode) {
    int count = this.peopleMapper.getPeopleNum(villageCode);
    int addCount = this.addPersonMapper.getAddPeopleCount(villageCode, PropertiesUtil.getAddPersonJudgedDays());
    int leaveCount = this.leavePersonMapper.getLeavePeopleCount(villageCode, PropertiesUtil.getLeaveDays());
    
    Long totalTemp = Long.valueOf(count + Long.valueOf(addCount).longValue() - Long.valueOf(leaveCount).longValue());
    if (totalTemp.longValue() < 0L) {
      totalTemp = Long.valueOf(0L);
    }
    Map<String, Integer> m = new HashMap<String, Integer>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
    
    m.put("count", Integer.valueOf(count));
    
    m.put("add", Integer.valueOf(addCount));
    
    m.put("leave", Integer.valueOf(leaveCount));
    
    m.put("total", Integer.valueOf(Integer.parseInt(totalTemp.toString())));
    return m;
  }


  
  public List<CountDTO> populaceByVillages(List<String> villages) {
    List<CountDTO> list = new ArrayList<CountDTO>();
    for (String villageCode : villages) {
      
      String villageName = this.vehicleMapper.selectVillageName(villageCode);
      int people = this.peopleMapper.getPeopleNum(villageCode);
      int add = this.addPersonMapper.getAddPeopleCount(villageCode, PropertiesUtil.getAddPersonJudgedDays());
      int leave = this.leavePersonMapper.getLeavePeopleCount(villageCode, PropertiesUtil.getLeaveDays());
      int everyVillageCount = people + add - leave;
      
      CountDTO c = new CountDTO();
      c.setNum(everyVillageCount);
      c.setVillageCode(villageCode);
      c.setVillageName(villageName);
      list.add(c);
    } 
    return list;
  }









  
  public Map<String, Object> statisticsCompany(String villageCode) {
    Map<String, Object> returnMap = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
    
    int total = 0;




    
    Company company = new Company();
    company.setVillageCode(villageCode);
    total = this.companyMapper.selectCount(company);
    returnMap.put("companyTotal", new StatistiscsDTO("companyType", "实有单位总数", Integer.valueOf(4), Integer.valueOf(total)));
    return returnMap;
  }













  
  public List<CaptureCountDTO> snapTendency(String villageCode) { return this.captureMapper.snapTendency(villageCode); }














  
  public List<Tendency> addTendency(String villageCode) { return this.captureMapper.queryAddTendency(villageCode, PropertiesUtil.getAddPersonJudgedDays()); }












  
  public List<Tendency> leaveTendency(String villageCode) { return this.captureMapper.queryLeaveTendency(villageCode, PropertiesUtil.getLeaveDays()); }
}
