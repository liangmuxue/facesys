package com.ss.facesys.data.baseinfo.common.util;

import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.data.baseinfo.mapper.EnumMapper;
import com.ss.facesys.util.SpringUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体转换工具
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public class EntityUtil {

    private static EnumMapper enumMapper = SpringUtil.getBean(EnumMapper.class);
    private static final String METHOD_PREFIX_GET = "get";
    private static final String METHOD_PREFIX_SET = "set";
    private static final Map<String, List<BaseEnums>> DIC_CACHE_MAP = new HashMap<>();

    private static List<BaseEnums> getListByKeyName(String enumType) {
        List<BaseEnums> enumList;
        if (DIC_CACHE_MAP.containsKey(enumType) && CollectionUtils.isNotEmpty(enumList = DIC_CACHE_MAP.get(enumType))) {
            return enumList;
        }
        BaseEnums query = new BaseEnums();
        query.setEnumType(enumType);
        enumList = enumMapper.select(query);
        DIC_CACHE_MAP.put(enumType, enumList);
        return enumList;
    }

    private static Map<String, String> getMapByKeyName(String enumType) {
        List<BaseEnums> enumList = getListByKeyName(enumType);
        Map<String, String> resultMap;
        // 从缓存中获取
        if (CollectionUtils.isNotEmpty(enumList)) {
            resultMap = new HashMap<>(enumList.size());
            for (BaseEnums baseEnums : enumList) {
                resultMap.put(String.valueOf(baseEnums.getEnumValue()), baseEnums.getEnumName());
            }
            return resultMap;
        }
        return new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
    }

    /**
     * 转换对象字典项
     *
     * @param entity
     * @param dic    "fieldName0-KEY_NAME0-newFieldName0", "fieldName1-KEY_NAME1-newFieldName1", "fieldName2-KEY_NAME2-newFieldName2"
     */
    public static void dealDic(Object entity, String... dic) {
        if (dic != null && dic.length > 0) {
            String[] arr;
            Method get, set;
            String fieldName, enumType, newFieldName, enumName;
            for (String params : dic) {
                try {
                    arr = params.split("-");
                    fieldName = arr[0];
                    enumType = arr[1];
                    newFieldName = (arr.length >= 3 && StringUtils.isNotBlank(arr[2])) ? arr[2] : fieldName;
                    Map<String, String> dicMap = getMapByKeyName(enumType);
                    fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.replaceFirst("\\w", "");
                    get = entity.getClass().getMethod(METHOD_PREFIX_GET + fieldName);
                    enumName = dicMap.get(String.valueOf(get.invoke(entity)));
                    newFieldName = newFieldName.substring(0, 1).toUpperCase() + newFieldName.replaceFirst("\\w", "");
                    set = entity.getClass().getMethod(METHOD_PREFIX_SET + newFieldName, String.class);
                    set.invoke(entity, enumName);
                } catch (Exception ignored) {
                }
            }
        }
    }

}
