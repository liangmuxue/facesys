package com.ss.facesys.data.baseinfo.client;

import com.ss.facesys.data.baseinfo.common.model.User;

/**
 * IBaseService
 *
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
public interface IBaseService {

    /**
     * 返回查询用户权限小区的sql条件
     * @param paramUser
     * @return
     */
    String dataScopeFilter(User paramUser);

}
