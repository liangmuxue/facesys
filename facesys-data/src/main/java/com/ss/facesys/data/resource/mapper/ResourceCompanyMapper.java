package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.Company;
import com.ss.mapper.SsMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ResourceCompanyMapper extends SsMapper<Company> {
  List<Company> pages(Company paramCompany);
  
  Company check(Company paramCompany);

  Company selectById(@Param("id") Integer id);
  
  int updateBatch(List<Company> paramList);
  
  List<Company> selectByCompanyCode(@Param("companyCode") String paramString);
}
