package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.Company;
import com.ss.facesys.data.resource.common.model.CompanyPeople;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyPeopleMapper extends SsMapper<CompanyPeople> {
  List<CompanyPeople> pages(CompanyPeople paramCompanyPeople);
  
  CompanyPeople check(CompanyPeople paramCompanyPeople);
  
  int updateBatch(List<CompanyPeople> paramList);

  /**
   * 删除从业人员
   * @param paramCompany
   * @return
   */
  int deleteCompanyPeople(Company paramCompany);
}
