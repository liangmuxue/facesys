package com.ss.facesys.data.baseinfo.service;

import com.ss.facesys.data.baseinfo.client.IBaseService;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * BaseServiceImpl
 *
 * @author FrancisYs
 * @date 2019/12/6
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class BaseServiceImpl implements IBaseService {

    /**
     * 返回查询用户权限小区的sql条件
     *
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

    private static final String METHOD_PREFIX_GET = "get";

    /**
     * 实体属性条件转换为Example形式
     *
     * @param entity
     * @return
     */
    protected Example entityToExample(Object entity) {
        String property;
        Method get;
        Object value;
        Class<?> entityClass = entity.getClass();
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                property = field.getName();
                get = entityClass.getMethod(METHOD_PREFIX_GET + property.substring(0, 1).toUpperCase() + property.replaceFirst("\\w", ""));
                if ((value = get.invoke(entity)) != null && !"".equals(value)) {
                    criteria.andEqualTo(property, value);
                }
            } catch (Exception ignored) {
            }
        }
        return example;
    }

    protected String like(String target) {
        return "%" + target + "%";
    }

}
