package com.ss.facesys.data.collect.service;

import com.ss.facesys.data.collect.client.IPopuStatisticsService;
import com.ss.facesys.data.collect.common.dto.PopulationSta;
import com.ss.facesys.data.collect.common.model.People;
import com.ss.facesys.data.collect.mapper.AddPersonMapper;
import com.ss.facesys.data.collect.mapper.LeavePersonMapper;
import com.ss.facesys.data.collect.mapper.PeopleMapper;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.constant.CommonConstant;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;














@Service
@Transactional(rollbackFor = {Exception.class})
public class PopuStatisticsServiceImpl
  implements IPopuStatisticsService
{
  @Autowired
  private PeopleMapper peopleMapper;
  @Autowired
  private AddPersonMapper addPersonMapper;
  @Autowired
  private LeavePersonMapper leavePersonMapper;
  
  public Map<String, Object> getPopulationData(String villageCode) {
    Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

    
    int peopleNumber = this.peopleMapper.getPeopleNum(villageCode);
    
    List<PopulationSta> genderscale = this.peopleMapper.getGenderRatio(villageCode);
    
    int maleNumber = 0;
    int femaleNumber = 0;
    int gkNumber = 0;
    if (genderscale != null && genderscale.size() >= CommonConstant.COMMON_1.intValue()) {
      
      maleNumber = ((PopulationSta)genderscale.get(0)).getNum().intValue();
      if (genderscale.size() >= CommonConstant.COMMON_2.intValue()) {
        
        femaleNumber = ((PopulationSta)genderscale.get(1)).getNum().intValue();
        if (genderscale.size() >= CommonConstant.COMMON_3.intValue())
        {
          gkNumber = ((PopulationSta)genderscale.get(2)).getNum().intValue();
        }
      } 
    } 
    
    List<PopulationSta> gsList = new ArrayList<PopulationSta>();
    gsList.add(new PopulationSta(Integer.valueOf(1), "男", "sex", Integer.valueOf(maleNumber), safeDivide(Integer.valueOf(maleNumber), Integer.valueOf(peopleNumber), 6)));
    gsList.add(new PopulationSta(Integer.valueOf(2), "女", "sex", Integer.valueOf(femaleNumber), safeDivide(Integer.valueOf(femaleNumber), Integer.valueOf(peopleNumber), 6)));
    gsList.add(new PopulationSta(Integer.valueOf(3), "性别不明", "sex", Integer.valueOf(gkNumber), safeDivide(Integer.valueOf(gkNumber), Integer.valueOf(peopleNumber), 6)));
    
    map.put("genderscale", gsList);

    
    List<PopulationSta> typelList = this.peopleMapper.getPeopleType(villageCode);
    for (PopulationSta populationSta : typelList) {
      populationSta.setPercent(safeDivide(populationSta.getNum(), Integer.valueOf(peopleNumber), 6));
    }
    map.put("peopleType", typelList);

    
    List<PopulationSta> labelList = this.peopleMapper.getPeopleLabelInfo(villageCode);
    for (PopulationSta populationSta : labelList) {
      populationSta.setPercent(safeDivide(populationSta.getNum(), Integer.valueOf(peopleNumber), 6));
    }
    map.put("peoplelabel", labelList);

    
    List<PopulationSta> psList = new ArrayList<PopulationSta>();
    int addNum = this.addPersonMapper.getAddPeopleCount(villageCode, PropertiesUtil.getAddPersonJudgedDays());
    int leaveNum = this.leavePersonMapper.getLeavePeopleCount(villageCode, PropertiesUtil.getLeaveDays());
    int peopleCount = peopleNumber + addNum + leaveNum;
    psList.add(new PopulationSta(Integer.valueOf(1), "实际登记人口", "", Integer.valueOf(peopleNumber), safeDivide(Integer.valueOf(peopleNumber), Integer.valueOf(peopleCount), 6)));
    psList.add(new PopulationSta(Integer.valueOf(2), "疑似新增", "", Integer.valueOf(addNum), safeDivide(Integer.valueOf(addNum), Integer.valueOf(peopleCount), 6)));
    psList.add(new PopulationSta(Integer.valueOf(3), "疑似离开", "", Integer.valueOf(leaveNum), safeDivide(Integer.valueOf(leaveNum), Integer.valueOf(peopleCount), 6)));
    map.put("distribution", psList);

    
    List<PopulationSta> agestatisticsList = new ArrayList<PopulationSta>();
    int ageNumOne = this.peopleMapper.getPeopleAgeGroupNum(new People(Integer.valueOf(1), Integer.valueOf(16), villageCode));
    int ageNumTwo = this.peopleMapper.getPeopleAgeGroupNum(new People(Integer.valueOf(17), Integer.valueOf(40), villageCode));
    int ageNumThr = this.peopleMapper.getPeopleAgeGroupNum(new People(Integer.valueOf(41), Integer.valueOf(60), villageCode));
    int ageNumFour = this.peopleMapper.getPeopleAgeGroupNum(new People(Integer.valueOf(60), Integer.valueOf(80), villageCode));
    int ageNumFive = this.peopleMapper.getPeopleAgeGroupNum(new People(Integer.valueOf(81), Integer.valueOf(200), villageCode));
    agestatisticsList
      .add(new PopulationSta(Integer.valueOf(1), "1-16岁", "ageGroup", Integer.valueOf(ageNumOne), safeDivide(Integer.valueOf(ageNumOne), Integer.valueOf(peopleNumber), 6)));
    agestatisticsList
      .add(new PopulationSta(Integer.valueOf(2), "17-40岁", "ageGroup", Integer.valueOf(ageNumTwo), safeDivide(Integer.valueOf(ageNumTwo), Integer.valueOf(peopleNumber), 6)));
    agestatisticsList
      .add(new PopulationSta(Integer.valueOf(3), "41-60岁", "ageGroup", Integer.valueOf(ageNumThr), safeDivide(Integer.valueOf(ageNumThr), Integer.valueOf(peopleNumber), 6)));
    agestatisticsList
      .add(new PopulationSta(Integer.valueOf(4), "61-80岁", "ageGroup", Integer.valueOf(ageNumFour), safeDivide(Integer.valueOf(ageNumFour), Integer.valueOf(peopleNumber), 6)));
    agestatisticsList
      .add(new PopulationSta(Integer.valueOf(5), "80岁", "ageGroup", Integer.valueOf(ageNumFive), safeDivide(Integer.valueOf(ageNumFive), Integer.valueOf(peopleNumber), 6)));
    map.put("agestatistics", agestatisticsList);
    
    return map;
  }










  
  public static <T extends Number> BigDecimal safeDivide(T b1, T b2, int scale) { return safeDivide(b1, b2, BigDecimal.ZERO, scale); }









  
  public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue, int scale) {
    if (null == b1 || null == b2) {
      return defaultValue;
    }
    try {
      return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), scale, 4);
    } catch (Exception e) {
      return defaultValue;
    } 
  }
}
