package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.model.AlarmRecord;
import com.ss.facesys.data.collect.common.web.AlarmRecordVO;
import com.ss.facesys.data.collect.common.web.AlarmVO;
import com.ss.facesys.data.process.common.web.PeopleProcessVO;

import java.util.Date;
import java.util.List;

/**
 * IAlarmService
 *
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
public interface IAlarmService {

    /**
     * 查询预警分页列表
     *
     * @param alarm
     * @return
     */
    List<AlarmRecordVO> findListByPage(AlarmVO alarm);

    List<AlarmRecordVO> findList(AlarmVO alarm);

    List<AlarmRecord> queryAnalysisData(Integer paramInteger1, Integer paramInteger2, int paramInt, Date paramDate1, Date paramDate2);

    AlarmRecord getByCaptureId(String paramString);

    /**
     * 预警人员处置
     *
     * @param paramString
     * @param paramInteger
     * @return
     */
    boolean alarmProcess(String paramString, Integer paramInteger);

    /**
     * 查询预警未处理条数
     *
     * @param alarm
     * @return
     */
    List<Integer> getUntreatedCount(AlarmVO alarm);

    /**
     * 人口预警一键处置
     *
     * @param para
     * @return
     */
    String warningProcessBatch(PeopleProcessVO para);

    /**
     * 人口预警一键处置有效
     *
     * @param processVO
     * @return
     */
    String handleUntreated(PeopleProcessVO processVO);

}
