package com.ss.isc.data.collect.service;

import com.ss.isc.data.baseinfo.common.model.User;
import com.ss.isc.data.baseinfo.service.BaseServiceImpl;
import com.ss.isc.data.collect.client.IHouseService;
import com.ss.isc.data.collect.common.model.Building;
import com.ss.isc.data.collect.common.model.Company;
import com.ss.isc.data.collect.common.model.Employee;
import com.ss.isc.data.collect.common.model.Floor;
import com.ss.isc.data.collect.common.model.House;
import com.ss.isc.data.collect.common.model.Vehicle;
import com.ss.isc.data.collect.common.web.CompanyListVO;
import com.ss.isc.data.collect.mapper.CompanyMapper;
import com.ss.isc.data.collect.mapper.HouseMapper;
import com.ss.isc.data.resource.common.model.CompanyPeople;
import com.ss.isc.data.resource.common.web.HouseListVO;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class HouseServiceImpl extends BaseServiceImpl implements IHouseService {
  public static final Log LOG = LogFactory.getLog(HouseServiceImpl.class);

  @Autowired
  private HouseMapper houseMapper;
  
  @Autowired
  private CompanyMapper companyMapper;

  /**
   * 查找单位
   * @param para
   * @param currPage
   * @param pageSize
   * @return
   */
  @Override
  public List<Company> getCompany(CompanyListVO para, int currPage, int pageSize) {
    PageHelper.startPage(currPage, pageSize);
    Company company = new Company();
    BeanUtils.copyProperties(para, company);
    //Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
    //查询用户权限小区的sql条件
    if (StringUtils.isNotBlank((CharSequence)para.getUserId())) {
      Map<String, String> sql = new HashMap<String, String>();
      User user = new User();
      user.setUserId((String)para.getUserId());
      //Map<String, String> sqlMap = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
      company.setDsf(dataScopeFilter(user).replace("t1", "a"));
    }
    //查找单位
    company.setIsDelete(CommonConstant.COMMON_1);
    List<Company> company1 = this.companyMapper.getCompany(company);
    
    for (Company company2 : company1) {
      if (StringUtils.isNotBlank(company2.getCompanyPic()) && !company2.getCompanyPic().contains("http")) {
        //取得单位图片绝对路径
        company2.setCompanyPic(FilePropertiesUtil.getHttpUrl() + company2.getCompanyPic());
      }
    }
    return company1;
  }
  
  @Override
  public Map<String, Object> getBuildHouses(Building building) {
    Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

    int floorNumber = building.getFloornum().intValue();
    
    List<Floor> floors = new ArrayList<Floor>();
    for (int i = 1; i <= floorNumber; i++) {
      building.setFloornum(Integer.valueOf(i));
      List<House> buildHouses = this.houseMapper.getBuildHouses(building);
      for (House house : buildHouses) {
        House house3 = new House();
        house3.setBuildingno(house.getBuildingno());
        house3.setUnitNo(house.getUnitNo());
        house3.setHouseno(house.getHouseno());
        house3.setVillageCode(house.getVillageCode());
        Integer peopleCount = this.houseMapper.getCountPeople(house3);
        house.setCountPeople(peopleCount + "");
        house.setBuildingNoAndUnitNo(house.getBuildingno() + "-" + house.getUnitNo());
      } 
      floors.add(new Floor(building.getVillageCode(), building.getBuildingno(), Integer.valueOf(i), buildHouses));
    }
    map.put("datas", floors);
    return map;
  }

  /**
   * 查找房屋
   * @param para
   * @param currPage
   * @param pageSize
   * @return
   */
  @Override
  public List<House> getHouse(HouseListVO para, int currPage, int pageSize) {
    PageHelper.startPage(currPage, pageSize);
    House house = new House();
    BeanUtils.copyProperties(para, house);

    //查询房屋信息
    List<House> house1 = this.houseMapper.getHouse(house);
    
    for (House house2 : house1) {
      Integer id = house2.getId();
      String buildingno = house2.getBuildingno();
      String unitNo = house2.getUnitNo();
      String houseno = house2.getHouseno();
      String villageCode = house2.getVillageCode();
      House house3 = new House();
      house3.setId(house2.getId());
      house3.setBuildingno(buildingno);
      house3.setUnitNo(unitNo);
      house3.setHouseno(houseno);
      house3.setVillageCode(villageCode);
      //查询房屋居住人员总数
      Integer peopleCount = this.houseMapper.getCountPeople(house3);
      house2.setCountPeople(peopleCount + "");
      //查询房屋登记车辆总数
      Integer carCount = this.houseMapper.getCountCar(house3);
      house2.setCountCar(carCount + "");
    }
    return house1;
  }

  @Override
  public List<Employee> searchEmployee(Map<String, String> map, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    
    return this.companyMapper.searchEmployee(map);
  }

  @Override
  public List<Employee> getEmployee(Map<String, String> map, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    
    return this.companyMapper.getEmployee(map);
  }

  @Override
  public List<Vehicle> findList(Map<String, String> map) {
    PageHelper.startPage(NumberUtils.toInt((String)map.get("pageNum")), NumberUtils.toInt((String)map.get("pageSize")));
    
    return this.houseMapper.findList(map);
  }
}
