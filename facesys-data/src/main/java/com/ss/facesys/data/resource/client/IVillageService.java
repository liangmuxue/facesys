package com.ss.facesys.data.resource.client;

import com.ss.facesys.data.resource.common.model.Facedb;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.model.VillageEntrance;
import com.ss.facesys.data.resource.common.web.VillageQueryVO;

import java.util.List;
import java.util.Map;

/**
 * IVillageService
 * @author FrancisYs
 * @date 2019/8/15
 * @email yaoshuai@ss-cas.com
 */
public interface IVillageService {

    List<Village> select(Village paramVillage);

    /**
     * 查询小区列表
     * @param queryVO
     * @return
     */
    List<Village> findAllVillage(VillageQueryVO queryVO);

    /**
     * 新增小区
     * @param paramVillage
     * @return
     * @throws Exception
     */
    int insertVillage(Village paramVillage) throws Exception;

    /**
     * 删除小区
     * @param paramVillage
     * @param facedbId
     * @return
     */
    Integer deleteVillage(Village paramVillage, String facedbId);

    /**
     * 修改小区
     * @param paramVillage
     * @return
     */
    Integer updateVillage(Village paramVillage);

    /**
     * 查询小区详情
     * @param paramMap
     * @return
     */
    List<Village> findVillage(Map<String, String> paramMap);

    List<VillageEntrance> findAllVillageEntrances(VillageEntrance paramVillageEntrance);

    /**
     * 根据小区编号查询小区对象
     * @param paramString
     * @return
     */
    Village findVillageByCode(String paramString);

    Village selectBythirdId(String paramString);

    int getVillageNumByArea(String paramString);

    Facedb findFaceDbByCode(String paramString);

    List<Village> findList(Village paramVillage);

    String getVillageFacedbId(String paramString);

}
