package com.ss.facesys.data.resource.client;

import com.ss.facesys.data.resource.common.dto.RegionTree;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.facesys.data.resource.common.model.ThirdRegion;
import com.ss.facesys.data.resource.common.web.RegionVO;

import java.util.List;

/**
 * IRegionService
 * @author FrancisYs
 * @date 2019/8/14
 * @email yaoshuai@ss-cas.com
 */
public interface IRegionService {

    /**
     * 查询行政区划树（包含小区）
     * @param paramRegion
     * @param paramArrayOfString
     * @return
     */
    List<RegionTree> getTreeData(Region paramRegion, String[] paramArrayOfString);

    RegionTree getThirdTreeData();

    List<Region> findCurrentRegion(Region paramRegion);

    /**
     * 查询区划下的全部小区编号
     * @param paramString
     * @return
     */
    String getVilllageCodes(String paramString);

    /**
     * 查询区划下的全部小区编号
     * @param regionCode 区划编号
     * @return
     */
    String getVilllageCodesByRegionCode(String regionCode);

    Integer insertRegion(Region paramRegion);

    Integer deleteRegion(Region paramRegion);

    Integer deleteChildrenRegion(Region paramRegion);

    Integer updateRegion(Region paramRegion);

    List<Region> list(Region paramRegion);

    /**
     * 查询行政区划分页列表
     * @param region
     * @return
     */
    List<Region> regionInfolist(RegionVO region);

    Region selectOne(Region paramRegion);

    /**
     * 查询行政区划树
     * @param paramRegion
     * @return
     */
    List<RegionTree> regionTree(Region paramRegion);

    int matchRegion(Region paramRegion, ThirdRegion paramThirdRegion, int paramInt);

    List<Region> getBindThirdRegion();

    int getThirdRegionNumber();

    List<Region> getRegion(Region paramRegion);

    Region getRegionParent(String paramString);

    Region selectRegionByCode(String paramString);

    Region isRepeat(String paramString);

    String selectRegionCodeByThirdId(String paramString);

}
