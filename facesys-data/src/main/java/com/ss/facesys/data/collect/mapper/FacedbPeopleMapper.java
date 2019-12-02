package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.FacedbPeople;
import com.ss.facesys.data.collect.common.web.FacedbPeopleVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * FacedbPeopleMapper
 *
 * @author FrancisYs
 * @date 2019/9/4
 * @email yaoshuai@ss-cas.com
 */
@Mapper
public interface FacedbPeopleMapper extends SsMapper<FacedbPeople> {

    /**
     * 查询重点人员分页列表
     * @param vo
     * @return
     */
    List<FacedbPeopleVO> getFacedbPeopleList(FacedbPeopleVO vo);

    /**
     * 批量删除人口-人像库关系数据
     * @param facedbPeople id属性为多个id逗号拼接
     * @return
     */
    int deleteByIdBatch(FacedbPeople facedbPeople);

}
