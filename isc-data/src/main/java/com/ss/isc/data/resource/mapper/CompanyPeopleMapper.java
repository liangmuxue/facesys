package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.model.Company;
import com.ss.isc.data.resource.common.model.CompanyPeople;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyPeopleMapper extends CWMapper<CompanyPeople> {
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
