package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.FrequencyNightPersonCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* 夜间高频汇总数据操作
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
@Repository
@Mapper
public interface FrequencyNightPersonCollectMapper {
  List<FrequencyNightPersonCollect> queryList(@Param("frequencyNightPersonIds") List<String> frequencyNightPersonIds);

  int batchCompareCollect(List<FrequencyNightPersonCollect> paramList);
}
