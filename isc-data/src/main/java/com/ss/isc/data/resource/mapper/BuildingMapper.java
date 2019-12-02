package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.model.Building;
import com.ss.isc.data.resource.common.model.Village;
import com.ss.isc.data.resource.common.web.BuildingQueryVO;
import com.ss.mapper.CWMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * BuildingMapper
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Component
@Mapper
public interface BuildingMapper extends CWMapper<Building> {

    List<Building> findBuildings(Village paramVillage);

    /**
     * 查询楼栋列表
     * @param buildingQueryVO
     * @return
     */
    List<Building> pages(BuildingQueryVO buildingQueryVO);

    /**
     * 根据主键查询楼栋
     * @param paramBuilding
     * @return
     */
    Building findById(Building paramBuilding);

    /**
     * 新增楼栋
     * @param paramBuilding
     */
    void inserts(Building paramBuilding);

    /**
     * 查询当前小区楼栋编号最大值
     * @param paramBuilding
     * @return
     */
    Building maxBuildingNo(Building paramBuilding);

    /**
     * 修改楼栋
     * @param paramBuilding
     * @return
     */
    int updateBuilding(Building paramBuilding);

    Building getByThirdId(@Param("thirdId") String paramString);

    /**
     * 删除楼栋
     * @param paramBuilding
     */
    void deletes(Building paramBuilding);

}
