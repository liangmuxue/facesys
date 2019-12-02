package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.model.People;
import com.ss.isc.data.resource.common.model.PeopleFacedbFace;
import com.ss.isc.data.resource.common.model.PeopleVehicle;
import com.ss.isc.data.resource.common.web.PeopleHouseVO;
import com.ss.isc.data.resource.common.web.PeopleQueryVO;
import com.ss.mapper.CWMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ResourcePeopleMapper：实有人口Mapper
 * @author FrancisYs
 * @date 2019/8/13
 * @email yaoshuai@ss-cas.com
 */
@Repository
@Mapper
public interface ResourcePeopleMapper extends CWMapper<People> {

    /**
     * 查询实有人口分页列表
     * @param paramPeople
     * @return
     */
    List<People> page(PeopleQueryVO paramPeople);

    /**
     * 根据id主键查询实有人口信息
     * @param paramInteger
     * @return
     */
    People selectById(Integer paramInteger);

    /**
     * 查询人口集合
     * @param ids 多个id以","分隔
     * @return
     */
    List<People> selectByIdArrStr(@Param("ids") String ids);

    /**
     * 删除实有人口（支持批量）
     * @param ids 批量删除时多个id以","分隔
     * @return
     */
    int deleteById(@Param("ids") String ids);

    /**
     * 查询小区关联人像库
     * @param paramString
     * @return
     */
    String selectFacedbId(@Param("villageCode") String paramString);

    /**
     * 新增实有人口与人像集关联关系
     * @param paramString1
     * @param paramString2
     * @return
     */
    Integer insertFacedbfaceInfo(@Param("peopleId") String paramString1, @Param("facedbfaceId") String paramString2);

    People check(People paramPeople);

    int updateBatch(List<People> paramList);

    int batchInsertFaceInfo(List<PeopleFacedbFace> paramList);

    /**
     * 查询人房关系待添加人员列表
     * @param paramPeopleHouseVO
     * @return
     */
    List<People> peopleList(PeopleHouseVO paramPeopleHouseVO);

    /**
     * 查询人车关系待添加车辆列表
     * @param paramPeopleVehicle
     * @return
     */
    List<People> peopleVehicleList(PeopleVehicle paramPeopleVehicle);

}
