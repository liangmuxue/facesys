package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.Company;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.mapper.SsMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyDao extends SsMapper<Company> {
  List<Company> findAllCompanys(Village paramVillage);
}
