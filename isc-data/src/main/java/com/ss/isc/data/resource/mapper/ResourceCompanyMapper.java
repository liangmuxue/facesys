package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.model.Company;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ResourceCompanyMapper extends CWMapper<Company> {
  List<Company> pages(Company paramCompany);
  
  Company check(Company paramCompany);

  Company selectById(@Param("id") Integer id);
  
  int updateBatch(List<Company> paramList);
  
  List<Company> selectByCompanyCode(@Param("companyCode") String paramString);
}
