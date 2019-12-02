package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.dto.PeopleHouseDTO;
import com.ss.isc.data.resource.common.model.PeopleHouse;
import com.ss.isc.data.resource.common.model.PeopleVehicle;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
/**
* 人房关联、人车关联
* @author chao
* @create 2019/9/23
* @email lishuangchao@ss-cas.com
**/
@Mapper
public interface PeopleHouseMapper extends CWMapper<PeopleHouse> {
  List<PeopleHouseDTO> selectPeopleHouse(PeopleHouse paramPeopleHouse);
  
  int insertPeopleRelation(List<PeopleHouse> paramList);
  
  int deletePeopleRelation(PeopleHouse paramPeopleHouse);

  int updateVehiclePeopleRelation(PeopleVehicle peopleVehicle);
}
