package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.model.Company;
import com.ss.isc.data.resource.common.model.Village;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyDao extends CWMapper<Company> {
  List<Company> findAllCompanys(Village paramVillage);
}
