package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.model.AlarmRecord;
import com.ss.isc.data.collect.common.model.AlarmRecordDetail;
import com.ss.isc.data.collect.common.web.AlarmRecordVO;
import com.ss.isc.data.collect.common.web.AlarmVO;
import com.ss.mapper.CWMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * AlarmMapper
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
@Mapper
public interface AlarmMapper extends CWMapper<AlarmRecordDetail> {

    /**
     * 查询预警列表
     * @param alarm
     * @return
     */
    List<AlarmRecordVO> findList(AlarmVO alarm);

    long queryAnalysisDataCount(Map<String, Object> paramMap);

    List<AlarmRecord> queryAnalysisData(Map<String, Object> paramMap);

    AlarmRecord getByCaptureId(@Param("captureId") String paramString);

    /**
     * 新增预警处置
     * @param paramString
     * @param paramInteger
     * @return
     */
    int alarmProcess(@Param("id") String paramString, @Param("state") Integer paramInteger);

    /**
     * 查询预警未处理条数
     * @param alarm
     * @return
     */
    List<Integer> getUntreatedCount(AlarmVO alarm);

    /**
     * 更新未处置的人口预警信息状态为有效
     */
    void updateUntreatedAsValid();

}
