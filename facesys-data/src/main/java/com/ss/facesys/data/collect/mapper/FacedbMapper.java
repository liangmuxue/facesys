package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * FacedbMapper
 *
 * @author FrancisYs
 * @date 2019/9/3
 * @email yaoshuai@ss-cas.com
 */
@Mapper
public interface FacedbMapper extends SsMapper<Facedb> {

    /**
     * 查询人像库列表
     * @param facedb
     * @return
     */
    List<Facedb> getFacedbList(Facedb facedb);

}
