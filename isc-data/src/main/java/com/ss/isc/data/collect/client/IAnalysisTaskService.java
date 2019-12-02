package com.ss.isc.data.collect.client;

import com.ss.isc.data.collect.common.model.*;

import java.util.List;
/**
* 智能分析模块数据操作
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
public interface IAnalysisTaskService {
  List<AnalysisTask> queryAnalysisTask(int paramInt, Integer paramInteger);
  
  int insertAnalysisTask(AnalysisTask paramAnalysisTask);
  
  int updateAnalysisTaskState(AnalysisTask paramAnalysisTask);
  
  int batchUpdateState(List<String> paramList);
  
  void analysisAddJob(List<AddPerson> paramList1, List<AnalysisTask> paramList2, List<AddPersonCollect> paramList3, List<FrequencyRecord> paramList4);

  void analysisFrequencyNightJob(List<FrequencyNightPerson> paramList1, List<AnalysisTask> paramList2, List<FrequencyNightPersonCollect> paramList3);

  void analysisSensitiveJob(List<SensitivePerson> paramList1, List<AnalysisTask> paramList2, List<SensitivePersonCollect> paramList3);
  
  List<AddPersonDetail> queryAccordDetail(String paramString, int paramInt);
  
  AddPersonCollect getCollect(String paramString);
  
  FrequencyRecord getFrequencyRecordById(String paramString);
}
