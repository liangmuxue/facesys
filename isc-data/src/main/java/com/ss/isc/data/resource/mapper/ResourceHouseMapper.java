package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.baseinfo.common.dto.StatistiscsDTO;
import com.ss.isc.data.resource.common.dto.HouseDTO;
import com.ss.isc.data.resource.common.model.House;
import com.ss.isc.data.resource.common.model.PeopleHouse;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
/**
* 实有房屋
* @author chao
* @create 2019/9/24
* @email lishuangchao@ss-cas.com
**/
@Component
@Mapper
public interface ResourceHouseMapper extends CWMapper<House> {

  House getHouseNo(@Param("id") int id);

  Integer houseTotal(@Param("villageCode") String paramString);
  
  List<StatistiscsDTO> statisticsHouse(@Param("villageCode") String paramString);
  
  List<HouseDTO> pages(House paramHouse);
  
  HouseDTO selectById(Integer paramInteger);
  
  int deleteById(Integer paramInteger);
  
  int update(House paramHouse);
  
  House check(House paramHouse);
  
  int updateBatch(List<House> paramList);
  
  int insertBatchPeopleRelation(List<PeopleHouse> paramList);
  
  int updateBatchPeopleRelation(List<PeopleHouse> paramList);
  
  PeopleHouse checkPeopleHouse(PeopleHouse paramPeopleHouse);
}
