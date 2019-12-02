package com.ss.isc.data.archives.mapper;

import com.ss.isc.data.archives.common.dto.PersonInfraDTO;
import com.ss.isc.data.archives.common.model.Electric;
import com.ss.isc.data.archives.common.model.Gas;
import com.ss.isc.data.archives.common.model.Water;
import com.ss.isc.data.archives.common.web.InfrastructureVO;
import com.ss.mapper.CWMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface InfrastructureMapper extends CWMapper<InfrastructureVO> {
  Water getWater(PersonInfraDTO paramPersonInfraDTO);
  
  Electric getElectric(PersonInfraDTO paramPersonInfraDTO);
  
  Gas getGas(PersonInfraDTO paramPersonInfraDTO);
}
