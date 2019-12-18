package com.ss.facesys.data.engine.service;

import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.engine.client.IFacedbEngineService;
import com.ss.facesys.data.engine.mapper.FacedbEngineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 人像库绑定引擎关系
 *
 * @author FrancisYs
 * @date 2019/12/16
 * @email yaoshuai@ss-cas.com
 */
@Service("facedbEngineService")
public class FacedbEngineServiceImpl extends BaseServiceImpl implements IFacedbEngineService {

    private static final Logger logger = LoggerFactory.getLogger(FacedbEngineServiceImpl.class);

    @Resource
    private FacedbEngineMapper facedbEngineMapper;


}
