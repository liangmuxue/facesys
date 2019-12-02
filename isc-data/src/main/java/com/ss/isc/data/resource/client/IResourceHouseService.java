package com.ss.isc.data.resource.client;

import com.ss.isc.data.resource.common.dto.HouseDTO;
import com.ss.isc.data.resource.common.model.House;
import com.ss.isc.data.resource.common.model.PeopleHouse;
import com.ss.isc.data.resource.common.model.PeopleVehicle;
import com.ss.isc.data.resource.common.web.HouseEditVO;
import com.ss.isc.data.resource.common.web.HouseQueryVO;
import com.ss.isc.data.resource.common.web.PeopleHouseVO;
import java.util.List;
import java.util.Map;
/**
 * 房屋、车辆有关操作
 * @author chao
 * @create 2019/8/16
 * @email lishuangchao@ss-cas.com
 **/
public interface IResourceHouseService {
  List<HouseDTO> pages(HouseQueryVO paramHouseQueryVO);
  
  HouseDTO get(Integer paramInteger);

  /**
   * 新增房屋
   * @param paramHouse
   * @return
   */
  String save(House paramHouse);

  /**
   * 删除房屋信息
   * @param paramInteger
   * @return
   */
  String delete(Integer paramInteger);

  String batchDelete(Integer... param);

  /**
   * 修改房屋信息
   * @param paramHouse
   * @return
   */
  String edit(House paramHouse);

  /**
   * 人房关联编辑
   * @param paramPeopleHouseVO
   * @return
   */
  Object housePeopleRelation(PeopleHouseVO paramPeopleHouseVO);
  
  List<House> list(House paramHouse);
  
  Map<String, String> batchImport(List<House> paramList);
  
  Map<String, String> importPeopleRelation(List<PeopleHouse> paramList);

  /**
   * 人车关联编辑
   * @param paramPeopleVehicle
   * @return
   */
  Object vehiclePeopleRelation(PeopleVehicle paramPeopleVehicle);
}
