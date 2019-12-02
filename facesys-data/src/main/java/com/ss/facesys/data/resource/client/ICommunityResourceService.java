package com.ss.facesys.data.resource.client;

import com.ss.facesys.data.resource.common.dto.CameraCaptureDTO;
import com.ss.facesys.data.resource.common.model.Building;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.model.House;
import com.ss.facesys.data.resource.common.model.Unit;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.model.VillageEntrance;
import java.util.List;
import java.util.Map;
/**
 * 小区楼栋单元房屋信息查询
 * @author chao
 * @create 2019/8/16
 * @email lishuangchao@ss-cas.com
 **/
public interface ICommunityResourceService {
  List<Village> findVillages(Village paramVillage);
  
  List<VillageEntrance> findVillageEntrance(Village paramVillage);
  
  List<Building> findBuildings(Village paramVillage);
  
  List<Camera> findCameras(Camera paramCamera);

  /**
   * 查询楼栋
   * @param paramBuilding
   * @return
   */
  List<Building> findBuildingsByParam(Building paramBuilding);

  /**
   * 查询单元
   * @param paramUnit
   * @return
   */
  List<Unit> select(Unit paramUnit);

  /**
   * 查询房屋详情(只有编号)
   * @param paramHouse
   * @return
   */
  List<House> findHouseByParam(House paramHouse);
  
  Map<String, Object> statisticsHouse(String paramString);
  
  List<CameraCaptureDTO> findCapturedeviceIds();

  /**
   * 所有小区查询
   * @param paramVillage
   * @return
   */
  List<Village> findVillageList(Village paramVillage);
}
