package com.ss.isc.data.collect.client;

import com.ss.isc.data.collect.common.dto.*;
import com.ss.isc.data.collect.common.model.*;
import com.ss.isc.data.collect.common.web.*;

import java.util.List;

/**
 * 智能分析
 *
 * @author chao
 * @create 2019/8/22
 * @email lishuangchao@ss-cas.com
 **/
public interface IPeopleService {

    AddPerson getAddPersonById(String paramString);

    LeavePerson getLeavePersonById(String paramString);

    List<AddPerson> queryDayAddList();

    List<FrequencyNightPerson> queryFrequencyNightList();

    List<SensitivePerson> querySensitiveList();

    List<AddPersonDetail> queryDayAddDetailList(int paramInt1, int paramInt2);

    int batchCompareAddPerson(List<AddPerson> paramList1, List<AddPersonDetail> paramList2);

    List<LeavePerson> queryLeaveListByState(String paramString, Integer paramInteger);

    List<SpecialPerson> querySpecialListByState(Integer paramInteger, int paramInt);

    int batchCompareLeavePerson(List<LeavePerson> paramList1, List<SpecialPerson> paramList2, List<String> paramList3);

    List<AddPersonDetailVO> findAddPersonDetails(String paramString);

    List<AddPersonDetailVO> frequencyRecordTopDetails(String paramString);

    int batchInsertAddPersonWith(List<AddPersonWith> paramList1, List<String> paramList2);

    /**
     * 一社一档-居民详情
     * @param paramPeopleQuery
     * @return
     */
    List<People> page(PeopleQuery paramPeopleQuery);

    /**
     * 疑似新增分页查询
     *
     * @param paramAddPersonQuery
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    List<AddPersonDTO> addPersonPage(AddPersonQuery paramAddPersonQuery, int paramInt1, int paramInt2);

    /**
     * 疑似离开分页查询
     *
     * @param paramLeavePersonQuery
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    List<LeavePerson> leavePersonPage(LeavePersonQuery paramLeavePersonQuery, int paramInt1, int paramInt2);

    /**
     * 疑似新增处置
     * @param paramAddPerson
     * @return
     * @throws Exception
     */
    String addPeopleProcess(AddPerson paramAddPerson);

    /**
     * 疑似离开处置
     * @param paramLeavePerson
     * @return
     */
    boolean leavePeopleProcess(LeavePerson paramLeavePerson);

    People findPeopleInfo(String paramString);

    List<FrequencyRecordDTO> frequencyPersonPage(FrequencyRecordQuery paramFrequencyRecordQuery, int paramInt1, int paramInt2);

    /**
     * 高频陌生人处置
     * @param paramFrequencyRecord
     * @return
     */
    boolean frequencyPersonProcess(FrequencyRecord paramFrequencyRecord);

    List<SpecialPersonDTO> specialPersonPage(SpecialPersonQuery paramSpecialPersonQuery, int paramInt1, int paramInt2);

    /**
     * 高龄老人处置
     * @param paramSpecialPerson
     * @return
     * @throws Exception
     */
    boolean specialPersonProcess(SpecialPerson paramSpecialPerson) throws Exception;

    List<FrequencyRecordDTO> frequencyRecordTop(String paramString);

    boolean batchInsertFacePeople(List<FacedbfaceVO> paramList);

    String facedbfaceInsert(FacedbfaceVO paramFacedbfaceVO);

    List<FrequencyRecord> analysisFrequeryRecord(AddPersonDetailQuery paramAddPersonDetailQuery);

    FrequencyRecord findFrequencyRecordById(String paramString);

    int batchCompareFrequencyRecord(List<FrequencyRecord> paramList);

    /**
     * 疑似新增抓拍照编号查询
     * @param paramString
     * @return
     */
    List<String> queryAddPersonDetCaptureIds(String paramString);

    /**
     * 夜间高频抓拍照编号查询
     * @param paramString
     * @return
     */
    List<String> queryFrequencyNightPersonDetCaptureIds(String paramString);

    /**
     * 敏感通行抓拍照编号查询
     * @param paramString
     * @return
     */
    List<String> querySensitivePersonDetCaptureIds(String paramString);

    /**
     * 夜间高频人员分页查询
     * @param paramFrequencyNightQuery
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    List<FrequencyNightDTO> frequencyNightPage(FrequencyNightQuery paramFrequencyNightQuery, int paramInt1, int paramInt2);

    /**
     * 长时间逗留人员分页查询
     * @param paramLongtimeStayQuery
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    List<LongtimeStayDTO> longtimeStayPage(LongtimeStayQuery paramLongtimeStayQuery, int paramInt1, int paramInt2);

    /**
     * 敏感通行人员分页查询
     * @param paramSensitiveTrafficQuery
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    List<SensitiveTrafficDTO> sensitiveTrafficPage(SensitiveTrafficQuery paramSensitiveTrafficQuery, int paramInt1, int paramInt2);

    /**
     * 夜间高频处置
     * @param paramFrequencyNightPerson
     * @return
     */
    boolean frequencyNightPersonProcess(FrequencyNightPerson paramFrequencyNightPerson);

    /**
     * 敏感通行处置
     * @param paramSensitivePerson
     * @return
     */
    boolean sensitivePersonProcess(SensitivePerson paramSensitivePerson);

    /**
     * 长时间逗留处置
     * @param paramLongtimeStayPerson
     * @return
     */
    boolean longtimeStayPersonProcess(LongtimeStayPerson paramLongtimeStayPerson);

    /**
     * 疑似离开处置结果查询
     * @param id
     * @return
     */
    LeavePerson findLeavePersonResult(String id);

    /**
     * 夜间高频处置结果查询
     * @param id
     * @return
     */
    FrequencyNightPerson findFrequencyNightPersonResult(String id);

    /**
     * 敏感通行处置结果查询
     * @param id
     * @return
     */
    SensitivePerson findSensitivePersonResult(String id);

    /**
     * 长时间逗留处置结果查询
     * @param id
     * @return
     */
    LongtimeStayPerson findLongtimeStayPersonResult(String id);

}
