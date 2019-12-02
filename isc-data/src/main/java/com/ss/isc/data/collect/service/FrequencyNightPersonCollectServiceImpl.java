package com.ss.isc.data.collect.service;

import com.ss.isc.data.collect.client.IFrequencyNightPersonCollectService;
import com.ss.isc.data.collect.common.model.FrequencyNightPersonCollect;
import com.ss.isc.data.collect.mapper.FrequencyNightPersonCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
* 夜间高频汇总数据查询
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class FrequencyNightPersonCollectServiceImpl implements IFrequencyNightPersonCollectService {

  @Autowired
  private FrequencyNightPersonCollectMapper collectMapper;
  
  @Override
  public List<FrequencyNightPersonCollect> queryCollectList(List<String> frequencyNightPersonIdList) { return this.collectMapper.queryList(frequencyNightPersonIdList); }
}
