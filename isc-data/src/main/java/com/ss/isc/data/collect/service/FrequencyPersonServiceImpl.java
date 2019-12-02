package com.ss.isc.data.collect.service;

import com.ss.isc.data.collect.client.IFrequencyPersonService;
import com.ss.isc.data.collect.common.model.FrequencyPerson;
import com.ss.isc.data.collect.mapper.FrequencyPersonMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(rollbackFor = {Exception.class})
public class FrequencyPersonServiceImpl
  implements IFrequencyPersonService
{
  @Autowired
  private FrequencyPersonMapper frequencyPersonMapper;
  
  public int batchCompareFrequencyPerson(List<FrequencyPerson> frequencyPersonList) { return this.frequencyPersonMapper.batchCompareFrequencyPerson(frequencyPersonList); }



  
  public int updateFrequencyPerson(FrequencyPerson frequencyPerson) { return this.frequencyPersonMapper.updateFrequencyPerson(frequencyPerson); }
}
