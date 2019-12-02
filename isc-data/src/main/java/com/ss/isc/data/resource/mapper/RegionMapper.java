package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.dto.RegionTree;
import com.ss.isc.data.resource.common.model.Region;
import com.ss.isc.data.resource.common.model.ThirdRegion;
import com.ss.isc.data.resource.common.web.RegionVO;
import com.ss.mapper.CWMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * RegionMapper：区划Mapper
 * @author FrancisYs
 * @date 2019/8/14
 * @email yaoshuai@ss-cas.com
 */
@Component
@Mapper
public interface RegionMapper extends CWMapper<Region> {

    List<Region> findRegion(@Param("regionName") String paramString);

    List<Region> searchNextRegion(@Param("regionCode") String paramString);

    List<RegionTree> findList(RegionTree paramRegionTree);

    List<Region> findCurrentRegion(Region paramRegion);

    Integer insertRegion(Region paramRegion);

    Integer deleteRegion(Region paramRegion);

    Integer deleteChildrenRegion(Region paramRegion);

    Integer updateRegion(Region paramRegion);

    /**
     * 查询区划树
     * @param paramRegion
     * @return
     */
    List<RegionTree> treeList(Region paramRegion);

    List<RegionTree> findVillageRegion();

    List<ThirdRegion> getThirdTreeData(@Param("parentId") String paramString1, @Param("parentCode") String paramString2);

    List<Region> getRegion(Region paramRegion);

    List<Region> getBindThirdRegion();

    int getThirdRegionNumber();

    Region getRegionParent(@Param("regionId") String paramString);

    List<Region> selectRegionList(Region paramRegion);

    /**
     * 查询行政区划分页列表
     * @param region
     * @return
     */
    List<Region> selectRegionInfoList(RegionVO region);

    Region selectRegionByCode(@Param("regionCode") String paramString);

    String selectRegionNameByRegionType(@Param("thirdId") String paramString);

    String selectRegionName(@Param("thirdId") String paramString);

    Region isRepeat(@Param("regionCode") String paramString);

    String selectRegionCodeByThirdId(@Param("thirdId") String paramString);

    List<ThirdRegion> selectIdByRegionCode5(@Param("thirdId") String paramString);

    String selectIdByRegionCode(@Param("thirdId") String paramString);

}
