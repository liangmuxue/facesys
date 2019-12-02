package com.ss.isc.sync.data.mapper;

import com.ss.isc.data.resource.common.model.Building;
import com.ss.isc.data.resource.common.model.Region;
import com.ss.isc.data.resource.common.model.Village;
import com.ss.isc.sync.data.model.Camera;
import com.ss.isc.sync.data.model.DeviceParam;
import com.ss.isc.sync.data.model.House;
import com.ss.mapper.CWMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemMapper extends CWMapper<DeviceParam> {

    List<Camera> queryCamera();

    Camera getCameraByNo(@Param("cameraNo") String paramString1, @Param("productCode") String paramString2);

    int batchInsertCamera(List<Camera> paramList);

    int insertCamera(Camera paramCamera);

    int updateCamera(Camera paramCamera);

    int batchCompareRegion(List<Region> paramList);

    String getFacedbfaceId(@Param("credentialNo") String paramString);

    Building getBuildingByCode(@Param("villageCode") String paramString1, @Param("buildingNo") String paramString2);

    int insertBuilding(Building paramBuilding);

    int updateBuilding(Building paramBuilding);

    House getHouseByCode(@Param("houseNo") String paramString);

    House getHouseByThirdId(@Param("thirdId") String paramString);

    int insertHouse(House paramHouse);

    int updateHouse(House paramHouse);

    Village getVillage(Village paramVillage);

}
