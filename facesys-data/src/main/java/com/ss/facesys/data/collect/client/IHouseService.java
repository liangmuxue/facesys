package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.model.Building;
import com.ss.facesys.data.collect.common.model.Company;
import com.ss.facesys.data.collect.common.model.Employee;
import com.ss.facesys.data.collect.common.model.House;
import com.ss.facesys.data.collect.common.model.Vehicle;
import com.ss.facesys.data.collect.common.web.CompanyListVO;
import com.ss.facesys.data.resource.common.web.HouseListVO;

import java.util.List;
import java.util.Map;
/**
* 房屋和单位列表查询
* @author chao
* @create 2019/8/16
* @email lishuangchao@ss-cas.com
**/
public interface IHouseService {
  List<Company> getCompany(CompanyListVO param, int paramInt1, int paramInt2);
  
  Map<String, Object> getBuildHouses(Building paramBuilding);

  /**
   * 实有房屋分页查询
   * @param param
   * @param paramInt1
   * @param paramInt2
   * @return
   */
  List<House> getHouse(HouseListVO param, int paramInt1, int paramInt2);
  
  List<Employee> searchEmployee(Map<String, String> paramMap, int paramInt1, int paramInt2);
  
  List<Employee> getEmployee(Map<String, String> paramMap, int paramInt1, int paramInt2);
  
  List<Vehicle> findList(Map<String, String> paramMap);
}
