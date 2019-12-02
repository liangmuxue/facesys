package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.model.Building;
import com.ss.isc.data.collect.common.model.House;
import com.ss.isc.data.collect.common.model.Vehicle;
import com.ss.mapper.CWMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseMapper extends CWMapper<House> {
  List<House> getBuildHouses(Building paramBuilding);
  
  List<House> getHouseId(House paramHouse);
  
  List<House> getHouse(House house);
  
  List<Vehicle> findList(Map<String, String> paramMap);
  
  Integer getCountPeople(House paramHouse);
  
  Integer getCountCar(House paramHouse);
}
