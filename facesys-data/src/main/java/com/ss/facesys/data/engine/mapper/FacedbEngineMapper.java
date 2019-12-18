package com.ss.facesys.data.engine.mapper;

import com.ss.facesys.data.engine.common.model.FacedbEngine;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * FacedbEngineMapper
 * 
 * @author FrancisYs
 * @date 2019/12/16
 * @email yaoshuai@ss-cas.com
 */
@Mapper
@Component
public interface FacedbEngineMapper extends SsMapper<FacedbEngine> {

}
