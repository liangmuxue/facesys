package com.ss.isc.data.statistic.mapper;


import com.ss.isc.data.statistic.common.dto.OneBidThirtyVO;
import com.ss.isc.data.statistic.common.dto.StPerceptVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface HomeDataShowMapper {
    //一标三十
    //List<OneBidThirtyVO> selAllOneBidThirty(@Param("villageCode") String villageCode);

    List<OneBidThirtyVO> selPeopleDay(@Param("villageCode") String villageCode);

    List<OneBidThirtyVO> selHouseDay(@Param("villageCode") String villageCode);

    List<OneBidThirtyVO> selCompanyDay(@Param("villageCode") String villageCode);

    List<OneBidThirtyVO> selPeopleWeek(@Param("villageCode") String villageCode);

    List<OneBidThirtyVO> selHouseWeek(@Param("villageCode") String villageCode);

    List<OneBidThirtyVO> selCompanyWeek(@Param("villageCode") String villageCode);

    List<OneBidThirtyVO> selPeopleMouth(@Param("villageCode") String villageCode);

    List<OneBidThirtyVO> selHouseMouth(@Param("villageCode") String villageCode);

    List<OneBidThirtyVO> selCompanyMouth(@Param("villageCode") String villageCode);

    List<StPerceptVO> selPerceptDay(@Param("villageCode") String villageCode, @Param("dayNum") int dayNum);

//    List<> selPerceptWeek(@Param("villageCode") String villageCode);
//
//    List<> selPerceptMouth(@Param("villageCode") String villageCode);

}