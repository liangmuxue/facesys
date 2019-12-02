package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.dto.PopulationSta;
import com.ss.facesys.data.collect.common.model.People;
import com.ss.mapper.SsMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * PeopleMapper
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
@Repository
@Mapper
public interface PeopleMapper extends SsMapper<People> {

    int getPeopleNum(@Param("villageCode") String paramString);

    List<PopulationSta> getGenderRatio(@Param("villageCode") String paramString);

    List<PopulationSta> getPeopleType(@Param("villageCode") String paramString);

    List<PopulationSta> getPeopleLabelInfo(@Param("villageCode") String paramString);

    int getPeopleAgeGroupNum(People paramPeople);

    /**
     * 一社一档-居民信息
     * @param paramPeople
     * @return
     */
    List<People> page(People paramPeople);

    Integer updateByPeopleId(People paramPeople);

    Integer insertFacedbfaceInfo(@Param("peopleId") String paramString1, @Param("facedbfaceId") String paramString2);

    String selectFacedbId(@Param("villageCode") String paramString);

}
