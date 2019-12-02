package com.ss.facesys.data.archives.mapper;

import com.ss.facesys.data.archives.common.dto.PersonHousePeopleDTO;
import com.ss.facesys.data.archives.common.dto.VillageCodeDTO;
import com.ss.facesys.data.archives.common.model.People;
import com.ss.facesys.data.archives.common.model.WifiCollect;
import com.ss.facesys.data.archives.common.web.PeopleVO;
import com.ss.facesys.data.archives.common.web.PersonVO;
import com.ss.facesys.data.archives.common.web.WarningVO;
import com.ss.mapper.SsMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PersonMapper extends SsMapper<People> {

    List<PersonVO> getPeopleByHouseId(PersonHousePeopleDTO paramPersonHousePeopleDTO);

    List<People> getPeople();

    List<People> getPeopleDiscoveryLeave(String paramString);

    List<WarningVO> strangeList(VillageCodeDTO paramVillageCodeDTO);

    List<WarningVO> addList(VillageCodeDTO paramVillageCodeDTO);

    List<WarningVO> leaveList(VillageCodeDTO paramVillageCodeDTO);

    int oldCound(VillageCodeDTO paramVillageCodeDTO);

    List<WarningVO> oldList(VillageCodeDTO paramVillageCodeDTO);

    List<People> detail(People paramPeople);

    List<PeopleVO> voDetail(People paramPeople);

    List<WifiCollect> mac(VillageCodeDTO paramVillageCodeDTO);

}
