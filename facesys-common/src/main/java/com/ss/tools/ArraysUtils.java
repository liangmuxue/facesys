package com.ss.tools;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组转换工具
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public class ArraysUtils {

    private static final String COMMA = ",";

    public static List<String> asList(String str) {
        return Arrays.asList(str.split(COMMA));
    }

    public static List<Integer> asList2Int(String str) {
        List<Integer> ret = new ArrayList<>();
        for (String it : asList(str)) {
            if (StringUtils.hasText(it)) {
                ret.add(Integer.valueOf(it.trim()));
            }
        }
        return ret;
    }

    public static List<Long> asList2Long(String str) {
        List<Long> ret = new ArrayList<>();
        for (String it : asList(str)) {
            if (StringUtils.hasText(it)) {
                ret.add(Long.valueOf(it.trim()));
            }
        }
        return ret;
    }

}
