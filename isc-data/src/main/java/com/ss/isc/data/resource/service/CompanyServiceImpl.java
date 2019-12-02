package com.ss.isc.data.resource.service;

import com.ss.isc.data.baseinfo.service.BaseServiceImpl;
import com.ss.isc.data.resource.client.ICompanyService;
import com.ss.isc.data.resource.common.model.Company;
import com.ss.isc.data.resource.common.model.CompanyPeople;
import com.ss.isc.data.resource.common.web.CompanyPeopleVO;
import com.ss.isc.data.resource.common.web.CompanyQueryVO;
import com.ss.isc.data.resource.common.web.CompanyVO;
import com.ss.isc.data.resource.mapper.CompanyPeopleMapper;
import com.ss.isc.data.resource.mapper.ResourceCompanyMapper;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.constant.NumberConstant;
import com.ss.isc.util.em.CredentialTypeEnum;
import com.ss.isc.util.em.Enums;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.github.pagehelper.PageHelper;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class CompanyServiceImpl extends BaseServiceImpl implements ICompanyService {

  public static final Log LOG = LogFactory.getLog(CompanyServiceImpl.class);

  @Autowired
  private ResourceCompanyMapper resourceCompanyMapper;

  @Autowired
  private CompanyPeopleMapper companyPeopleMapper;

  @Override
  public List<Company> pages(CompanyQueryVO para, int currPage, int pageSize) {
    PageHelper.startPage(currPage, pageSize);
    Company company = new Company();
    BeanUtils.copyProperties(para, company);

    company.getSqlMap().put("dsf", para.getVillageCodes());
    List<Company> list = this.resourceCompanyMapper.pages(company);
    int rowNum = pageSize * (currPage - 1);
    for (Company entity : list) {
      rowNum++;
      entity.setRowNum(Integer.valueOf(rowNum));
      entity.setCompanyTypeName(Enums.CompanyType.getName(entity.getCompanyType()));
      entity.setCompanySizeName(Enums.CompanySize.getName(entity.getCompanySize()));
      entity.setEconomicTypeName(Enums.EconomicType.getName(entity.getEconomicType()));
      entity.setImportantFlagName(Enums.ImportantFlag.getName(entity.getImportantFlag()));
      entity.setCredentialTypeName(CredentialTypeEnum.getName(entity.getCredentialType()));
      entity.setGisTypeName(Enums.gisType.getName(String.valueOf(entity.getGisType())));

      if (StringUtils.isNotBlank(entity.getCompanyPic()) &&
          !entity.getCompanyPic().contains("http")) {
        entity.setCompanyPic(FilePropertiesUtil.getHttpUrl() + entity.getCompanyPic());
      }
    }
    return list;
  }

  /**
   * 删除实有单位
   * @param para
   * @return
   */
  @Override
  public String delete(CompanyVO para) {
    String message = "";
    //获取要删除单位的信息
    Company company = (Company) this.resourceCompanyMapper.selectByPrimaryKey(para.getId());
    //单位状态设置为删除
    company.setIsDelete(CommonConstant.COMMON_NEGATIVE_1);
    //修改单位信息
    int count = this.resourceCompanyMapper.updateByPrimaryKey(company);
    //删除单位下关联人员
    company.setIds(new ArrayList<>());
    company.getIds().add(String.valueOf(company.getId()));
    int countPeople = this.companyPeopleMapper.deleteCompanyPeople(company);
    return (count > CommonConstant.COMMON_0 && countPeople > CommonConstant.COMMON_0) ? "success" : "failed";
  }

  /**
   * 实有单位新增
   * @param company
   * @return
   */
  @Override
  public String save(Company company) {
    String message = "";
    //检查新增单位是否已经存在
    Company entity = this.resourceCompanyMapper.check(company);
    if (entity == null) {
      company.setCreateTime(new Date());
      company.setUpdateTime(new Date());
      //状态设置为启用
      company.setIsDelete(CommonConstant.COMMON_1);
      //存入数据库
      message = (this.resourceCompanyMapper.insertSelective(company) > CommonConstant.COMMON_0) ? "success" : "failed";
    } else {
      message = "isHave";
    }
    return message;
  }

  /**
   * 修改实有单位
   * @param company
   * @return
   */
  @Override
  public String edit(Company company) {
    String message = "";
    //查询是否存在相同统一社会信用代码
    Company entity = this.resourceCompanyMapper.check(company);
    if (entity == null) {
      company.setUpdateTime(new Date());
      //修改实有单位信息
      message = (this.resourceCompanyMapper.updateByPrimaryKeySelective(company) > CommonConstant.COMMON_0) ? "success" : "failed";
    } else {
      message = "isHave";
    }
    return message;
  }

  /**
   * 实有单位详情信息查询
   * @param id
   * @return
   */
  @Override
  public Company get(Integer id) {
    //实有单位详情信息查询
    Company company = (Company) this.resourceCompanyMapper.selectById(id);

      if (StringUtils.isNotBlank(company.getCompanyPic()) && !company.getCompanyPic().contains("http")) {
        //获取图片绝对路径
        company.setCompanyPic(FilePropertiesUtil.getHttpUrl() + company.getCompanyPic());
      }
    return company;
  }

  /**
   * 从业人员分页查询
   * @param para
   * @param currPage
   * @param pageSize
   * @return
   */
  @Override
  public List<CompanyPeople> companyPeoplePages(CompanyPeopleVO para, int currPage, int pageSize) {
    PageHelper.startPage(currPage, pageSize);
    CompanyPeople companyPeople = new CompanyPeople();
    BeanUtils.copyProperties(para, companyPeople);
    //返回查询单位从业人员
    return this.companyPeopleMapper.pages(companyPeople);
  }

  /**
   * 删除从业人员
   * @param para
   * @return
   */
  @Override
  public String companyPeopleDelete(CompanyPeopleVO para) {
    List<String> result = Arrays.asList(para.getIds().split(","));
    Company company = new Company();
    company.setIds(result);
    //删除从业人员
    int countPeople = this.companyPeopleMapper.deleteCompanyPeople(company);
    return countPeople > CommonConstant.COMMON_0 ? "success" : "failed";
  }

  @Override
  public Map<String, String> importCompany(List<Company> tempList, Map<String, String> imagePaths) {
    Map<String, String> map = new HashMap<String, String>(
        CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
    boolean ckeckFlag = true;
    StringBuilder failureMsg = new StringBuilder();
    int index = NumberConstant.TWO.intValue();
    List<Company> insertList = new ArrayList<Company>();
    List<Company> updateList = new ArrayList<Company>();
    Date date = new Date();
    for (Company company : tempList) {

      if (StringUtils.isBlank(company.getCompanyCode()) && StringUtils
          .isBlank(company.getVillageCode()) &&
          StringUtils.isBlank(company.getCompanyName()) &&
          StringUtils.isBlank(company.getCredentialNo())) {
        break;
      }
      index++;
      company.setCreateTime(date);
      company.setUpdateTime(date);
      if (StringUtils.isBlank(company.getCompanyCode())) {
        failureMsg.append(index + "行“统一社会信用代码”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (StringUtils.isBlank(company.getVillageCode())) {
        failureMsg.append(index + "行“小区编号”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (StringUtils.isBlank(company.getCompanyName())) {
        failureMsg.append(index + "行“单位名称”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (StringUtils.isBlank(company.getCompanyAdress())) {
        failureMsg.append(index + "行“单位地址”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (StringUtils.isBlank(company.getCompanyRegisterAddress())) {
        failureMsg.append(index + "行“注册地详址”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (StringUtils.isBlank(company.getCompanyTel())) {
        failureMsg.append(index + "行“联系电话(单位)”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (StringUtils.isBlank(company.getLeaderName())) {
        failureMsg.append(index + "行“法定代表人姓名”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (company.getCredentialType() == null) {
        failureMsg.append(index + "行“证件类型”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (StringUtils.isBlank(company.getCredentialNo())) {
        failureMsg.append(index + "行“证件号码”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (StringUtils.isBlank(company.getLeaderTel())) {
        failureMsg.append(index + "行“联系电话(法人)”列数据格式非法。");
        ckeckFlag = false;

        break;
      }
      company.setCompanyPic((String) imagePaths.get(company.getCompanyCode()));

      if (ckeckFlag) {
        Company entity = this.resourceCompanyMapper.check(company);
        if (entity == null) {
          insertList.add(company);
          continue;
        }
        company.setId(entity.getId());
        updateList.add(company);
      }
    }

    if (ckeckFlag) {
      if (insertList.size() > 0) {
        this.resourceCompanyMapper.insertList(insertList);
      }
      if (updateList.size() > 0) {
        this.resourceCompanyMapper.updateBatch(updateList);
      }
      map.put("result", "success");
      map.put("message",
          "成功导入" + (index - NumberConstant.TWO.intValue()) + "条数据，新增" + insertList.size() + "条，更新"
              + updateList
              .size() + "条");
    } else {

      map.put("result", "failed");
      map.put("message", "导入失败：失败原因" + failureMsg.toString());
    }
    return map;
  }

  @Override
  public Map<String, String> importCompanyPeople(List<CompanyPeople> tempList, Company company,
      Map<String, String> imagePaths) {
    Map<String, String> map = new HashMap<String, String>(
        CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
    boolean ckeckFlag = true;
    StringBuilder failureMsg = new StringBuilder();
    List<CompanyPeople> insertList = new ArrayList<CompanyPeople>();
    List<CompanyPeople> updateList = new ArrayList<CompanyPeople>();
    Date date = new Date();
    int index = NumberConstant.TWO.intValue();
    for (CompanyPeople companyPeople : tempList) {

      if (StringUtils.isBlank(companyPeople.getPeopleName())
          && companyPeople.getCredentialType() == null &&
          StringUtils.isBlank(companyPeople.getCredentialNo()) &&
          StringUtils.isBlank(companyPeople.getDomicileAddress())) {
        break;
      }
      index++;
      if (StringUtils.isBlank(company.getCompanyCode()) || StringUtils
          .isBlank(company.getVillageCode())) {
        failureMsg.append(index + "参数错误");
        ckeckFlag = false;
        break;
      }
      companyPeople.setCreateTime(date);
      companyPeople.setUpdateTime(date);
      companyPeople.setCompanyCode(company.getCompanyCode());
      companyPeople.setVillageCode(company.getVillageCode());
      companyPeople.setIdCardPicUrl((String) imagePaths.get(companyPeople.getCredentialNo()));
      if (StringUtils.isBlank(companyPeople.getPeopleName())) {
        failureMsg.append(index + "行“人员姓名”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (companyPeople.getCredentialType() == null) {
        failureMsg.append(index + "行“证件类型”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      if (StringUtils.isBlank(companyPeople.getCredentialNo())) {
        failureMsg.append(index + "行“证件号码”列数据格式非法。");
        ckeckFlag = false;
      }
      if (StringUtils.isBlank(companyPeople.getDomicileAddress())) {
        failureMsg.append(index + "行“户籍地”列数据格式非法。");
        ckeckFlag = false;
        break;
      }
      CompanyPeople entity = this.companyPeopleMapper.check(companyPeople);
      if (entity == null) {
        insertList.add(companyPeople);
        continue;
      }
      companyPeople.setId(entity.getId());
      updateList.add(companyPeople);
    }

    if (ckeckFlag) {
      if (insertList.size() > 0) {
        this.companyPeopleMapper.insertList(insertList);
      }
      if (updateList.size() > 0) {
        this.companyPeopleMapper.updateBatch(updateList);
      }
      map.put("result", "success");
      map.put("message", "成功导入" + (index - NumberConstant.TWO
          .intValue()) + "条数据，新增" + insertList.size() + "条，更新" + updateList.size() + "条");
    } else {

      map.put("result", "failed");
      map.put("message", "导入失败：失败原因" + failureMsg.toString());
    }
    return map;
  }
}
