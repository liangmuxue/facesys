package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.SensitivePersonCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
* 敏感通行汇总数据操作
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
@Repository
@Mapper
public interface SensitivePersonCollectMapper {

  List<SensitivePersonCollect> queryList(@Param("sensitivePersonIds") List<String> sensitivePersonIds);

  int batchCompareCollect(List<SensitivePersonCollect> paramList);
}
