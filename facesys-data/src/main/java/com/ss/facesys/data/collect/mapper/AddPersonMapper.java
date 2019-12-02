package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.dto.AddPersonDTO;
import com.ss.facesys.data.collect.common.dto.AddPersonDetailVO;
import com.ss.facesys.data.collect.common.model.AddPerson;
import com.ss.facesys.data.collect.common.model.AddPersonCollect;
import com.ss.facesys.data.collect.common.model.AddPersonDetail;
import com.ss.facesys.data.collect.common.web.AddPersonQuery;
import com.ss.mapper.SsMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddPersonMapper extends SsMapper<AddPerson> {
  int getAddPeopleCount(@Param("villageCode") String paramString, @Param("days") Integer paramInteger);
  
  List<AddPerson> queryAddList(Map<String, String> paramMap);
  
  AddPerson getAddPersonById(String paramString);
  
  int updateAddPersonById(Map<String, Object> paramMap);
  
  List<AddPerson> queryDayAddList();
  
  List<AddPersonDetail> queryDayAddDetailList(@Param("state") int paramInt1, @Param("days") int paramInt2);
  
  int batchCompareAddPerson(List<AddPerson> paramList);
  
  int batchInsertAddPersonDetail(List<AddPersonDetail> paramList);
  
  List<AddPerson> findTopAddPerson(@Param("villageCode") String paramString);
  
  List<AddPersonDetail> queryTopAddPersonDetail(@Param("addPersonId") String paramString);
  
  List<AddPersonDetailVO> findAddPersonDetails(AddPersonCollect paramAddPersonCollect);
  
  List<AddPersonDTO> page(AddPersonQuery paramAddPersonQuery);
  
  Integer update(AddPerson paramAddPerson);
  
  List<AddPersonDetail> queryAccordDetail(@Param("addPersonId") String paramString, @Param("dayBegin") int paramInt);
  
  List<String> queryAddPersonDetCaptureIds(@Param("addPersonId") String paramString);

  AddPerson findAddPerson(AddPerson param);
}
