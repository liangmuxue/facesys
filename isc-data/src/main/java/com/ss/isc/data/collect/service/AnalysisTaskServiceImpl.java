package com.ss.isc.data.collect.service;

import com.ss.isc.data.collect.client.IAnalysisTaskService;
import com.ss.isc.data.collect.common.model.*;
import com.ss.isc.data.collect.mapper.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* 智能分析数据操作类
* @author chao
* @create 2019/9/10
* @email lishuangchao@ss-cas.com
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class AnalysisTaskServiceImpl implements IAnalysisTaskService {

  @Autowired
  private AnalysisTaskMapper analysisTaskMapper;
  @Autowired
  private AddPersonMapper addPersonMapper;
  @Autowired
  private FrequencyRecordMapper frequencyRecordMapper;
  @Autowired
  private AddPersonCollectMapper collectMapper;
  @Autowired
  private FrequencyNightPersonMapper frequencyNightPersonMapper;
  @Autowired
  private FrequencyNightPersonCollectMapper frequencyNightCollectMapper;
  @Autowired
  private SensitivePersonMapper sensitivePersonMapper;
  @Autowired
  private SensitivePersonCollectMapper sensitivePersonCollectMapper;
  
  @Override
  public List<AnalysisTask> queryAnalysisTask(int state, Integer taskType) { return this.analysisTaskMapper.queryAnalysisTask(state, taskType); }
  
  @Override
  public int insertAnalysisTask(AnalysisTask analysisTask) { return this.analysisTaskMapper.insertAnalysisTask(analysisTask); }

  @Override
  public int updateAnalysisTaskState(AnalysisTask task) { return this.analysisTaskMapper.updateAnalysisTaskState(task); }

  @Override
  public int batchUpdateState(List<String> list) { return this.analysisTaskMapper.batchUpdateState(list); }

  /**
   * 新增疑似新增有关数据
   * @param compareList
   * @param finishList
   * @param collectList
   * @param frequencyRecordList
   */
  @Override
  public void analysisAddJob(List<AddPerson> compareList, List<AnalysisTask> finishList, List<AddPersonCollect> collectList, List<FrequencyRecord> frequencyRecordList) {
    if (!compareList.isEmpty()) {
      List<AddPersonDetail> detairList = new ArrayList<AddPersonDetail>();
      //存放已存在疑似新增数据
      List<AddPerson> addPersonTempList = new ArrayList<>();
      //存放已存在疑似新增汇总数据
      List<AddPersonCollect> addPersonCollectTempList = new ArrayList<>();
      //查询已存在数据
      for (int i = 0; i < compareList.size(); i++){
        AddPerson addPerson = this.addPersonMapper.findAddPerson(compareList.get(i));
        if(addPerson != null){
          if (null != collectList && !collectList.isEmpty()){
            for (int j = 0; j < collectList.size(); j++){
              if (collectList.get(j).getAddPersonId().equals(compareList.get(i).getId())){
                addPersonCollectTempList.add(collectList.get(j));
              }
            }
          }
          addPersonTempList.add(compareList.get(i));
        }
      }
      //移除重复数据
      compareList.removeAll(addPersonTempList);
      if (collectList != null){
        collectList.removeAll(addPersonCollectTempList);
      }
      for (AddPerson ap : compareList) {
        if (null != ap.getDetail()) {
          detairList.addAll(ap.getDetail());
        }
      }
      int count = 0;
      if (compareList.size() > 0){
        //插入疑似新增数据
        count = this.addPersonMapper.batchCompareAddPerson(compareList);
      }
      if (count > 0 && detairList.size() > 0) {
        //插入疑似新增详细数据
        this.addPersonMapper.batchInsertAddPersonDetail(detairList);
      }
    }
    
    if (null != collectList && !collectList.isEmpty()) {
      //插入疑似新增汇总数据
      this.collectMapper.batchCompareCollect(collectList);
    }
    
    if (null != frequencyRecordList && !frequencyRecordList.isEmpty()) {
      //插入高频陌生人数据
      this.frequencyRecordMapper.batchCompareFrequencyRecord(frequencyRecordList);
    }

//    if (null != finishList)
//    {
//      for (AnalysisTask task : finishList) {
          //更新任务表
//        updateAnalysisTaskState(task);
//      }
//    }
  }

  /**
   * 新增夜间高频有关数据
   * @param compareList
   * @param finishList
   * @param collectList
   */
  @Override
  public void analysisFrequencyNightJob(List<FrequencyNightPerson> compareList, List<AnalysisTask> finishList, List<FrequencyNightPersonCollect> collectList) {
    if (!compareList.isEmpty()) {
      List<FrequencyNightPersonDetail> detairList = new ArrayList<FrequencyNightPersonDetail>();
      //存放已存在夜间高频数据
      List<FrequencyNightPerson> frequencyNightPersonTempList = new ArrayList<>();
      //存放已存在夜间高频汇总数据
      List<FrequencyNightPersonCollect> frequencyNightPersonCollectTempList = new ArrayList<>();
      //查询已存在数据
      for (int i = 0; i < compareList.size(); i++) {
        FrequencyNightPerson frequencyNightPerson = this.frequencyNightPersonMapper.findfrequencyNightPerson(compareList.get(i));
        if (frequencyNightPerson != null) {
          if (null != collectList && !collectList.isEmpty()) {
            for (int j = 0; j < collectList.size(); j++) {
              if (collectList.get(j).getFrequencyNightPersonId().equals(compareList.get(i).getId())) {
                frequencyNightPersonCollectTempList.add(collectList.get(j));
              }
            }
          }
          frequencyNightPersonTempList.add(compareList.get(i));
        }
      }
      //移除重复数据
      compareList.removeAll(frequencyNightPersonTempList);
      if (collectList != null) {
        collectList.removeAll(frequencyNightPersonCollectTempList);
      }
      for (FrequencyNightPerson ap : compareList) {
        if (null != ap.getDetail()) {
          detairList.addAll(ap.getDetail());
        }
      }
      int count = 0;
      if (compareList.size() > 0) {
        //新增夜间高频数据
        count = this.frequencyNightPersonMapper.batchCompareFrequencyNightPerson(compareList);
      }
      if (count > 0 && detairList.size() > 0) {
        //新增夜间高频详情数据
        this.frequencyNightPersonMapper.batchInsertFrequencyNightPersonDetail(detairList);
      }
    }

    if (null != collectList && !collectList.isEmpty()) {
      //新增夜间高频汇总数据
      this.frequencyNightCollectMapper.batchCompareCollect(collectList);
    }

  }

  /**
   * 新增敏感通行有关数据
   * @param compareList
   * @param finishList
   * @param collectList
   */
  @Override
  public void analysisSensitiveJob(List<SensitivePerson> compareList, List<AnalysisTask> finishList, List<SensitivePersonCollect> collectList) {
    if (!compareList.isEmpty()) {
      List<SensitivePersonDetail> detairList = new ArrayList<SensitivePersonDetail>();
      //存放已存在敏感通行数据
      List<SensitivePerson> sensitivePersonTempList = new ArrayList<>();
      //存放已存在夜间敏感通行数据
      List<SensitivePersonCollect> sensitivePersonCollectTempList = new ArrayList<>();
      //查询已存在数据
      for (int i = 0; i < compareList.size(); i++) {
        SensitivePerson sensitivePerson = this.sensitivePersonMapper.findSensitivePerson(compareList.get(i));
        if (sensitivePerson != null) {
          if (null != collectList && !collectList.isEmpty()) {
            for (int j = 0; j < collectList.size(); j++) {
              if (collectList.get(j).getSensitivePersonId().equals(compareList.get(i).getId())) {
                sensitivePersonCollectTempList.add(collectList.get(j));
              }
            }
          }
          sensitivePersonTempList.add(compareList.get(i));
        }
      }
      //移除重复数据
      compareList.removeAll(sensitivePersonTempList);
      if (collectList != null) {
        collectList.removeAll(sensitivePersonCollectTempList);
      }
      for (SensitivePerson ap : compareList) {
        if (null != ap.getDetail()) {
          detairList.addAll(ap.getDetail());
        }
      }
      int count = 0;
      if (compareList.size() > 0) {
        //新增夜间高频数据
        count = this.sensitivePersonMapper.batchCompareSensitivePerson(compareList);
      }
      if (count > 0 && detairList.size() > 0) {
        //新增夜间高频详情数据
        this.sensitivePersonMapper.batchInsertSensitivePersonDetail(detairList);
      }
    }

    if (null != collectList && !collectList.isEmpty()) {
      //新增夜间高频汇总数据
      this.sensitivePersonCollectMapper.batchCompareCollect(collectList);
    }
  }

  @Override
  public AddPersonCollect getCollect(String addPersonId) { return this.collectMapper.getCollect(addPersonId); }

  @Override
  public List<AddPersonDetail> queryAccordDetail(String addPersonId, int dayBegin) { return this.addPersonMapper.queryAccordDetail(addPersonId, dayBegin); }
  
  @Override
  public FrequencyRecord getFrequencyRecordById(String addPersonId) { return this.frequencyRecordMapper.getFrequencyRecordById(addPersonId); }
}
