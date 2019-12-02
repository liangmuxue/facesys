package com.ss.facesys.data.resource.client;

import com.ss.facesys.data.resource.common.model.Building;

import java.util.List;

import com.ss.facesys.data.resource.common.web.BuildingQueryVO;
import org.apache.ibatis.annotations.Param;

/**
 * IBuildingService
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public interface IBuildingService {

    /**
     * 查询楼栋列表
     * @param buildingQueryVO
     * @return
     */
    List<Building> pages(BuildingQueryVO buildingQueryVO);

    /**
     * 新增楼栋
     * @param paramBuilding
     */
    void add(Building paramBuilding);

    /**
     * 修改楼栋
     * @param paramBuilding
     * @return
     */
    int update(Building paramBuilding);

    /**
     * 删除楼栋
     * @param paramBuilding
     */
    void delete(Building paramBuilding);

    List<Building> list(Building paramBuilding);

    /**
     * 查询楼栋详情
     * @param paramBuilding
     * @return
     */
    Building selectOne(Building paramBuilding);

    Building getByThirdId(@Param("thirdId") String paramString);

}
