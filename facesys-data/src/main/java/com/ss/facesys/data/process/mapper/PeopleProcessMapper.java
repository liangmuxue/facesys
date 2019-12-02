package com.ss.facesys.data.process.mapper;

import com.ss.facesys.data.process.common.dto.WarningSumDTO;
import com.ss.facesys.data.process.common.model.PeopleProcess;
import com.ss.facesys.data.process.common.model.Vehicle;
import com.ss.facesys.data.process.common.web.PeopleProcessVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * PeopleProcessMapper
 *
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
@Mapper
@Repository
public interface PeopleProcessMapper extends SsMapper<PeopleProcess> {

    /**
     * 查询预警处置记录（此处实际包含了人员预警和车辆预警）
     *
     * @param paramPeopleProcess
     * @return
     */
    PeopleProcess selectPeopleProcess(PeopleProcess paramPeopleProcess);

    List<WarningSumDTO> frequencyCount();

    List<WarningSumDTO> oldmanCount(@Param("oldMan") Integer paramInteger);

    List<WarningSumDTO> addCount(@Param("addPersonJudgedDays") Integer paramInteger);

    List<WarningSumDTO> leaveCount(@Param("leaveDays") Integer paramInteger);

    int untreatedCount(Map<String, String> paramMap);

    int dealedCount(Map<String, String> paramMap);

    /**
     * 增加车辆处置记录
     *
     * @param paramVehicle
     * @return
     */
    int addVehicleProcess(Vehicle paramVehicle);

    List<WarningSumDTO> untreatedCount1(@Param("s") String paramString);

    List<WarningSumDTO> dealedCount1(@Param("s") String paramString);

    List<WarningSumDTO> addUntreatedCount(@Param("addPersonJudgedDays") Integer paramInteger);

    List<WarningSumDTO> addDealedCount(@Param("addPersonJudgedDays") Integer paramInteger);

    List<WarningSumDTO> leaveUntreatedCount(@Param("leaveDays") Integer paramInteger);

    List<WarningSumDTO> leaveDealedCount(@Param("leaveDays") Integer paramInteger);

    List<WarningSumDTO> frequencyUntreatedCount(@Param("amountDays") Integer paramInteger);

    List<WarningSumDTO> frequencyDealedCount(@Param("amountDays") Integer paramInteger);

    List<WarningSumDTO> oldmanuntreatedCount(@Param("oldMan") Integer paramInteger);

    List<WarningSumDTO> oldmandealedCount(@Param("oldMan") Integer paramInteger);

    List<WarningSumDTO> alarmUntreatedCount(@Param("libraryIdsString") String paramString);

    List<WarningSumDTO> alarmdealedCount(@Param("libraryIdsString") String paramString);

    /**
     * 新增未处置的人口预警信息为有效
     *
     * @param processVO
     */
    void insertUntreatedPeople(PeopleProcessVO processVO);

    /**
     * 新增未处置的车辆预警信息为有效
     *
     * @param processVO
     */
    void insertUntreatedVehicle(PeopleProcessVO processVO);

}
