package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.DevicePersoncard;
import com.ss.facesys.data.resource.common.web.CameraQueryVO;
import com.ss.mapper.SsMapper;
import com.ss.spider.system.organization.model.Organization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DevicePersoncardMapper
 *
 * @author FrancisYs
 * @date 2020/2/11
 * @email yaoshuai@ss-cas.com
 */
@Mapper
public interface DevicePersoncardMapper extends SsMapper<DevicePersoncard> {

    List<Organization> findTreeCameras(CameraQueryVO queryVO);

}
