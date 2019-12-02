package com.ss.facesys.data.archives.mapper;

import com.ss.facesys.data.archives.common.dto.PersonInfraDTO;
import com.ss.facesys.data.archives.common.model.Electric;
import com.ss.facesys.data.archives.common.model.Gas;
import com.ss.facesys.data.archives.common.model.Water;
import com.ss.facesys.data.archives.common.web.InfrastructureVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface InfrastructureMapper extends SsMapper<InfrastructureVO> {
  Water getWater(PersonInfraDTO paramPersonInfraDTO);
  
  Electric getElectric(PersonInfraDTO paramPersonInfraDTO);
  
  Gas getGas(PersonInfraDTO paramPersonInfraDTO);
}
