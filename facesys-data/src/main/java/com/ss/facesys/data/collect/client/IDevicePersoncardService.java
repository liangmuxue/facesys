package com.ss.facesys.data.collect.client;

import com.ss.exception.ServiceException;
import com.ss.facesys.data.collect.common.model.DevicePersoncard;

import java.util.List;

/**
 * IDevicePersoncardService
 *
 * @author FrancisYs
 * @date 2020/2/11
 * @email yaoshuai@ss-cas.com
 */
public interface IDevicePersoncardService {

    /**
     * 查询人证设备分页列表
     *
     * @param devicePersoncard
     * @return
     */
    List<DevicePersoncard> getPersonCardPage(DevicePersoncard devicePersoncard, int currentPage, int pageSize);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，查询条件使用等号
     *
     * @param devicePersoncard
     * @return
     */
    DevicePersoncard selectOne(DevicePersoncard devicePersoncard);

    /**
     * 修改人证设备
     *
     * @param devicePersoncard
     * @return
     * @throws ServiceException
     */
    void updatePersonCard(DevicePersoncard devicePersoncard) throws ServiceException;

    /**
     * 删除人证设备
     *
     * @param devicePersoncard
     * @return
     * @throws ServiceException
     */
    void deletePersonCard(DevicePersoncard devicePersoncard) throws ServiceException;

}