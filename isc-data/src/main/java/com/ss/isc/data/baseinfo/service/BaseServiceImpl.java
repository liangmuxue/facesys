package com.ss.isc.data.baseinfo.service;

import com.ss.isc.data.baseinfo.client.IBaseService;
import com.ss.isc.data.baseinfo.common.model.User;
import com.ss.isc.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BaseServiceImpl
 *
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class BaseServiceImpl implements IBaseService {

    /**
     * 返回查询用户权限小区的sql条件
     * @param user
     * @return
     */
    @Override
    public String dataScopeFilter(User user) {
        StringBuilder sqlString = new StringBuilder();
        if (user.isAdmin()) {
            sqlString
                    .append("FIND_IN_SET(t1.villageCode,(SELECT GROUP_CONCAT(villageCode) FROM cw_base_village WHERE villageCode IS NOT NULL))");
        } else {
            sqlString
                    .append("FIND_IN_SET(t1.villageCode,(SELECT\tREGION_ID FROM\tcw_relation_organization_region WHERE\tORG_ID = (\tSELECT ORG_ID FROM cw_ge_user WHERE USER_ID='")
                    .append(user.getUserId())
                    .append("')))");
        }
        if (StringUtils.isNotBlank(sqlString.toString())) {
            return sqlString.toString();
        }
        return "";
    }

}
