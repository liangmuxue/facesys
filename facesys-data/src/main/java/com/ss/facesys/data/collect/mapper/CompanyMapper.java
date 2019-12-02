package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.baseinfo.common.dto.StatistiscsDTO;
import com.ss.facesys.data.collect.common.model.Company;
import com.ss.facesys.data.collect.common.model.Employee;
import com.ss.mapper.SsMapper;
import com.github.pagehelper.Page;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyMapper extends SsMapper<Company> {
  List<Company> getCompany(Company company);
  
  List<Employee> getEmployee(Map<String, String> paramMap);
  
  List<Employee> searchEmployee(Map<String, String> paramMap);
  
  List<StatistiscsDTO> statisticsCompany(@Param("villageCode") String paramString);
  
  Page<Company> pages(Company paramCompany);
}
