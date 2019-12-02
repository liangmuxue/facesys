package com.ss.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;


public class ArraysUtils {

    private static final String COMMA = ",";

    public static List<String> asList(String str) {
        return Arrays.asList(str.split(","));
    }


    public static List<Integer> asList2Int(String str) {
        List<Integer> ret = new ArrayList<Integer>();
        for (String it : asList(str)) {
            if (StringUtils.hasText(it)) {
                ret.add(Integer.valueOf(it.trim()));
            }
        }

        return ret;
    }


    public static List<Long> asList2Long(String str) {
        List<Long> ret = new ArrayList<Long>();
        for (String it : asList(str)) {
            if (StringUtils.hasText(it)) {
                ret.add(Long.valueOf(it.trim()));
            }
        }

        return ret;
    }

}
