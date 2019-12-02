package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.Facedb;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.web.VillageQueryVO;
import com.ss.mapper.SsMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * VillageMapper
 * @author FrancisYs
 * @date 2019/8/15
 * @email yaoshuai@ss-cas.com
 */
@Component
@Mapper
public interface VillageMapper extends SsMapper<Village> {

    List<Village> findVillage(@Param("villageCode") String paramString);

    List<Village> findList(Village paramVillage);

    /**
     * 查询小区列表
     * @param queryVO
     * @return
     */
    List<Village> findAllVillage(VillageQueryVO queryVO);

    Integer insertVillage(Village paramVillage);

    /**
     * 删除小区
     * @param paramVillage
     * @return
     */
    Integer deleteVillage(Village paramVillage);

    /**
     * 修改小区
     * @param paramVillage
     * @return
     */
    Integer updateVillage(Village paramVillage);

    List<Village> findCurrentVillage(Map<String, String> paramMap);

    List<Village> list(Village paramVillage);

    void deleteVillageCode(@Param("villageCode") String paramString);

    /**
     * 新增小区关联人像库数据
     * @param paramFacedb
     * @return
     */
    Integer insertFacedb(Facedb paramFacedb);

    Village selectBythirdId(@Param("thirdId") String paramString);

    /**
     * 查询某个区划下的小区数量
     * @param paramString
     * @return
     */
    int getVillageNumByArea(@Param("areaCode") String paramString);

    void deleteFaceDb(String paramString);

    Facedb findFaceDbByCode(@Param("villageCode") String paramString);

    List<Village> selectListBythirdId(String paramString);

    List<Facedb> selectFacedb();

}
