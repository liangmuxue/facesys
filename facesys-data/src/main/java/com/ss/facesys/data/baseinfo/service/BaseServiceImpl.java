package com.ss.facesys.data.baseinfo.service;

import com.ss.facesys.data.baseinfo.client.IBaseService;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.organization.service.OrganizationService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private OrganizationService<Organization> organizationService;

    @Resource
    public JedisUtil jedisUtil;

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

    /**
     * 获取全部的子集单位（包含本身）
     *
     * @param orgId 单位id
     * @return
     */
    protected List<String> getAllOrgNodes(String orgId) {
        if (StringUtils.isBlank(orgId)) {
            return Collections.emptyList();
        }
        List<Organization> allList = organizationService.getCascadeChildren(orgId);
        if (CollectionUtils.isEmpty(allList)) {
            return Collections.emptyList();
        }
        return allList.stream().map(Organization::getOrgId).collect(Collectors.toList());
    }

    /**
     * 获取测温安防库id
     *
     * @param
     * @return
     */
    protected Long createDbId(){
        return mask(PropertiesUtil.getDbPrefix(), PropertiesUtil.getDbMagnification(), CacheConstant.COMM_PLAT_DB_INFO, CacheConstant.DB_INFO);
    }

    /**
     * 获取掩码
     *
     * @param prefix 前缀
     * @param magnification 倍率
     * @param titleOne 标题一
     * @param titleTwo 标题二
     * @return
     */
    public Long mask(int prefix,int magnification,String titleOne,String titleTwo){
        Long defaultValue = Long.valueOf(prefix) * magnification + 1;
        String hget = String.valueOf(jedisUtil.hget(titleOne, titleTwo));
        if("null".equals(hget)){
            jedisUtil.hset(titleOne,titleTwo ,defaultValue);
        }else{
            defaultValue = Long.valueOf(hget) + 1;
            jedisUtil.hset(titleOne,titleTwo,defaultValue);
        }
        return defaultValue;
    }
}
