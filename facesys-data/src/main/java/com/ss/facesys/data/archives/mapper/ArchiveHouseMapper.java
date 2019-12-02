package com.ss.facesys.data.archives.mapper;

import com.ss.facesys.data.archives.common.dto.PersonHouseDTO;
import com.ss.facesys.data.archives.common.dto.PersonInfraDTO;
import com.ss.facesys.data.archives.common.dto.VehicleDTO;
import com.ss.facesys.data.archives.common.model.ArchiveHouse;
import com.ss.facesys.data.archives.common.model.People;
import com.ss.facesys.data.archives.common.web.HouseVO;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ArchiveHouseMapper extends SsMapper<ArchiveHouse> {
  List<HouseVO> getHouseByPeopleId(PersonHouseDTO paramPersonHouseDTO);
  
  List<People> getPeopleInformation(PersonInfraDTO paramPersonInfraDTO);
  
  ArchiveHouse getHouseInfo(@Param("id") Integer paramInteger);
  
  List<VehicleDTO> getVehicleDTO(@Param("plateNo") String paramString1, @Param("villageCode") String paramString2);
  
  String getVillageName(@Param("villageCode") String paramString);
  
  ArchiveHouse houseDetail(PersonInfraDTO paramPersonInfraDTO);

  String getLabel(@Param("label") String label);
}
