package com.ss.isc.data.collect.client;

import com.ss.isc.data.collect.common.model.SensitivePersonCollect;
import java.util.List;
/**
* 敏感通行汇总数据查询
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
public interface ISensitivePersonCollectService {
  List<SensitivePersonCollect> queryCollectList(List<String> paramList);
}
