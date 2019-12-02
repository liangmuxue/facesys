package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.dto.PeopleHouseDTO;
import com.ss.facesys.data.resource.common.model.PeopleHouse;
import com.ss.facesys.data.resource.common.model.PeopleVehicle;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
/**
* 人房关联、人车关联
* @author chao
* @create 2019/9/23
* @email lishuangchao@ss-cas.com
**/
@Mapper
public interface PeopleHouseMapper extends SsMapper<PeopleHouse> {
  List<PeopleHouseDTO> selectPeopleHouse(PeopleHouse paramPeopleHouse);
  
  int insertPeopleRelation(List<PeopleHouse> paramList);
  
  int deletePeopleRelation(PeopleHouse paramPeopleHouse);

  int updateVehiclePeopleRelation(PeopleVehicle peopleVehicle);
}
