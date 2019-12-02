package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.dto.FrequencyRecordDTO;
import com.ss.facesys.data.collect.common.model.AnalysisTask;
import com.ss.facesys.data.collect.common.model.FrequencyPerson;
import com.ss.facesys.data.collect.common.web.FrequencyRecordQuery;
import com.ss.mapper.SsMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FrequencyPersonMapper extends SsMapper<AnalysisTask> {
  int batchCompareFrequencyPerson(List<FrequencyPerson> paramList);
  
  int updateFrequencyPerson(FrequencyPerson paramFrequencyPerson);
  
  List<FrequencyRecordDTO> pages(FrequencyRecordQuery paramFrequencyRecordQuery);
}
