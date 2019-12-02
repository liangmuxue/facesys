package com.ss.facesys.data.resource.service;

import com.ss.facesys.data.baseinfo.common.dto.StatistiscsDTO;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.resource.client.ICommunityResourceService;
import com.ss.facesys.data.resource.common.dto.CameraCaptureDTO;
import com.ss.facesys.data.resource.common.model.Building;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.model.House;
import com.ss.facesys.data.resource.common.model.Latitude;
import com.ss.facesys.data.resource.common.model.Unit;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.model.VillageEntrance;
import com.ss.facesys.data.resource.mapper.BuildingMapper;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.data.resource.mapper.ResourceHouseMapper;
import com.ss.facesys.data.resource.mapper.UnitMapper;
import com.ss.facesys.data.resource.mapper.VillageEntranceMapper;
import com.ss.facesys.data.resource.mapper.VillageMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.jedis.JedisUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class CommunityResourceServiceImpl extends BaseServiceImpl implements ICommunityResourceService {

  private static final String[] HOUSERTYPES = {"live", "rent", "setAside", "houseTotal"};
  @Autowired
  private VillageMapper villageMapper;
  @Autowired
  private VillageEntranceMapper villageEntranceMapper;
  @Autowired
  private BuildingMapper buildingMapper;
  @Autowired
  private UnitMapper unitMapper;
  @Autowired
  private CameraMapper cameraMapper;
  @Autowired
  private ResourceHouseMapper houseMapper;
  @Resource
  public JedisUtil jedisUtil;

  @Override
  public List<Village> findVillages(Village village) {
    User user = new User();
    user.setUserId(village.getUserIds());
    Map<String, String> map = new HashMap<String, String>();
    map.put("dsf", dataScopeFilter(user).replace("t1.", ""));
    village.setSqlMap(map);

    List<Village> villages = this.villageMapper.findList(village);
    for (Village v : villages) {
      if (StringUtils.isNotBlank(v.getVillagePicUrl()) && !v.getVillagePicUrl().contains("http")) {
        v.setVillagePicUrl(FilePropertiesUtil.getHttpUrl() + v.getVillagePicUrl());
      }

      if (StringUtils.isNoneBlank(new CharSequence[]{v.getGisArea()})) {
        String[] arr = v.getGisArea().trim().split(";");
        List<Latitude> latitudeList = v.getLatitudeList();
        for (int i = 0; i < arr.length; i++) {
          double lat = Double.parseDouble(arr[i].substring(0, arr[i].indexOf(",")));
          double lon = Double
              .parseDouble(arr[i].substring(arr[i].indexOf(",") + 1, arr[i].length()));

          (new Village()).getClass();
          latitudeList
              .add(new Latitude( Double.valueOf(lon), Double.valueOf(lat)));
        }
      }
    }
    return villages;
  }

  @Override
  public List<VillageEntrance> findVillageEntrance(Village village) {
    User user = new User();
    user.setUserId(village.getUserIds());
    Map<String, String> map = new HashMap<String, String>();
    map.put("dsf", dataScopeFilter(user).replace("t1.", ""));
    village.setSqlMap(map);
    List<VillageEntrance> findVillageEntrance = this.villageEntranceMapper
        .findVillageEntrance(village);
    for (VillageEntrance villageEntrance : findVillageEntrance) {

      if (StringUtils.isNotBlank(villageEntrance.getEntrancePicUrl()) && !villageEntrance
          .getEntrancePicUrl().contains("http")) {
        villageEntrance.setEntrancePicUrl(
            FilePropertiesUtil.getHttpUrl() + villageEntrance.getEntrancePicUrl());
      }
    }
    return findVillageEntrance;
  }

  @Override
  public List<Building> findBuildings(Village village) {
    User user = new User();
    user.setUserId(village.getUserIds());
    Map<String, String> map = new HashMap<String, String>();
    map.put("dsf", dataScopeFilter(user).replace("t1.", ""));
    village.setSqlMap(map);
    List<Building> findBuildings = this.buildingMapper.findBuildings(village);
    for (Building building : findBuildings) {
      List<Unit> units = this.unitMapper.getUnitNumByBuilding(building);
      building.setUnits(units);
    }
    return findBuildings;
  }

  @Override
  public List<Camera> findCameras(Camera camera) {
    User user = new User();
    user.setUserId(camera.getUserIds());
    Map<String, String> map = new HashMap<String, String>();
    map.put("dsf", dataScopeFilter(user).replace("t1", "a"));
    camera.setSqlMap(map);
    return this.cameraMapper.findCameras(camera);
  }

  /**
   * 查询楼栋
   * @param building
   * @return
   */
  @Override
  public List<Building> findBuildingsByParam(Building building) {
    //设置楼栋状态为未删除
    building.setIsDelete(CommonConstant.COMMON_0);
    //返回楼栋信息
    return this.buildingMapper.select(building);
  }

  /**
   * 查询房屋详情(只有编号)
   * @param house
   * @return
   */
  @Override
  public List<House> findHouseByParam(House house) {
    //返回房屋信息
    return this.houseMapper.select(house);
  }

  @Override
  public Map<String, Object> statisticsHouse(String villageCode) {
    Map<String, Object> resultMap = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

    int total = 0;
    total = this.houseMapper.houseTotal(villageCode).intValue();
    resultMap.put(HOUSERTYPES[3], new StatistiscsDTO("peopleRelation", "实有房屋总数", Integer.valueOf(4), Integer.valueOf(total)));
    return resultMap;
  }

  @Override
  public List<CameraCaptureDTO> findCapturedeviceIds() {
    return this.cameraMapper.findCapturedeviceIds();
  }

  /**
   * 查询单元
   * @param dto
   * @return
   */
  @Override
  public List<Unit> select(Unit dto) {
    //设置单元状态为未删除
    dto.setIsDelete(CommonConstant.COMMON_0);
    //返回查询单元信息
    return this.unitMapper.select(dto);
  }

  /**
   * 小区查询
   * @param dto
   * @return
   */
  @Override
  public List<Village> findVillageList(Village dto) {
    //判断用户id是否为空
    if (StringUtils.isNotBlank(dto.getUserIds())) {
      User user = new User();
      user.setUserId(dto.getUserIds());
      //获取用户权限
      String sqlString = dataScopeFilter(user);
      dto.setSqlString(sqlString);
    }
    //返回小区信息
    return this.villageMapper.list(dto);
  }
}
