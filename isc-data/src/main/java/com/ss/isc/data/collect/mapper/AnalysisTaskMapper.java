package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.model.AnalysisTask;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnalysisTaskMapper extends CWMapper<AnalysisTask> {
  List<AnalysisTask> queryAnalysisTask(@Param("state") int paramInt, @Param("taskType") Integer paramInteger);
  
  int insertAnalysisTask(AnalysisTask paramAnalysisTask);
  
  int updateAnalysisTaskState(AnalysisTask paramAnalysisTask);
  
  int batchUpdateState(List<String> paramList);
}
