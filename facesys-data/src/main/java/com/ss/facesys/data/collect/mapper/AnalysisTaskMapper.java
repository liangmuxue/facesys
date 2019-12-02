package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.AnalysisTask;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnalysisTaskMapper extends SsMapper<AnalysisTask> {
  List<AnalysisTask> queryAnalysisTask(@Param("state") int paramInt, @Param("taskType") Integer paramInteger);
  
  int insertAnalysisTask(AnalysisTask paramAnalysisTask);
  
  int updateAnalysisTaskState(AnalysisTask paramAnalysisTask);
  
  int batchUpdateState(List<String> paramList);
}
