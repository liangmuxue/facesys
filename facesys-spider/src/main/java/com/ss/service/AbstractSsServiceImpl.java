package com.ss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通用基础服务
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public abstract class AbstractSsServiceImpl<T> implements SsService<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected Mapper<T> mapper;

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

}