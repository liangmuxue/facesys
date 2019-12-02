package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.dto.FrequencyRecordDTO;
import com.ss.isc.data.collect.common.model.FrequencyRecord;
import com.ss.isc.data.collect.common.web.AddPersonDetailQuery;
import com.ss.isc.data.collect.common.web.FrequencyRecordQuery;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FrequencyRecordMapper {
  int batchCompareFrequencyRecord(List<FrequencyRecord> paramList);
  
  List<FrequencyRecord> analysisFrequeryRecord(AddPersonDetailQuery paramAddPersonDetailQuery);
  
  FrequencyRecord getFrequencyRecordById(@Param("addPersonId") String paramString);
  
  List<FrequencyRecordDTO> pages(FrequencyRecordQuery paramFrequencyRecordQuery);
  
  List<FrequencyRecordDTO> frequencyRecordTop(FrequencyRecordQuery paramFrequencyRecordQuery);
  
  int update(FrequencyRecord paramFrequencyRecord);
}
