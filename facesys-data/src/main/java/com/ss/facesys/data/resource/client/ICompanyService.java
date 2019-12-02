package com.ss.facesys.data.resource.client;

import com.ss.facesys.data.resource.common.model.Company;
import com.ss.facesys.data.resource.common.model.CompanyPeople;
import com.ss.facesys.data.resource.common.web.CompanyPeopleVO;
import com.ss.facesys.data.resource.common.web.CompanyQueryVO;
import com.ss.facesys.data.resource.common.web.CompanyVO;
import java.util.List;
import java.util.Map;
/**
 * 实有单位增删改查
 * @author chao
 * @create 2019/8/16
 * @email lishuangchao@ss-cas.com
 **/
public interface ICompanyService {
  List<Company> pages(CompanyQueryVO paramCompanyQueryVO, int paramInt1, int paramInt2);

  /**
   * 删除实有单位
   * @param paramCompanyVO
   * @return
   */
  String delete(CompanyVO paramCompanyVO);

  /**
   * 新增实有单位
   * @param paramCompany
   * @return
   */
  String save(Company paramCompany);

  /**
   * 实有单位详情信息
   * @param paramInteger
   * @return
   */
  Company get(Integer paramInteger);

  /**
   * 实有单位修改
   * @param paramCompany
   * @return
   */
  String edit(Company paramCompany);

  /**
   * 单位从业人员列表
   * @param paramCompanyPeopleVO
   * @param paramInt1
   * @param paramInt2
   * @return
   */
  List<CompanyPeople> companyPeoplePages(CompanyPeopleVO paramCompanyPeopleVO, int paramInt1, int paramInt2);

  /**
   * 删除从业人员
   * @param paramCompanyPeopleVO
   * @return
   */
  String companyPeopleDelete(CompanyPeopleVO paramCompanyPeopleVO);
  
  Map<String, String> importCompany(List<Company> paramList, Map<String, String> paramMap);
  
  Map<String, String> importCompanyPeople(List<CompanyPeople> paramList, Company paramCompany, Map<String, String> paramMap);
}
