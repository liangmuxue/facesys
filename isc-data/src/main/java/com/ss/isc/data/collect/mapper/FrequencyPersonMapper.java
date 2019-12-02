package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.dto.FrequencyRecordDTO;
import com.ss.isc.data.collect.common.model.AnalysisTask;
import com.ss.isc.data.collect.common.model.FrequencyPerson;
import com.ss.isc.data.collect.common.web.FrequencyRecordQuery;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FrequencyPersonMapper extends CWMapper<AnalysisTask> {
  int batchCompareFrequencyPerson(List<FrequencyPerson> paramList);
  
  int updateFrequencyPerson(FrequencyPerson paramFrequencyPerson);
  
  List<FrequencyRecordDTO> pages(FrequencyRecordQuery paramFrequencyRecordQuery);
}
