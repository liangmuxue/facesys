package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.baseinfo.common.dto.CompareResultDTO;
import com.ss.facesys.data.baseinfo.common.dto.PersonCaptureDTO;
import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.facesys.data.collect.common.web.SnapRecordVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SnapRecordMapper extends SsMapper<SnapRecord> {

    List<PersonCaptureDTO> getById(@Param("idList") List<CompareResultDTO> idList,
                                   @Param("ageB")Integer ageB,
                                   @Param("ageE")Integer ageE,
                                   @Param("gender")Integer gender,
                                   @Param("glasses")Integer glasses,
                                   @Param("sunglasses")Integer sunglasses,
                                   @Param("mask")Integer mask,
                                   @Param("minority")Integer minority);

    List<SnapRecord> page(SnapRecordVO snapRecordVO);
}
