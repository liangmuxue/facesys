package com.ss.isc.data.collect.client;

import com.ss.isc.data.collect.common.model.FrequencyNightPersonCollect;
import java.util.List;
/**
* 夜间高频汇总数据查询
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
public interface IFrequencyNightPersonCollectService {

  List<FrequencyNightPersonCollect> queryCollectList(List<String> paramList);
}
