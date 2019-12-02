package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.dto.CountDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResourceCountMapper {
  int peopleCount(String paramString);
  
  int companyCount(@Param("villageCode") String paramString);
  
  int houseCount(@Param("villageCode") String paramString);
  
  List<CountDTO> everyPeopleCount();
  
  CountDTO everyCompanyCount(@Param("villageCode") String paramString);
  
  CountDTO everyHouseCount(@Param("villageCode") String paramString);
}
