package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.model.LeavePerson;
import com.ss.isc.data.collect.common.model.LeavePersonDetail;
import com.ss.isc.data.collect.common.model.People;
import com.ss.isc.data.collect.common.web.LeavePersonQuery;
import com.ss.mapper.CWMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LeavePersonMapper extends CWMapper<People> {
  int getLeavePeopleCount(@Param("villageCode") String paramString, @Param("leaveDays") Integer paramInteger);
  
  List<LeavePerson> queryLeaveList(Map<String, String> paramMap);
  
  LeavePerson getLeavePersonById(String paramString);
  
  int updateLeavePersonById(Map<String, Object> paramMap);
  
  List<LeavePerson> queryLeaveListByState(@Param("villageCode") String paramString, @Param("state") Integer paramInteger);
  
  int batchCompareLeavePerson(List<LeavePerson> paramList);
  
  int batchInsertLeavePersonDetail(List<LeavePersonDetail> paramList);
  
  List<LeavePerson> page(LeavePersonQuery paramLeavePersonQuery);
  
  Integer update(LeavePerson paramLeavePerson);
  
  int updateLeaveState(@Param("pId") String paramString, @Param("state") int paramInt);
  
  int batchDeleteLeaveDetail(List<String> paramList);

  LeavePerson findLeavePersonResult(@Param("id") String id);

}
