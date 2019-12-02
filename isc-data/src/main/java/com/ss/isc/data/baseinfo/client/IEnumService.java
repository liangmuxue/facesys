package com.ss.isc.data.baseinfo.client;

import com.ss.isc.data.baseinfo.common.model.BaseEnums;

import java.util.List;

/**
 * 枚举服务接口
 * @author FrancisYs
 * @date 2019/08/08
 * @email yaoshuai@ss-cas.com
 */
public interface IEnumService {

    /**
     * 查找枚举列表
     * @param paramBaseEnums
     * @return
     */
    List<BaseEnums> findList(BaseEnums paramBaseEnums);

}
