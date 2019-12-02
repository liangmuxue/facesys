package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.FrequencyNightPerson;
import com.ss.facesys.data.collect.common.model.FrequencyNightPersonDetail;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
* 夜间高频数据操作
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
@Repository
@Mapper
public interface FrequencyNightPersonMapper extends SsMapper<FrequencyNightPerson> {

  List<FrequencyNightPerson> queryFrequencyNightList();

  FrequencyNightPerson findfrequencyNightPerson(FrequencyNightPerson param);

  int batchCompareFrequencyNightPerson(List<FrequencyNightPerson> paramList);

  int batchInsertFrequencyNightPersonDetail(List<FrequencyNightPersonDetail> paramList);

  List<String> queryFrequencyNightPersonDetCaptureIds(@Param("frequencyNightPersonId") String frequencyNightPersonId);

  Integer update(FrequencyNightPerson paramFrequencyNightPerson);

  FrequencyNightPerson findFrequencyNightPersonResult(@Param("id") String id);

  String getLabel(@Param("label") String label);

}
