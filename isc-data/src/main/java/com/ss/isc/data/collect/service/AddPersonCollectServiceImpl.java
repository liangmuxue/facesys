package com.ss.isc.data.collect.service;

import com.ss.isc.data.collect.client.IAddPersonCollectService;
import com.ss.isc.data.collect.common.model.AddPersonCollect;
import com.ss.isc.data.collect.mapper.AddPersonCollectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* 疑似新增汇总数据查询
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class AddPersonCollectServiceImpl implements IAddPersonCollectService {

  @Autowired
  private AddPersonCollectMapper collectMapper;
  
  @Override
  public List<AddPersonCollect> queryCollectList(List<String> addPersonIdList) { return this.collectMapper.queryList(addPersonIdList); }
}
