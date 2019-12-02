package com.ss.isc.data.collect.service;

import com.ss.isc.data.collect.client.ISensitivePersonCollectService;
import com.ss.isc.data.collect.common.model.SensitivePersonCollect;
import com.ss.isc.data.collect.mapper.SensitivePersonCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
* 敏感通行汇总数据查询
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class SensitivePersonCollectServiceImpl implements ISensitivePersonCollectService {

  @Autowired
  private SensitivePersonCollectMapper collectMapper;
  
  @Override
  public List<SensitivePersonCollect> queryCollectList(List<String> sensitivePersonIdList) { return this.collectMapper.queryList(sensitivePersonIdList); }
}
