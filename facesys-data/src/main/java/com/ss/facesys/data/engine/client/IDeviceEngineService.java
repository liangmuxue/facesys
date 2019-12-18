package com.ss.facesys.data.engine.client;

import com.ss.exception.ServiceException;
import com.ss.facesys.data.engine.common.dto.DeviceEngineDTO;
import com.ss.facesys.data.engine.common.model.DeviceEngine;

import java.util.List;

/**
 * 设备绑定引擎关系
 *
 * @author FrancisYs
 * @date 2019/12/16
 * @email yaoshuai@ss-cas.com
 */
public interface IDeviceEngineService {

    /**
     * 查询设备绑定引擎列表
     *
     * @param engineDTO
     * @return
     */
    List<DeviceEngineDTO> engineList(DeviceEngineDTO engineDTO);

    /**
     * 设备绑定引擎关系
     *
     * @param deviceEngine
     * @throws ServiceException
     * @return
     */
    String bindEngineControl(DeviceEngine deviceEngine) throws ServiceException;

}
